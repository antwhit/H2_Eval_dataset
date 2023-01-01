import java.rmi.RemoteException;
import fi.hip.gb.data.EventData;
import fi.hip.gb.data.TextualEventData;
import fi.hip.gb.mobile.Combiner;
import fi.hip.gb.mobile.DefaultCombiner;
import fi.hip.gb.mobile.DefaultProcessor;
import fi.hip.gb.mobile.Job;
import fi.hip.gb.mobile.Observer;
import fi.hip.gb.mobile.Processor;

/**
 * Simple example of how to write agents. This example only returns "Hello
 * world from $HOST with parameters $PARAMETER" text.
 * <p>
 * <b>Description of flags:</b><br>
 * not used
 * <p>
 * <b>Description of parameters:</b><br>
 * Can be just anything
 * 
 * @author Juho Karppinen
 * @version $Id: HelloWorld.java 165 2005-01-31 16:03:10Z jkarppin $
 */
public class HelloWorld extends DefaultProcessor implements Job {

    public HelloWorld() {
    }

    /**
	 * @see fi.hip.gb.mobile.Processor#processEvent(fi.hip.gb.data.EventData)
	 */
    public void processEvent(EventData data) throws RemoteException {
        this.results.insertResult("hello", "World simplest example", "Hello world on " + this.api.getHostname() + "\n with parameters " + ((TextualEventData) data).getData());
    }

    public Processor getProcessor() {
        return this;
    }

    public Combiner getCombiner() {
        return new DefaultCombiner(this.api);
    }

    public Observer getObserver() {
        return new TextObserver(this.api);
    }
}
