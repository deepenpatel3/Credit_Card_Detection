

import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public class Client {

	public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException, TransformerFactoryConfigurationError, TransformerException {
		
		//Concrete Handlers 
		Handler visaHandler = new VisaHandler();
		Handler mastercardHandler = new MasterCardHandler();
		Handler discoverHandler = new DiscoverHandler();
		Handler americanExpressHandler = new AmericanExpressHandler();
		
		//setting the successor
		visaHandler.setSuccessor(mastercardHandler);
		mastercardHandler.setSuccessor(discoverHandler);
		discoverHandler.setSuccessor(americanExpressHandler);
		
		System.out.println("\nEnter the path of your directory where input files are present.\n");
		Scanner input = new Scanner(System.in); 
		
		String directoryPath = input.nextLine(); 
		
		System.out.println("Enter Input File name(with proper extension: .csv, .json or .xml): ");
		String inputFileName = input.nextLine(); 
		
	    System.out.println("Enter output File name(with proper extension: .csv, .json or .xml): ");
		String outputFileName = input.nextLine();
		
		//Based on which file name is input, appropriate strategy is applied 
		if(inputFileName.contains(".csv") || inputFileName.contains(".CSV")) {
			CSV csv= new CSV();
			csv.setDirectoryPath(directoryPath + '/');
			csv.readCSV(inputFileName, outputFileName, visaHandler);		
		}
		else if(inputFileName.contains(".json")) {
			Json json = new Json();
			json.setDirectoryPath(directoryPath  + '/');
			json.readJson(inputFileName, outputFileName, visaHandler);	
		}
		else if(inputFileName.contains(".xml")){
			XML xml = new XML();
			xml.setDirectoryPath(directoryPath + '/');
			xml.readXml(inputFileName, outputFileName, visaHandler);
		}
		else {
			System.out.println("Wrong extension entered");
		}
		input.close();
	}
}
