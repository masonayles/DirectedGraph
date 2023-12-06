import java.util.NoSuchElementException;

/**
 * This exception is thrown to indicate that the requested edge is not present in the graph.
 */
public class NoSuchEdgeException extends NoSuchElementException
{
    /**
     * Constructs a NoSuchEdgeException with no detail message.
     */
    public NoSuchEdgeException()
    {
        super();
    }

    /**
     * Constructs a NoSuchEdgeException with the specified detail message.
     *
     * @param msg msg is the detailed message.
     */
    public NoSuchEdgeException(String msg)
    {
        super(msg);
    }
}
