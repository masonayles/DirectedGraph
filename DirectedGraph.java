/**
 * Abstract class representing a directed graph with vertices and edges.
 *
 * @param <V> the type of the labels for vertices
 * @param <E> the type of the labels for edges
 */
public abstract class DirectedGraph<V, E>
{
    /**
     * Adds a vertex to the graph.
     *
     * @param v the label of the vertex to add
     * @throws DuplicateVertexException if a vertex with the same label already exists
     */
    public abstract void add(V v);

    /**
     * Checks whether a vertex exists in the graph.
     *
     * @param v the label of the vertex to check
     * @return true if the vertex exists, false otherwise
     */
    public abstract boolean contains(V v);

    /**
     * Retrieves a vertex from the graph.
     *
     * @param v the label of the vertex to retrieve
     * @return the vertex with the specified label
     * @throws NoSuchVertexException if the vertex does not exist
     */
    public abstract Vertex<V> get(V v) throws NoSuchVertexException;

    /**
     * Removes a vertex from the graph.
     *
     * @param v the label of the vertex to remove
     * @return the removed vertex
     * @throws NoSuchVertexException if the vertex does not exist
     */
    public abstract V remove(V v) throws NoSuchVertexException;

    /**
     * Adds an edge to the graph.
     *
     * @param u the label of the source vertex
     * @param v the label of the destination vertex
     * @param label the label of the edge
     * @throws DuplicateEdgeException if an edge between the given vertices already exists
     * @throws NoSuchVertexException if the source or destination vertex does not exist
     */
    public abstract void addEdge(V u, V v, E label) throws DuplicateEdgeException, NoSuchVertexException;

    /**
     * Checks whether an edge exists in the graph.
     *
     * @param u the label of the source vertex
     * @param v the label of the destination vertex
     * @return true if the edge exists, false otherwise
     */
    public abstract boolean containsEdge(V u, V v);

    /**
     * Retrieves an edge from the graph.
     *
     * @param u the label of the source vertex
     * @param v the label of the destination vertex
     * @return the edge with the specified vertices
     * @throws NoSuchVertexException if the source or destination vertex does not exist
     * @throws NoSuchEdgeException if the edge does not exist
     */
    public abstract Edge<V,E> getEdge(V u, V v) throws NoSuchVertexException, NoSuchEdgeException;

    /**
     * Removes an edge from the graph.
     *
     * @param u the label of the source vertex
     * @param v the label of the destination vertex
     * @return the removed edge
     * @throws NoSuchVertexException if the source or destination vertex does not exist
     * @throws NoSuchEdgeException if the edge does not exist
     */
    public abstract E removeEdge(V u, V v) throws NoSuchVertexException, NoSuchEdgeException;

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the total number of vertices in the graph
     */
    public abstract int size();

    /**
     * Returns the degree of the specified vertex. The degree is the number of
     * outgoing edges from the vertex.
     *
     * @param v the label of the vertex whose degree is to be calculated
     * @return the degree of the vertex
     * @throws NoSuchVertexException if the vertex with the specified label does not exist
     */
    public abstract int degree(V v) throws NoSuchVertexException;

    /**
     * Returns the total number of edges in the graph.
     *
     * @return the total number of edges in the graph
     */
    public abstract int edgeCount();

    /**
     * Provides an iterator over all the vertices in the graph.
     *
     * @return an iterator over all vertices in the graph
     */
    public abstract Iterator<Vertex<V>> vertices();

    /**
     * Provides an iterator over all vertices adjacent to the specified vertex.
     * A vertex 'u' is adjacent to vertex 'v' if there is a directed edge from 'v' to 'u'.
     *
     * @param v the label of the vertex whose adjacent vertices are to be iterated over
     * @return an iterator over all vertices adjacent to the specified vertex
     * @throws NoSuchVertexException if the vertex with the specified label does not exist
     */
    public abstract Iterator<Vertex<V>> adjacent(V v) throws NoSuchVertexException;

    /**
     * Provides an iterator over all the edges in the graph.
     *
     * @return an iterator over all edges in the graph
     */
    public abstract Iterator<Edge<V, E>> edges();

    /**
     * Removes all vertices and edges from the graph, effectively clearing it.
     */
    public abstract void clear();

    /**
     * Checks whether the graph is empty. A graph is considered empty if it has no vertices.
     *
     * @return true if the graph contains no vertices, otherwise false
     */
    public abstract boolean isEmpty();
}

