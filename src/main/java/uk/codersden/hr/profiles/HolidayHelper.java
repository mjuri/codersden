package uk.codersden.hr.profiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidayHelper {
	@Autowired
	private ProfileDao profileDao;

	@Autowired
	private HolidayDao holidayDao;

	public double calculateRemainingDays(Holiday holiday, Profile profile)
			throws ProfileNotFoundException, HolidayDaysException {
		double currentHolidayDays = holiday.getTotalDays();

		// Fetching the list of holidays for the profile
		List<Holiday> holidaysForProfile = findAllHolidayByProfileIdentifierAndYear(holiday.getProfileIdentifier(), holiday.getStart().toLocalDateTime().getYear());

		// Calculate the total number of days already taken
		double totalTakenDays = 0;
		for (Holiday h : holidaysForProfile) {
			totalTakenDays += h.getTotalDays();
		}

		// Adding the current holiday days to the total taken days
		totalTakenDays += currentHolidayDays;

		// Fetching the entitled absence days
		int entitledDays = profile.getContract().getHolidayEntitlement();

		// Calculate the remaining days
		double remainingDays = entitledDays - totalTakenDays;

		// If remaining days are negative, throw an exception
		if (remainingDays < 0) {
			throw new HolidayDaysException("Remaining days cannot be negative.");
		}

		return remainingDays;
	}

	
	public static double calculateHolidaysTaken(Holiday holiday) {
		LocalDate startDate = holiday.getStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate endDate = holiday.getEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		double holidaysTaken = calculateHolidaysTaken(startDate, endDate);
		
		if(holiday.isHalfDayStart()) {
			holidaysTaken = holidaysTaken - 0.5;
		}
		
		if(holiday.isHalfDayEnd() ){
			holidaysTaken = holidaysTaken - 0.5;
		}
		
		return holidaysTaken;
		
	}
	
	public static double calculateHolidaysTaken(LocalDate startDate, LocalDate endDate) {
		double holidaysTaken = 0;

		// Loop through each day in the range
		LocalDate currentDate = startDate;
		List<LocalDate> bankHolidays = BankHolidayAPI.fetchBankHolidays();

		while (!currentDate.isAfter(endDate)) {
			// Check if it's a weekday (Monday to Friday) and not a bank holiday
			if (isWeekday(currentDate) && !bankHolidays.contains(currentDate)) {
				holidaysTaken++;
			}
			currentDate = currentDate.plusDays(1);
		}

		return holidaysTaken;
	}

	private static boolean isWeekday(LocalDate date) {
		DayOfWeek day = date.getDayOfWeek();
		return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
	}

	public List<Holiday> findAllHolidayByProfileIdentifier(String id) throws ProfileNotFoundException {
		Optional<Profile> optional = profileDao.findById(id);
		if (optional.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		Profile p = optional.get();

		List<Holiday> list = holidayDao.findAllByProfileIdentifier(id);

		// Add Holidays of his/her team.
		List<Profile> children = p.getChildren();

		if (children.size() > 0) {
			for (Profile child : children) {
				list.addAll(this.findAllHolidayByProfileIdentifier(child.getIdentifier()));
			}

		}

		return list;
	}
	public List<Holiday> findAllHolidayByProfileIdentifierAndYear(String identifier, int year) throws ProfileNotFoundException {
		Optional<Profile> optional = profileDao.findById(identifier);
		if (optional.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		Profile p = optional.get();

		List<Holiday> list = holidayDao.findAllByProfileIdentifierAndYear(identifier, year);

		// Add Holidays of his/her team.
		List<Profile> children = p.getChildren();

		if (children.size() > 0) {
			for (Profile child : children) {
				list.addAll(this.findAllHolidayByProfileIdentifier(child.getIdentifier()));
			}

		}

		return list;
	}	
}
