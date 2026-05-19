package uk.codersden.hospitality;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hospitality.Room;
import uk.codersden.hospitality.RoomService;

@RestController("/hospitality/room")
public class RoomController {
	@Autowired
	private RoomService roomService;
	
	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveRoomsByProfile(@PathVariable("accountIdentifier") String accountIdentifier){
		List<Room> list = new ArrayList<>();
		try {
			list = roomService.findAllByAccountIdentifier(accountIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(list);
		
	}
	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody Room room) {
		Room e = this.roomService.createRoom(room);
		
		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateRoom(@PathVariable("identifier") String roomIdentifier, @RequestBody Room room) {
		Room updatedRoom;
		try {
			updatedRoom = this.roomService.updateRoom(roomIdentifier, room);
		} catch ( NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(updatedRoom);
	}
	
	
	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveRoom(@PathVariable("identifier") String identifier) throws NotFoundException {
		Room room;
		try {
			room = this.roomService.findByIdentifier(identifier);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(room);
	}
	
	@CrossOrigin
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteRoom(@PathVariable("identifier") String identifier){
		Room room = null;
		try {
			room = roomService.deleteRoom(identifier);
			
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(room);
	}
}
