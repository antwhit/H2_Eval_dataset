/**
 * An <code>Exchange</code> is an implementation of a queue that can 
 * be used to enqueue socket channels and dequeue readable channels. 
 *
 * @author Niall Gallagher
 */
public class PipelineHandler {

    /**
    *
    */
    public void process(Pipeline pipe) throws InterruptedException;

    /**
    *
    */
    public Pipeline ready() throws InterruptedException;
}
