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
     *
     * @return
     */
    public E getLabel()
    {
        return _label;
    }

    /**
     *
     * @param label
     */
    public void setLabel(E label)
    {

    }

    /**
     *
     * @param o
     * @return
     */
    public boolean equals(Edge<V,E> o)
    {

    }
}
