package uk.codersden.hr.annoucements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.profiles.AccountDao;
import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileDao;
import uk.codersden.hr.profiles.ProfileNotFoundException;

@Service
public class AnnoucementService {

	@Autowired
	private AnnoucementDao dao;

	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private AccountDao accountDao;

	public Annoucement createAnnoucement(Annoucement annoucement) {
		// Check audience
		Annoucement a = dao.save(annoucement);
		// Send email

		return a;
	}

	public Annoucement updateAnnoucement(String identifier, Annoucement annoucement) throws AnnoucementNotFoundException{
		Optional<Annoucement> op = dao.findById(identifier);
		// Check audience
		if(op.isEmpty()) {
			throw new AnnoucementNotFoundException();
		}
		annoucement.setIdentifier(identifier);
		
		return dao.save(annoucement);
	}

	public Annoucement findByAnnoucementIdentifier(String identifier) throws AnnoucementNotFoundException{
		Optional<Annoucement> op = dao.findById(identifier);
		
		if(op.isEmpty()) {
			throw new AnnoucementNotFoundException();
		}
		return op.get();
	}

	public List<Annoucement> findAllAnnoucementsByProfileIdentifier(String profileIdentifier) throws ProfileNotFoundException{
		Optional<Profile> op = profileDao.findById(profileIdentifier);
		if(op.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		List<Annoucement> list = dao.findAllByProfileIdentifier(profileIdentifier);
		return list;
	}
}
