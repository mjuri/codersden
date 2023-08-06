package uk.codersden.hr.profiles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

	@Autowired
	private HolidayService  holidayService;
	
	@CrossOrigin
	@PostMapping("/request")
	public ResponseEntity<?> requestHoliday(@RequestBody Holiday holiday) {
		Holiday h = this.holidayService.requestHoliday(holiday);
		
		return ResponseEntity.ok(h);
	}
	@CrossOrigin
	@PostMapping("/approve/{holidayIdentifier}")
	public ResponseEntity<?> approveHoliday(@PathVariable("holidayIdentifier") String identifier) throws HolidayNotFoundException{
		
		Holiday holiday = this.holidayService.approveHolidayRequest(identifier);
		return ResponseEntity.ok(holiday);
		
	}
	
	@CrossOrigin
	@PostMapping("/reject/{holidayIdentifier}")
	public ResponseEntity<?> rejectHoliday(@PathVariable("holidayIdentifier") String identifier) throws HolidayNotFoundException{
		
		Holiday holiday = this.holidayService.rejectHolidayRequest(identifier);
		return ResponseEntity.ok(holiday);
		
	}
	@CrossOrigin
	@GetMapping("/profile/{profileIdentifier}")
	public ResponseEntity<?> retrieveHolidaysByProfile(@PathVariable("profileIdentifier") String profileIdentifier){
		List<Holiday> list = new ArrayList<>();
		try {
			list = holidayService.findAllHolidayByProfileIdentifier(profileIdentifier);
		}catch(Exception e) {
			ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(list);
		
	}
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<?> retrieveHolidaysByUser(@PathVariable("id") String identifier){
		List<Holiday> list = new ArrayList<>();
		try {
			list = holidayService.findAllHolidayByProfileIdentifier(identifier);
		} catch (ProfileNotFoundException e) {
			ResponseEntity.notFound();
			e.printStackTrace();
		}
		return ResponseEntity.ok(list);
		
	}
	@CrossOrigin
	@GetMapping("/awaiting-approval/{id}")
	public ResponseEntity<?> retrieveAwaitingApprovalHolidays(@PathVariable("id") String id){
		List<Holiday> list = new ArrayList<>();
		list = holidayService.findAllRequestedHolidays(id);
		return ResponseEntity.ok(list);
	}
}
