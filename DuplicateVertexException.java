/**
 * This exception is thrown to indicate that there is a duplicate vertex exception.
 */
public class DuplicateVertexException extends RuntimeException
{
    /**
     * Constructs a DuplicateVertexException with no detail message.
     */
    public DuplicateVertexException()
    {
        super();
    }

    /**
     * Constructs a DuplicateVertexException with the specified detail message.
     * @param msg msg is the detailed message.
     */
    public DuplicateVertexException(String msg)
    {
        super(msg);
    }
}
