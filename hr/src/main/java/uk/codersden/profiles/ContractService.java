package uk.codersden.profiles;

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
	
	public Contract createContract(String profileIdentifier, Contract contract) throws ProfileNotFoundException {
		Contract newContract = null;
		Optional<Profile> op = this.profileDao.findById(profileIdentifier);
		if(op.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		Profile profiile = op.get();
		contract.setProfile(profiile);
		newContract = this.contractDao.save(contract);
		
		return contract;
	}



	public Contract findContractByIdentifier(String contractIdentifier) throws ContractNotFoundException {
		Optional<Contract> op = this.contractDao.findById(contractIdentifier);
		if(op.isEmpty()) {
			throw new ContractNotFoundException();
		}
		return op.get();
	}

	//This method has no any sense
	public List<Contract> findAllContracts() {
		List<Contract> contracts = new ArrayList<>();
		contracts = this.contractDao.findAll();
		return contracts;
	}



	public Contract updateContract(String profileIdentifier, Contract contract) throws ContractNotFoundException, ProfileNotFoundException {
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
		Contract updatedContract = this.contractDao.save(contract);
		return updatedContract;
	}

}
