package uk.codersden.hr.profiles;

import java.sql.Date;
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
		if(holiday.getProfileIdentifier().equals(holiday.getAuthorizedBy())) {
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
		
		// Add Holidays of his/her team.
		List<Profile> children = p.getChildren();
		
		if(children.size() > 0) {
			for (Profile child : children) {
				list.addAll(this.findAllHolidayByProfileIdentifier(child.getIdentifier()));
			}

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
	
	private long calculateRemainingDays(Holiday holiday) throws ProfileNotFoundException, HolidayDaysException {
	    int currentHolidayDays = holiday.calculateDays();
	    Profile profile = profileDao.getById(holiday.getProfileIdentifier());
	    // Fetching the list of holidays for the profile
	    List<Holiday> holidaysForProfile = this.findAllHolidayByProfileIdentifier(holiday.getProfileIdentifier());

	    // Calculate the total number of days already taken
	    int totalTakenDays = 0;
	    for (Holiday h : holidaysForProfile) {
	        totalTakenDays += h.calculateDays();
	    }

	    // Adding the current holiday days to the total taken days
	    totalTakenDays += currentHolidayDays;

	    // Fetching the entitled absence days
	    int entitledDays = profile.getContract().getHolidayEntitlement();

	    // Calculate the remaining days
	    int remainingDays = entitledDays - totalTakenDays;

	    // If remaining days are negative, throw an exception
	    if (remainingDays < 0) {
	        throw new HolidayDaysException("Remaining days cannot be negative.");
	    }

	    return remainingDays;
	}


	public Holiday saveHoliday(Holiday holiday) throws HolidayNotFoundException, ProfileNotFoundException, HolidayDaysException {
		calculateRemainingDays(holiday);
		if(holiday.getDateCreated() == null) {
			java.sql.Date d = new Date(System.currentTimeMillis());
			holiday.setDateCreated(d);
			
			if(holiday.getAuthorizedBy() == null || "".equals(holiday.getAuthorizedBy()) ) {
				holiday.setStatus(HolidayStatus.APPROVED.toString());
			}
			if(holiday.getProfileIdentifier().equals(holiday.getAuthorizedBy())) {
				holiday.setStatus(HolidayStatus.APPROVED.toString());
			}
		}
		Holiday h = null;
		if(holiday.getIdentifier() != null) {
			h = findByHolidayIdentifier(holiday.getIdentifier());
		}
		// Update h //TODO
		h = holidayDao.save(holiday);
		return h;
	}

	public Holiday archiveHoliday(String identifier) {

		Optional<Holiday> op = holidayDao.findById(identifier);
		Holiday holiday = op.get();
		holiday.setStatus("ARCHIVED");
		return holidayDao.save(holiday);

	}
}
