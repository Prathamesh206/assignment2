package in.sts.assignment2.output;

import java.util.ArrayList;



public class ConsoleOutput {
<<<<<<< Updated upstream

=======
	
>>>>>>> Stashed changes
	/*
	 *  displayFilterJob Method Display Employee Details Filter by job
	 */

	public void displayFilterJobList(ArrayList<String> employeeList) {
<<<<<<< Updated upstream

		for(String employee:employeeList) {                 //iterating each employee and display the full name of employee
			System.out.println("FullName : " + employee);

		}
	}

	public void displayInsertion(boolean result)
	{
		if(result==true)
		{
			System.out.println("Insertion Successful");
		}
		else {
			System.out.println("Unsuccessful");
		}
		
	}
=======
		
		for(String employee:employeeList) {                 //iterating each employee and display the full name of employee
			System.out.println("FullName : " + employee);
			
		}
	}


>>>>>>> Stashed changes



}
