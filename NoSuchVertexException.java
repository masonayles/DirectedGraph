import java.util.NoSuchElementException;

/**
 * This exception is throwns to indicate that the there is no such vertex.
 */
public class NoSuchVertexException extends NoSuchElementException
{
    /**
     * Constructs a NoSuchVertexException with no detail message.
      */
    public NoSuchVertexException()
    {
        super();
    }

    /**
     * Constructs a NoSuchVertexException with specified detail message.
     * @param msg msg is the detailed message.
     */
    public NoSuchVertexException(String msg)
    {
        super(msg);
    }
}
