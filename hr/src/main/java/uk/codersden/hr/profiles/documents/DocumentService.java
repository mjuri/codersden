package uk.codersden.hr.profiles.documents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.type.IdentifierType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileDao;
import uk.codersden.hr.profiles.ProfileNotFoundException;
import uk.codersden.hr.profiles.StaticResourceService;

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

    @Autowired
    private StaticResourceService resourceService;
	
	public List<Document> findAllDocumentsByProfile(String profileId) {
		Optional<Profile> op = profileDao.findById(profileId);
		List<Document> list = dao.findAllByProfile(op.get());

		return list;

	}

	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}
	public Document archiveDocument(String identifier) {
		Optional<Document> op = dao.findById(identifier);
		Document doc = op.get();
		doc.setStatus(DocumentStatus.ARCHIVED);
		Document updatedDocument = dao.save(doc);
		
		return updatedDocument;
		
	}
	public Document saveDocument(DocumentPayload documentPayload, MultipartFile file, String profileIdentifier)
			throws ProfileNotFoundException {
		Document doc = new Document();
		UUID identifier = UUID.randomUUID();
		String status = DocumentStatus.ACTIVE;
		doc.setStatus(status);
		
		doc.setIdentifier(identifier.toString());
		Path pathFolder = Paths.get(resourceService.getStaticDirectoryPath("files") + '/' + identifier.toString());
		

		try {
			Files.createDirectories(pathFolder);
			Files.copy(file.getInputStream(), pathFolder.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
		if(documentPayload.getSharedWith() != null && documentPayload.getSharedWith().size() > 0) {
			documentPayload.getSharedWith().forEach((shared_profile)->{
				Optional<Profile> opSharedProfile = profileDao.findById(shared_profile.get("value"));
				if(!opSharedProfile.isEmpty()) {
					doc.addSharedWith(opSharedProfile.get());
				}	
			});
		}
		String fileName = pathFolder.resolve(file.getOriginalFilename()).toString();
		fileName = fileName.replaceAll("static", "");
		doc.setImg(fileName);
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

	public List<Document> findAllActiveDocumentsByProfile(String profileIdentifier) {
		List<Document> onlyActiveDocuments = new ArrayList<>();
		List<Document> list = this.findAllDocumentsByProfile(profileIdentifier);
		List<Document> sharedWithList = dao.findAllSharedWithUser(profileIdentifier);
		
        Set<Document> mergedSet = new HashSet<>(list);
        mergedSet.addAll(sharedWithList);
		
        List<Document> mergedList = new ArrayList<>(mergedSet);
        
		for (Document document : mergedList) {
			if(document.getStatus().equals(DocumentStatus.ACTIVE.toString())) {
				onlyActiveDocuments.add(document);
			}
		}
		return onlyActiveDocuments;
	}

}
