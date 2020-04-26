
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSV {

	public String directoryPath;	
	
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public void readCSV(String inputFileName, String outputFileName , Handler firstHandler) throws NumberFormatException, IOException {

		String inputFileURL= this.directoryPath.concat(inputFileName);
		String outputFileURL=this.directoryPath.concat(outputFileName);
		
		//Initializing reader and writer objects
		BufferedReader csvReader = new BufferedReader(new FileReader(inputFileURL));
		FileWriter csvWriter = new FileWriter(outputFileURL);
		
		//adding the headers to the output file
		csvWriter.append("CardNumber");
		csvWriter.append(",");
		csvWriter.append("CardType");
		csvWriter.append(",");
		csvWriter.append("Error");
		csvWriter.append("\n");
		
		String row;
		for (int i = 0; (row = csvReader.readLine()) != null; i++) {
			if (i != 0) {
				
				//invoking the first concrete handler
				String[] data = row.split(",");
				String str = data[0];
				String result = new String(firstHandler.handleRequest(str));
				
				System.out.println("\n" +str + " is a "+ result);

				//adding the rows in output csv file
				csvWriter.append(data[0]); 
				csvWriter.append(","); 
				csvWriter.append(result); 
				csvWriter.append(",");
				if(result.contentEquals("Invalid"))
					csvWriter.append("InvalidCardNumber");
				else
					csvWriter.append("None"); 
				csvWriter.append("\n"); 
			}
		}
		csvReader.close();
		csvWriter.flush();
		csvWriter.close();
	}
}