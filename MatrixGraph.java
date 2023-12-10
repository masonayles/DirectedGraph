import java.util.*;

/**
 *  MatrixGraph is a concrete implementation of DirectedGraph.
 * @param <V>
 * @param <E>
 */
public class MatrixGraph<V,E> extends DirectedGraph<V,E>
{
    private List<Vertex<V>> _vertices;
    private E[][] _adjacencyMatrix;
    private Map<V, Integer> _vertexIndices;

    /**
     * Constructs a new MatrixGraph.
     */
    public MatrixGraph()
    {
        _vertices = new ArrayList<>();
        _vertexIndices = new HashMap<>();
        _adjacencyMatrix = (E[][]) new Object[10][10];
    }

    /**
     * Adds a vertax at the given label.
     * @param v v is the vertex label.
     */
    @Override
    public void add(V v)
    {
        if (v == null || _vertexIndices.containsKey(v))
        {
            throw new DuplicateVertexException();
        }
        Vertex<V> vertex = new Vertex<>(v);
        _vertices.add(vertex);
        _vertexIndices.put(v, _vertices.size() - 1);

        if (_vertices.size() > _adjacencyMatrix.length)
        {
            grow();
        }
    }

    /**
     *
     * @param v v is the label of the vertex to check.
     * @return
     */
    @Override
    public boolean contains(V v)
    {
        return _vertexIndices.containsKey(v);
    }

    /**
     *
     * @param v the label of the vertex to retrieve
     * @return
     */
    @Override
    public Vertex<V> get(V v)
    {
        Integer index = _vertexIndices.get(v);
        if (index == null)
        {
            throw new NoSuchVertexException();
        }
        return _vertices.get(index);
    }


    /**
     *
     * @param v v is the vertex label to be removed.
     * @return
     */
    @Override
    public V remove(V v)
    {
        if (v == null || !_vertexIndices.containsKey(v))
        {
            throw new NoSuchVertexException();
        }

        int indexToRemove = _vertexIndices.get(v);
        _vertexIndices.remove(v);
        _vertices.remove(indexToRemove);

        // Shift rows and columns in the adjacency matrix
        for (int i = indexToRemove; i < _vertices.size(); i++)
        {
            _vertexIndices.put(_vertices.get(i).getLabel(), i);
        }

        // Adjust the adjacency matrix
        removeMatrixRow(indexToRemove);
        removeMatrixColumn(indexToRemove);

        // Return the label of the removed vertex
        return v;
    }


