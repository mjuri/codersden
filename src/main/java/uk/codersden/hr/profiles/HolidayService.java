package uk.codersden.hr.profiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {
	@Autowired
	private HolidayDao holidayDao;
	private static Role ROLE_HR_ADMIN = new Role("HR-ADMIN");
	@Autowired
	private ProfileDao profileDao;
	
	public Holiday requestHoliday(Holiday holiday) {
		// Save holiday on the DB
		// Send email
		if(holiday.getAuthorizedBy() == null || "".equals(holiday.getAuthorizedBy()) ) {
			holiday.setStatus(HolidayStatus.APPROVED.toString());
		}
		Holiday h = this.holidayDao.save(holiday);
		return h;
	}

	public List<Holiday> findAllHolidayByProfileIdentifier(String id) throws ProfileNotFoundException {
		Optional<Profile> optional = profileDao.findById(id);
		if(optional.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		Profile p = optional.get();
		
		List<Holiday> list = this.holidayDao.findAllByProfileIdentifier(id);
		
		if(p.getRoles().contains(ROLE_HR_ADMIN)) {
			list.addAll(this.getHolidaysAsAHRManager(p));
		}
		
		
		return list;
	}

	
	private Collection<? extends Holiday> getHolidaysAsAHRManager(Profile p) throws ProfileNotFoundException {
		List<Holiday> list = new ArrayList<>();
		List<Profile> children = p.getChildren();
		
		for (Profile child : children) {
			list.addAll(this.findAllHolidayByProfileIdentifier(child.getIdentifier()));
		}
		
		return list;
		
	}

	public List<Holiday> findAllRequestedHolidays(String managerIdentifer){
		return this.holidayDao.findAllByAuthorizedByAndStatus(managerIdentifer, HolidayStatus.REQUESTED.toString());
	}

	public Holiday findByHolidayIdentifier(String identifier) throws HolidayNotFoundException {
		Optional<Holiday> op = holidayDao.findById(identifier);
		if(op.isEmpty()) {
			throw new HolidayNotFoundException();
		}
		Holiday h = op.get();
		return h;
	}

	public Holiday approveHolidayRequest(String identifier) throws HolidayNotFoundException {
		Holiday h = this.findByHolidayIdentifier(identifier);
		
		h.setStatus(HolidayStatus.APPROVED.toString());
		return this.holidayDao.save(h);
		
	}

	public Holiday rejectHolidayRequest(String identifier) throws HolidayNotFoundException {
		Holiday h = this.findByHolidayIdentifier(identifier);
		
		h.setStatus(HolidayStatus.REJECTED.toString());
		return this.holidayDao.save(h);
	}
}
