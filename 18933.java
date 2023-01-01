/**
 * SAMZIndexRWFactory.java
 * 
 * This is an implementation of the CorpusIndexRWFactory interface.
 * 
 * @author Zachary M. Allen
 */
public class SAMZIndexRWFactory implements CorpusIndexRWFactory {

    /**
	 * Returns a SAMZIndexReader
	 * @return a new SAMZIndexReader
	 */
    public CorpusIndexReader getReader() {
        return new SAMZIndexReader(this);
    }

    /**
	 * Returns a SAMZIndexWriter
	 * @return a new SAMNIndexWriter
	 */
    public CorpusIndexWriter getWriter() {
        return new SAMZIndexWriter();
    }
}
