package uk.codersden.wod.templates;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.codersden.hr.NotFoundException;

@RestController
@RequestMapping("/wod/template")
public class TemplateController {

	@Autowired
	private TemplateService service;

	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveTemplatesByProfile(@PathVariable("accountIdentifier") String accountIdentifier) {
		List<Template> list = new ArrayList<>();
		try {
			list = service.findAllByAccountIdentifier(accountIdentifier);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}

		return ResponseEntity.ok(list);

	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createTemplate(@RequestBody Template obj) {
	    if (obj.getDays() != null) {
	    	obj.getDays().forEach(day -> day.setClassTemplate(obj));
	    }
		Template e = this.service.createTemplate(obj);

		return ResponseEntity.ok(e);
	}

	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateTemplate(@PathVariable("identifier") String identifier, @RequestBody Template obj) {
		Template updatedTemplate;
		try {
			updatedTemplate = this.service.updateTemplate(identifier, obj);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(updatedTemplate);
	}

	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveTemplate(@PathVariable("identifier") String identifier) throws NotFoundException {
		Template obj;
		try {
			obj = this.service.findByIdentifier(identifier);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj);
	}

	@CrossOrigin
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteTemplate(@PathVariable("identifier") String identifier) {
		Template obj = null;
		try {
			obj = this.service.deleteTemplate(identifier);

		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(obj);
	}

}

