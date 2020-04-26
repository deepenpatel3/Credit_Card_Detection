

public class MasterCardHandler implements Handler{

	private Handler successor = null;

	
	public String handleRequest(String request) {
		
		if(request.charAt(0) == '5'&& Integer.parseInt(String.valueOf(request.charAt(1))) >= 1 && request.length() == 16) {
			
			if(request.matches("-?\\d+"))
				return "MasterCard";
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
