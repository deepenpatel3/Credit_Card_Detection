
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {
	
	public String directoryPath;
	
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	@SuppressWarnings("unchecked")
	public void readJson(String inputFileName, String outputFileName , Handler firstHandler) throws IOException, ParseException {
			
		String inputFileURL= this.directoryPath.concat(inputFileName);
		String outputFileURL=this.directoryPath.concat(outputFileName);
		
		//Reading from json file
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(inputFileURL);
		Object obj = jsonParser.parse(reader);
		JSONArray cardList = (JSONArray) obj;
		JSONArray cardOutPutList = new JSONArray();

		for( Object card : cardList ) {
			
        	String oneCard= parseCardObject( (JSONObject) card);
        	
        	//invoking the first concrete handler in the chain of responsibility
        	String cardType = firstHandler.handleRequest(oneCard);
        	
			System.out.println("\n"+oneCard + " is a "+ cardType);

        	//adding the output to the list
        	cardOutPutList.add(writeJson(oneCard, cardType));
        }
       
		//writing the output
       @SuppressWarnings("resource")
		FileWriter jsonWriter = new FileWriter(outputFileURL);
       jsonWriter.write(cardOutPutList.toJSONString());
       jsonWriter.flush();
	}

	private String parseCardObject(JSONObject card) {
		//fetching the card number
		return card.get("CardNumber").toString();
	}

	@SuppressWarnings("unchecked")
	private JSONObject writeJson(String card, String cardType) throws IOException {
		
		//constructing a member of the list
		JSONObject cardDetails = new JSONObject();
        cardDetails.put("CardNumber",card);
        cardDetails.put("CardType",cardType);
        if(cardType == "Invalid")
        	cardDetails.put("Error", "InvalidCardNumber");
        else
        	cardDetails.put("Error", "None");
        
       return cardDetails;
        
	}
}
