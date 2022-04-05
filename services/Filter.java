package in.sts.assignment2.services;

import java.util.ArrayList;

import in.sts.assignment2.model.Employee;

public class Filter {
	/*
	 * getFilterJob Method For return  the Employee List Filter by job 
	 */
	
	@SuppressWarnings("unlikely-arg-type")
	public ArrayList<String> getFilterJobList(String job,ArrayList<Employee> empList){

<<<<<<< Updated upstream
               boolean result=false;
=======

>>>>>>> Stashed changes
		ArrayList<String> employeeList=new ArrayList<String>();                     //EmployeList For  Storing employee who's job is match the job taking from user
		for(Employee employee:empList){                                             //iterating empList 
			
			 if(job.equalsIgnoreCase(employee.getJob())) {           //if job is equals to the job taking from user then it condition become true
				String firstName=employee.getFirstName();                             //get the employee firstName 
				String lastName=employee.getLastName();                               //get the employee LastName
				employeeList.add(firstName+" "+lastName);                             //concat the firstName And lastName and stored in the employeeList
<<<<<<< Updated upstream
				result=true;
			}
			
		}
		if(result==false){
			System.out.println("JobProfile Not Found");
		}
		
=======
				
			}
			
		}

		if(!empList.contains(job)) {                                                 //if User Job Input not equals empList then these condition become true
			System.out.println("JobProfile Not Found");
			
		}
>>>>>>> Stashed changes

		return employeeList;                                                        
	}

}
