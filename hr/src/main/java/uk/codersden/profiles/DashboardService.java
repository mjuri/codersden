package uk.codersden.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
	@Autowired
	private ToDoDao toDoDao;
}
