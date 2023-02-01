package uk.codersden.accountancy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private PaymentService paymentService;
	
	
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<?> retrieveInvoice(@PathVariable("id") String id) {
		Invoice invoice = null;
		try {
			invoice = invoiceService.findInvoiceByIdentifier(id);
		}catch(InvoiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(invoice);
		
	}
	
	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveAllInvoices(@PathVariable("accountIdentifier") String accountIdentifier){
		List<Invoice> invoices = new ArrayList<>();
		try {
			invoices = invoiceService.findAllInvoicesByAccount(accountIdentifier);
		} catch (InvoiceException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(invoices);
	}
	@CrossOrigin
	@PutMapping("/{id}")
	public ResponseEntity<?> upadteInvoice(@PathVariable("id") String invoiceIdentifier, 
			@RequestBody Invoice invoice) {
		Invoice updatedInvoice;
		try {
			updatedInvoice = invoiceService.update(invoiceIdentifier, invoice);
		} catch (InvoiceException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedInvoice);
		
	}
	@CrossOrigin
	@GetMapping("/{invoiceIdentifier}/payments")
	public ResponseEntity<?> retrieveAllPaymentsByInvoice(@PathVariable("invoiceIdentifier") String invoiceIdentifier){
		Invoice invoice = null;
		List<Payment> payments = new ArrayList<>();
		try {
			invoice = invoiceService.findInvoiceByIdentifier(invoiceIdentifier);
			payments = invoice.getPayments();
			
		}catch(InvoiceException e) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(payments);
			
	}
	@CrossOrigin
	@PostMapping("/{invoiceIdentifier}/payment")
	public ResponseEntity<?> makePaymentForInvoice(@PathVariable("invoiceIdentifier") String invoiceIdentifier, 
			@RequestBody Payment payment){
		Payment newPayment;
		try {
			newPayment = paymentService.create(invoiceIdentifier, payment);
		}catch(InvoiceException e) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(newPayment);
	}
	@CrossOrigin
	@PostMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> createInvoice(@PathVariable("accountIdentifier") String accountIdentifier, 
			@RequestBody Invoice invoice) {
		Invoice newInvoice;
		try {
			invoice = invoiceService.create(accountIdentifier, invoice);
		} catch (InvoiceException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(invoice);
		
	}

}
