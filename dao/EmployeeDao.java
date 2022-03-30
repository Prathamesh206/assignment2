package in.sts.assignemt2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import in.sts.assignment2.connection.DBConnection;
import in.sts.assignment2.model.Employee;


public class EmployeeDao {
	final static Logger log=Logger.getLogger(EmployeeDao.class);
	
	final int FirstName=1;
	final int LastName=2;
	final int City =3;
    final int Job=4;
	
	Connection connection=DBConnection.getConnection();
	
	/* 
	 * insertEmployee method for storing the employee in the database
	 * 
	 */
	
	public boolean insertEmployee(Employee employee) {
		String query="insert into employee_data  values(id,?,?,?,?)";
		try {
			PreparedStatement employeeStatment=connection.prepareStatement(query);
		   employeeStatment.setString(FirstName , employee.getFirstName());
		   employeeStatment.setString(LastName, employee.getLastName());
		   employeeStatment.setString(City, employee.getCity());
		   employeeStatment.setString(Job, employee.getJob());
		   int rowInserted=employeeStatment.executeUpdate();
		   if(rowInserted>0) {
			   return true;
		   }
		   
		   connection.close();	
		} catch (SQLException sqlException) {
			log.error("sql Exception");
		}
		return false;
		
		
	} 
	
	/*
	 *
	 *  getEmployeeDetails  method  showing the all the details of employee from the firstname and lastname fetch the empid and 
	 * 
	 * 
	 */
	
	
	 public ArrayList<Employee> getEmployeeDetails(ArrayList<Employee> jsonEmployeeList) {
		 ArrayList<Employee> employeeAllDetails=new ArrayList<Employee>();
		 String query="select * from employee_data where firstname=? and lastname=?";
		 try {
			 
			PreparedStatement preparedStatement=connection.prepareStatement(query);       
			for(Employee employee:jsonEmployeeList) {
				preparedStatement.setString(FirstName, employee.getFirstName());
				preparedStatement.setString(LastName, employee.getLastName());
				ResultSet result=preparedStatement.executeQuery();
				while(result.next()) {
					int empId=result.getInt("id");                        
					Employee employeeModel=new Employee(empId,employee.getFirstName(),employee.getLastName(),employee.getCity(),employee.getEducation(),employee.getJob());
					employeeAllDetails.add(employeeModel);
				}
			}
			connection.close();
		} catch (SQLException sqlException) {
			// TODO Auto-generated catch block
			log.error("Sql Exception");
		}
		
		return employeeAllDetails;
		
		 
	 }
	
	

}
