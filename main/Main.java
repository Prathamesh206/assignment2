
package in.sts.assignment2.main;



import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;

import in.sts.assignemt2.dao.EducationDao;
import in.sts.assignemt2.dao.EmployeeDao;
import in.sts.assignment2.input.FileInput;
import in.sts.assignment2.model.Employee;
import in.sts.assignment2.output.ConsoleOutput;
import in.sts.assignment2.reader.JSONFileReader;

import in.sts.assignment2.services.FileWriter;
import in.sts.assignment2.services.Filter;


public class Main {
	@SuppressWarnings("static-access")
	public static void main(String[] args)  {
		BasicConfigurator.configure();
		boolean result=false;
		/*
		 * 
		 * Creating Classes Object used in the operation
		 */

		EmployeeDao employeeDao=new EmployeeDao();
		ConsoleOutput consoleOutput=new ConsoleOutput();
		JSONFileReader fileReader=new JSONFileReader();
		FileInput fileInput=new FileInput();
		Filter filterJob=new Filter();
		FileWriter fileWriter=new FileWriter();
		EducationDao education=new EducationDao();


		ArrayList<Employee> employeeList= fileReader.getEmployeeList(fileInput.getJsonFilePath());    //Stored employees in employeeList ArrayList

		/*
		 * First Operation for read the data from JSON File and dump into Excel file
		 * 
		 */

		fileWriter.excelFileWriter(employeeList,fileInput.getExcelFilePath());                  

		/*
		 * Second Operation for taking the job as input from user and display the profile which matching to that job in the display in the console
		 *   
		 */

		consoleOutput.displayFilterJobList(filterJob.getFilterJobList(fileInput.getJob(),employeeList));     
		/*
		 * Third Operation is Database operation 
		 * 1) First Operation is for storing the employee Data in the Database
		 * 
		 */
		for(Employee employee:employeeList) {

			result	=employeeDao.insertEmployee(employee.getFirstName(),employee.getLastName(),employee.getCity(),employee.getJob());

		}
		consoleOutput.displayInsertion(result);

		/* 
		 *  
		 *  2)Second  Operation is for storing the education Data in the Database
		 */

		consoleOutput.displayInsertion(education.insertEducation(employeeDao.getEmployeeDetails(employeeList)));;


	}







}
