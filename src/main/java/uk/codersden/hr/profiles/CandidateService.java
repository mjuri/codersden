package uk.codersden.hr.profiles;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import uk.codersden.hr.profiles.documents.Document;
import uk.codersden.hr.profiles.documents.DocumentStatus;

@Service
public class CandidateService {
	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private JobApplicationDao jobApplicationDao;
	
    @Autowired
    private StorageService storageService;
	
	public Candidate create(Candidate candidate) {

		if(candidate.getIdentifier() != null) {
			candidate.setIdentifier(generateIdentifier());
		}
		candidate.setType("candidate");
		Candidate newCandidate = candidateDao.save(candidate);


		return newCandidate;
	}
	public Candidate update(Candidate candidate) throws ProfileNotFoundException {
		Optional<Candidate> optional = candidateDao.findById(candidate.getIdentifier());
		if(optional.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		

		return this.candidateDao.save(candidate);
	}

	public Candidate findCandidateByIdentifier(String id) throws ProfileNotFoundException {
		Optional<Candidate> optional = candidateDao.findById(id);
		if(optional.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		Candidate c = optional.get();

		return c;
		
	}
	
	public List<Candidate> findCandidatesByAccount(String accountIdentifier) {
		
		List<Profile> profiles = this.profileDao.findAllCandidatesByAccountIdentifier(accountIdentifier);
		List<Candidate> candidates = new ArrayList<Candidate>();
		Candidate c;
		// Improve this in the future
		for (Profile p : profiles) {
			c = new Candidate();
			c.setFirstName(p.getFirstName());
			c.setLastName(p.getLastName());
			c.setEmail(p.getEmail());
			c.setIdentifier(p.getIdentifier());
			
			candidates.add(c);
		}
		
		return candidates;
	}
	
	private String generateIdentifier() {
		return UUID.randomUUID().toString();
	}
	
	public Candidate saveCandidate(Candidate candidate, MultipartFile file, String profileIdentifier) {
		
		UUID identifier = UUID.randomUUID();

		if(candidate.getIdentifier() == null) {
			candidate.setIdentifier(identifier.toString());
		}
		
		String fileName = this.storageService.save("files/cv", profileIdentifier, file);



		candidate.setCv(fileName);


		Candidate c = this.candidateDao.save(candidate);
		
		
		return c;
	}
	
	

}
