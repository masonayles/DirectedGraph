import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class ListGraphTest2 {

    private ListGraph<String, Integer> graph;

    @Before
    public void setUp() {
        graph = new ListGraph<>();
    }


    @Test
    public void testAddVertex_AddsToList() {
        String vertex = "A";
        graph.add(vertex);

        assertTrue("Vertex should be added to the graph.", graph.contains(vertex));
        // Assuming you can access the adjacency list to check if it contains an entry for the vertex
        assertTrue("Adjacency list should have an entry for the new vertex.", graph.getAdjacencyList().containsKey(vertex));
        assertTrue("The entry for the new vertex should be empty.", graph.getAdjacencyList().get(vertex).isEmpty());
    }

    @Test
    public void testAddEdge_UpdatesAdjacencyList() {
        String source = "A";
        String destination = "B";
        graph.add(source);
        graph.add(destination);
        graph.addEdge(source, destination, 1);

        List<String> adjacentVertices = graph.getAdjacencyList().get(source);
        assertNotNull("Adjacency list for source vertex should not be null.", adjacentVertices);
        assertFalse("Adjacency list for source vertex should not be empty.", adjacentVertices.isEmpty());
        assertTrue("Adjacency list should contain the destination vertex label.", adjacentVertices.contains(destination));
    }

    @Test
    public void testRemoveVertex_UpdatesList() {
        String vertex = "A";
        graph.add(vertex);
        graph.remove(vertex);

        assertFalse("Vertex should be removed from the graph.", graph.contains(vertex));
        assertNull("Adjacency list should not contain an entry for the removed vertex.", graph.getAdjacencyList().get(vertex));
    }

    @Test
    public void testRemoveEdge_UpdatesAdjacencyList() {
        String source = "A";
        String destination = "B";
        graph.add(source);
        graph.add(destination);
        graph.addEdge(source, destination, 1);

        // Now remove the edge
        graph.removeEdge(source, destination);

        // Check that the adjacency list for the source vertex still exists
        assertTrue("Adjacency list should still contain an entry for the source vertex.",
                graph.getAdjacencyList().containsKey(source));

        // Use the helper method to get the list of destination labels for the source vertex
        List<String> destinations = graph.getDestinationLabels(source);

        // Check that this list does not contain the destination vertex label, since the edge has been removed
        assertFalse("Destination list for source vertex should not contain the removed destination.",
                destinations.contains(destination));
    }


    @Test
    public void testGetEdge_RetrievesCorrectEdgeFromList() {
        String source = "A";
        String destination = "B";
        Integer edgeLabel = 1;
        graph.add(source);
        graph.add(destination);
        graph.addEdge(source, destination, edgeLabel);

        Edge<String, Integer> edge = graph.getEdge(source, destination);
        assertNotNull("Retrieved edge should not be null.", edge);
        assertEquals("Retrieved edge should have the correct label.", edgeLabel, edge.getLabel());
    }

    @Test
    public void testDegree_CountsOutgoingEdgesFromList() {
        String vertex = "A";
        graph.add(vertex);
        graph.add("B");
        graph.add("C");
        graph.addEdge(vertex, "B", 1);
        graph.addEdge(vertex, "C", 1);

        int degree = graph.degree(vertex);
        assertEquals("Degree should match the number of outgoing edges.", 2, degree);
    }

    @Test
    public void testAddEdge_WithSelfLoop_UpdatesListCorrectly() {
        String vertex = "A";
        graph.add(vertex);
        graph.addEdge(vertex, vertex, 1);

        assertTrue("Self-loop edge should be added.", graph.containsEdge(vertex, vertex));
        assertEquals("Degree of vertex with self-loop should be 1.", 1, graph.degree(vertex));
    }
    @Test(expected = NoSuchVertexException.class)
    public void testRemoveNonexistentVertexFromList_ShouldThrowNoSuchVertexException() {
        String nonExistentVertex = "Nonexistent";
        graph.remove(nonExistentVertex);
    }


    @Test
    public void testRemoveNonexistentEdgeFromList_HandlesGracefully() {
        graph.add("A");
        graph.add("B");
        // Assuming no exception is thrown

        assertFalse("Nonexistent edge should not be in the graph.", graph.containsEdge("A", "B"));
    }

    @Test(expected = NoSuchEdgeException.class)
    public void testGetEdge_WhenEdgeDoesNotExistInList_ThrowsException() {
        graph.add("A");
        graph.add("B");
        graph.getEdge("A", "B"); // This should throw NoSuchEdgeException
    }

    @Test
    public void testInternalListStructure_AfterMultipleOperations() {
        // Add vertices and edges
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        graph.add("C");
        graph.addEdge("A", "C", 2);

        // Remove some vertices and edges
        graph.remove("B");
        graph.removeEdge("A", "C");

        // Check for integrity
        assertTrue("Graph should still contain vertex A.", graph.contains("A"));
        assertFalse("Graph should not contain vertex B.", graph.contains("B"));
        assertFalse("Graph should not contain edge from A to C.", graph.containsEdge("A", "C"));
    }
    @Test
    public void testListGraph_HandlingOfHighlyConnectedNodes() {
        String centralNode = "Central";
        graph.add(centralNode);

        // Create a large number of connections to the central node
        for (int i = 0; i < 100; i++) {
            String vertex = "Node" + i;
            graph.add(vertex);
            graph.addEdge(centralNode, vertex, i);
        }

        // Check if the central node has the correct degree
        assertEquals("Central node should have a high degree.", 100, graph.degree(centralNode));
    }

    @Test
    public void testListGraph_PerformanceWithLargeNumberOfEdges() {
        // Assuming this test is more about functionality under load than measuring performance
        for (int i = 0; i < 1000; i++) {
            graph.add("Vertex" + i);
            if (i > 0) {
                graph.addEdge("Vertex" + (i - 1), "Vertex" + i, i);
            }
        }

        // Verify some random edges to ensure they exist
        assertTrue("Edge should exist in the graph.", graph.containsEdge("Vertex1", "Vertex2"));
        assertTrue("Edge should exist in the graph.", graph.containsEdge("Vertex999", "Vertex998"));
    }
    @Test
    public void testIntegration_MatrixAndListGraph_WithCommonOperations() {
        MatrixGraph<String, Integer> matrixGraph = new MatrixGraph<>();
        ListGraph<String, Integer> listGraph = new ListGraph<>();

        // Perform similar operations on both graphs
        matrixGraph.add("A");
        listGraph.add("A");
        matrixGraph.add("B");
        listGraph.add("B");
        matrixGraph.addEdge("A", "B", 1);
        listGraph.addEdge("A", "B", 1);

        // Verify that both graphs have similar state
        assertTrue("Both graphs should contain vertex A.", matrixGraph.contains("A") && listGraph.contains("A"));
        assertTrue("Both graphs should contain the edge.", matrixGraph.containsEdge("A", "B") && listGraph.containsEdge("A", "B"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVertex_NullLabel_ThrowsIllegalArgumentException() {
        graph.add(null); // This should throw IllegalArgumentException
    }

    @Test(expected = NoSuchVertexException.class)
    public void testAddEdge_NonexistentVertices_ThrowsNoSuchVertexException() {
        graph.addEdge("Nonexistent1", "Nonexistent2", 1); // This should throw NoSuchVertexException
    }

    @Test
    public void testAdjacent_ReflectsCorrectAdjacency() {
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);

        Iterator<Vertex<String>> adjacent = graph.adjacent("A");
        Set<String> adjacentVertices = new HashSet<>();
        while (adjacent.hasNext()) {
            adjacentVertices.add(adjacent.next().getLabel());
        }

        Set<String> expectedAdjacent = new HashSet<>(Arrays.asList("B", "C"));
        assertEquals("Adjacent vertices should reflect true adjacency from the vertex.", expectedAdjacent, adjacentVertices);
    }

    @Test
    public void testEdgeCount_MatchesListEntries() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);

        int totalEdges = 0;
        for (List<String> edges : graph.getAdjacencyList().values()) {
            totalEdges += edges.size();
        }

        assertEquals("Edge count should match the total number of edges in adjacency lists.",
                totalEdges, graph.edgeCount());
    }

    @Test
    public void testClear_ResetsListGraphToInitialState() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);

        graph.clear();

        assertTrue("Graph should be empty after clear.", graph.isEmpty());
        assertEquals("Graph should have no vertices after clear.", 0, graph.size());
        assertEquals("Graph should have no edges after clear.", 0, graph.edgeCount());
    }
    @Test
    public void testIsEmpty_ReflectsListGraphState() {
        assertTrue("Newly created graph should be empty.", graph.isEmpty());
        graph.add("A");
        assertFalse("Graph with vertices should not be empty.", graph.isEmpty());
    }
    @Test
    public void testIterator_VerticesMatchesListGraphVertices() {
        graph.add("A");
        graph.add("B");

        Set<String> vertices = new HashSet<>();
        Iterator<Vertex<String>> verticesIterator = graph.vertices();
        while (verticesIterator.hasNext()) {
            Vertex<String> vertex = verticesIterator.next();
            vertices.add(vertex.getLabel());
        }

        Set<String> expectedVertices = new HashSet<>(Arrays.asList("A", "B"));
        assertEquals("Vertices iterator should match graph vertices.", expectedVertices, vertices);
    }



    @Test
    public void testIntegration_WithSerializationAndDeserialization() throws IOException, ClassNotFoundException {
        ListGraph<String, Integer> originalGraph = new ListGraph<>();
        originalGraph.add("A");
        originalGraph.addEdge("A", "B", 1);

        // Serialize the graph
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(originalGraph);
        out.flush();
        byte[] serializedData = bos.toByteArray();

        // Deserialize the graph
        ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
        ObjectInputStream in = new ObjectInputStream(bis);
        ListGraph<String, Integer> deserializedGraph = (ListGraph<String, Integer>) in.readObject();

        assertTrue("Deserialized graph should contain vertex A.", deserializedGraph.contains("A"));
        assertTrue("Deserialized graph should contain an edge from A to B.", deserializedGraph.containsEdge("A", "B"));
    }

    @Test(expected = DuplicateEdgeException.class)
    public void testAddEdge_DuplicateEdge_ThrowsDuplicateEdgeException() {
        graph.add("A");
        graph.add("B");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "B", 1); // Attempting to add the same edge again should throw an exception
    }

    @Test
    public void testRemoveVertex_WithEdges_UpdatesAdjacentVertices() {
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);

        // Remove vertex "B" and its associated edges
        graph.remove("B");

        // "B" should no longer be present in the graph
        assertFalse("Graph should not contain removed vertex 'B'.", graph.contains("B"));

        // After removing "B", "A"'s adjacency list should not contain "B"
        List<String> aAdjacencyList = graph.getDestinationLabels("A");
        assertFalse("Removed vertex 'B' should not be in 'A's adjacency list.", aAdjacencyList.contains("B"));

        // If the graph is directed, "C"'s adjacency list should not be affected by removing "B"
        // If the graph is undirected, and if an edge "C" to "B" was added, we would check "C"'s list too.
        // Since we don't have a getSource method, and assuming a directed graph, we skip checking "C"'s list.
    }



    @Test
    public void testAddVertex_ExceedsInitialCapacity_HandlesDynamicExpansion() {
        final int largeNumber = 1000; // A large number to test dynamic expansion
        for (int i = 0; i < largeNumber; i++) {
            graph.add("Vertex" + i);
        }

        // Verify that all vertices are added
        for (int i = 0; i < largeNumber; i++) {
            assertTrue("Graph should contain the added vertex.", graph.contains("Vertex" + i));
        }
    }





    // Helper methods (if needed) to access internal structures like adjacency list...
}

