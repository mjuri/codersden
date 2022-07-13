package uk.codersden.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

	@Autowired
	private HolidayService  holidayService;
	
	@PostMapping("/request")
	public ResponseEntity<?> requestHoliday(@RequestBody Holiday holiday) {
		Holiday h = this.holidayService.requestHoliday(holiday);
		
		return ResponseEntity.ok(h);
	}
}
