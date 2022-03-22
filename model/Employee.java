package in.sts.assignment2.model;

import java.util.ArrayList;

public class Employee {
	private String firstName;
	private String lastName;
	private String city;
	private ArrayList<String> education ;
	private String job;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public ArrayList<String> getEducation() {
		return education;
	}
	
	public void setEducation(ArrayList<String> education) {
		this.education = education;
	}
	
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	

}
