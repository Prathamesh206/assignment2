package in.sts.assignemt2.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;



public class EducationDao {

	final static Logger log=Logger.getLogger(EducationDao.class);

	final int EMP_ID=1;
	final int EDUCATION=3;
	final int UPDATE_EDUCATION=1;
	final int FIRSTNAME=2;
	final int UPDATE_FIRSTNAME=3;
	final int UPDATE_EMPID=2;
	/*
	 * 
	 *
	 * insertEducation method for storing the education of employee in the table
	 * 
	 * 
	 */

	public  boolean insert(int empid,ArrayList<String> educations,Connection connection,String name) {        //insert education method for insert the education in the database

		PreparedStatement educationStatment=null;

		int rowInserted=0;
		try {

			String query="insert into education_data values(educationID,?,?,?)";
			educationStatment=connection.prepareStatement(query);
			educationStatment.setInt(EMP_ID,empid);
			int count=1;
			for(String education:educations) {
				educationStatment.setString(FIRSTNAME, name + count);
				educationStatment.setString(EDUCATION, education);
				count++;
				rowInserted =educationStatment.executeUpdate();

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
					log.error("sqlException");

				}
				System.out.println("Data not inserted");
			}

		}finally {
			if(educationStatment!=null) {
				try {
					educationStatment.close();
				} catch (SQLException sqlException) {
					log.error("sql Exception");
				}
			}
		}




		return false;


	}

	/*
	 * 
	 * update method for update records in the eduaction_table
	 * 
	 */

	public boolean update(	int empid,ArrayList<String> educations,Connection connection,String name) {

		PreparedStatement educationStatment=null;

		int rowInserted=0;
		try {

			String sql="update education_data set  education=? where empid=? and name=?";
			educationStatment=connection.prepareStatement(sql);
			educationStatment.setInt(UPDATE_EMPID,empid);

			int count=1;
			for(String education:educations) {
				educationStatment.setString(UPDATE_FIRSTNAME, name + count);
				educationStatment.setString(UPDATE_EDUCATION, education);
				count++;
				rowInserted =educationStatment.executeUpdate();
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
					log.error("sqlException");

				}
				System.out.println("Data not inserted");
			}

		}finally {
			if(educationStatment!=null) {
				try {
					educationStatment.close();
				} catch (SQLException sqlException) {
					log.error("sql Exception");
				}
			}
		}




		return false;


	}


	/*
	 * 
	 *  getEducations method for fetch the education data from the database
	 */

	public ArrayList<String> getEducations(Connection connection,int empId) {
		String education=null;                      
		ArrayList <String> educations=new ArrayList<String>();                 
		PreparedStatement  preparedStatement=null;
		String educationQuery="select education  from education_data  where empid = ?";                                         
		try {
			preparedStatement =connection.prepareStatement(educationQuery);
			preparedStatement.setInt(1, empId);
			ResultSet educationSet=preparedStatement.executeQuery();
			while(educationSet.next()) {
				education=	educationSet.getString("education");
				educations.add(education);
			}

		} catch (SQLException sqlException) {

			System.out.println("Sql Exception");
		}
		return educations;



					

	}




}

