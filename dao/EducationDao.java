package in.sts.assignemt2.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class EducationDao {
	
	final static Logger log=Logger.getLogger(EducationDao.class);
	
	final int EMP_ID=1;
	final int EDUCATION=2;
	
	
//	Connection connection=DBConnection.getConnection();
/*
 * 
 *
 * insertEducation method for storing the education of employee in the table
 * 
 * 
 */
	
	public  boolean insert(int empid,ArrayList<String> educations,Connection connection) {        //insert education method for insert the education in the database
		
		
		
		int rowInserted=0;
		try {
			
			String query="insert into education values(educationID,?,?)";
			PreparedStatement educationStatment=connection.prepareStatement(query);
					educationStatment.setInt(EMP_ID,empid);
					for(String education:educations) {
					educationStatment.setString(EDUCATION, education);
					rowInserted =educationStatment.executeUpdate();
					
				}
					
			if(rowInserted>0) {
				return true;
			
			}
			connection.commit();
		}  
		
	
		

		catch (SQLException sqlException) {
			
			 if(connection!=null) {
	               try {
					connection.rollback();
				} catch (SQLException sqlException1) {
					log.error("sqlException");
					
				}
	               System.out.println("Data not inserted");
	            }

		}
		
		
		
		
		return false;
		
		
	}



}
