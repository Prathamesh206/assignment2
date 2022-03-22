package in.sts.assignment2.main;


import in.sts.assignment2.input.FileInput;
import in.sts.assignment2.output.ConsoleOutput;
import in.sts.assignment2.reader.FileReader;
import in.sts.assignment2.services.FileWriter;
import in.sts.assignment2.services.FilterJob;

public class Main {
	public static void main(String[] args) {
		
		ConsoleOutput consoleOutput=new ConsoleOutput();
		FileReader fileReader=new FileReader();
		FileInput fileInput=new FileInput();
		FilterJob filterJob=new FilterJob();
		FileWriter fileWriter=new FileWriter();
		
		
	  fileWriter.excelFileWriter(fileReader.getEmployeeList(fileInput.getJsonFilePath()),fileInput.getExcelFilePath());
		
		
		consoleOutput.displayFilterJobList(filterJob.getFilterJobList(fileInput.getJob()));
		
	}
	
	
	

	
	

}
