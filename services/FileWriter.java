package in.sts.assignment2.services;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
<<<<<<< Updated upstream

=======
import org.apache.poi.ss.usermodel.Cell;
>>>>>>> Stashed changes
import org.apache.poi.ss.usermodel.Row;


import in.sts.assignment2.model.Employee;


public class FileWriter {
	final static Logger log=Logger.getLogger(FileWriter.class);
	/*
	 * excelFileWriter Method Display Employee Details  in excel File
	 * 
	 */
	public void excelFileWriter(ArrayList<Employee> employeList ,String excelFilePath) {
		@SuppressWarnings("resource")
		HSSFWorkbook workBook=new HSSFWorkbook();                             //creating workbook 
		HSSFSheet sheet=workBook.createSheet("employeeSheet");                //create sheet for that workbook
<<<<<<< Updated upstream
		int rowCount=0;                                                      
		Row header = sheet.createRow(rowCount++);
		header.createCell(0).setCellValue("firstName");
		header.createCell(1).setCellValue("lastName");
		header.createCell(2).setCellValue("city");
		header.createCell(3).setCellValue("education");
		header.createCell(4).setCellValue("job");

		for (Employee employee : employeList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			row.createCell(columnCount++).setCellValue(employee.getFirstName());
			row.createCell(columnCount++).setCellValue(employee.getLastName());
			row.createCell(columnCount++).setCellValue(employee.getCity());

			String getEducation ="";
			int getCount=0;
			for(String education:employee.getEducation())

			{
				if(getCount==employee.getEducation().size()-1) {
					getEducation+=education;

				}
				else {
					getEducation+=education+",";
				}
				getCount++;
			}
			row.createCell(columnCount++).setCellValue(getEducation);
			row.createCell(columnCount++).setCellValue(employee.getJob());
		}

		try {
			File file=new File(excelFilePath);                                         //taking file path
			FileOutputStream outputStream=new FileOutputStream(file);
			workBook.write(outputStream);                                    //write  all the information in the excelfile


			System.out.println("SusccesFully");
			outputStream.close();

=======
        
		Row row=sheet.createRow(0);                                           //create first row for sheet for ColumnHeading of data
		for(int i=0;i<5;i++) {                                               
			Cell cell=row.createCell(i);                                      //creates cells for that first row 
			switch(i) {
			case 0: cell.setCellValue("firstName");
			break;
			case 1: cell.setCellValue("lastname");
			break;
			case 2:  cell.setCellValue("city");
			break;
			case 3:  cell.setCellValue("education");
			break;
			case 4: cell.setCellValue("job");
			break;


			}

		}
		
		int count=1;                                    
		for(Employee employee:employeList) {                              //iterating employeeList 

			row=sheet.createRow(count);                                  //creating row  storing the data into into the excel sheet
			for(int j=0;j<5;j++) {
			Cell cell=row.createCell(j);                                 //for each row creates cells and storing the employee info
				switch(j) {
				case 0: cell.setCellValue(employee.getFirstName());
				break;
				case 1: cell.setCellValue(employee.getLastName());
				break;
				case 2:  cell.setCellValue(employee.getCity());
				break;
				case 3:  
					String getEducation ="";
					int getCount=0;
					for(String education:employee.getEducation())

					{
						if(getCount==employee.getEducation().size()-1) {
							getEducation+=education;
						}
						else {
							getEducation+=education+",";
						}
						getCount++;
					}
					cell.setCellValue(getEducation);
					break;
					
					
				
				case 4: 
					cell.setCellValue(employee.getJob());
					break;
				}

			}
			count++;

		}

		try {
			File file=new File(excelFilePath);                                         //taking file path
			FileOutputStream outputStream=new FileOutputStream(file);
			workBook.write(outputStream);                                     //stored the all the information in that file


			System.out.println("SusccesFully");
>>>>>>> Stashed changes
		}
		catch (FileNotFoundException fileNotFoundException) {
			log.error("File Not Found");
		}catch(IOException ioException) {
			log.error("Cannot Read File Properly");
		}
	}
}










<<<<<<< Updated upstream







=======
>>>>>>> Stashed changes
