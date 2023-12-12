import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class MatrixGraphTest {

    private MatrixGraph<String, String> matrixGraph;
    private static final int DEFAULT_MATRIX_SIZE = 10; // Assuming initial matrix size is 10

    @Before
    public void setUp() {
        matrixGraph = new MatrixGraph<>();
    }

    @Test
    public void testAddVertex_IncreasesGraphCapacity() {
        // Add a vertex and verify its presence
        matrixGraph.add("Vertex1");
        assertTrue("Graph should contain the added vertex.", matrixGraph.contains("Vertex1"));

        // Add another vertex
        matrixGraph.add("Vertex2");
        assertTrue("Graph should contain the second added vertex.", matrixGraph.contains("Vertex2"));

        // Add an edge between the two vertices and verify
        matrixGraph.addEdge("Vertex1", "Vertex2", "EdgeLabel");
        assertTrue("Graph should contain the edge between Vertex1 and Vertex2.",
                matrixGraph.containsEdge("Vertex1", "Vertex2"));

        // Verify the degree of Vertex1 is correct after adding the edge
        assertEquals("Degree of Vertex1 should be 1 after adding one edge.",
                1, matrixGraph.degree("Vertex1"));
    }

    @Test
    public void testAddEdge_UpdatesMatrixCorrectly() {
        matrixGraph.add("Vertex1");
        matrixGraph.add("Vertex2");
        matrixGraph.addEdge("Vertex1", "Vertex2", "EdgeLabel");
        assertTrue("Matrix should reflect the edge between Vertex1 and Vertex2.",
                matrixGraph.containsEdge("Vertex1", "Vertex2"));
    }

    @Test
    public void testRemoveVertex_UpdatesGraphState() {
        // Add vertices and an edge
        matrixGraph.add("Vertex1");
        matrixGraph.add("Vertex2");
        matrixGraph.addEdge("Vertex1", "Vertex2", "EdgeLabel");

        // Ensure the edge is present before removal
        assertTrue("Edge should exist before removing the vertex.", matrixGraph.containsEdge("Vertex1", "Vertex2"));

        // Remove the vertex and verify it's no longer in the graph
        matrixGraph.remove("Vertex1");
        assertFalse("Graph should no longer contain Vertex1 after removal.", matrixGraph.contains("Vertex1"));

        // Verify the edge related to the removed vertex is also removed
        try {
            matrixGraph.getEdge("Vertex1", "Vertex2");
            fail("Accessing an edge related to a removed vertex should throw an exception.");
        } catch (NoSuchEdgeException e) {
            // Test passes if exception is thrown
        }

        // Optionally, verify that the graph state is consistent (e.g., size and other properties)
        assertEquals("Graph size should be 1 after removing one of two vertices.", 1, matrixGraph.size());
    }

    @Test
    public void testRemoveEdge_UpdatesMatrixCorrectly() {
        matrixGraph.add("Vertex1");
        matrixGraph.add("Vertex2");
        matrixGraph.addEdge("Vertex1", "Vertex2", "EdgeLabel");
        matrixGraph.removeEdge("Vertex1", "Vertex2");
        assertFalse("Matrix should no longer reflect the edge between Vertex1 and Vertex2.",
                matrixGraph.containsEdge("Vertex1", "Vertex2"));
    }

    @Test
    public void testGetEdge_RetrievesCorrectEdgeFromMatrix() {
        matrixGraph.add("Vertex1");
        matrixGraph.add("Vertex2");
        String edgeLabel = "EdgeLabel";
        matrixGraph.addEdge("Vertex1", "Vertex2", edgeLabel);
        Edge<String, String> edge = matrixGraph.getEdge("Vertex1", "Vertex2");
        assertNotNull("Should retrieve a non-null edge.", edge);
        assertEquals("The retrieved edge should have the correct label.", edgeLabel, edge.getLabel());
    }

    @Test
    public void testDegree_CountsOutgoingEdgesCorrectly() {
        matrixGraph.add("Vertex1");
        matrixGraph.add("Vertex2");
        matrixGraph.addEdge("Vertex1", "Vertex2", "EdgeLabel");
        int degree = matrixGraph.degree("Vertex1");
        assertEquals("Degree should count the number of outgoing edges.", 1, degree);
    }

    @Test
    public void testAddEdge_WithSelfLoop_UpdatesMatrixCorrectly() {
        matrixGraph.add("Vertex1");
        matrixGraph.addEdge("Vertex1", "Vertex1", "SelfLoop");
        assertTrue("Matrix should reflect the self-loop edge on Vertex1.",
                matrixGraph.containsEdge("Vertex1", "Vertex1"));
    }

    @Test
    public void testRemoveNonexistentVertex_HandlesGracefully() {
        try {
            matrixGraph.remove("NonexistentVertex");
        } catch (Exception e) {
            fail("Removing a nonexistent vertex should be handled gracefully without throwing exceptions.");
        }
    }

    @Test
    public void testRemoveNonexistentEdge_HandlesGracefully() {
        matrixGraph.add("Vertex1");
        matrixGraph.add("Vertex2");
        try {
            matrixGraph.removeEdge("Vertex1", "Vertex2");
        } catch (Exception e) {
            fail("Removing a nonexistent edge should be handled gracefully without throwing exceptions.");
        }
    }

    @Test
    public void testGetEdge_WhenEdgeDoesNotExist_ReturnsNull() {
        matrixGraph.add("Vertex1");
        matrixGraph.add("Vertex2");
        assertNull("Retrieving a non-existent edge should return null.",
                matrixGraph.getEdge("Vertex1", "Vertex2"));
    }
    @Test
    public void testAddVertex_WhenMatrixIsFull_ExpandsMatrix() {
        // Add vertices to exceed the initial capacity
        for (int i = 0; i < DEFAULT_MATRIX_SIZE + 1; i++) {
            matrixGraph.add("Vertex" + i);
        }

        // Check if all vertices are present and correctly handled
        for (int i = 0; i < DEFAULT_MATRIX_SIZE + 1; i++) {
            assertTrue("Graph should contain the vertex added.", matrixGraph.contains("Vertex" + i));
        }
    }

    @Test
    public void testGetEdge_WhenEdgeDoesNotExist_ThrowsException() {
        matrixGraph.add("Vertex1");
        matrixGraph.add("Vertex2");
        try {
            matrixGraph.getEdge("Vertex1", "Vertex2");
            fail("Should throw NoSuchEdgeException for a non-existent edge.");
        } catch (NoSuchEdgeException e) {
            // Test passes
        }
    }

    @Test
    public void testInternalMatrixStructure_AfterMultipleOperations() {
        // Add vertices and edges in various combinations
        matrixGraph.add("Vertex1");
        matrixGraph.add("Vertex2");
        matrixGraph.addEdge("Vertex1", "Vertex2", "EdgeLabel1");

        matrixGraph.add("Vertex3");
        matrixGraph.addEdge("Vertex2", "Vertex3", "EdgeLabel2");

        // Remove a vertex and an edge
        matrixGraph.remove("Vertex1");
        matrixGraph.removeEdge("Vertex2", "Vertex3");

        // Verify remaining vertices and edges
        assertTrue("Vertex2 should still exist in the graph.", matrixGraph.contains("Vertex2"));
        assertFalse("Vertex1 should no longer exist in the graph.", matrixGraph.contains("Vertex1"));
        assertFalse("Edge from Vertex2 to Vertex3 should no longer exist.", matrixGraph.containsEdge("Vertex2", "Vertex3"));

        // Verify the graph's size and other properties
        assertEquals("Graph size should be correct after multiple operations.", 2, matrixGraph.size());
        // Other relevant assertions can be added here
    }

    @Test
    public void testMatrixGraph_DynamicExpansionBehavior() {
        // Add vertices beyond the initial capacity to test dynamic expansion
        for (int i = 0; i < DEFAULT_MATRIX_SIZE + 5; i++) {
            matrixGraph.add("Vertex" + i);
        }

        // Verify all vertices are present and graph size is correct
        assertEquals("Graph size should reflect all added vertices.", DEFAULT_MATRIX_SIZE + 5, matrixGraph.size());
        for (int i = 0; i < DEFAULT_MATRIX_SIZE + 5; i++) {
            assertTrue("Graph should contain all added vertices.", matrixGraph.contains("Vertex" + i));
        }
    }

    @Test
    public void testMatrixGraph_HandlingOfSparseGraphs() {
        // Add vertices without adding many edges to create a sparse graph
        for (int i = 0; i < 20; i++) {
            matrixGraph.add("Vertex" + i);
        }
        // Add only a few edges
        matrixGraph.addEdge("Vertex0", "Vertex1", "EdgeLabel1");
        matrixGraph.addEdge("Vertex2", "Vertex3", "EdgeLabel2");

        // Verify the sparse nature of the graph
        assertTrue("Sparse graph should contain the few edges added.", matrixGraph.containsEdge("Vertex0", "Vertex1"));
        assertTrue("Sparse graph should contain the few edges added.", matrixGraph.containsEdge("Vertex2", "Vertex3"));

        int edgeCount = 0;
        Iterator<Edge<String, String>> edges = matrixGraph.edges();
        while (edges.hasNext()) {
            edges.next();
            edgeCount++;
        }
        assertEquals("Sparse graph should have a limited number of edges.", 2, edgeCount);
    }


}


