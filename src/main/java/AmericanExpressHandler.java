

public class AmericanExpressHandler implements Handler{

	

	public String handleRequest(String request) {

		if( request.charAt(0) == '3' && (request.charAt(1) == '4' || request.charAt(1) == '7') && request.length() == 15) {
			if(request.matches("-?\\d+"))
				return "AmericanExpress";
			else
				return "Invalid";
		}else {
			return "Invalid";		
		}
	}

	public void setSuccessor(Handler next) {
		
	}

}
