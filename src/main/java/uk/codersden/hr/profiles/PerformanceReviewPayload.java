package uk.codersden.hr.profiles;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PerformanceReviewPayload {
	private String identifier;
	
	private String comments;
	private Map<String,String> employee;
	private Map<String,String> reviewer;
	private String reviewerIdentifier;
	private String employeeIdentifier;
	
	private String reviewDate;
	
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

	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public List<Map<String, String>> getGoals() {
		return goals;
	}
	public void setGoals(List<Map<String, String>> goals) {
		this.goals = goals;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public Map<String, String> getReviewer() {
		return reviewer;
	}
	public void setReviewer(Map<String, String> reviewer) {
		this.reviewer = reviewer;
	}
	public String getReviewerIdentifier() {
		return reviewerIdentifier;
	}
	public void setReviewerIdentifier(String reviewerIdentifier) {
		this.reviewerIdentifier = reviewerIdentifier;
	}
	public String getEmployeeIdentifier() {
		return employeeIdentifier;
	}
	public void setEmployeeIdentifier(String employeeIdentifier) {
		this.employeeIdentifier = employeeIdentifier;
	}
	
	
}
