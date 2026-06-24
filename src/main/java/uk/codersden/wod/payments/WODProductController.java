package uk.codersden.wod.payments;

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

import com.stripe.exception.StripeException;

import uk.codersden.hr.NotFoundException;

@RestController
@RequestMapping("/wod/product")
public class WODProductController {

	@Autowired
	private WODProductService service;

	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveWODProductsByProfile(@PathVariable("accountIdentifier") String accountIdentifier) {
		List<WODProduct> list = new ArrayList<>();
		try {
			list = service.findAllByAccountIdentifier(accountIdentifier);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}

		return ResponseEntity.ok(list);

	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createWODProduct(@RequestBody WODProduct obj) throws StripeException {
		WODProduct e = this.service.createWODProduct(obj);

		return ResponseEntity.ok(e);
	}

	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateWODProduct(@PathVariable("identifier") String identifier, @RequestBody WODProduct obj) {
		WODProduct updatedWODProduct;
		try {
			updatedWODProduct = this.service.updateWODProduct(identifier, obj);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(updatedWODProduct);
	}

	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveWODProduct(@PathVariable("identifier") String identifier) throws NotFoundException {
		WODProduct obj;
		try {
			obj = this.service.findByIdentifier(identifier);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj);
	}

	@CrossOrigin
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteWODProduct(@PathVariable("identifier") String identifier) throws StripeException {
		WODProduct obj = null;
		try {
			obj = this.service.deleteWODProduct(identifier);

		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(obj);
	}

}
