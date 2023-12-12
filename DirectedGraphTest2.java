import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DirectedGraphTest2 {

    private MatrixGraph<String, Integer> graph;

    @Before
    public void setUp() {
        graph = new MatrixGraph<>();
    }

    @Test
    public void testAdd_NewVertex_ShouldAddSuccessfully() {
        String vertex = "A";
        graph.add(vertex);
        assertTrue("Graph should contain the newly added vertex.", graph.contains(vertex));
    }

    @Test(expected = DuplicateVertexException.class)
    public void testAdd_DuplicateVertex_ShouldThrowDuplicateVertexException() {
        String vertex = "A";
        graph.add(vertex);
        graph.add(vertex); // This should throw the exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd_NullVertex_ShouldThrowIllegalArgumentException() {
        graph.add(null); // This should throw the exception
    }

    @Test
    public void testContains_ExistingVertex_ShouldReturnTrue() {
        String vertex = "A";
        graph.add(vertex);
        assertTrue("Graph should report true for a vertex that exists.", graph.contains(vertex));
    }
    // Continuing from the previous test class...

    @Test
    public void testContains_NonexistentVertex_ShouldReturnFalse() {
        String vertex = "Nonexistent";
        assertFalse("Graph should return false for a non-existent vertex.", graph.contains(vertex));
    }

    @Test
    public void testGet_ExistingVertex_ShouldReturnCorrectVertex() {
        String vertex = "A";
        graph.add(vertex);
        assertEquals("The get method should return the correct vertex.", vertex, graph.get(vertex).getLabel());
    }

    @Test(expected = NoSuchVertexException.class)
    public void testGet_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        graph.get("Nonexistent"); // This should throw the exception
    }

    @Test
    public void testRemove_ExistingVertex_ShouldRemoveSuccessfully() {
        String vertex = "A";
        graph.add(vertex);
        graph.remove(vertex);
        assertFalse("Graph should no longer contain the removed vertex.", graph.contains(vertex));
    }

    @Test(expected = NoSuchVertexException.class)
    public void testRemove_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        graph.remove("Nonexistent"); // This should throw the exception
    }
    // Continuing from the previous test class...

    @Test
    public void testAddEdge_NewEdge_ShouldAddSuccessfully() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1); // Assuming the edge label is an Integer
        assertTrue("Graph should contain the newly added edge.", graph.containsEdge("A", "B"));
    }

    @Test(expected = DuplicateEdgeException.class)
    public void testAddEdge_DuplicateEdge_ShouldThrowDuplicateEdgeException() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "B", 1); // Attempt to add the same edge again
    }

    @Test(expected = NoSuchVertexException.class)
    public void testAddEdge_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        graph.add("A");
        graph.addEdge("A", "Nonexistent", 1); // "Nonexistent" vertex does not exist
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdge_NullVertex_ShouldThrowIllegalArgumentException() {
        graph.add("A");
        graph.addEdge(null, "A", 1); // Source vertex is null
    }

    @Test
    public void testContainsEdge_ExistingEdge_ShouldReturnTrue() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        assertTrue("Graph should report true for an edge that exists.", graph.containsEdge("A", "B"));
    }

    @Test
    public void testContainsEdge_NonexistentEdge_ShouldReturnFalse() {
        graph.add("A");
        graph.add("B");
        assertFalse("Graph should report false for a non-existent edge.", graph.containsEdge("A", "B"));
    }

    // Continuing from the previous test class...

    @Test
    public void testContainsEdge_NonexistentVertex_ShouldReturnFalse() {
        graph.add("A");
        assertFalse("Checking an edge with a non-existent vertex should return false.", graph.containsEdge("A", "Nonexistent"));
    }

    @Test
    public void testGetEdge_ExistingEdge_ShouldReturnCorrectEdge() {
        graph.add("A");
        graph.add("B");
        Integer edgeLabel = 1;
        graph.addEdge("A", "B", edgeLabel);
        Edge<String, Integer> edge = graph.getEdge("A", "B");
        assertNotNull("Edge should not be null.", edge);
        assertEquals("Edge should have the correct label.", edgeLabel, edge.getLabel());
    }

    @Test(expected = NoSuchEdgeException.class)
    public void testGetEdge_NonexistentEdge_ShouldThrowNoSuchEdgeException() {
        graph.add("A");
        graph.add("B");
        graph.getEdge("A", "B"); // This should throw the exception
    }

    @Test(expected = NoSuchVertexException.class)
    public void testGetEdge_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        graph.add("A");
        graph.getEdge("A", "Nonexistent"); // This should throw the exception
    }

    @Test
    public void testRemoveEdge_ExistingEdge_ShouldRemoveSuccessfully() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        Integer removedEdge = graph.removeEdge("A", "B");
        assertNotNull("Removed edge label should not be null.", removedEdge);
        assertFalse("Edge should no longer exist in the graph.", graph.containsEdge("A", "B"));
    }

    @Test(expected = NoSuchEdgeException.class)
    public void testRemoveEdge_NonexistentEdge_ShouldThrowNoSuchEdgeException() {
        graph.add("A");
        graph.add("B");
        graph.removeEdge("A", "B"); // This should throw the exception
    }

    @Test(expected = NoSuchVertexException.class)
    public void testRemoveEdge_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        graph.add("A");
        graph.removeEdge("A", "Nonexistent"); // This should throw the exception
    }

    // Continuing from the previous test class...

    @Test
    public void testSize_EmptyGraph_ShouldReturnZero() {
        assertEquals("An empty graph should have a size of 0.", 0, graph.size());
    }

    @Test
    public void testSize_NonEmptyGraph_ShouldReturnCorrectSize() {
        graph.add("A");
        graph.add("B");
        assertEquals("Graph should report the correct size.", 2, graph.size());
    }

    @Test
    public void testDegree_VertexWithEdges_ShouldReturnCorrectDegree() {
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);
        assertEquals("Degree of vertex A should be 2.", 2, graph.degree("A"));
    }

    @Test(expected = NoSuchVertexException.class)
    public void testDegree_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        graph.degree("Nonexistent"); // This should throw the exception
    }

    // Continuing from the previous test class...

    @Test
    public void testAdjacent_ExistingVertexWithEdges_ShouldReturnCorrectIterator() {
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);

        Iterator<Vertex<String>> adjacentVertices = graph.adjacent("A");
        Set<String> adjacentLabels = new HashSet<>();
        while (adjacentVertices.hasNext()) {
            adjacentLabels.add(adjacentVertices.next().getLabel());
        }

        Set<String> expectedLabels = new HashSet<>(Arrays.asList("B", "C"));
        assertEquals("Adjacent vertices should match expected vertices.", expectedLabels, adjacentLabels);
    }

    @Test(expected = NoSuchVertexException.class)
    public void testAdjacent_NonexistentVertex_ShouldThrowNoSuchVertexException() {
        graph.adjacent("Nonexistent"); // This should throw the exception
    }

    @Test
    public void testIsEmpty_EmptyGraph_ShouldReturnTrue() {
        assertTrue("An empty graph should be identified as empty.", graph.isEmpty());
    }

    @Test
    public void testIsEmpty_NonEmptyGraph_ShouldReturnFalse() {
        graph.add("A");
        assertFalse("A non-empty graph should not be identified as empty.", graph.isEmpty());
    }

    @Test
    public void testClear_NonEmptyGraph_ShouldRemoveAllVerticesAndEdges() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        graph.clear();
        assertTrue("Graph should be empty after clear operation.", graph.isEmpty());
        assertEquals("Graph should have no vertices after clear.", 0, graph.size());
        assertEquals("Graph should have no edges after clear.", 0, graph.edgeCount());
    }

    // Continuing from the previous test class...

    @Test(expected = IllegalArgumentException.class)
    public void testAdd_NullEdge_ShouldThrowIllegalArgumentException() {
        graph.addEdge("A", "B", null); // Adding an edge with a null label
    }



    @Test
    public void testRemove_ValidVertexWithEdges_ShouldRemoveEdges() {
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);
        graph.remove("A");
        assertFalse("Edges associated with the removed vertex should also be removed.", graph.containsEdge("A", "B") || graph.containsEdge("A", "C"));
    }

    @Test
    public void testEdgeCount_AfterAddingAndRemovingEdges_ShouldReturnCorrectCount() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        assertEquals("Graph should have 1 edge after adding an edge.", 1, graph.edgeCount());
        graph.removeEdge("A", "B");
        assertEquals("Graph should have 0 edges after removing the edge.", 0, graph.edgeCount());
    }

    @Test
    public void testVertices_IteratorShouldReflectGraphState() {
        graph.add("A");
        graph.add("B");
        Iterator<Vertex<String>> verticesIterator = graph.vertices();
        Set<String> vertexLabels = new HashSet<>();
        while (verticesIterator.hasNext()) {
            vertexLabels.add(verticesIterator.next().getLabel());
        }
        Set<String> expectedLabels = new HashSet<>(Arrays.asList("A", "B"));
        assertEquals("Vertices iterator should reflect the current state of the graph.", expectedLabels, vertexLabels);
    }











    // More tests can be added here...

}

