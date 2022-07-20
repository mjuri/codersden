package uk.codersden.profiles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {
	@Autowired
	private HolidayDao holidayDao;
	
	public Holiday requestHoliday(Holiday holiday) {
		// Save holiday on the DB
		// Send email
		Holiday h = this.holidayDao.save(holiday);
		return h;
	}

	public List<Holiday> findAllHolidayByProfileIdentifier(String id) {
		return this.holidayDao.findAllByProfileIdentifier(id);
	}

}
