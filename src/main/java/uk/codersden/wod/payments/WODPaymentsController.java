package uk.codersden.wod.payments;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileNotFoundException;
import uk.codersden.hr.profiles.ProfileService;

import java.util.Map;

@RestController
@RequestMapping("/wod/payment")
public class WODPaymentsController {
	@Autowired
	private StripeService stripeService;

	@Autowired
	private ProfileService profileService;

	@Autowired
	private WODProductService productService;

	@CrossOrigin
	@PostMapping("/account/{accountIdentifier}/stripe/connect")
	public ResponseEntity<?> connectStripe(@PathVariable("accountIdentifier") String accountIdentifier)
			throws StripeException {
		String onboardingUrl = stripeService.createOnboardingLink(accountIdentifier);

		return ResponseEntity.ok(Map.of("url", onboardingUrl));
	}

	@CrossOrigin
	@PostMapping("/purchase")
	public ResponseEntity<?> purchase(@RequestBody Map<String, String> obj)
			throws StripeException, ProfileNotFoundException, NotFoundException {
		String paymentUrl = stripeService.createPurchaseLink(obj.get("profileIdentifier"), obj.get("accountIdentifier"),
				obj.get("productIdentifier"));

		Profile profile = profileService.findProfileByIdentifier(obj.get("profileIdentifier"));
		WODProduct product = productService.findByIdentifier(obj.get("productIdentifier"));

		profile.setMembershipPlan(product);
		profileService.update(profile);
		
		return ResponseEntity.ok(Map.of("url", paymentUrl));
	}

}
