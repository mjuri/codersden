package uk.codersden.accountancy;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceDao invoiceDao;
	

	
	public Invoice findInvoiceByIdentifier(String id) throws InvoiceException {
		Optional<Invoice> optional = invoiceDao.findById(id);
		if(optional.isEmpty()) {
			throw new InvoiceException();
		}
		
		return optional.get();
		
	}
	public List<Invoice> findAllInvoicesByAccount(String accountIdentifier) throws InvoiceException {
		List<Invoice> invoices = invoiceDao.findAllByAccountIdentifier(accountIdentifier);
		
		return invoices;
	}

	public Invoice create(String accountIdentifier, Invoice invoice) throws InvoiceException {

		invoice.setAccountIdentifier(accountIdentifier);
		Invoice savedInvoice = this.invoiceDao.save(invoice);

		return savedInvoice;
		
	}

	public Invoice update(String invoiceIdentifier, Invoice invoice) throws InvoiceException {
		Optional<Invoice> optional = invoiceDao.findById(invoiceIdentifier);
		
		if(optional.isEmpty()) {
			throw new InvoiceException();
			
		}
		invoice.setIdentifier(invoiceIdentifier);
		invoice.setAccountIdentifier(optional.get().getAccountIdentifier());
		Invoice updatedInvoice = this.invoiceDao.save(invoice);
		
		return updatedInvoice;
	}
}