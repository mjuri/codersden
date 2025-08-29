package uk.codersden.hr.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.profiles.Contract;
import uk.codersden.hr.profiles.ContractDao;
import uk.codersden.hr.profiles.ContractNotFoundException;
import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileNotFoundException;

import java.util.Optional;
@Service
public class PayrollService {
	
	@Autowired
	private PayrollDao dao;
	
	@Autowired
	private ContractDao contractDao;
	
	@Autowired
	private EmployeePaymentDao employeePaymentDao;

	public Payroll findPayrollByAccount(String accountIdentifier) throws NotFoundException {
		/*Optional<Payroll> op = dao.findByAccountIdentiferAndFiscalYear(accountIdentifier, "2025");
		if(op.isEmpty()) {
			throw new NotFoundException(accountIdentifier);
		}
		return op.get();*/
		return null;
	}


	public EmployeePayment createEmployeePayment(EmployeePayment payment) {
		return employeePaymentDao.save(payment);
	}
	
	public EmployeePayment updateEmployeePayment(String identifier, EmployeePayment payment) 
			throws ContractNotFoundException, ProfileNotFoundException, NotFoundException 
	{

		Optional<EmployeePayment> op = this.employeePaymentDao.findById(identifier);
		if(op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		Optional<Contract> opContract = this.contractDao.findById(payment.getContractIdentifier());
		if(op.isEmpty()) {
			throw new ContractNotFoundException();
		}
		
		Contract contract = opContract.get();
		
		payment.setContract(contract);


		EmployeePayment updatedPayment = this.employeePaymentDao.save(payment);
		return updatedPayment;
	}

}
