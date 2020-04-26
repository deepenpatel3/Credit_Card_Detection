
public interface Handler {
	
	public String handleRequest(String request);
	
	public void setSuccessor(Handler next);
}
