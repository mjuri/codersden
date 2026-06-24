package uk.codersden.wod.payments;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;

import com.stripe.net.RequestOptions;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;


import uk.codersden.hr.profiles.AccountDao;
import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileDao;

@Service
public class StripeService {

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;	
	
	@Autowired
	private WODGymDao wodGymDao;
	
	@Autowired
	private WODProductDao wodProductDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private ProfileDao profileDao;
	
	public String createOnboardingLink(String accountIdentifier) throws StripeException {

		
		String stripeAccountId;
		
	    Optional<WODGym> op = wodGymDao.findById(accountIdentifier);
        WODGym wodGym = new WODGym();
	    if(!op.isEmpty()) {
	    	wodGym = op.get();
	    	stripeAccountId = wodGym.getStripeAccountId();
	    }else {
	        Stripe.apiKey = stripeSecretKey; 
	
	        AccountCreateParams accountParams = AccountCreateParams.builder()
	                .setType(AccountCreateParams.Type.STANDARD)
	                .setCountry("GB")
	                .build();
	        
	
	        Account account = Account.create(accountParams); // llamada real a Stripe
	        stripeAccountId = account.getId();
	
	        wodGym.setAccountIdentifier(accountIdentifier);
	        wodGym.setStripeAccountId(account.getId());
	        
	        wodGymDao.save(wodGym);
	    }
        AccountLinkCreateParams linkParams = AccountLinkCreateParams.builder()
                .setAccount(stripeAccountId)
                .setRefreshUrl("http://localhost:3000/settings/payments")
                .setReturnUrl("http://localhost:3000/settings/payments/success")
                .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
                .build();

        AccountLink accountLink = AccountLink.create(linkParams); // llamada real a Stripe

        return accountLink.getUrl();
	}

	public String createPurchaseLink(String profileIdentifier, String accountIdentifier, String productIdentifier) throws StripeException {
		Optional<uk.codersden.hr.profiles.Account> opAccount = accountDao.findById(accountIdentifier);
		uk.codersden.hr.profiles.Account account = opAccount.orElseThrow();
		
		Optional<Profile> opProfile = profileDao.findById(profileIdentifier);
		Profile profile = opProfile.orElseThrow();
		
		Optional<WODProduct> opWodProduct = wodProductDao.findById(productIdentifier);
		WODProduct wodProduct = opWodProduct.orElseThrow();

		WODGym wodGym = wodGymDao.findAllByAccountIdentifier(accountIdentifier).get(0);

				
		return createCheckoutSession(wodGym, profile, wodProduct);
	}
	
	public String createCheckoutSession(WODGym gym, Profile athlete, WODProduct product) throws StripeException {
	    if ("ONE_TIME".equals(product.getBillingCyrcle())) {
	        return createOneTimeCheckoutSession(gym, athlete, product);
	    }

	    return createSubscriptionCheckoutSession(gym, athlete, product);
	}

	private String createSubscriptionCheckoutSession(WODGym gym, Profile athlete, WODProduct product) throws StripeException {
        Stripe.apiKey = stripeSecretKey; 
		RequestOptions requestOptions =
			    RequestOptions.builder()
			        .setStripeAccount(gym.getStripeAccountId())
			        .build();
		SessionCreateParams params = SessionCreateParams.builder()
			    .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
			    .setCustomerEmail(athlete.getEmail())
			    .addLineItem(
			        SessionCreateParams.LineItem.builder()
			            .setPrice(product.getStripePriceId())
			            .setQuantity(1L)
			            .build()
			    )
			    .setSuccessUrl("http://localhost:3000/app/wod/memberships")
			    .setCancelUrl("http://localhost:3000/app/wod/memberships")
			    .putMetadata("athlete_id", athlete.getIdentifier())
			    .putMetadata("product_id", product.getIdentifier())
			    .putMetadata("gym_id", athlete.getAccountIdentifier())
			    .build();

			Session session = Session.create(params.toMap(), requestOptions);
			return session.getUrl();
	}

	private String createOneTimeCheckoutSession(WODGym gym, Profile athlete, WODProduct product) throws StripeException {
        Stripe.apiKey = stripeSecretKey; 
		RequestOptions requestOptions =
			    RequestOptions.builder()
			        .setStripeAccount(gym.getStripeAccountId())
			        .build();
		SessionCreateParams params = SessionCreateParams.builder()
			    .setMode(SessionCreateParams.Mode.PAYMENT)
			    .setCustomerEmail(athlete.getEmail())
			    .addLineItem(
			        SessionCreateParams.LineItem.builder()
			            .setPrice(product.getStripePriceId())
			            .setQuantity(1L)
			            .build()
			    )
			    .setSuccessUrl("https://yourapp.com/success")
			    .setCancelUrl("https://yourapp.com/cancel")
			    .putMetadata("athlete_id", athlete.getIdentifier())
			    .putMetadata("product_id", product.getIdentifier())
			    .putMetadata("gym_id", athlete.getAccountIdentifier())
			    .build();

			Session session = Session.create(params.toMap(), requestOptions);
			return session.getUrl();
	}

}
