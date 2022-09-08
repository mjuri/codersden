package uk.codersden.profiles.documents;



import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;

/**
 * Website used as reference: https://www.bezkoder.com/spring-boot-file-upload/
 * @author mvelasco
 *
 */
@RestController
@RequestMapping("/document")
public class DocumentController {
	@Autowired
	private DocumentService service;
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<?> retrieveDocumentsByProfile() {
		List<Document> list = service.findAllDocumentsByProfile();
		
		return ResponseEntity.ok(list);
		
	}
	
	@CrossOrigin
	@PostMapping("/upload/profile/{profileIdentifier}")
	public ResponseEntity<?> createDocument(@RequestParam("documentPayload") String documentPayload,
			@RequestParam("file") MultipartFile file, 
			@PathVariable("profileIdentifier") String profileIdentifier) {
		
		String message = "";
		try {
		  ObjectMapper mapper = new ObjectMapper();
		  DocumentPayload payload = mapper.readValue(documentPayload, DocumentPayload.class);
		  Document docUploaded = service.saveDocument(payload, file, profileIdentifier);
	      message = "Uploaded the file successfully: " + file.getOriginalFilename() + "identifier: " + docUploaded.getIdentifier();

		}catch(Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			e.printStackTrace();
		     return ResponseEntity.internalServerError().build();
		}
	      return ResponseEntity.ok(message);
	}
	
	@CrossOrigin
	@PostMapping("/profile/{profileIdentifier}")
	public ResponseEntity<?> createDocument(@PathVariable("profileIdentifier") String profileIdentifier, @RequestBody Document document) {
		Document newDocument = null;
		/*try {
			newDocument = service.saveDocument(document, profileIdentifier);
		} catch (ProfileNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
			
		}*/
		return ResponseEntity.ok(newDocument);
	}
	
	  //@GetMapping("/files/{filename:.+}")
	  @GetMapping("/download/{identifier}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String identifier) throws FileNotFoundException {
	    Resource file = this.service.loadFile(identifier);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
}
