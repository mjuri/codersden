package uk.codersden.wod.classes;

import java.util.List;

import uk.codersden.hr.profiles.Profile;

public class Rank {
	private Profile athlete;

	private List<WODClass> classes;
	public Rank(Profile profile, List<WODClass> classes) {
		this.setAthlete(profile);
		this.classes = classes;
	}
	public Profile getAthlete() {
		return athlete;
	}
	public void setAthlete(Profile athlete) {
		this.athlete = athlete;
	}
	public Integer getTotalClasses() {
		return classes.size();
	}
	public List<WODClass> getClasses() {
		return classes;
	}
	

}
