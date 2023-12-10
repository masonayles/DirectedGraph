import java.util.*;

/**
 * A concrete implementation of DirectedGraph using adjacency lists to represent
 * the graph structure.
 *
 * @param <V> the type of the labels for vertices
 * @param <E> the type of the labels for edges
 */
public class ListGraph<V,E> extends DirectedGraph<V, E>
{
    private Map<V, List<Edge<V, E>>> adjacencyList;
    /**
     *
     */
    public ListGraph()
    {
        adjacencyList = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph with the specified label.
     *
     * @param v the label of the vertex to add
     * @throws DuplicateVertexException if a vertex with the same label already exists
     */
    @Override
    public void add(V v)
    {
        if (v == null)
        {
            throw new IllegalArgumentException("Vertex label cannot be null.");
        }
        if (adjacencyList.containsKey(v))
        {
            throw new DuplicateVertexException("Vertex with label '" + v + "' already exists.");
        }
        adjacencyList.put(v, new LinkedList<Edge<V, E>>());
    }

    /**
     *
     * @param v the label of the vertex to check
     * @return
     */
    @Override
    public boolean contains(V v)
    {

    }

    /**
     *
     * @param v the label of the vertex to retrieve
     * @return
     */
    @Override
    public Vertex<V> get(V v)
    {

    }

    /**
     *
     * @param v the label of the vertex to remove
     * @return
     */
    @Override
    public V remove(V v)
    {

    }

    /**
     *
     * @param from the label of the source vertex
     * @param to the label of the destination vertex
     * @param label the label of the edge
     */
    @Override
    public void addEdge(V from, V to, E label)
    {

    }

    /**
     *
     * @param from the label of the source vertex
     * @param to the label of the destination vertex
     * @return
     */
    @Override
    public boolean containsEdge(V from, V to)
    {

    }

    /**
     *
     * @param from the label of the source vertex
     * @param to the label of the destination vertex
     * @return
     */
    @Override
    public Edge<V, E> getEdge(V from, V to)
    {

    }

    /**
     *
     * @param from the label of the source vertex
     * @param to the label of the destination vertex
     * @return
     */
    @Override
    public E removeEdge(V from, V to)
    {

    }

    /**
     *
     * @param v the label of the vertex whose degree is to be calculated
     * @return
     */
    @Override
    public int degree(V v)
    {

    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<Vertex<V>> vertices()
    {

    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<Edge<V, E>> edges()
    {

    }

    /**
     *
     * @param v the label of the vertex whose adjacent vertices are to be iterated over
     * @return
     */
    @Override
    public Iterator<Vertex<V>> adjacent(V v)
    {

    }

    /**
     *
     * @return
     */
    @Override
    public int size()
    {

    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty()
    {

    }

    /**
     *
     */
    @Override
    public void clear()
    {

    }

    /**
     *
     * @return
     */
    @Override
    public int edgeCount()
    {

    }
}
