package in.sts.assignemt2.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import in.sts.assignment2.model.Education;
import in.sts.assignment2.output.ConsoleOutput;



public class EducationDao {
	ConsoleOutput consoleOutput=new ConsoleOutput();

	final static Logger log=Logger.getLogger(EducationDao.class);

	final int EMP_ID=1;
	final int EDUCATION=2;
	
	/*
	 * 
	 *
	 * insertEducation method for storing the education of employee in the table
	 * 
	 * 
	 */

	public  boolean insert(int empid,ArrayList<String> educations,Connection connection) {        //insert education method for insert the education in the database

		PreparedStatement educationStatment=null;
		int count=1;
		int rowInserted=0;
		try {

			String query="insert into education_data values(educationID,?,?)";
			educationStatment=connection.prepareStatement(query);
			educationStatment.setInt(EMP_ID,empid);

			for(String education:educations) {

				educationStatment.setString(EDUCATION, education);
				rowInserted =educationStatment.executeUpdate();
				count++;
			}
			if(rowInserted>0) {

				return true;

			}

		}

		catch (SQLException sqlException) {

			if(connection!=null) {
				try {
					connection.rollback();
				} catch (SQLException sqlException1) {
					log.error("message" +sqlException1);

				}
				System.out.println("Data not inserted");
			}

		}finally {
			if(educationStatment!=null) {
				try {
					educationStatment.close();
				} catch (SQLException sqlException) {
					log.error("message"+ sqlException);
				}
			}
		}

		return false;

	}

	/*
	 * 
	 *  getEducations method for fetch the education data from the database
	 */

	public ArrayList<Education> getAllEducations(Connection connection) {
		String education=null;                      
		ArrayList <Education> educations=new ArrayList<Education>();                 
		PreparedStatement  preparedStatement=null;
		String educationQuery="select * from education_data";                                         
		try {
			preparedStatement =connection.prepareStatement(educationQuery);

			ResultSet educationSet=preparedStatement.executeQuery();
			while(educationSet.next()) {
				int id=educationSet.getInt("empid");


				education=	educationSet.getString("education");
				Education educationModel=new Education(id,education);
				educations.add(educationModel);
			}

		} catch (SQLException sqlException) {

			log.error("message" + sqlException);
		}
		return educations;

	}

	/*
	 * 
	 * getEducations method for fetch on the educationData from the database
	 */

	public ArrayList<String> getEducations(Connection connection,int empId) {
		String education=null;                      
		ArrayList <String> educations=new ArrayList<String>();                 
		PreparedStatement  preparedStatement=null;
		String educationQuery="select * from education_data  where empid = ?";                                         
		try {
			preparedStatement =connection.prepareStatement(educationQuery);
			preparedStatement.setInt(EMP_ID, empId);
			ResultSet educationSet=preparedStatement.executeQuery();
			while(educationSet.next()) {
				int id=educationSet.getInt("empid");

				education=	educationSet.getString("education");

				educations.add(education);
			}

		} catch (SQLException sqlException) {

			log.error("message" +sqlException);
		}
		return educations;

	}

	/*
	 * delete method for delete the education from the database and insert the new education in it.
	 * 
	 */
	public void  delete(Connection connection,int empid,ArrayList<String> newEducations) {

		try {

			String deleteQuery="delete from education_data where empid=? ";

			PreparedStatement deleteStatement=connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(EMP_ID,empid);  
			int result=deleteStatement.executeUpdate();
			if(result>0) {
				consoleOutput.displayUpdate(false,insert(empid,newEducations,connection),empid);
				
			}
			//				
		}


		catch (SQLException sqlExcpeption) {
			// TODO Auto-generated catch block
			log.error("message" +sqlExcpeption);
		}

	


	}




}



