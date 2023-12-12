import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class ListGraphTest {

    private ListGraph<String, String> listGraph;

    @Before
    public void setUp() {
        listGraph = new ListGraph<>();
    }

    @Test
    public void testAddVertex_AddsToList() {
        listGraph.add("Vertex1");
        assertTrue("ListGraph should contain the added vertex.", listGraph.contains("Vertex1"));
    }

    @Test
    public void testAddEdge_UpdatesAdjacencyList() {
        listGraph.add("Vertex1");
        listGraph.add("Vertex2");
        listGraph.addEdge("Vertex1", "Vertex2", "EdgeLabel");
        assertTrue("Adjacency list should reflect the edge between Vertex1 and Vertex2.",
                listGraph.containsEdge("Vertex1", "Vertex2"));
    }

    @Test
    public void testRemoveVertex_UpdatesList() {
        listGraph.add("Vertex1");
        listGraph.remove("Vertex1");
        assertFalse("ListGraph should no longer contain Vertex1 after removal.", listGraph.contains("Vertex1"));
    }

    @Test
    public void testRemoveEdge_UpdatesAdjacencyList() {
        listGraph.add("Vertex1");
        listGraph.add("Vertex2");
        listGraph.addEdge("Vertex1", "Vertex2", "EdgeLabel");
        listGraph.removeEdge("Vertex1", "Vertex2");
        assertFalse("Adjacency list should no longer reflect the edge between Vertex1 and Vertex2.",
                listGraph.containsEdge("Vertex1", "Vertex2"));
    }

    @Test
    public void testGetEdge_RetrievesCorrectEdgeFromList() {
        listGraph.add("Vertex1");
        listGraph.add("Vertex2");
        String edgeLabel = "EdgeLabel";
        listGraph.addEdge("Vertex1", "Vertex2", edgeLabel);
        Edge<String, String> edge = listGraph.getEdge("Vertex1", "Vertex2");
        assertNotNull("Should retrieve a non-null edge from the list.", edge);
        assertEquals("The retrieved edge should have the correct label.", edgeLabel, edge.getLabel());
    }

    @Test
    public void testDegree_CountsOutgoingEdgesFromList() {
        listGraph.add("Vertex1");
        listGraph.add("Vertex2");
        listGraph.addEdge("Vertex1", "Vertex2", "EdgeLabel");
        int degree = listGraph.degree("Vertex1");
        assertEquals("Degree should count the number of outgoing edges in the list.", 1, degree);
    }

    @Test
    public void testAddEdge_WithSelfLoop_UpdatesListCorrectly() {
        listGraph.add("Vertex1");
        listGraph.addEdge("Vertex1", "Vertex1", "SelfLoop");
        assertTrue("ListGraph should reflect the self-loop edge on Vertex1.",
                listGraph.containsEdge("Vertex1", "Vertex1"));
    }

    @Test
    public void testRemoveNonexistentVertexFromList_HandlesGracefully() {
        try {
            listGraph.remove("NonexistentVertex");
            assertTrue("Removing a nonexistent vertex should be handled without exceptions.", true);
        } catch (Exception e) {
            fail("Removing a nonexistent vertex should not throw an exception.");
        }
    }

    @Test
    public void testRemoveNonexistentEdgeFromList_HandlesGracefully() {
        listGraph.add("Vertex1");
        listGraph.add("Vertex2");
        try {
            listGraph.removeEdge("Vertex1", "Vertex2");
            assertTrue("Removing a nonexistent edge should be handled without exceptions.", true);
        } catch (Exception e) {
            fail("Removing a nonexistent edge should not throw an exception.");
        }
    }

    @Test
    public void testGetEdge_WhenEdgeDoesNotExistInList_ThrowsException() {
        listGraph.add("Vertex1");
        listGraph.add("Vertex2");
        try {
            listGraph.getEdge("Vertex1", "Vertex2");
            fail("Should throw NoSuchEdgeException for a non-existent edge.");
        } catch (NoSuchEdgeException e) {
            // Test passes
        }
    }

    @Test
    public void testInternalListStructure_AfterMultipleOperations() {
        // Add and remove vertices and edges in various combinations
        listGraph.add("Vertex1");
        listGraph.add("Vertex2");
        listGraph.addEdge("Vertex1", "Vertex2", "EdgeLabel1");
        listGraph.add("Vertex3");
        listGraph.addEdge("Vertex2", "Vertex3", "EdgeLabel2");
        listGraph.remove("Vertex1");
        listGraph.removeEdge("Vertex2", "Vertex3");

        // Verify the list reflects these changes correctly
        assertFalse("Vertex1 should no longer exist.", listGraph.contains("Vertex1"));
        assertFalse("Edge from Vertex2 to Vertex3 should no longer exist.", listGraph.containsEdge("Vertex2", "Vertex3"));
        assertTrue("Vertex2 should still exist.", listGraph.contains("Vertex2"));
        assertEquals("Graph size should be correct after multiple operations.", 2, listGraph.size());
    }

    @Test
    public void testListGraph_HandlingOfHighlyConnectedNodes() {
        // Create a graph where one node is highly connected
        listGraph.add("CentralNode");
        for (int i = 0; i < 10; i++) {
            String vertex = "Vertex" + i;
            listGraph.add(vertex);
            listGraph.addEdge("CentralNode", vertex, "Edge" + i);
        }

        // Verify that the central node has the correct degree
        assertEquals("Central node should have a degree equal to the number of connected nodes.",
                10, listGraph.degree("CentralNode"));
    }

    @Test
    public void testListGraph_PerformanceWithLargeNumberOfEdges() {
        // Add a large number of edges and measure performance (simple performance check)
        listGraph.add("Vertex1");
        listGraph.add("Vertex2");

        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            listGraph.addEdge("Vertex1", "Vertex2", "Edge" + i);
        }
        long endTime = System.nanoTime();

        // Check if operation completes in a reasonable time
        assertTrue("Adding a large number of edges should complete in a reasonable time.",
                (endTime - startTime) < 1000000000); // For example, less than 1 second
    }

    @Test
    public void testIntegration_MatrixAndListGraph_WithCommonOperations() {
        MatrixGraph<String, String> matrixGraph = new MatrixGraph<>();
        ListGraph<String, String> listGraph = new ListGraph<>();

        // Perform similar operations on both graphs
        matrixGraph.add("Vertex1");
        listGraph.add("Vertex1");

        matrixGraph.add("Vertex2");
        listGraph.add("Vertex2");

        matrixGraph.addEdge("Vertex1", "Vertex2", "Edge1");
        listGraph.addEdge("Vertex1", "Vertex2", "Edge1");

        // Verify the operations reflect similarly in both graphs
        assertTrue(matrixGraph.contains("Vertex1") && listGraph.contains("Vertex1"));
        assertTrue(matrixGraph.containsEdge("Vertex1", "Vertex2") && listGraph.containsEdge("Vertex1", "Vertex2"));
    }


    @Test
    public void testIntegration_WithExternalDataSources() {
        // Simulate loading data from an external source
        loadDataFromExternalSource(listGraph);

        // Perform operations on the graph after loading data
        assertFalse("Graph should not be empty after loading data.", listGraph.isEmpty());

        // Additional assertions can be added here based on the expected data structure
        // For example, checking for the presence of specific vertices or edges
    }

    // Simulate a method that loads data into the ListGraph
    private void loadDataFromExternalSource(ListGraph<String, String> graph) {
        // Mock-up of data loading. Replace this with actual data loading logic
        graph.add("ExternalVertex1");
        graph.add("ExternalVertex2");
        graph.addEdge("ExternalVertex1", "ExternalVertex2", "ExternalEdge");
    }


    @Test
    public void testIntegration_WithGraphTraversalAlgorithms() {
        // Populate the graph with data
        populateGraphWithTestData(listGraph);

        // Perform a graph traversal algorithm (BFS in this case)
        List<String> traversalResult = performBreadthFirstSearch(listGraph, "StartVertex");

        // Assertions based on expected traversal results
        assertNotNull("Traversal result should not be null.", traversalResult);
        // Further assertions based on expected traversal behavior
        // Example: Check if a specific vertex is in the traversal result
    }

    // Method to populate the graph with test data
    private void populateGraphWithTestData(ListGraph<String, String> graph) {
        // Add vertices
        graph.add("StartVertex");
        graph.add("Vertex1");
        graph.add("Vertex2");
        graph.add("Vertex3");

        // Add edges
        graph.addEdge("StartVertex", "Vertex1", "Edge1");
        graph.addEdge("Vertex1", "Vertex2", "Edge2");
        graph.addEdge("Vertex1", "Vertex3", "Edge3");
    }

    // BFS traversal
    private List<String> performBreadthFirstSearch(ListGraph<String, String> graph, String startVertex) {
        List<String> traversalResult = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            String currentVertex = queue.poll();
            traversalResult.add(currentVertex);

            Iterator<Vertex<String>> it = graph.adjacent(currentVertex);
            while (it.hasNext()) {
                Vertex<String> adjacentVertex = it.next();
                if (!visited.contains(adjacentVertex.getLabel())) {
                    visited.add(adjacentVertex.getLabel());
                    queue.add(adjacentVertex.getLabel());
                }
            }
        }

        return traversalResult;
    }

    @Test
    public void testIntegration_WithSerializationAndDeserialization() {
        // Populate the graph
        populateGraphWithTestData(listGraph);

        // Serialize the graph
        String serializedGraph = serializeGraph(listGraph);

        // Deserialize the graph
        ListGraph<String, String> deserializedGraph = deserializeGraph(serializedGraph);

        // Compare the original and deserialized graph
        assertTrue("Deserialized graph should match the original graph.", compareGraphs(listGraph, deserializedGraph));
    }

    private void populateGraphWitheTestData(ListGraph<String, String> graph) {
        graph.add("Vertex1");
        graph.add("Vertex2");
        graph.addEdge("Vertex1", "Vertex2", "Edge1");
    }

    private String serializeGraph(ListGraph<String, String> graph) {
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
             ObjectOutputStream objStream = new ObjectOutputStream(byteStream)) {
            objStream.writeObject(graph);
            return Base64.getEncoder().encodeToString(byteStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Serialization failed", e);
        }
    }

    private ListGraph<String, String> deserializeGraph(String serializedGraph) {
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(Base64.getDecoder().decode(serializedGraph));
             ObjectInputStream objStream = new ObjectInputStream(byteStream)) {
            return (ListGraph<String, String>) objStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Deserialization failed", e);
        }
    }

    private boolean compareGraphs(ListGraph<String, String> graph1, ListGraph<String, String> graph2) {
        // Basic comparison of graph sizes
        return graph1.size() == graph2.size() && graph1.edgeCount() == graph2.edgeCount();
        // Additional comparisons can be added based on edge and vertex content
    }

    @Test
    public void testIntegration_WithSerializationAndDeseriealization() {
        ListGraph<String, String> originalGraph = new ListGraph<>();
        // Populate the graph
        populateGraphWithTestData(originalGraph); // Replace with actual data population method

        // Serialize the graph
        String serializedGraph = serializeGraph(originalGraph); // Replace with actual serialization method

        // Deserialize the graph
        ListGraph<String, String> deserializedGraph = deserializeGraph(serializedGraph); // Replace with actual deserialization method

        // Compare the original and deserialized graph
        assertTrue("Deserialized graph should match the original graph.", compareGraphs(originalGraph, deserializedGraph));
    }



    @Test
    public void testIntegration_HandlingExceptionsAcrossGraphTypes() {
        MatrixGraph<String, String> matrixGraph = new MatrixGraph<>();
        ListGraph<String, String> listGraph = new ListGraph<>();

        // Cause an exception in both graphs and verify handling
        try {
            matrixGraph.getEdge("NonexistentVertex1", "NonexistentVertex2");
            fail("MatrixGraph should throw NoSuchEdgeException.");
        } catch (NoSuchEdgeException e) {
            // Expected exception
        }

        try {
            listGraph.getEdge("NonexistentVertex1", "NonexistentVertex2");
            fail("ListGraph should throw NoSuchEdgeException.");
        } catch (NoSuchEdgeException e) {
            // Expected exception
        }
    }

    @Test
    public void testEndToEnd_GraphUsageInApplicationWorkflow() {
        ListGraph<String, String> graph = new ListGraph<>();

        // Simulate typical workflow operations
        graph.add("Vertex1");
        graph.add("Vertex2");
        graph.addEdge("Vertex1", "Vertex2", "Edge1");

        // Verify graph state after operations
        assertTrue("Graph should contain Vertex1.", graph.contains("Vertex1"));
        assertTrue("Graph should contain Vertex2.", graph.contains("Vertex2"));
        assertTrue("Graph should contain edge from Vertex1 to Vertex2.", graph.containsEdge("Vertex1", "Vertex2"));
    }
    @Test
    public void testEndToEnd_UserInteractionWithGraphThroughUI() {
        ListGraph<String, String> graph = new ListGraph<>();

        // Simulate user adding vertices and edges through a UI
        // (In an actual test, this would involve UI interaction)
        graph.add("UserVertex1");
        graph.add("UserVertex2");
        graph.addEdge("UserVertex1", "UserVertex2", "UserEdge");

        // Verify the graph reflects these user actions
        assertTrue("Graph should contain the vertices and edge added by the user.",
                graph.contains("UserVertex1") && graph.contains("UserVertex2") && graph.containsEdge("UserVertex1", "UserVertex2"));
    }

    @Test
    public void testEndToEnd_GraphModificationAndResultingBehavior() {
        ListGraph<String, String> graph = new ListGraph<>();
        graph.add("ModVertex1");
        graph.add("ModVertex2");
        graph.addEdge("ModVertex1", "ModVertex2", "ModEdge");

        // Simulate user modifying the graph
        graph.remove("ModVertex1");

        // Verify the graph after modifications
        assertFalse("Graph should not contain removed vertices.", graph.contains("ModVertex1"));
        assertFalse("Graph should not contain edges related to removed vertices.", graph.containsEdge("ModVertex1", "ModVertex2"));
    }

    // ... (other test methods)
}

