

public class DiscoverHandler implements Handler{

	private Handler successor = null;


	public String handleRequest(String request) {
		
		if(request.indexOf("6011") == 0 && request.length() == 16) {
			if(request.matches("-?\\d+"))
				return "Discover";
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
