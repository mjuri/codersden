package uk.codersden.profiles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {
	@Autowired
	private ToDoDao toDoDao;
	@Autowired
	private ProfileDao profileDao;
	public List<ToDo> findToDoListByUser(String profileIdentifier) throws ProfileNotFoundException {
		Optional<Profile> opp = this.profileDao.findById(profileIdentifier);
		if(opp.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		Profile p = opp.get();
		List<ToDo> list = this.toDoDao.findAllByProfile(p);
		
		return list;
	}
	public ToDo create(String profileIdentifier, ToDo item) throws ProfileNotFoundException {
		Optional<Profile> opp = this.profileDao.findById(profileIdentifier);
		if(opp.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		Profile p = opp.get();
		item.setProfile(p);
		ToDo toDo = this.toDoDao.save(item);
		
		return toDo;
	}
	public ToDo completeToDoItem(String identifier) throws TaskNotFoundException {
		Optional<ToDo> op = this.toDoDao.findById(identifier);
		if(op.isEmpty()) {
			throw new TaskNotFoundException();
		}
		ToDo todo = op.get();
		todo.setDone(true);
		todo = this.toDoDao.save(todo);
		
		return todo;
	}
	
}
