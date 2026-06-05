package uk.codersden.wod.payments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/wod/payment")
public class WODPaymentsController {
	@Autowired
	private StripeService stripeService;
	
	@CrossOrigin
	@PostMapping("/account/{accountIdentifier}/stripe/connect")
	public ResponseEntity<?> connectStripe(@PathVariable("accountIdentifier") String accountIdentifier) throws StripeException {
	    String onboardingUrl =
	    		stripeService.createOnboardingLink(accountIdentifier);

	    return ResponseEntity.ok(onboardingUrl);
	}	

}
