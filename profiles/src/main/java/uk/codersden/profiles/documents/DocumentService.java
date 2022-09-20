package uk.codersden.profiles.documents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import uk.codersden.profiles.Profile;
import uk.codersden.profiles.ProfileDao;
import uk.codersden.profiles.ProfileNotFoundException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.net.MalformedURLException;

@Service
public class DocumentService {
	private final Path root = Paths.get("/Users/mvelasco/Documents/uploads");

	@Autowired
	private DocumentDao dao;
	@Autowired
	private ProfileDao profileDao;

	public List<Document> findAllDocumentsByProfile() {
		List<Document> list = dao.findAll();

		return list;

	}

	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	public Document saveDocument(DocumentPayload documentPayload, MultipartFile file, String profileIdentifier)
			throws ProfileNotFoundException {
		Document doc = new Document();
		UUID identifier = UUID.randomUUID();

		doc.setIdentifier(identifier.toString());

		Path pathFolder = Paths.get("/Users/mvelasco/Documents/uploads/" + identifier.toString());

		try {
			Files.createDirectories(pathFolder);
			Files.copy(file.getInputStream(), pathFolder.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
		if(documentPayload.getSharedWith() != null && documentPayload.getSharedWith().size() > 0) {
			documentPayload.getSharedWith().forEach((shared_profile_identifier)->{
				Optional<Profile> opSharedProfile = profileDao.findById(shared_profile_identifier);
				if(!opSharedProfile.isEmpty()) {
					doc.addSharedWith(opSharedProfile.get());
				}	
			});
		}
		doc.setImg(pathFolder.resolve(file.getOriginalFilename()).toString());
		doc.setName(file.getOriginalFilename());
		doc.setDateCreated(new Date(Calendar.getInstance().getTime().getTime()));
		Optional<Profile> op = profileDao.findById(profileIdentifier);
		if (op.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		Profile p = op.get();
		doc.setProfile(p);

		Document d = this.dao.save(doc);

		return d;

	}

	public Resource loadFile(String identifier) throws FileNotFoundException {
		Optional<Document> op = this.dao.findById(identifier);
		if (op.isEmpty()) {
			throw new FileNotFoundException();
		}
		Document doc = op.get();
		;
		try {
			// Path pathFolder = Paths.get("/Users/mvelasco/Documents/uploads/" +
			// identifier.toString());
			// Path file = pathFolder.resolve(doc.getName());
			Path file = Paths.get(doc.getImg());
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

}
