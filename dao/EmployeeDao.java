

package in.sts.assignemt2.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import in.sts.assignment2.connection.DBConnection;
import in.sts.assignment2.model.Education;
import in.sts.assignment2.model.Employee;
import in.sts.assignment2.output.ConsoleOutput;


public class EmployeeDao {
	final static Logger log=Logger.getLogger(EmployeeDao.class);
	
	final int FIRSTNAME=1;
	final int LASTNAME=2;
	final int CITY =3;
	final int JOB=4;
	final int EXISTING_FIRSTNAME=5;
	final int EXISTING_LASTNAME=6;
	final int UPDATE_CITY=1;
	final int UPDATE_JOB=2;
	final int UPDATE_FIRST_NAME=3;
	final int UPDATE_LAST_NAME=4;

	Connection connection=DBConnection.getConnection();
	EducationDao educationDao=new EducationDao();
	ConsoleOutput consoleOutput=new ConsoleOutput();
	/* 
	 * 
	 * 
	 * insertEmployee method for storing the employee in the database
	 * 
	 * 
	 */


	@SuppressWarnings("resource")
	public void upsert(ArrayList<Employee> employeeList) {
		//		getjsonEmployeeList.addAll(jsonEmployeeList);

		PreparedStatement preparedStatement=null;
		int empId=0;
		String databaseJob=null;
		String databaseCity=null;
		boolean update=true;

		//   for Insert data into employee_data & eduaction_data if the firstName and lastName not exist in database

		String insertquery="INSERT INTO employee_data(firstname,lastname,city,job) SELECT * FROM (SELECT ? AS firstname, ? AS"        //insert query for insert the employee in the database if the employee is not present in the database
				+ " lastname,? AS city,? AS job) AS temp WHERE NOT EXISTS (SELECT * FROM  employee_data WHERE firstname = ? "
				+ "and lastname=?)  ";

		try {
			connection.setAutoCommit(false);                                                                //set AutoCommit false
			for(Employee employee:employeeList) {                                                      //iterating employee from jsonEmployee List

				preparedStatement=connection.prepareStatement(insertquery);            
				preparedStatement.setString(FIRSTNAME, employee.getFirstName());
				preparedStatement.setString(LASTNAME, employee.getLastName());
				preparedStatement.setString(CITY, employee.getCity());
				preparedStatement.setString(JOB, employee.getJob());
				preparedStatement.setString(EXISTING_FIRSTNAME, employee.getFirstName());
				preparedStatement.setString(EXISTING_LASTNAME,employee.getLastName());
				int rowInserted=preparedStatement.executeUpdate();

				//fetch the id from the database and pass to the educationDao insert method
				ArrayList<Employee> dataBaseEmployeList=getEmployeeList(employee.getFirstName(),employee.getLastName());
				for(Employee databaseEmployee:dataBaseEmployeList) {
					empId=databaseEmployee.getId();
					databaseCity=databaseEmployee.getCity();
					databaseJob=databaseEmployee.getJob();
				}



				//fetch the employee educations from the database   
				ArrayList<Education> databaseEducations=educationDao.getEducations(connection, empId);   

				if(rowInserted>0) {  
					//call the eduactionDao insert method in the employeeDao insert for inserting the educations in the database
					consoleOutput.displayInsertion(educationDao.insert(empId, employee.getEducation(),connection,employee.getFirstName(),employee.getLastName(),false));

					update=false;

				}

				//if  firstName and lastName are same in the file and if any other changes are found in the file then comes into the else if block 
				else if(!databaseJob.equals(employee.getJob()) || !databaseCity.equals(employee.getCity()) || !databaseEducations.equals(employee.getEducation()) && update==true){ 

					boolean flag=false;
					int size=employee.getEducation().size();
					int count=0;
					if(count<=size) {

						for(String education:employee.getEducation()) {
							for(Education getEducation:databaseEducations) {
								if(education.equals(getEducation.getEducation())){          //if 
									continue;
								}else if(!databaseJob.equals(employee.getJob()) || !databaseCity.equals(employee.getCity())){
									flag=true;
								}
								count++;
							}
						}
					}
					if(flag) {
						//update method for update the  database data and if changes are found the file
						consoleOutput.displayUpdate(update(employee.getCity(),employee.getJob(),employee.getFirstName(),employee.getLastName(),empId,connection,employee.getEducation()),false);

					}

                                             //else it will comes into else block and delete the education from the education table if education are not match
					else {
						educationDao.delete(connection,empId,employeeList);

					}


				}

			}


			connection.commit();                                                                         //IF all the data are inserted in the  database then the data commit  else it will rollback the data

		} 
		catch (SQLException sqlException) {
			try {

				if(connection!=null) {

					connection.rollback();
				}
			}
			catch (SQLException sqlException1) {
				log.error("message"+sqlException1);

			}
			log.error("message" + sqlException);
			System.out.println("Data not inserted");
		}finally {
			if(connection!=null) {
				try {
					connection.close();
					preparedStatement.close();
				} catch (SQLException sqlException) {
					log.error("message" +sqlException);
				}
			}
		}


	}

	/*
	 * getEmployeeList method for fetch employee Data from database
	 */


	public  ArrayList<Employee> getEmployeeList(String FirstName,String LastName) {
		ArrayList<Employee> dataBaseEmployeeList=new ArrayList<Employee>();
		Employee employee = null;
		int id = 0;
		String job = null;
		String city = null;
		String firstName = null;
		String lastName = null;


		PreparedStatement preparedStatement=null;

		String selectQuery="select * from employee_data where firstname=? and lastname=?";       //select query for fetch the record from the database
		try {
			preparedStatement=connection.prepareStatement(selectQuery);
			preparedStatement.setString(FIRSTNAME,FirstName); 
			preparedStatement.setString(LASTNAME, LastName);
			ResultSet result=preparedStatement.executeQuery();
			while(result.next()) {  
				job=result.getString("job");
				city=result.getString("city");
				id=result.getInt("id");
				firstName=result.getString("firstname");
				lastName=result.getString("lastname"); 
				employee=new Employee(id,firstName,lastName,city,job);


			} 
			dataBaseEmployeeList.add(employee);


			return dataBaseEmployeeList;
		} catch (SQLException sqlException) {
			// TODO Auto-generated catch block
			log.error("message" + sqlException);
		}
		return null; 


	}

	/*
	 * 
	 * update method for update the employee data in database
	 */

	public boolean update(String city,String job,String firstname,String lastname,int empId,Connection connection,ArrayList<String> educations) {

		PreparedStatement preparedStatement=null;
		String updateQuery="update employee_data set city=?,job=? where firstname =?and lastname=?";
		try {
			preparedStatement=connection.prepareStatement(updateQuery);
			preparedStatement.setString(UPDATE_CITY,city);
			preparedStatement.setString(UPDATE_JOB, job);
			preparedStatement.setString(UPDATE_FIRST_NAME,firstname);
			preparedStatement.setString(UPDATE_LAST_NAME, lastname);
			int updateRow=preparedStatement.executeUpdate();



			//			System.out.println("updated");
			if(updateRow>0) {
				return true;
			}
		} 

		catch (SQLException sqlException) {

			log.error("message" +sqlException);
		}
		return false;


	}
}	










