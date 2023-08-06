package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoDao extends JpaRepository<ToDo, String>{
	public List<ToDo> findAllByProfile(Profile profile);
}
