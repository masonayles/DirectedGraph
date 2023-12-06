/**
 * This exception is throwns to indicate that there is a duplicate edge.
 */
public class DuplicateEdgeException extends RuntimeException
{
    /**
     * Constructs a DuplicateEdgeException with no detail message.
     */
    public DuplicateEdgeException()
    {
        super();
    }

    /**
     * Constructs a DuplicateEdgeException with specified detail message.
     * @param msg msg is the detailed message.
     */
    public DuplicateEdgeException(String msg)
    {
        super(msg);
    }
}
