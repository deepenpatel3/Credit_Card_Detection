import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JUnitTest {

	public Handler initialize() {
		
		//Concrete Handlers 
		Handler visaHandler = new VisaHandler();
		Handler mastercardHandler = new MasterCardHandler();
		Handler discoverHandler = new DiscoverHandler();
		Handler americanExpressHandler = new AmericanExpressHandler();
		
		//setting the successor
		visaHandler.setSuccessor(mastercardHandler);
		mastercardHandler.setSuccessor(discoverHandler);
		discoverHandler.setSuccessor(americanExpressHandler);
		
		//returning the first Concrete Handler
		return visaHandler;
	}
	
	@Test
	public void testJson() throws IOException, ParseException{
				
		Handler firstHandler = initialize();
		Json json = new Json();
		
		//called the first concrete handler
		json.setDirectoryPath("/Users/deepenpatel3/Desktop/202Gopinath/Individual_Project/");
		json.readJson("Sample.json","OUTPUT.json", firstHandler);
		
		//Reading the output file to compare with expected result
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader("/Users/deepenpatel3/Desktop/202Gopinath/Individual_Project/OUTPUT.json");
		Object obj = jsonParser.parse(reader);
		
		//constructing an arrayList of cardTypes to compare with the expected cardTypes
		JSONArray cardList = (JSONArray) obj;
		ArrayList<String> outputList = new ArrayList<String>();
		
		for (Object card: cardList) {
			String oneCard=((JSONObject) card).get("CardType").toString();
			outputList.add(oneCard);
		}
		//Comparing
		assertEquals(new ArrayList<String>( Arrays.asList("MasterCard", "Visa", "AmericanExpress","Invalid") ), outputList);
	}
	
	@Test
	public void testCSV() throws IOException, ParseException{
				
		Handler firstHandler = initialize();
		CSV csv = new CSV();
		
		//called the first concrete handler
		csv.setDirectoryPath("/Users/deepenpatel3/Desktop/202Gopinath/Individual_Project/");
		csv.readCSV("Sample.csv","OUTPUT.csv", firstHandler);
		
		//Reading the output file to compare with expected result
		@SuppressWarnings("resource")
		BufferedReader csvReader = new BufferedReader(new FileReader("/Users/deepenpatel3/Desktop/202Gopinath/Individual_Project/OUTPUT.csv"));
		
		//constructing an arrayList of cardTypes to compare with the expected cardTypes
		ArrayList<String> outputList = new ArrayList<String>();
		
		String row;
		for (int i = 0; (row = csvReader.readLine()) != null; i++) {
			if (i != 0) {
				String[] data = row.split(",");
				String str = data[1];
				outputList.add(str);
			}
		}
		//Comparing
		assertEquals(new ArrayList<String>( Arrays.asList("MasterCard", "Visa", "AmericanExpress","Invalid") ), outputList);
	}
	
	@Test
	public void testXML() throws SAXException, TransformerFactoryConfigurationError, TransformerException, ParserConfigurationException, IOException{
				
		Handler firstHandler = initialize();
		XML xml = new XML();
		
		//called the first concrete handler
		xml.setDirectoryPath("/Users/deepenpatel3/Desktop/202Gopinath/Individual_Project/");
		xml.readXml("Sample.xml","OUTPUT.xml", firstHandler);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		//Reading the output file to compare with expected result
		Document doc = builder.parse("/Users/deepenpatel3/Desktop/202Gopinath/Individual_Project/OUTPUT.xml");
		NodeList list = doc.getElementsByTagName("row");
		
		//constructing an arrayList of cardTypes to compare with the expected cardTypes
		ArrayList<String> outputList = new ArrayList<String>();
		
		for(int i=0; i < list.getLength(); i++) {
			Node n = list.item(i);
			Element e = (Element) n;
			String card = e.getElementsByTagName("CardType").item(0).getTextContent();
			outputList.add(card);
		}
		
		//Comparing
		assertEquals(new ArrayList<String>( Arrays.asList("MasterCard", "Visa", "AmericanExpress","Invalid") ), outputList);

	}

}
