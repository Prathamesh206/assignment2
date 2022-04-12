package in.sts.assignment2.output;

import java.util.ArrayList;



public class ConsoleOutput {

	/*
	 *  displayFilterJob Method Display Employee Details Filter by job
	 */
int count=1;
	public void displayFilterJobList(ArrayList<String> employeeList) {

		for(String employee:employeeList) {                 //iterating each employee and display the full name of employee
			System.out.println("FullName : " + employee);

		}
	}
	


	public void displayInsertion(boolean result) {
 
	
		if(result==true)
		{
			
			System.out.println( count++ + " "+"Insertion Successful");
		}
		else  {
			System.out.println("Unsuccessful");
		}
		
	}
	
	public void  displayUpdate(boolean result) {
		if(result==true) {
			System.out.println(count++ + " "+"update succesfully");
		}
	}
  
	public void  displayUpToDate() {
		
			System.out.println("Data up to date");
		
	}

}
