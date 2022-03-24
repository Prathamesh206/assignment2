package in.sts.assignment2.main;



import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;

import in.sts.assignment2.input.FileInput;
import in.sts.assignment2.model.Employee;
import in.sts.assignment2.output.ConsoleOutput;
import in.sts.assignment2.reader.FileReader;

import in.sts.assignment2.services.FileWriter;
import in.sts.assignment2.services.Filter;


public class Main {
	@SuppressWarnings("static-access")
	public static void main(String[] args)  {
		BasicConfigurator.configure();
		
		ConsoleOutput consoleOutput=new ConsoleOutput();
		FileReader fileReader=new FileReader();
		FileInput fileInput=new FileInput();
		Filter filterJob=new Filter();
		FileWriter fileWriter=new FileWriter();
		
		ArrayList<Employee> employeeList= fileReader.getEmployeeList(fileInput.getJsonFilePath());    //Stored employees in employeeList ArrayList
		
	    fileWriter.excelFileWriter(employeeList,fileInput.getExcelFilePath());                   //for display word in the excel file
		
		
		consoleOutput.displayFilterJobList(filterJob.getFilterJobList(fileInput.getJob(),employeeList));    //taking the job name from user and display the fullName of employee for that job
		
	}
	
	
	

	
	

}
