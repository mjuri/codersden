package uk.codersden.hr.profiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
	@Autowired
	private ContractDao contractDao;
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private HolidayHelper holidayHelper;
	
	public Contract createContract(String profileIdentifier, Contract contract) throws ProfileNotFoundException {
		Optional<Profile> op = this.profileDao.findById(profileIdentifier);
		if(op.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		Profile profiile = op.get();
		contract.setProfile(profiile);
		contract.setHolidayTotalThisYear(contract.getHolidayBroughtForward() + contract.getHolidayEntitlement());
		//contract.setNetPay(contract.getGrossSalary() - contract.getPayeDeduction());
		contract.setNetPay(contract.getGrossSalary());
		Contract newContract = this.contractDao.save(contract);
		
		return newContract;
	}



	public Contract findContractByIdentifier(String contractIdentifier) 
			throws ContractNotFoundException 
	{	
		Optional<Contract> op = this.contractDao.findById(contractIdentifier);
		if(op.isEmpty()) {
			throw new ContractNotFoundException();
		}
		Contract contract = op.get();
		
		Double holidayBroughtForward = contract.getHolidayEntitlement() - holidayHelper.calculateHolidaysTakenLastYear(contract);
		holidayBroughtForward = holidayBroughtForward > contract.getBroughtForwardScheme() ? contract.getBroughtForwardScheme() : holidayBroughtForward;
		
		contract.setHolidayBroughtForward(holidayBroughtForward);
		
		return contract;
	}



	public Contract updateContract(String profileIdentifier, Contract contract) 
			throws ContractNotFoundException, ProfileNotFoundException 
	{
		Optional<Profile> opProfile = this.profileDao.findById(profileIdentifier);
		if(opProfile.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		Optional<Contract> op = this.contractDao.findById(contract.getIdentifier());
		if(op.isEmpty()) {
			throw new ContractNotFoundException();
		}
		Profile profile = opProfile.get();
		contract.setProfile(profile);
		if(null == contract.getHoursPerWeek()) {
			contract.setHolidayTotalThisYear(contract.getHolidayBroughtForward() + contract.getHolidayEntitlement());
		}

		Contract updatedContract = this.contractDao.save(contract);
		return updatedContract;
	}

}
