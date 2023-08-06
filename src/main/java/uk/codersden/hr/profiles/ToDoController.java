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
@RequestMapping("/todo")
public class ToDoController {
	
	@Autowired
	private ToDoService toDoService;

	@CrossOrigin
	@GetMapping("/profile/{id}")
	public ResponseEntity<?> retrieveToDoList(@PathVariable("id") String id) {
		List<ToDo> list = new ArrayList<>();
		try {
			list = toDoService.findToDoListByUser(id);
		} catch (ProfileNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();

		}
		return ResponseEntity.ok(list);
		
	}
	@CrossOrigin
	@PostMapping("/{id}")
	public ResponseEntity<?> createToDoItem(@PathVariable("id") String profileIdentifier, 
			@RequestBody ToDo item) {
		ToDo toDo;
		try {
			toDo = toDoService.create(profileIdentifier, item);
		} catch (ProfileNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(toDo);
		
	}
	@CrossOrigin
	@PostMapping("/complete/{identifier}")
	public ResponseEntity<?> completeToDoItem(@PathVariable("identifier") String identifier) {
		ToDo toDo;
		try {
			toDo = toDoService.completeToDoItem(identifier);
			return ResponseEntity.ok(toDo);
		} catch (TaskNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();

		}

		
	}
	
	
}
