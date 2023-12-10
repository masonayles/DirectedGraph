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
        vertexMap = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph with the specified label.
     *
     * @param v the label of the vertex to add
     * @throws DuplicateVertexException if a vertex with the same label already exists
     */
    @Override
    public void add(V v) {
        if (v == null) {
            throw new IllegalArgumentException("Vertex label cannot be null.");
        }
        if (adjacencyList.containsKey(v)) {
            throw new DuplicateVertexException("Vertex with label '" + v + "' already exists.");
        }
        adjacencyList.put(v, new LinkedList<>());
        vertexMap.put(v, new Vertex<>(v));
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
    public Vertex<V> get(V v)
    {
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
     * @param v the label of the vertex to remove
     * @return
     */
    @Override
    public V remove(V v)
    {
        if (!adjacencyList.containsKey(v))
        {
            throw new NoSuchVertexException();
        }

        // Remove all edges connected to the vertex v
        adjacencyList.remove(v);
        vertexMap.remove(v);

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
     * @param from  the label of the source vertex
     * @param to    the label of the destination vertex
     * @param label the label of the edge
     */
    @Override
    public void addEdge(V from, V to, E label) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            throw new NoSuchVertexException();
        }

        Vertex<V> fromVertex = vertexMap.get(from);
        Vertex<V> toVertex = vertexMap.get(to);
        Edge<V, E> newEdge = new Edge<>(fromVertex, toVertex, label);

        // Check for duplicate edge
        if (adjacencyList.get(from).contains(newEdge)) {
            throw new DuplicateEdgeException();
        }

        adjacencyList.get(from).add(newEdge);
    }



    /**
     * @param from the label of the source vertex
     * @param to   the label of the destination vertex
     * @return
     */
    @Override
    public boolean containsEdge(V from, V to)
    {
        if (!adjacencyList.containsKey(from))
        {
            return false;
        }

        for (Edge<V, E> edge : adjacencyList.get(from))
        {
            if (edge.getDestination().equals(new Vertex<>(to)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @param from the label of the source vertex
     * @param to   the label of the destination vertex
     * @return
     */
    @Override
    public Edge<V, E> getEdge(V from, V to)
    {
        if (!adjacencyList.containsKey(from))
        {
            throw new NoSuchVertexException();
        }

        for (Edge<V, E> edge : adjacencyList.get(from))
        {
            if (edge.getDestination().getLabel().equals(to))
            {
                return edge;
            }
        }
        throw new NoSuchEdgeException();
    }

    /**
     * @param from the label of the source vertex
     * @param to   the label of the destination vertex
     * @return
     */
    @Override
    public E removeEdge(V from, V to)
    {
        if (!adjacencyList.containsKey(from))
        {
            throw new NoSuchVertexException();
        }

        Iterator<Edge<V, E>> iterator = adjacencyList.get(from).iterator();
        while (iterator.hasNext())
        {
            Edge<V, E> edge = iterator.next();
            if (edge.getDestination().getLabel().equals(to))
            {
                iterator.remove();
                return edge.getLabel();
            }
        }
        throw new NoSuchEdgeException();
    }

    /**
     * @param v the label of the vertex whose degree is to be calculated
     * @return
     */
    @Override
    public int degree(V v)
    {
        if (!adjacencyList.containsKey(v))
        {
            throw new NoSuchVertexException();
        }
        return adjacencyList.get(v).size();
    }

    /**
     * @return
     */
    @Override
    public Iterator<Vertex<V>> vertices()
    {
        // Create a list to store the vertices
        List<Vertex<V>> vertices = new ArrayList<>();

        // Populate the list with vertices from the vertexMap
        for (V label : vertexMap.keySet())
        {
            vertices.add(vertexMap.get(label));
        }
        return vertices.iterator();
    }

    @Override
    public Iterator<Edge<V, E>> edges()
    {
        List<Edge<V, E>> allEdges = new ArrayList<>();
        for (List<Edge<V, E>> edges : adjacencyList.values())
        {
            allEdges.addAll(edges);
        }
        return allEdges.iterator();
    }

    /**
     * @param v the label of the vertex whose adjacent vertices are to be iterated over
     * @return
     */
    @Override
    public Iterator<Vertex<V>> adjacent(V v)
    {
        if (!adjacencyList.containsKey(v))
        {
            throw new NoSuchVertexException();
        }

        List<Vertex<V>> adjacentVertices = new ArrayList<>();
        for (Edge<V, E> edge : adjacencyList.get(v))
        {
            adjacentVertices.add(edge.getDestination());
        }
        return adjacentVertices.iterator();
    }

    /**
     * @return
     */
    @Override
    public int size()
    {
        return adjacencyList.size();
    }

    /**
     * @return
     */
    @Override
    public boolean isEmpty()
    {
        return adjacencyList.isEmpty();
    }

    /**
     *
     */
    @Override
    public void clear()
    {
        adjacencyList.clear();
        vertexMap.clear();
    }

    /**
     *
     * @return
     */
    @Override
    public int edgeCount()
    {
        int count = 0;
        for (List<Edge<V, E>> edges : adjacencyList.values())
        {
            count += edges.size();
        }
        return count;
    }
}


