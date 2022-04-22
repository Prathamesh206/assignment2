package in.sts.assignemt2.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import in.sts.assignment2.model.Education;
import in.sts.assignment2.model.Employee;



public class EducationDao {

	final static Logger log=Logger.getLogger(EducationDao.class);

	final int EMP_ID=1;
	final int EDUCATION=4;
	final int UPDATE_EDUCATION=1;
	final int FIRSTNAME=2;
	final int LASTNAME=3;
	final int UPDATE_FIRSTNAME=3;
	final int UPDATE_EMPID=2;
	/*
	 * 
	 *
	 * insertEducation method for storing the education of employee in the table
	 * 
	 * 
	 */

	public  boolean insert(int empid,ArrayList<String> educations,Connection connection,String firstName,String lastName,boolean result) {        //insert education method for insert the education in the database

		PreparedStatement educationStatment=null;
		int count=1;
		int rowInserted=0;
		try {

			String query="insert into education_data values(educationID,?,?,?,?)";
			educationStatment=connection.prepareStatement(query);
			educationStatment.setInt(EMP_ID,empid);

			for(String education:educations) {
				if(result==true) {
					educationStatment.setString(FIRSTNAME, firstName);
					educationStatment.setString(LASTNAME, lastName);

				}
				else if(result==false){
					educationStatment.setString(FIRSTNAME, firstName + count);
					educationStatment.setString(LASTNAME, lastName);
				}

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
				String firstName=educationSet.getString("firstname");
				String lastName=educationSet.getString("lastname");

				education=	educationSet.getString("education");
				Education educationModel=new Education(id,firstName,lastName,education);
				educations.add(educationModel);
			}

		} catch (SQLException sqlException) {

			System.out.println(sqlException);
		}
		return educations;

	}

	/*
	 * 
	 * getEducations method for fetch the educationData from the database
	 */

	public ArrayList<Education> getEducations(Connection connection,int empId) {
		String education=null;                      
		ArrayList <Education> educations=new ArrayList<Education>();                 
		PreparedStatement  preparedStatement=null;
		String educationQuery="select * from education_data  where empid = ?";                                         
		try {
			preparedStatement =connection.prepareStatement(educationQuery);
			preparedStatement.setInt(EMP_ID, empId);
			ResultSet educationSet=preparedStatement.executeQuery();
			while(educationSet.next()) {
				int id=educationSet.getInt("empid");
				String firstName=educationSet.getString("firstname");
				String lastName=educationSet.getString("lastname");

				education=	educationSet.getString("education");
				Education educationModel=new Education(id,firstName,lastName,education);
				educations.add(educationModel);
			}

		} catch (SQLException sqlException) {

			System.out.println(sqlException);
		}
		return educations;

	}

	/*
	 * delete method for delete the education from the database
	 * 
	 */
	public boolean delete(Connection connection,int empid,ArrayList<Employee> jsonEmployeeList) {
		ArrayList<Education> dataBaseEducationList=getAllEducations(connection);
		String deleteQuery="delete from education_data where empid=? and education=?";

		try {

			for(Education dataBaseEducation:dataBaseEducationList) {
				for(Employee jsonemployee:jsonEmployeeList) {
					int count=1;
					for(int i=0;i<=1;i++) {


						String firstName=jsonemployee.getFirstName()+count;
						String lastName=jsonemployee.getLastName();
						count++;
						if(firstName.equals(dataBaseEducation.getFirstname()) && lastName.equals(dataBaseEducation.getLastName()) && !jsonemployee.getEducation().get(i).equals(dataBaseEducation.getEducation())) {
							PreparedStatement deleteStatement=connection.prepareStatement(deleteQuery);
							deleteStatement.setInt(EMP_ID,dataBaseEducation.getEmpid());

							deleteStatement.setString(2,dataBaseEducation.getEducation());

							if(jsonemployee.getEducation().get(i)!=null) {
								ArrayList<String> setEducation=new ArrayList<String>();
								setEducation.add(jsonemployee.getEducation().get(i));
								insert(dataBaseEducation.getEmpid(),setEducation,connection,dataBaseEducation.getFirstname(),dataBaseEducation.getLastName(),true);
							}
							int result=deleteStatement.executeUpdate();
							if(result>0) {
								return true;
							}
						}

					}
				}
			}


		}
		catch (SQLException sqlExcpeption) {
			// TODO Auto-generated catch block
			log.error("sql Excpetion");
		}

		return false;


	}




}

