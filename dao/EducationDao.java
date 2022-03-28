package in.sts.assignemt2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import in.sts.assignment2.connection.DBConnection;
import in.sts.assignment2.model.Employee;

public class EducationDao {
	
	final static Logger log=Logger.getLogger(EducationDao.class);
	
	Connection connection=DBConnection.getConnection();
/*
 * 
 *
 * insertEducation method for storing the education of employee in the table
 */
	
	public boolean insertEducation(ArrayList<Employee> employeeAllDetails) {    
		String query="insert into education values(educationID,?,?)";
		int i=0;
		try {
			PreparedStatement educationStatment=connection.prepareStatement(query);
			for(Employee employee:employeeAllDetails) {
			
				for(String education:employee.getEducation()) {
					educationStatment.setInt(1, employee.getId());
					educationStatment.setString(2, education);
					i =educationStatment.executeUpdate();
				}
			}
			
			
			if(i>0) {
				return true;
			}
			connection.close();
			
			
		} catch (SQLException sqlException) {
			log.error("Sql exception");
		}
		
		
		return false;
		
		
	}

}
