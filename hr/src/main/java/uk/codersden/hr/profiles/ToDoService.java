package uk.codersden.hr.profiles;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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

		List<ToDo> list  = this.toDoDao.findAllByProfileAndStatus(profileIdentifier, getTwentyDaysAgo());
		
		return list;
	}
	public ToDo create(String profileIdentifier, ToDo item) throws ProfileNotFoundException {
		Optional<Profile> opp = this.profileDao.findById(profileIdentifier);
		if(opp.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		Profile p = opp.get();
		item.setProfile(p);
		java.sql.Date dateCreated = new Date(System.currentTimeMillis());
		item.setDateCreated(dateCreated);
		ToDo toDo = this.toDoDao.save(item);
		
		return toDo;
	}
	public ToDo completeToDoItem(String identifier) throws TaskNotFoundException {
		Optional<ToDo> op = this.toDoDao.findById(identifier);
		if(op.isEmpty()) {
			throw new TaskNotFoundException();
		}
		ToDo todo = op.get();
		java.sql.Date modDate = new Date(System.currentTimeMillis());
		todo.setModDate(modDate);
		todo.setDone(true);
		todo = this.toDoDao.save(todo);
		
		return todo;
	}
	
    private static Date getTwentyDaysAgo() {
        LocalDate twentyDaysAgo = LocalDate.now().minusDays(20);
        return Date.valueOf(twentyDaysAgo);
    }
	public ToDo removeToDoItem(String identifier) throws TaskNotFoundException {
		Optional<ToDo> op = this.toDoDao.findById(identifier);
		if(op.isEmpty()) {
			throw new TaskNotFoundException();
		}
	}
}
