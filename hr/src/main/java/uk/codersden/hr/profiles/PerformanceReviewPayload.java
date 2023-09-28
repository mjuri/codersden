package uk.codersden.hr.profiles;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PerformanceReviewPayload {
	
	private String comments;
	private Map<String,String> employee;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date deadline;
	
	private List<Map<String,String>> goals;
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Map<String, String> getEmployee() {
		return employee;
	}
	public void setEmployee(Map<String, String> employee) {
		this.employee = employee;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public List<Map<String, String>> getGoals() {
		return goals;
	}
	public void setGoals(List<Map<String, String>> goals) {
		this.goals = goals;
	}
	
	
}
