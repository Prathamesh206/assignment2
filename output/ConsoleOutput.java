package in.sts.assignment2.output;

import java.util.ArrayList;



public class ConsoleOutput {
	
	/*
	 *  displayFilterJob Method Display Employee Details Filter by job
	 */

	public void displayFilterJobList(ArrayList<String> employeeList) {
		
		for(String employee:employeeList) {                 //iterating each employee and display the full name of employee
			System.out.println("FullName : " + employee);
			
		}
	}





}
