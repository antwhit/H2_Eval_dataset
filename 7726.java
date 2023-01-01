import javax.mail.*;
import javax.mail.internet.*;

public class UkrpostUser
{
	private InternetAddress address = null;
	private Store store = null;
	private Session session = null;
	
	public UkrpostUser()
	{
		session = Session.getDefaultInstance(System.getProperties(), null);
		session.setDebug(true);
	}
	
	public UkrpostUser(String addr)
		throws Exception
	{
		UkrpostUser();
		setAddress(addr);
		//store = session.getStore(new URLName("maildir:///testhome/Maildir/"));
		String url = "maildir:////iplanet/qmailstore/" + address.getAddress()
	}

	public void setAddress(String addr)
		throws Exception
	{
		address = new InternetAddress(addr);
	}

	public String getAddress()
	{
		return address.getAddress();
	}

	public void setStore(String url)
	{
	}

	public Store getStore()
	{
		return store;
	}
}
