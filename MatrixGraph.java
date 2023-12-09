import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  MatrixGraph is a concrete implementation of DirectedGraph.
 * @param <V>
 * @param <E>
 */
public class MatrixGraph<V,E> extends DirectedGraph<V,E>
{
    private List<Vertex<V>> _vertices;
    private E[][] _adjacencyMatrix;
    private Map<Vertex<V>, Integer> _vertexIndices;

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
     *
     * @param vertex
     */
    public void add(Vertex<V> vertex)
    {
        if (vertex == null || _vertexIndices.containsKey(vertex.getLabel()))
        {
            throw new DuplicateVertexException("Vertex already exists.");
        }
        if (_vertices.size() == _adjacencyMatrix.length)
        {
            grow();
        }
        _vertices.add(vertex);
        _vertexIndices.put(vertex.getLabel(), _vertices.size() - 1);
    }

    public void addEdge(Vertex<V> from, Vertex<V> to, E label)
    {
        Integer fromIndex = _vertexIndices.get(from.getLabel());
        Integer toIndex = _vertexIndices.get(to.getLabel());

        if (fromIndex == null || toIndex == null)
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
     * @param vertex
     * @return
     */
    public boolean contains(Vertex<V> vertex)
    {
        return vertex != null && _vertexIndices.containsKey(vertex.getLabel());
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    public boolean containsEdge(Vertex<V> from, Vertex<V> to)
    {
        Integer fromIndex = _vertexIndices.get(from.getLabel());
        Integer toIndex = _vertexIndices.get(to.getLabel());

        if (fromIndex == null || toIndex == null)
        {
            return false;
        }

        return _adjacencyMatrix[fromIndex][toIndex] != null;
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
}
