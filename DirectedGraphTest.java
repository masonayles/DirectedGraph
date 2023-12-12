import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

import java.util.Iterator;

public class DirectedGraphTest {

    private DirectedGraph<String, String> graph;

    @Before
    public void setUp() {
        graph = new MatrixGraph<>(); // Or ListGraph<>(), depending on the implementation
    }

    @Test
    public void testAdd_NewVertex_ShouldAddSuccessfully() {
        String vertexLabel = "NewVertex";
        graph.add(vertexLabel);
        assertTrue("The graph should contain the new vertex.", graph.contains(vertexLabel));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testAdd_DuplicateVertex_ShouldThrowDuplicateVertexException() {
        String vertexLabel = "DuplicateVertex";
        graph.add(vertexLabel);
        thrown.expect(DuplicateVertexException.class);
        graph.add(vertexLabel);
    }

    @Test
    public void testAdd_NullVertex_ShouldThrowIllegalArgumentException() {
        thrown.expect(IllegalArgumentException.class);
        graph.add(null);
    }

    @Test
    public void testContains_ExistingVertex_ShouldReturnTrue() {
        String vertexLabel = "ExistingVertex";
        graph.add(vertexLabel);
        assertTrue("The graph should contain the vertex after adding it.", graph.contains(vertexLabel));
    }

    @Test
    public void testContains_NonexistentVertex_ShouldReturnFalse() {
        String vertexLabel = "NonexistentVertex";
        assertFalse("The graph should not contain a vertex that has not been added.", graph.contains(vertexLabel));
    }

    @Test
    public void testGet_ExistingVertex_ShouldReturnCorrectVertex() {
        String vertexLabel = "ExistingVertex";
        graph.add(vertexLabel);
        Vertex<String> vertex = graph.get(vertexLabel);
        assertNotNull("The get method should return a non-null vertex.", vertex);
        assertEquals("The vertex returned should have the correct label.", vertexLabel, vertex.getLabel());
    }

    @Test
    public void testGet_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        thrown.expect(NoSuchVertexException.class);
        graph.get("NonexistentVertex");
    }

    @Test
    public void testRemove_ExistingVertex_ShouldRemoveSuccessfully() {
        String vertexLabel = "VertexToRemove";
        graph.add(vertexLabel);
        graph.remove(vertexLabel);
        assertFalse("The graph should not contain the vertex after removal.", graph.contains(vertexLabel));
    }

