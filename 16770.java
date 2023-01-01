public class Handler{

    when Event1 do doEvent1;
    
	public Handler(){
		register(this);
	}

	public void doEvent1(Event1 eventBody){
		invoke(eventBody);
	}

	public void doAnnounce(){
	    int count = 0;
		announce Event1(3){
			//i += 3;
			System.out.println(count);
		}
	}

	public static void main(String[] args){
		Handler h = new Handler();
		h.doAnnounce();
		h.doAnnounce();
	}
}
