package uk.codersden.wod.payments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;

@Service
public class StripeService {

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;	
	
	public String createOnboardingLink(String accountIdentifier) throws StripeException {
        Stripe.apiKey = stripeSecretKey; // aquí conectas con Stripe

        AccountCreateParams accountParams = AccountCreateParams.builder()
                .setType(AccountCreateParams.Type.STANDARD)
                .setCountry("GB")
                .build();
        

        Account account = Account.create(accountParams); // llamada real a Stripe
        
        AccountLinkCreateParams linkParams = AccountLinkCreateParams.builder()
                .setAccount(account.getId())
                .setRefreshUrl("http://localhost:3000/settings/payments")
                .setReturnUrl("http://localhost:3000/settings/payments/success")
                .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
                .build();

        AccountLink accountLink = AccountLink.create(linkParams); // llamada real a Stripe

        return accountLink.getUrl();
	}

}
