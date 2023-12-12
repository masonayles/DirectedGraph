import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MatrixGraphtest2 {

    private MatrixGraph<String, Integer> graph;

    @Before
    public void setUp() {
        graph = new MatrixGraph<>();
    }

    @Test
    public void testAddVertex_IncreasesGraphCapacity() {
        // Assuming initial capacity of the matrix is known (for example, 10)
        final int initialCapacity = 10;
        for (int i = 0; i < initialCapacity + 1; i++) {
            graph.add("Vertex" + i);
        }
        // Verify if the internal capacity has increased
        // This verification depends on whether you can access the internal matrix or its size
        assertTrue("Graph should expand its capacity.", graph.getInternalMatrixSize() > initialCapacity);
    }

    @Test
    public void testAddEdge_UpdatesMatrixCorrectly() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        // Check if the edge is correctly reflected in the matrix
        // This check depends on your access to the internal matrix
        assertEquals("Matrix should reflect the added edge.", (Integer) 1, graph.getMatrixValue("A", "B"));
    }

    @Test
    public void testRemoveVertex_UpdatesGraphState() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        graph.remove("A");
        assertFalse("Removed vertex should not be in the graph.", graph.contains("A"));
        // Further check if the matrix is updated correctly
        assertNull("Matrix should not have entries related to the removed vertex.", graph.getMatrixValue("A", "B"));
    }

    @Test
    public void testRemoveEdge_UpdatesMatrixCorrectly() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        graph.removeEdge("A", "B");
        assertNull("Removed edge should not be reflected in the matrix.", graph.getMatrixValue("A", "B"));
    }

    @Test
    public void testGetEdge_RetrievesCorrectEdgeFromMatrix() {
        graph.add("A");
        graph.add("B");
        Integer edgeLabel = 1;
        graph.addEdge("A", "B", edgeLabel);
        Edge<String, Integer> edge = graph.getEdge("A", "B");
        assertNotNull("Edge should be retrieved from the matrix.", edge);
        assertEquals("Retrieved edge should have the correct label.", edgeLabel, edge.getLabel());
    }

    @Test
    public void testDegree_CountsOutgoingEdgesCorrectly() {
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);
        assertEquals("Degree should count the number of outgoing edges.", 2, graph.degree("A"));
    }

    // Continuing from the MatrixGraphTest class...

    @Test
    public void testAddEdge_WithSelfLoop_UpdatesMatrixCorrectly() {
        graph.add("A");
        graph.addEdge("A", "A", 1); // Self-loop
        assertEquals("Self-loop edge should be added to the matrix.", (Integer) 1, graph.getMatrixValue("A", "A"));
    }

    @Test
    public void testRemoveNonexistentVertex_HandlesGracefully() {
        // Assuming no exception should be thrown for removing a non-existent vertex
        graph.remove("Nonexistent");
        // Further checks can be added to verify the state of the graph
    }

    @Test
    public void testRemoveNonexistentEdge_HandlesGracefully() {
        graph.add("A");
        graph.add("B");
        // Assuming no exception should be thrown for removing a non-existent edge
        graph.removeEdge("A", "B");
        // Additional checks can be added if necessary
    }

    @Test(expected = NoSuchEdgeException.class)
    public void testGetEdge_WhenEdgeDoesNotExist_ThrowsException() {
        graph.add("A");
        graph.add("B");
        graph.getEdge("A", "B"); // This should throw the exception
    }

    @Test
    public void testAddVertex_WhenMatrixIsFull_ExpandsMatrix() {
        // Assuming initial capacity and expansion logic are known
        final int initialCapacity = 10;
        for (int i = 0; i < initialCapacity + 1; i++) {
            graph.add("Vertex" + i);
        }
        assertTrue("Graph should expand its matrix capacity.", graph.getInternalMatrixSize() > initialCapacity);
    }

    @Test
    public void testInternalMatrixStructure_AfterMultipleOperations() {
        // Add and remove vertices and edges, then verify the internal matrix state
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        graph.remove("B");
        graph.add("C");
        graph.addEdge("A", "C", 2);
        // Check the matrix to ensure it reflects these operations correctly
        assertNull("Matrix should not have an edge for removed vertices.", graph.getMatrixValue("A", "B"));
        assertEquals("Matrix should reflect the new edge.", (Integer) 2, graph.getMatrixValue("A", "C"));
    }

    @Test
    public void testMatrixGraph_DynamicExpansionBehavior() {
        // Add a large number of vertices and check if the matrix expands and retains correct information
        for (int i = 0; i < 100; i++) {
            graph.add("Vertex" + i);
        }
        // Verify the matrix size and some randomly selected entries
        assertTrue("Graph matrix should expand to accommodate many vertices.", graph.getInternalMatrixSize() >= 100);
        // Further checks can be added to verify specific matrix entries
    }

    @Test
    public void testMatrixGraph_HandlingOfSparseGraphs() {
        // Add a large number of vertices but only a few edges
        for (int i = 0; i < 100; i++) {
            graph.add("Vertex" + i);
        }
        graph.addEdge("Vertex1", "Vertex2", 1);
        graph.addEdge("Vertex3", "Vertex4", 2);

        // Now check if these specific edges exist
        assertTrue("Edge between Vertex1 and Vertex2 should exist.", graph.containsEdge("Vertex1", "Vertex2"));
        assertTrue("Edge between Vertex3 and Vertex4 should exist.", graph.containsEdge("Vertex3", "Vertex4"));

        // Optionally, check if some random edges do not exist to confirm sparseness
        assertFalse("Non-existent edge should not be found in the graph.", graph.containsEdge("Vertex1", "Vertex3"));
        assertFalse("Non-existent edge should not be found in the graph.", graph.containsEdge("Vertex2", "Vertex4"));
    }

    // Assuming you already have a MatrixGraphTest class with a setup method

    @Test
    public void testClear_ResetsMatrixToInitialState() {
        // Add some vertices and edges
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);

        // Clear the graph
        graph.clear();

        // Check if the graph is empty
        assertTrue("Graph should be empty after clear.", graph.isEmpty());
        assertEquals("Graph should have no vertices after clear.", 0, graph.size());
        assertEquals("Graph should have no edges after clear.", 0, graph.edgeCount());

        // Optionally, add new vertices and edges to verify the graph is still functional
        graph.add("C");
        graph.add("D");
        graph.addEdge("C", "D", 2);
        assertTrue("Graph should allow adding new vertices after clear.", graph.contains("C"));
        assertTrue("Graph should allow adding new edges after clear.", graph.containsEdge("C", "D"));
    }


    @Test
    public void testAdjacent_ReflectsCorrectAdjacencyInMatrix() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);

        Iterator<Vertex<String>> adjacent = graph.adjacent("A");
        assertTrue("B should be adjacent to A.", adjacent.hasNext() && adjacent.next().getLabel().equals("B"));
    }

    @Test
    public void testEdgeCount_MatchesMatrixEntries() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        assertEquals("Edge count should match matrix entries.", 1, graph.edgeCount());
    }

    @Test
    public void testIterator_VerticesMatchesGraphVertices() {
        graph.add("A");
        graph.add("B");

        Set<String> vertices = new HashSet<>();
        graph.vertices().forEachRemaining(v -> vertices.add(v.getLabel()));

        assertEquals("Vertices iterator should match graph vertices.", new HashSet<>(Arrays.asList("A", "B")), vertices);
    }

    @Test
    public void testIterator_EdgesMatchesGraphEdges() {
        graph.add("A");
        graph.add("B");
        Integer edgeLabel = 1;
        graph.addEdge("A", "B", edgeLabel);

        boolean edgeFound = false;
        Iterator<Edge<String, Integer>> edges = graph.edges();
        while (edges.hasNext()) {
            Edge<String, Integer> edge = edges.next();
            if (edge.getLabel().equals(edgeLabel)) {
                edgeFound = true;
                break;
            }
        }

        assertTrue("Edges iterator should match graph edges.", edgeFound);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddEdge_InvalidVertices_ThrowsException() {
        graph.addEdge("Invalid1", "Invalid2", 1);
    }

    @Test
    public void testAddVertex_MaintainsMatrixIntegrity() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);

        graph.add("C");

        assertTrue("Adding a vertex should not alter existing edges.", graph.containsEdge("A", "B"));
    }

    @Test
    public void testRemoveVertex_ShiftsMatrixElementsCorrectly() {
        // Add vertices and edges, remove a vertex, then check the integrity of the matrix
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);

        graph.remove("B");

        assertFalse("Matrix should not have edges related to the removed vertex.", graph.containsEdge("A", "B"));
        assertTrue("Matrix should maintain other edges correctly.", graph.containsEdge("A", "C"));
    }

    @Test
    public void testRemoveEdge_DoesNotAffectOtherEdges() {
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);

        graph.removeEdge("A", "B");

        assertFalse("Removed edge should not be in the matrix.", graph.containsEdge("A", "B"));
        assertTrue("Other edges should remain unaffected.", graph.containsEdge("A", "C"));
    }

    @Test
    public void testMatrixGraph_ResizingDoesNotLoseData() {
        final int largeNumber = 100; // Assuming this number is large enough to trigger resizing
        for (int i = 0; i < largeNumber; i++) {
            String vertex = "Vertex" + i;
            graph.add(vertex);

            // Add an edge to the previous vertex to create some edges
            if (i > 0) {
                graph.addEdge("Vertex" + (i - 1), vertex, i);
            }
        }

        // Verify that all vertices and edges are intact
        for (int i = 0; i < largeNumber; i++) {
            assertTrue("Graph should contain vertex added before resizing.", graph.contains("Vertex" + i));
            if (i > 0) {
                assertTrue("Graph should contain edge added before resizing.", graph.containsEdge("Vertex" + (i - 1), "Vertex" + i));
            }
        }
    }


    @Test
    public void testIsEmpty_ReflectsMatrixState() {
        assertTrue("Newly created graph should be empty.", graph.isEmpty());
        graph.add("A");
        assertFalse("Graph with vertices should not be empty.", graph.isEmpty());
    }


    @Test
    public void testVerticesAndEdges_AfterMultipleAdditionsAndRemovals() {
        // Add vertices and edges
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        graph.add("C");
        graph.addEdge("A", "C", 2);

        // Remove some vertices and edges
        graph.removeEdge("A", "B");
        graph.remove("C");

        // Check for consistency
        assertTrue("Graph should still contain vertex A.", graph.contains("A"));
        assertFalse("Graph should not contain vertex C.", graph.contains("C"));
        assertFalse("Graph should not contain removed edge A-B.", graph.containsEdge("A", "B"));
        assertTrue("Graph should still contain edge A-C.", graph.containsEdge("A", "C"));
    }







    // Helper methods to access internal matrix (if needed)...
    // These methods depend on the internal structure of your MatrixGraph implementation
}

