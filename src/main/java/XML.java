
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML {	
	
	public String directoryPath;
	
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public void readXml(String inputFileName, String outputFileName , Handler firstHandler) throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		
		String inputFileURL= this.directoryPath.concat(inputFileName);
		String outputFileURL=this.directoryPath.concat(outputFileName);
		
		//Created DocumenetBuilder object to read from and write to XML file
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.parse(inputFileURL);
		
		//created outputDom to build output xml file
		Document outputDom = builder.newDocument();
		Element root = outputDom.createElement("root");
		outputDom.appendChild(root);
		
		NodeList list = doc.getElementsByTagName("row");
		
		for(int i=0; i < list.getLength(); i++) {
			
			Node n = list.item(i);
			Element e = (Element) n;
			String card = e.getElementsByTagName("CardNumber").item(0).getTextContent();
			
			//Invoked first concrete handler
			String result = firstHandler.handleRequest(card);
			System.out.println("\n"+ card + " is a "+ result);
			
			//created elements to append to outputDom
			Element row = outputDom.createElement("row");
			Element CardNumber = outputDom.createElement("CardNumber"); CardNumber.setTextContent(card);
			Element CardType = outputDom.createElement("CardType"); CardType.setTextContent(result);
			Element Error = outputDom.createElement("Error");
			if(result == "Invalid") {
				 Error.setTextContent("InvalidCardNumber");
			}
			else {
				 Error.setTextContent("None");
			}
			row.appendChild(CardNumber);
			row.appendChild(CardType);
			row.appendChild(Error);
			root.appendChild(row);
		}
		
		//writing outputDom to XML file
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(new DOMSource(outputDom), new StreamResult(new File(outputFileURL)));
	}
	
}
