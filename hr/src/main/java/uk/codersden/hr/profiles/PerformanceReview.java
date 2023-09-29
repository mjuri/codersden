package uk.codersden.hr.profiles;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="performance_reviews")
public class PerformanceReview {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String identifier;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="reviewdate")
	private Date reviewDate;
	
    @ManyToOne
    @JoinColumn(name="employee_identifier",  nullable=false, insertable=false, updatable=false)
	private Profile employee;
    
    @ManyToOne
    @JoinColumn(name="reviewer_identifier",  nullable=false, insertable=false, updatable=false)
    private Profile reviewer;
    
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "goal_performance_review", 
        joinColumns = { @JoinColumn(name = "performance_review_identifier") }, 
        inverseJoinColumns = { @JoinColumn(name = "review_identifier") })
    private List<Goal> goals;
	
	private String comments;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public Profile getEmployee() {
		return employee;
	}
	public void setEmployee(Profile employee) {
		this.employee = employee;
	}
	public List<Goal> getGoals() {
		return goals;
	}
	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}
	public Profile getReviewer() {
		return reviewer;
	}
	public void setReviewer(Profile reviewer) {
		this.reviewer = reviewer;
	}
	

	
	
}
