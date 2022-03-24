package in.sts.assignment2.reader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import in.sts.assignment2.model.Employee;

public class FileReader {
	final static Logger log=Logger.getLogger(FileReader.class);
	/*
	 * getEmployeList Method For Read The employee Details from JSON File
	 * 
	 * 
	 */

	public static ArrayList<Employee> getEmployeeList(String jsonFilepath){
		ArrayList<Employee> employeeList=new ArrayList<Employee>();                   // employeeList For Storing The Employees 

		JSONParser jsonParser=new JSONParser();                                       //JSONParser Object is creating for parse the JSON  File object 
		try {
			java.io.FileReader fileReader=new java.io.FileReader("F:\\Data.json");    //Created FileReader Object for Read the JSON File
			Object object=jsonParser.parse(fileReader);                                  //read the JSON file and with the JSONParser stored in the object class object variable
			JSONObject employeeObject=(JSONObject) object;                                //This object stored into the jsonObject
			JSONArray employeeArray=(JSONArray) employeeObject.get("employees");       //Created  JSONArray for stored the all the employee in it
			for(int i=0;i<employeeArray.size();i++) {                                 //Iterating each each employee object
				JSONObject employe=(JSONObject) employeeArray.get(i);                 
				String firstName=(String) employe.get("firstName");
				String lastName=(String) employe.get("lastName");
				String city=(String) employe.get("city");
				String job=(String)employe.get("job");
				@SuppressWarnings("unchecked")
				ArrayList<String> educationArray=(ArrayList<String>) employe.get("education");    //educations in ArrayList

				Employee employee=new Employee(firstName,lastName,city,educationArray,job);    //creating employee object and stored the all the value in it
				
				employeeList.add(employee);                                                    //add all employee in arraylist

			}
		

		} catch (FileNotFoundException fileNotFoundException) {
			log.error("File Not Found");
		}catch(IOException ioException) {
			log.error("Cannot Read File Properly");
		}catch(ParseException parseException) {
			log.error("Cannot parse the Object");
		}
		return employeeList;





	}

}
