package uk.codersden.accountancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	public Payment findPaymentByIdentifier(String id) throws InvoiceException {
		Optional<Payment> optional = paymentDao.findById(id);
		if(optional.isEmpty()) {
			throw new InvoiceException();
		}
		
		return optional.get();
		
	}
	public Payment create(String invoiceIdentifier, Payment p) throws InvoiceException {
		Optional<Invoice> op = this.invoiceDao.findById(invoiceIdentifier);
		if(op.isEmpty()) {
			throw new InvoiceException();
		}
		List<Invoice> invoices = new ArrayList<>();
		invoices.add(op.get());
		p.setInvoices(invoices);
		
		Payment savedPayment = this.paymentDao.save(p);

		return savedPayment;	
	}
	
	public Payment update(String paymentIdentifier, Payment payment) throws InvoiceException {
		Optional<Payment> optional = paymentDao.findById(paymentIdentifier);
		if(optional.isEmpty()) {
			throw new InvoiceException();
		}
		payment.setIdentifier(paymentIdentifier);
		Payment updatedPayment = this.paymentDao.save(payment);
		
		return updatedPayment;
	}

}
