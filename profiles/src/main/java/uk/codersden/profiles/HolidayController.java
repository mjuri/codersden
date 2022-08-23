package uk.codersden.profiles;

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
	@GetMapping("/{id}")
	public ResponseEntity<?> retrieveHolidaysByUser(@PathVariable("id") String id){
		List<Holiday> list = new ArrayList<>();
		list = holidayService.findAllHolidayByProfileIdentifier(id);
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
