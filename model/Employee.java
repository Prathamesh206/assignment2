package in.sts.assignment2.model;

import java.util.ArrayList;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private String city;
	private ArrayList<String> education ;
	private String job;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}



<<<<<<< Updated upstream
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



=======
>>>>>>> Stashed changes
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

	public Employee(String firstName, String lastName, String city, ArrayList<String> education,String job ) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.education = education;
		this.job = job;
	}



<<<<<<< Updated upstream
	public Employee(int id, String firstName, String lastName, String city, ArrayList<String> education, String job) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.education = education;
		this.job = job;
	}



=======
>>>>>>> Stashed changes
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", city=" + city + ", education="
				+ education + ", job=" + job + "]";
	}


}
