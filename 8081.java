public class EventClient{
	public void doAnnounce(){
		String[] args = new String[10];
		announce Event1(args){
			
		}
	}
	
	public void handler(Event1 eventBody){
		invoke(eventBody);
	}
	
	when Event1 do handler;
}