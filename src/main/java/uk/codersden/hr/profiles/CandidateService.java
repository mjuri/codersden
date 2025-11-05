package uk.codersden.hr.profiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

public class CandidateService {
	@Autowired
	private CandidateDao candidateDao;
	
	
	@Autowired
	private JobApplicationDao jobApplicationDao;
	
	public Candidate create(Candidate candidate) {

		if(candidate.getIdentifier() != null) {
			candidate.setIdentifier(generateIdentifier());
		}
		
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
		
		List<Candidate> candidates = this.candidateDao.findAllByAccountIdentifier(accountIdentifier);
		
		
		return candidates;
	}
	
	private String generateIdentifier() {
		return UUID.randomUUID().toString();
	}
	
	

}
