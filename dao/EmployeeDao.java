package in.sts.assignemt2.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import in.sts.assignment2.connection.DBConnection;
import in.sts.assignment2.model.Employee;
import in.sts.assignment2.output.ConsoleOutput;


public class EmployeeDao {
	EducationDao eduactionDao=new EducationDao();
	ConsoleOutput consoleOutput=new ConsoleOutput();
	final static Logger log=Logger.getLogger(EmployeeDao.class);

	final int FIRSTNAME=1;
	final int LASTNAME=2;
	final int CITY =3;
	final int JOB=4;
	final int EXISTING_FIRSTNAME=5;
	final int EXISTING_LASTNAME=6;


	Connection connection=DBConnection.getConnection();

	/* 
	 * 
	 * 
	 * insertEmployee method for storing the employee in the database
	 * 
	 */

	public void insert(ArrayList<Employee> jsonEmployeeList) {
		EducationDao educationDao=new EducationDao();
		int empId=0;
		String insertquery="INSERT INTO employee_data(firstname,lastname,city,job) SELECT * FROM (SELECT ? AS firstname, ? AS"        //insert query for insert the employee in the database if the employee is not present in the database
				+ " lastname,? AS city,? AS job) AS temp WHERE NOT EXISTS (SELECT * FROM  employee_data WHERE firstname = ? "
				+ "and lastname=?)  ";

		try {
			connection.setAutoCommit(false);                                                                //set AutoCommit false
			for(Employee employee:jsonEmployeeList) {                                                      //iterating employee from jsonEmployee List

				PreparedStatement employeeStatment=connection.prepareStatement(insertquery);            
				employeeStatment.setString(FIRSTNAME, employee.getFirstName());
				employeeStatment.setString(LASTNAME, employee.getLastName());
				employeeStatment.setString(CITY, employee.getCity());
				employeeStatment.setString(JOB, employee.getJob());
				employeeStatment.setString(EXISTING_FIRSTNAME, employee.getFirstName());
				employeeStatment.setString(EXISTING_LASTNAME, employee.getLastName());
				int rowInserted=employeeStatment.executeUpdate();
				
				if(rowInserted>0) {                                                                      
					String selectQuery="select * from employee_data where firstname=? and lastname=?";       //select query for fetch the record from the database
					PreparedStatement preparedStatement=connection.prepareStatement(selectQuery); 
					preparedStatement.setString(FIRSTNAME, employee.getFirstName());                   
					preparedStatement.setString(LASTNAME, employee.getLastName());
					ResultSet result=preparedStatement.executeQuery();

					while(result.next()) {                                                                 
						empId=result.getInt("id");                                                          //fetch the id from the database and pass to the educationDao insert method
						consoleOutput.displayInsertion(educationDao.insert(empId, employee.getEducation(),connection));	  //call the eduactionDao insert method in the employeeDao insert for inserting the educations in the database
						

					}

				}

			}	
			
			connection.commit();                                                                         //if all the  data inserted prope
			connection.close();
		} 
		catch (SQLException sqlException) {
			try {

				if(connection!=null) {
			
					connection.rollback();
				}
			}
			catch (SQLException sqlException1) {
				log.error("sqlException");
				
			}
			System.out.println("Data not inserted");
		}
	
	}





} 



