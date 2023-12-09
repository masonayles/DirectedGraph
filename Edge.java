/**
 * Class Edge represents a directed edge in a graph. It holds references to the source and destination
 * vertices and has a label of generic type.
 *
 * @param <V> The type of the vertex labels
 * @param <E> The type of the edge label
 */
public class Edge<V, E>
{
    private Vertex<V> _source;
    private Vertex<V> _destination;
    private E _label;

    /**
     * Constructs a new Edge with the specified source vertex, destination vertex, and label.
     *
     * @param source The source vertex of the edge
     * @param destination The destination vertex of the edge
     * @param label The label associated with the edge
     */
    public Edge(Vertex<V> source, Vertex<V> destination, E label)
    {
        if (source == null)
        {
            throw new IllegalArgumentException();
        }

        if (destination == null)
        {
            throw new IllegalArgumentException();
        }

        if (label == null)
        {
            throw new IllegalArgumentException();
        }
        this._source = source;
        this._destination = destination;
        this._label = label;
    }

    /**
     * Retrieves the source vertex of this edge.
     *
     * @return The source vertex of this edge
     */
    public Vertex<V> getU()
    {
        return _source;
    }

    /**
     * Retrieves the destination vertex of this edge.
     *
     * @return The destination vertex of this edge
     */
    public Vertex<V> getV()
    {
        return _destination;
    }

    /**
     * Retrieves the label of this edge.
     *
     * @return The label of the edge.
     */
    public E getLabel()
    {
        return _label;
    }

    /**
     * Sets the label of this edge.
     *
     * @param label The new label for the edge.
     */
    public void setLabel(E label)
    {
        this._label = label;
    }

    /**
     * Compares this edge with another edge for equality. Two edges are considered equal if their
     * source vertices, destination vertices, and labels are equal.
     *
     * @param o The other edge to compare to.
     * @return true if this edge is equal to the specified edge; false otherwise.
     */
    public boolean equals(Edge<V,E> o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null)
        {
            return false;
        }
        if (!_source.equals(o._source))
        {
            return false;
        }
        if (!_destination.equals(o._destination))
        {
            return false;
        }
        if (_label == null)
        {
            return o._label == null;
        }
        else
        {
            return _label.equals(o._label);
        }
    }

    /**
     * Calculates the hash code for this edge. The hash code is computed based on the hash codes of the
     * source and destination vertices, as well as the label of the edge. This method ensures that
     * edges that are equal according to the {@link #equals(Object)} method also have the same hash code.
     *
     * @return the hash code value for this edge.
     */

    //TODO MIGHT HAVE TO DELETE AFTER RUNNING TESTS.
    @Override
    public int hashCode() {
        // The numbers 31 and 17 are arbitrary primes that result in a well-distributed range of hash codes
        // for collections of edges. The choice of these specific numbers is traditional to improve the
        // performance of hash tables.
        int result = 17;
        result = 31 * result + (_source != null ? _source.hashCode() : 0);
        result = 31 * result + (_destination != null ? _destination.hashCode() : 0);
        result = 31 * result + (_label != null ? _label.hashCode() : 0);
        return result;
    }
}