    /**
     *
     * @param from
     * @param to
     * @param label
     */
    @Override
    public void addEdge(V from, V to, E label)
    {
        Integer fromIndex = _vertexIndices.get(from);
        Integer toIndex = _vertexIndices.get(to);

        if (fromIndex == null)
        {
            throw new NoSuchVertexException();
        }
        if (toIndex == null)
        {
            throw new NoSuchVertexException();
        }
        if (_adjacencyMatrix[fromIndex][toIndex] != null)
        {
            throw new DuplicateEdgeException();
        }

        _adjacencyMatrix[fromIndex][toIndex] = label;
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    @Override
    public boolean containsEdge(V from, V to)
    {
        Integer fromIndex = _vertexIndices.get(from);
        Integer toIndex = _vertexIndices.get(to);

        if (fromIndex == null || toIndex == null)
        {
            return false;
        }

        return _adjacencyMatrix[fromIndex][toIndex] != null;
    }

    /**
     * @param u The label of the source vertex.
     * @param v The label of the destination vertex.
     * @return An Edge<V, E> object representing the edge from 'u' to 'v'.
     */
    @Override
    public Edge<V, E> getEdge(V u, V v) throws NoSuchVertexException, NoSuchEdgeException
    {
        Integer fromIndex = _vertexIndices.get(u);
        Integer toIndex = _vertexIndices.get(v);

        if (fromIndex == null) {
            throw new NoSuchVertexException("Source vertex with label '" + u + "' does not exist.");
        }
        if (toIndex == null)
        {
            throw new NoSuchVertexException("Destination vertex with label '" + v + "' does not exist.");
        }

        E edgeLabel = _adjacencyMatrix[fromIndex][toIndex];
        if (edgeLabel == null)
        {
            throw new NoSuchEdgeException("Edge from '" + u + "' to '" + v + "' does not exist.");
        }

        return new Edge<>(_vertices.get(fromIndex), _vertices.get(toIndex), edgeLabel);
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
        Integer fromIndex = _vertexIndices.get(from);
        Integer toIndex = _vertexIndices.get(to);

        if (fromIndex == null || toIndex == null)
        {
            throw new NoSuchVertexException();
        }

        E removedEdge = _adjacencyMatrix[fromIndex][toIndex];
        if (removedEdge == null)
        {
            throw new NoSuchEdgeException();
        }

        _adjacencyMatrix[fromIndex][toIndex] = null;
        return removedEdge;
    }

    /**
     *
     * @param v the label of the vertex whose degree is to be calculated
     * @return
     */
    @Override
    public int degree(V v)
    {
        Integer index = _vertexIndices.get(v);
        if (index == null)
        {
            throw new NoSuchVertexException("Vertex with label '" + v + "' not found.");
        }

        int degree = 0;
        for (E edge : _adjacencyMatrix[index])
        {
            if (edge != null)
            {
                degree++;
            }
        }
        return degree;
    }


    /**
     *
     * @return
     */
    @Override
    public Iterator<Vertex<V>> vertices()
    {
        return new ArrayList<>(_vertices).iterator();
    }

    /**
     *
     * @param v the label of the vertex whose adjacent vertices are to be iterated over
     * @return
     * @throws NoSuchVertexException
     */
    @Override
    public Iterator<Vertex<V>> adjacent(V v) throws NoSuchVertexException
    {
        List<Vertex<V>> adjacentVertices = new ArrayList<>();

        Integer index = _vertexIndices.get(v);
        if (index == null) {
            throw new NoSuchVertexException("Vertex with label '" + v + "' not found.");
        }

        for (int i = 0; i < _adjacencyMatrix[index].length; i++)
        {
            if (_adjacencyMatrix[index][i] != null) { // There is an edge from v to vertex at index i
                adjacentVertices.add(_vertices.get(i));
            }
        }

        return adjacentVertices.iterator();
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<Edge<V, E>> edges()
    {
        List<Edge<V, E>> allEdges = new ArrayList<>();

        for (int i = 0; i < _adjacencyMatrix.length; i++)
        {
            for (int j = 0; j < _adjacencyMatrix[i].length; j++)
            {
                if (_adjacencyMatrix[i][j] != null)
                {
                    Vertex<V> source = _vertices.get(i);
                    Vertex<V> destination = _vertices.get(j);
                    E label = _adjacencyMatrix[i][j];
                    allEdges.add(new Edge<>(source, destination, label));
                }
            }
        }
        return allEdges.iterator();
    }

    /**
     *
     * @return
     */
    @Override
    public int size()
    {
        return _vertices.size();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty()
    {
        return _vertices.isEmpty();
    }

    /**
     *
     */
    @Override
    public void clear()
    {
        _vertices.clear();
        _vertexIndices.clear();
        _adjacencyMatrix = (E[][]) new Object[10][10];
    }

    /**
     *
     * @param rowIndex
     */
    private void removeMatrixRow(int rowIndex)
    {
        for (int i = rowIndex; i < _vertices.size() - 1; i++)
        {
            System.arraycopy(_adjacencyMatrix[i + 1], 0, _adjacencyMatrix[i], 0, _adjacencyMatrix[i].length);
        }
        // Nullify the last row after the shift.
        Arrays.fill(_adjacencyMatrix[_vertices.size()], null);
    }

    /**
     *
     * @param columnIndex
     */
    private void removeMatrixColumn(int columnIndex)
    {
        for (int i = 0; i < _vertices.size(); i++)
        {
            System.arraycopy(_adjacencyMatrix[i], columnIndex + 1, _adjacencyMatrix[i], columnIndex, _adjacencyMatrix[i].length - columnIndex - 1);
            _adjacencyMatrix[i][_vertices.size() - 1] = null;
            // Nullify the last element in the row.
        }
    }


    /**
     * Resizes the adjacency matrix to accommodate more vertices.
     */
    private void grow()
    {
        int newSize = _adjacencyMatrix.length * 2;
        E[][] newMatrix = (E[][]) new Object[newSize][newSize];
        for (int i = 0; i < _adjacencyMatrix.length; i++)
        {
            System.arraycopy(_adjacencyMatrix[i], 0, newMatrix[i], 0, _adjacencyMatrix[i].length);
        }
        _adjacencyMatrix = newMatrix;
    }

    /**
     *
     * @return
     */
    @Override
    public int edgeCount()
    {
        int count = 0;
        for (int i = 0; i < _adjacencyMatrix.length; i++)
        {
            for (int j = 0; j < _adjacencyMatrix[i].length; j++)
            {
                if (_adjacencyMatrix[i][j] != null) {
                    count++;
                }
            }
        }
        return count;
    }
}
