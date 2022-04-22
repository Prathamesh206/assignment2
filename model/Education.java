package in.sts.assignment2.model;

public class Education {
	private int empid;
	private String firstname;
	private String lastName;
	private String education;

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Education(int empid, String firstname, String lastName, String education) {
		super();
		this.empid = empid;
		this.firstname = firstname;
		this.lastName = lastName;
		this.education = education;
	}
	
	

}
