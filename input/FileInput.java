ppackage in.sts.assignment2.input;

import java.util.Scanner;

import java.util.Scanner;

public class FileInput {
	
	/*
	 * getJsonFile Method for JSON File Input
	 * 
	 */
	public String getJsonFilePath() {
		
		String jsonFilePath="F:\\Data.json";
		
		return jsonFilePath;
		
	}
	
	
	/*
	 * getExcelFile Method for Excel File Input
	 * 
	 */
	
	public String getExcelFilePath() {
<<<<<<< Updated upstream
		
		String excelFilePath="F:\\mmv.xls";
=======
		String excelFilePath="F:\\excel.xls";
>>>>>>> Stashed changes
		
		return excelFilePath;
		
	}
	
	
	
	/*
	 * getJob Method for job as Input From User
	 */
	
	public String getJob() {
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		System.out.println("Which Profile You Want To See By Job  ");
		String job=scanner.nextLine();
<<<<<<< Updated upstream
		scanner.close();
=======
>>>>>>> Stashed changes
		
		return job;
		
	}

}
