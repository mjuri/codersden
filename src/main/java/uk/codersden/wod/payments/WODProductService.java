package uk.codersden.wod.payments;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.net.RequestOptions;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.profiles.Account;
import uk.codersden.hr.profiles.AccountDao;
import uk.codersden.hr.profiles.AccountNotFoundException;

@Service
public class WODProductService {
	@Autowired
	private WODProductDao dao;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private WODGymDao gymDao;

	@Value("${stripe.secret-key}")
	private String stripeSecretKey;

	public List<WODProduct> findAllByAccountIdentifier(String accountIdentifier) throws AccountNotFoundException {
		Optional<Account> op = accountDao.findById(accountIdentifier);
		if (op.isEmpty()) {
			throw new AccountNotFoundException(accountIdentifier + " not found");
		}

		List<WODProduct> list = dao.findAllByAccountIdentifierAndActiveTrue(accountIdentifier);

		return list;
	}

	private Product createProductOnStripe(WODProduct wodProduct, WODGym gym, RequestOptions requestOptions)
			throws StripeException {

		Product product = Product.create(ProductCreateParams.builder().setName(wodProduct.getDescription()).build(),
				requestOptions);
		return product;
	}

	private PriceCreateParams.Recurring.Interval retrieveBillingCyrcle(String billingCyrcle) {
		return PriceCreateParams.Recurring.Interval.valueOf(billingCyrcle.trim().toUpperCase());
	}

	private Price createPriceOnStripe(WODProduct wodProduct, Product stripeProduct, RequestOptions requestOptions)
			throws StripeException {

		PriceCreateParams.Builder builder = PriceCreateParams.builder().setCurrency("gbp")
				.setUnitAmount(wodProduct.getPrice().multiply(BigDecimal.valueOf(100)).longValue())
				.setProduct(stripeProduct.getId());

		if (!"ONE_TIME".equalsIgnoreCase(wodProduct.getBillingCyrcle())) {
			builder.setRecurring(PriceCreateParams.Recurring.builder()
					.setInterval(retrieveBillingCyrcle(wodProduct.getBillingCyrcle())).build());
		}

		return Price.create(builder.build(), requestOptions);
	}

	public WODProduct createWODProduct(WODProduct obj) throws StripeException {
		List<WODGym> list = gymDao.findAllByAccountIdentifier(obj.getAccountIdentifier());
		WODGym wodGym;
		Stripe.apiKey = stripeSecretKey;
		if (!list.isEmpty()) {
			wodGym = list.get(0);
			RequestOptions requestOptions = RequestOptions.builder().setStripeAccount(wodGym.getStripeAccountId())
					.build();

			Product product = this.createProductOnStripe(obj, wodGym, requestOptions);
			Price price = this.createPriceOnStripe(obj, product, requestOptions);

			obj.setWodGymIdentifier(wodGym.getIdentifier());
			obj.setStripeProductId(product.getId());
			obj.setStripePriceId(price.getId());
			obj.setActive(true);
		}

		return dao.save(obj);
	}

	public WODProduct updateWODProduct(String identifier, WODProduct obj) throws NotFoundException {
		Optional<WODProduct> op = dao.findById(identifier);
		if (op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		obj.setIdentifier(identifier);

		return dao.save(obj);
	}

	public WODProduct findByIdentifier(String identifier) throws NotFoundException {
		Optional<WODProduct> op = dao.findById(identifier);
		if (op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		return op.get();
	}

	public WODProduct deleteWODProduct(String identifier) throws NotFoundException, StripeException {
		Stripe.apiKey = stripeSecretKey;
		Optional<WODProduct> op = dao.findById(identifier);

		if (op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		WODProduct obj = op.get();
		Optional<WODGym> opWodGym = gymDao.findById(obj.getWodGymIdentifier());

		WODGym wodGym = opWodGym.orElseThrow();

		if (obj.getStripeProductId() != null && wodGym.getStripeAccountId() != null) {
			Product product = Product.retrieve(obj.getStripeProductId(),
					RequestOptions.builder().setStripeAccount(wodGym.getStripeAccountId()).build());

			Map<String, Object> params = new HashMap<>();
			params.put("active", false);

			product.update(params, RequestOptions.builder().setStripeAccount(wodGym.getStripeAccountId()).build());
		}
		obj.setActive(false);
		dao.save(obj);

		return obj;
	}

}
