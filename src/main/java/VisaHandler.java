
public class VisaHandler implements Handler {

	private Handler successor = null;
	

	public String handleRequest(String request) {
		
		if(request.charAt(0) == '4' && (request.length() == 13 || request.length() == 16)){
			
			if(request.matches("-?\\d+"))
				return "Visa";
			else
				
				return "Invalid";
			
		}else {

			return successor.handleRequest(request);
		}
	}

	public void setSuccessor(Handler next) {

		this.successor = next;

	}

}
