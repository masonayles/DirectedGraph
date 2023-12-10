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
    private Map<V, Vertex<V>> vertexMap;
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
            throw new IllegalArgumentException();
        }
        if (adjacencyList.containsKey(v))
        {
            throw new DuplicateVertexException();
        }
        adjacencyList.put(v, new LinkedList<Edge<V, E>>());
    }

    /**
     * Checks whether a vertex with the specified label exists in the graph.
     *
     * @param v the label of the vertex to check
     * @return true if the vertex exists, false otherwise
     */
    @Override
    public boolean contains(V v)
    {
        return adjacencyList.containsKey(v);
    }

    /**
     * Retrieves the vertex with the specified label from the graph.
     *
     * @param v the label of the vertex to retrieve
     * @return the Vertex object with the specified label
     */
    @Override
    public Vertex<V> get(V v) {
        // Check if the vertex exists in the adjacency list, which indicates the vertex is part of the graph
        if (!adjacencyList.containsKey(v))
        {
            throw new NoSuchVertexException();
        }

        Vertex<V> vertex = vertexMap.get(v);
        if (vertex == null)
        {
            throw new IllegalStateException();
        }
        return vertex;
    }

    /**
     *
     * @param v the label of the vertex to remove
     * @return
     */
    @Override
    public V remove(V v)
    {
        if (!adjacencyList.containsKey(v))
        {
            throw new NoSuchVertexException("Vertex with label '" + v + "' does not exist.");
        }

        // Remove all edges connected to the vertex v
        adjacencyList.remove(v);

        // Also remove any edge from other vertices that lead to v
        for (V key : adjacencyList.keySet())
        {
            List<Edge<V, E>> edgesList = adjacencyList.get(key);
            List<Edge<V, E>> edgesToRemove = new ArrayList<>();
            for (Edge<V, E> edge : edgesList)
            {
                if (edge.getDestination().equals(v))
                {
                    edgesToRemove.add(edge);
                }
            }
            edgesList.removeAll(edgesToRemove);
        }
        // Remove the vertex from the vertexMap
        vertexMap.remove(v);

        return v;
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