    @Test
    public void testRemove_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        thrown.expect(NoSuchVertexException.class);
        graph.remove("NonexistentVertex");
    }

    @Test
    public void testAddEdge_NewEdge_ShouldAddSuccessfully() {
        String vertexU = "VertexU";
        String vertexV = "VertexV";
        String edgeLabel = "EdgeLabel";
        graph.add(vertexU);
        graph.add(vertexV);
        graph.addEdge(vertexU, vertexV, edgeLabel);
        assertTrue("The graph should contain the new edge after adding.", graph.containsEdge(vertexU, vertexV));
    }

    @Test
    public void testAddEdge_DuplicateEdge_ShouldThrowDuplicateEdgeException() {
        String vertexU = "VertexU";
        String vertexV = "VertexV";
        String edgeLabel = "EdgeLabel";
        graph.add(vertexU);
        graph.add(vertexV);
        graph.addEdge(vertexU, vertexV, edgeLabel);
        thrown.expect(DuplicateEdgeException.class);
        graph.addEdge(vertexU, vertexV, edgeLabel);
    }

    @Test
    public void testAddEdge_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        String vertexU = "VertexU";
        String vertexV = "VertexV";
        String edgeLabel = "EdgeLabel";
        graph.add(vertexU);
        // Do not add vertexV to the graph
        thrown.expect(NoSuchVertexException.class);
        graph.addEdge(vertexU, vertexV, edgeLabel);
    }

    @Test
    public void testAddEdge_NullLabel_ShouldThrowIllegalArgumentException() {
        String vertexU = "VertexU";
        String vertexV = "VertexV";
        graph.add(vertexU);
        graph.add(vertexV);
        thrown.expect(IllegalArgumentException.class);
        graph.addEdge(vertexU, vertexV, null);
    }

    @Test
    public void testContainsEdge_ExistingEdge_ShouldReturnTrue() {
        String vertexU = "VertexU";
        String vertexV = "VertexV";
        String edgeLabel = "EdgeLabel";
        graph.add(vertexU);
        graph.add(vertexV);
        graph.addEdge(vertexU, vertexV, edgeLabel);
        assertTrue("The graph should contain the edge after it has been added.", graph.containsEdge(vertexU, vertexV));
    }

    @Test
    public void testContainsEdge_NonexistentEdge_ShouldReturnFalse() {
        String vertexU = "VertexU";
        String vertexV = "VertexV";
        graph.add(vertexU);
        graph.add(vertexV);
        assertFalse("The graph should not contain an edge that has not been added.", graph.containsEdge(vertexU, vertexV));
    }

    @Test
    public void testContainsEdge_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        try {
            graph.containsEdge("NonexistentVertexU", "NonexistentVertexV");
            fail("Expected NoSuchVertexException to be thrown");
        } catch (NoSuchVertexException e) {
            // Test passes if NoSuchVertexException is caught
        }
    }


    @Test
    public void testGetEdge_ExistingEdge_ShouldReturnCorrectEdge() {
        String vertexU = "VertexU";
        String vertexV = "VertexV";
        String edgeLabel = "EdgeLabel";
        graph.add(vertexU);
        graph.add(vertexV);
        graph.addEdge(vertexU, vertexV, edgeLabel);
        Edge<String, String> edge = graph.getEdge(vertexU, vertexV);
        assertNotNull("The getEdge method should return a non-null edge.", edge);
        assertEquals("The edge returned should have the correct label.", edgeLabel, edge.getLabel());
    }

    @Test
    public void testGetEdge_NonexistentEdge_ShouldThrowNoSuchEdgeException() {
        String vertexU = "VertexU";
        String vertexV = "VertexV";
        graph.add(vertexU);
        graph.add(vertexV);
        thrown.expect(NoSuchEdgeException.class);
        graph.getEdge(vertexU, vertexV);
    }

    @Test
    public void testGetEdge_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        thrown.expect(NoSuchVertexException.class);
        graph.getEdge("NonexistentVertexU", "NonexistentVertexV");
    }

    @Test
    public void testRemoveEdge_ExistingEdge_ShouldRemoveSuccessfully() {
        String vertexU = "VertexU";
        String vertexV = "VertexV";
        String edgeLabel = "EdgeLabel";
        graph.add(vertexU);
        graph.add(vertexV);
        graph.addEdge(vertexU, vertexV, edgeLabel);
        graph.removeEdge(vertexU, vertexV);
        assertFalse("The graph should not contain the edge after it has been removed.", graph.containsEdge(vertexU, vertexV));
    }

    @Test
    public void testRemoveEdge_NonexistentEdge_ShouldThrowNoSuchEdgeException() {
        String vertexU = "VertexU";
        String vertexV = "VertexV";
        graph.add(vertexU);
        graph.add(vertexV);
        thrown.expect(NoSuchEdgeException.class);
        graph.removeEdge(vertexU, vertexV);
    }

    @Test
    public void testRemoveEdge_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        thrown.expect(NoSuchVertexException.class);
        graph.removeEdge("NonexistentVertexU", "NonexistentVertexV");
    }

    @Test
    public void testSize_EmptyGraph_ShouldReturnZero() {
        assertEquals("The size of an empty graph should be zero.", 0, graph.size());
    }

    @Test
    public void testSize_NonEmptyGraph_ShouldReturnCorrectSize() {
        graph.add("Vertex1");
        graph.add("Vertex2");
        assertEquals("The size of the graph should match the number of vertices.", 2, graph.size());
    }

    @Test
    public void testDegree_VertexWithEdges_ShouldReturnCorrectDegree() {
        String vertexU = "VertexU";
        String vertexV1 = "VertexV1";
        String vertexV2 = "VertexV2";
        graph.add(vertexU);
        graph.add(vertexV1);
        graph.add(vertexV2);
        graph.addEdge(vertexU, vertexV1, "EdgeLabel1");
        graph.addEdge(vertexU, vertexV2, "EdgeLabel2");
        assertEquals("The degree of the vertex should match the number of outgoing edges.", 2, graph.degree(vertexU));
    }

    @Test
    public void testDegree_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        thrown.expect(NoSuchVertexException.class);
        graph.degree("NonexistentVertex");
    }

    @Test
    public void testAdjacent_ExistingVertexWithEdges_ShouldReturnCorrectIterator() {
        String vertexU = "VertexU";
        String vertexV = "VertexV";
        graph.add(vertexU);
        graph.add(vertexV);
        graph.addEdge(vertexU, vertexV, "EdgeLabel");
        Iterator<Vertex<String>> it = graph.adjacent(vertexU);
        assertTrue("The iterator should have at least one element.", it.hasNext());
        assertEquals("The adjacent vertex should be VertexV.", vertexV, it.next().getLabel());
        assertFalse("The iterator should not have any more elements.", it.hasNext());
    }

    @Test
    public void testAdjacent_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        thrown.expect(NoSuchVertexException.class);
        graph.adjacent("NonexistentVertex");
    }

    @Test
    public void testIsEmpty_EmptyGraph_ShouldReturnTrue() {
        assertTrue("The graph should be empty.", graph.isEmpty());
    }

    @Test
    public void testIsEmpty_NonEmptyGraph_ShouldReturnFalse() {
        graph.add("Vertex1");
        assertFalse("The graph should not be empty.", graph.isEmpty());
    }

    @Test
    public void testClear_NonEmptyGraph_ShouldRemoveAllVerticesAndEdges() {
        graph.add("Vertex1");
        graph.add("Vertex2");
        graph.addEdge("Vertex1", "Vertex2", "EdgeLabel");
        graph.clear();
        assertTrue("The graph should be empty after clear.", graph.isEmpty());
        assertEquals("The size of the graph should be zero after clear.", 0, graph.size());
    }


}
