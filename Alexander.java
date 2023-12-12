/**
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.HashMap;

public class Alexander
{

    // Alexander's DirectedGraphTest
    //      Contains tests for all spec'd classes in the UML.
    //      Version 1.0

    // Test Exception classes

    @Test
    public void ar_test_DirectedGraph_NSVE()
    {
        // Arrange
        RuntimeException nsve;
        RuntimeException nsveM;
        // Act
        nsve = new NoSuchVertexException();
        nsveM = new NoSuchVertexException("uh-oh");
        // Assert
        assertThat(nsve, instanceOf(NoSuchElementException.class));
        assertThat(nsve, instanceOf(NoSuchVertexException.class));
        assertThat(nsveM, instanceOf(NoSuchElementException.class));
        assertThat(nsveM, instanceOf(NoSuchVertexException.class));
    }

    @Test
    public void ar_test_DirectedGraph_NSEdgeE()
    {
        // Arrange
        RuntimeException nsee;
        RuntimeException nseeM;
        // Act
        nsee = new NoSuchEdgeException();
        nseeM = new NoSuchEdgeException("uh-oh");
        // Assert
        assertThat(nsee, instanceOf(NoSuchElementException.class));
        assertThat(nsee, instanceOf(NoSuchEdgeException.class));
        assertThat(nseeM, instanceOf(NoSuchElementException.class));
        assertThat(nseeM, instanceOf(NoSuchEdgeException.class));
    }

    @Test
    public void ar_test_DirectedGraph_DVE()
    {
        // Arrange
        RuntimeException dve;
        RuntimeException dveM;
        // Act
        dve = new DuplicateVertexException();
        dveM = new DuplicateVertexException("uh-oh");
        // Assert
        assertThat(dve, instanceOf(DuplicateVertexException.class));
        assertThat(dveM, instanceOf(DuplicateVertexException.class));
    }

    @Test
    public void ar_test_DirectedGraph_DEE()
    {
        // Arrange
        RuntimeException dee;
        RuntimeException deeM;
        // Act
        dee = new DuplicateEdgeException();
        deeM = new DuplicateEdgeException("uh-oh");
        // Assert
        assertThat(dee, instanceOf(DuplicateEdgeException.class));
        assertThat(deeM, instanceOf(DuplicateEdgeException.class));
    }


    // Test Vertex

    @Test
    public void ar_test_DirectedGraph_Vertex_ctor()
    {
        // Arrange
        Vertex<String> v;
        // Act
        v = new Vertex<>("A");
        // Assert
        assertNotNull(v);
        assertThat(v, instanceOf(Vertex.class));
    }

    @Test
    public void ar_test_DirectedGraph_Vertex_ctor_IAE()
    {
        // Arrange
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { new Vertex<String>(null); });
    }

    @Test
    public void ar_test_DirectedGraph_Vertex_getLabel()
    {
        // Arrange
        Vertex<String> v;
        // Act
        v = new Vertex<>("A");
        // Assert
        assertEquals("A", v.getLabel());
    }

    @Test
    public void ar_test_DirectedGraph_Vertex_eq_IAE()
    {
        // Arrange
        Vertex<String> v;
        // Act
        v = new Vertex<>("A");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { v.equals(null); });
    }

    @Test
    public void ar_test_DirectedGraph_Vertex_eq_true()
    {
        // Arrange
        Vertex<Boolean> v;
        // Act
        v = new Vertex<>(false);
        // Assert
        assertTrue(v.equals(new Vertex<Boolean>(false)));
    }

    @Test
    public void ar_test_DirectedGraph_Vertex_eq_false()
    {
        // Arrange
        Vertex<Boolean> v;
        // Act
        v = new Vertex<>(false);
        // Assert
        assertFalse(v.equals(new Vertex<Boolean>(true)));
    }


    // Test Edge

    @Test
    public void ar_test_DirectedGraph_Edge_ctor()
    {
        // Arrange
        Edge<String, Boolean> e;
        // Act
        e = new Edge<>("A", "B", true);
        // Assert
        assertNotNull(e);
        assertThat(e, instanceOf(Edge.class));
    }

    @Test
    public void ar_test_DirectedGraph_Edge_u_IAE()
    {
        // Arrange
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { new Edge<>(null, "B", true); });
    }

    @Test
    public void ar_test_DirectedGraph_Edge_v_IAE()
    {
        // Arrange
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { new Edge<>("A", null, true); });
    }

    @Test
    public void ar_test_DirectedGraph_Edge_IAE()
    {
        // Arrange
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { new Edge<>("A", "B", null); });
    }

    @Test
    public void ar_test_DirectedGraph_Edge_accessors()
    {
        // Arrange
        Edge<String, Boolean> e;
        // Act
        e = new Edge<>("A", "B", true);
        // Assert
        assertEquals("A", e.getU());
        assertEquals("B", e.getV());
        assertEquals(true, e.getLabel());
    }

    @Test
    public void ar_test_DirectedGraph_Edge_setLabel_IAE()
    {
        // Arrange
        Edge<String, Boolean> e;
        // Act
        e = new Edge<>("A", "B", true);
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { e.setLabel(null); });
    }

    @Test
    public void ar_test_DirectedGraph_Edge_setLabel()
    {
        // Arrange
        Edge<String, Boolean> e;
        // Act
        e = new Edge<>("A", "B", true);
        e.setLabel(false);
        // Assert
        assertEquals("A", e.getU());
        assertEquals("B", e.getV());
        assertEquals(false, e.getLabel());
    }

    @Test
    public void ar_test_DirectedGraph_Edge_equal_IAE()
    {
        // Arrange
        Edge<String, Boolean> e;
        // Act
        e = new Edge<>("A", "B", true);
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { e.equals(null); });
    }

    @Test
    public void ar_test_DirectedGraph_Edge_equal_true()
    {
        // Arrange
        Edge<String, Boolean> e1;
        Edge<String, Boolean> e2;
        // Act
        e1 = new Edge<>("A", "B", false);
        e2 = new Edge<>("A", "B", true);
        // Assert
        assertTrue(e1.equals(e2));
    }

    @Test
    public void ar_test_DirectedGraph_Edge_equal_falseU()
    {
        // Arrange
        Edge<String, Boolean> e1;
        Edge<String, Boolean> e2;
        // Act
        e1 = new Edge<>("A", "B", true);
        e2 = new Edge<>("C", "B", true);
        // Assert
        assertFalse(e1.equals(e2));
    }

    @Test
    public void ar_test_DirectedGraph_Edge_equal_falseV()
    {
        // Arrange
        Edge<String, Boolean> e1;
        Edge<String, Boolean> e2;
        // Act
        e1 = new Edge<>("A", "B", true);
        e2 = new Edge<>("A", "D", true);
        // Assert
        assertFalse(e1.equals(e2));
    }

    //
    // Test MatrixGraph
    //


    // Test MG ctor

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_ctor_noarg()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        // Assert
        assertNotNull(mg);
        assertThat(mg, instanceOf(DirectedGraph.class));
        assertThat(mg, instanceOf(MatrixGraph.class));
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_ctor_witharg()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>(10);
        // Assert
        assertNotNull(mg);
        assertThat(mg, instanceOf(DirectedGraph.class));
        assertThat(mg, instanceOf(MatrixGraph.class));
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_ctor_withargZero()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>(0);
        // Assert
        assertNotNull(mg);
        assertThat(mg, instanceOf(DirectedGraph.class));
        assertThat(mg, instanceOf(MatrixGraph.class));
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_ctor_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { new MatrixGraph(-1); });
    }


    // Test MG add

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_add_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.add(null); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_add_DVE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        // Assert
        assertThrows(DuplicateVertexException.class,
                () -> { mg.add("A"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_add_noGrow()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        // Assert
        assertEquals(0, mg.size());
        mg.add("A");
        assertEquals(1, mg.size());
        assertEquals("A", mg.get("A").getLabel());
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_add_grow()
    {
        // Arrange
        DirectedGraph<Integer, Boolean> mg;
        // Act
        mg = new MatrixGraph<>(1);
        for (int i = 0; i < 21; i++)
        {
            mg.add(i);
        }
        // Assert
        assertEquals(21, mg.size());
        for (int i = 0; i < 21; i++)
        {
            assertEquals(i, (int)mg.get(i).getLabel());
        }
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_add_fromZero()
    {
        // Arrange
        DirectedGraph<Integer, Boolean> mg;
        // Act
        mg = new MatrixGraph<>(0);
        for (int i = 0; i < 21; i++)
        {
            mg.add(i);
        }
        // Assert
        assertEquals(21, mg.size());
        for (int i = 0; i < 21; i++)
        {
            assertEquals(i, (int)mg.get(i).getLabel());
        }
    }


    // Test MG get

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_get_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.get(null); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_get_NSVE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { mg.get("A"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_get_simple()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        Vertex<String> a;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        a = mg.get("A");
        // Assert
        assertThat(a, instanceOf(Vertex.class));
        assertEquals("A", a.getLabel());
    }


    // Test MG contains

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_contains_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.contains(null); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_contains_false()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        // Assert
        assertFalse(mg.contains("B"));
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_contains_true()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        // Assert
        assertTrue(mg.contains("A"));
    }


    // Test MG addEdge

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_addEdge_IAE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.addEdge(null, "B", false); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_addEdge_IAE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.addEdge("A", null, false); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_addEdge_IAE_label()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.addEdge("A", "B", null); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_addEdge_DEE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.addEdge("A", "B", false);
        // Assert
        assertThrows(DuplicateEdgeException.class,
                () -> { mg.addEdge("A", "B", true); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_addEdge_valid()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.addEdge("A", "B", false);
        // Assert
        assertTrue(mg.containsEdge("A", "B"));
    }


    // Test MG containsEdge

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_containsEdge_IAE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.containsEdge(null, "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_containsEdge_IAE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.containsEdge("A", null); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_containsEdge_NSVE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { mg.containsEdge("C", "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_containsEdge_NSVE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { mg.containsEdge("A", "D"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_containsEdge_true()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.addEdge("A", "B", false);
        // Assert
        assertTrue(mg.containsEdge("A", "B"));
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_containsEdge_false()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertFalse(mg.containsEdge("A", "B"));
    }


    // Test MG getEdge

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_getEdge_IAE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.getEdge(null, "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_getEdge_IAE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.getEdge("A", null); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_getEdge_NSVE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { mg.getEdge("C", "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_getEdge_NSVE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { mg.getEdge("A", "D"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_getEdge_NSEE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(NoSuchEdgeException.class,
                () -> { mg.getEdge("A", "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_getEdge_simple()
    {
        // Arrange
        DirectedGraph<String, String> mg;
        Edge<String, String> e;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.addEdge("A", "B", "ab");
        e = mg.getEdge("A", "B");
        // Assert
        assertThat(e, instanceOf(Edge.class));
        assertEquals("ab", e.getLabel());
    }


    // Test removeEdge

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_removeEdge_IAE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.removeEdge(null, "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_removeEdge_IAE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.removeEdge("A", null); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_removeEdge_NSVE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { mg.removeEdge("C", "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_removeEdge_NSVE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { mg.removeEdge("A", "D"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_removeEdge_NSEE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertThrows(NoSuchEdgeException.class,
                () -> { mg.removeEdge("A", "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_removeEdge_simple()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        // Assert
        assertFalse(mg.containsEdge("A", "B"));
        mg.addEdge("A", "B", true);
        assertTrue(mg.containsEdge("A", "B"));
        assertTrue(mg.getEdge("A", "B").getLabel());
        assertTrue(mg.removeEdge("A", "B"));
        assertFalse(mg.containsEdge("A", "B"));
    }


    // Test MG size


    // Test MG degree

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_degree_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.degree(null); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_degree_NSVE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { mg.degree("A"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_degree_outgoing()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.add("C");
        mg.add("D");
        // Assert
        assertEquals(0, mg.degree("A"));
        mg.addEdge("A", "A", true);
        assertEquals(1, mg.degree("A"));
        mg.addEdge("A", "B", true);
        assertEquals(2, mg.degree("A"));
        mg.addEdge("A", "C", true);
        assertEquals(3, mg.degree("A"));
        mg.addEdge("A", "D", true);
        assertEquals(4, mg.degree("A"));
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_degree_incoming()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.add("C");
        mg.add("D");
        // Assert
        assertEquals(0, mg.degree("A"));
        mg.addEdge("B", "A", true);
        assertEquals(0, mg.degree("A"));
        mg.addEdge("C", "A", true);
        assertEquals(0, mg.degree("A"));
        mg.addEdge("D", "A", true);
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_degree_zero()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        // Assert
        assertEquals(0, mg.degree("A"));
    }


    // Test MG edgeCount

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_edgeCount_empty()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>(0);
        // Assert
        assertEquals(0, mg.edgeCount());
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_edgeCount_noEdges()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.add("C");
        mg.add("D");
        // Assert
        assertEquals(0, mg.edgeCount());
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_edgeCount_full()
    {
        // Arrange
        DirectedGraph<Integer, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        for (int i = 0; i < 4; i++)
        {
            mg.add(i);
        }

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                mg.addEdge(i, j, true);
            }
        }
        // Assert
        assertEquals(16, mg.edgeCount());
    }


    // Test MG vertices

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_vertices_ctor()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        Iterator<Vertex<String>> iter;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.add("C");
        mg.add("D");
        iter = mg.vertices();
        // Assert
        assertNotNull(iter);
        assertThat(iter, instanceOf(Iterator.class));
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_vertices_empty()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        Iterator<Vertex<String>> iter;
        // Act
        mg = new MatrixGraph<>();
        iter = mg.vertices();
        // Assert
        assertNotNull(iter);
        assertThat(iter, instanceOf(Iterator.class));
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_vertices_simple()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        Iterator<Vertex<String>> iter;
        Vertex<String> v;
        HashMap<String, Boolean> hm = new HashMap<>();
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        hm.put("A", false);
        mg.add("B");
        hm.put("B", false);
        mg.add("C");
        hm.put("C", false);
        mg.add("D");
        hm.put("D", false);
        iter = mg.vertices();
        // Assert
        for (int i = 0; i < 4; i++)
        {
            v = iter.next();
            assertTrue(hm.containsKey(v.getLabel()));
            hm.remove(v.getLabel());
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_vertices_withRemove()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        Iterator<Vertex<String>> iter;
        Vertex<String> v;
        HashMap<String, Boolean> hm = new HashMap<>();
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        hm.put("A", false);
        mg.add("B");
        hm.put("B", false);
        mg.add("C");
        hm.put("C", false);
        mg.add("D");
        hm.put("D", false);
        mg.add("E");
        mg.remove("E");
        iter = mg.vertices();
        // Assert
        for (int i = 0; i < 4; i++)
        {
            v = iter.next();
            assertTrue(hm.containsKey(v.getLabel()));
            hm.remove(v.getLabel());
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_vertices_failSafe()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        Iterator<Vertex<String>> iter;
        Vertex<String> v;
        HashMap<String, Boolean> hm = new HashMap<>();
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        hm.put("A", false);
        mg.add("B");
        hm.put("B", false);
        mg.add("C");
        hm.put("C", false);
        mg.add("D");
        hm.put("D", false);
        iter = mg.vertices();
        mg.add("E");
        mg.remove("A");
        // Assert
        for (int i = 0; i < 4; i++)
        {
            v = iter.next();
            assertTrue(hm.containsKey(v.getLabel()));
            hm.remove(v.getLabel());
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }


    // Test MG edges

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_edges_ctor()
    {
        // Arrange
        DirectedGraph<Integer, Boolean> mg;
        Iterator<Edge<Integer, Boolean>> iter;
        // Act
        mg = new MatrixGraph<>();
        for (int i = 0; i < 4; i++)
        {
            mg.add(i);
        }

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                mg.addEdge(i, j, true);
            }
        }
        iter = mg.edges();
        // Assert
        assertNotNull(iter);
        assertThat(iter, instanceOf(Iterator.class));
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_edges_simple()
    {
        // Arrange
        DirectedGraph<Character, Character> mg;
        Iterator<Edge<Character, Character>> iter;
        Edge<Character, Character> e;
        HashMap<String, Character> hm = new HashMap<>();
        // Act
        char c = 'a';
        mg = new MatrixGraph<>();
        for (char i = 'A'; i <= 'D'; i++)
        {
            mg.add(i);
        }

        for (char i = 'A'; i <= 'D'; i++)
        {
            for (char j = 'A'; j <= 'D'; j++)
            {
                mg.addEdge(i, j, c);
                hm.put(String.valueOf(i) + String.valueOf(j), c++);
            }
        }
        iter = mg.edges();
        // Assert
        for (char i = 'A'; i <= 'D'; i++)
        {
            for (char j = 'A'; j <= 'D'; j++)
            {
                e = iter.next();
                assertTrue(hm.containsKey(String.valueOf(i) + String.valueOf(j)));
                hm.remove(String.valueOf(i) + String.valueOf(j));
            }
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_edges_withRemove()
    {
        // Arrange
        DirectedGraph<Character, Character> mg;
        Iterator<Edge<Character, Character>> iter;
        Edge<Character, Character> e;
        HashMap<String, Character> hm = new HashMap<>();
        // Act
        char c = 'a';
        mg = new MatrixGraph<>();
        for (char i = 'A'; i <= 'E'; i++)
        {
            mg.add(i);
        }

        for (char i = 'A'; i <= 'E'; i++)
        {
            for (char j = 'A'; j <= 'E'; j++)
            {
                mg.addEdge(i, j, c);
                if (i != j && i != 'E' &&  j != 'E')
                {
                    hm.put(String.valueOf(i) + String.valueOf(j), c++);
                }
            }
        }
        mg.removeEdge('A', 'A');
        mg.removeEdge('B', 'B');
        mg.removeEdge('C', 'C');
        mg.removeEdge('D', 'D');
        mg.remove('E');
        iter = mg.edges();
        // Assert
        for (char i = 'A'; i <= 'D'; i++)
        {
            for (char j = 'A'; j <= 'D'; j++)
            {
                if (i != j)
                {
                    e = iter.next();
                    assertTrue(hm.containsKey(String.valueOf(i) + String.valueOf(j)));
                    hm.remove(String.valueOf(i) + String.valueOf(j));
                }
            }
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_edges_failSafe()
    {
        // Arrange
        DirectedGraph<Character, Character> mg;
        Iterator<Edge<Character, Character>> iter;
        Edge<Character, Character> e;
        HashMap<String, Character> hm = new HashMap<>();
        // Act
        mg = new MatrixGraph<>();
        char c = 'a';
        for (char i = 'A'; i <= 'D'; i++)
        {
            mg.add(i);
        }

        for (char i = 'A'; i <= 'D'; i++)
        {
            for (char j = 'A'; j <= 'D'; j++)
            {
                mg.addEdge(i, j, c);
                hm.put(String.valueOf(i) + String.valueOf(j), c++);
            }
        }
        iter = mg.edges();
        mg.add('E');
        mg.addEdge('E', 'A', 'a');
        mg.remove('B');
        // Assert
        for (char i = 'A'; i <= 'D'; i++)
        {
            for (char j = 'A'; j <= 'D'; j++)
            {
                e = iter.next();
                assertTrue(hm.containsKey(String.valueOf(i) + String.valueOf(j)));
                hm.remove(String.valueOf(i) + String.valueOf(j));
            }
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }


    // Test MG adjacent

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_adjacent_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.adjacent(null); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_adjacent_NSVE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { mg.adjacent("A"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_adjacent_empty()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        Iterator<Vertex<String>> iter;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.add("C");
        mg.add("D");
        iter = mg.adjacent("A");
        // Assert
        assertNotNull(iter);
        assertThat(iter, instanceOf(Iterator.class));
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_adjacent_self()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        Iterator<Vertex<String>> iter;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.add("C");
        mg.addEdge("A", "A", true);
        mg.addEdge("B", "A", true);
        iter = mg.adjacent("A");
        // Assert
        assertEquals("A", iter.next().getLabel());
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_adjacent_simple()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        Iterator<Vertex<String>> iter;
        Vertex<String> v;
        HashMap<String, Boolean> hm = new HashMap<>();
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.add("C");
        mg.addEdge("A", "A", true);
        hm.put("A", false);
        mg.addEdge("A", "B", true);
        hm.put("B", false);
        mg.addEdge("A", "C", true);
        hm.put("C", false);
        iter = mg.adjacent("A");
        // Assert
        for (int i = 0; i < 3; i++)
        {
            v = iter.next();
            assertTrue(hm.containsKey(v.getLabel()));
            hm.remove(v.getLabel());
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_adjacent_failSafe()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        Iterator<Vertex<String>> iter;
        Vertex<String> v;
        HashMap<String, Boolean> hm = new HashMap<>();
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.add("C");
        mg.addEdge("A", "A", true);
        hm.put("A", false);
        mg.addEdge("A", "B", true);
        hm.put("B", false);
        mg.addEdge("A", "C", true);
        hm.put("C", false);
        iter = mg.adjacent("A");
        mg.add("D");
        mg.addEdge("A", "D", true);
        // Assert
        for (int i = 0; i < 3; i++)
        {
            v = iter.next();
            assertTrue(hm.containsKey(v.getLabel()));
            hm.remove(v.getLabel());
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_adjacent_remove()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        Iterator<Vertex<String>> iter;
        Vertex<String> v;
        HashMap<String, Boolean> hm = new HashMap<>();
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.add("C");
        mg.addEdge("A", "A", true);
        hm.put("A", false);
        mg.addEdge("A", "B", true);
        hm.put("B", false);
        mg.addEdge("A", "C", true);
        hm.put("C", false);
        mg.remove("B");
        iter = mg.adjacent("A");
        // Assert
        for (int i = 0; i < 2; i++)
        {
            v = iter.next();
            assertTrue(hm.containsKey(v.getLabel()));
            hm.remove(v.getLabel());
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }


    // Test MG remove

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_remove_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { mg.remove(null); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_remove_NSVE()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { mg.remove("A"); });
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_remove_simple()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        // Assert
        assertEquals("A", mg.remove("A"));
        assertTrue(mg.isEmpty());
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_remove_edges()
    {
        // Arrange
        DirectedGraph<Character, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        for (char i = 'A'; i <= 'D'; i++)
        {
            mg.add(i);
        }

        for (char i = 'A'; i <= 'D'; i++)
        {
            for (char j = 'A'; j <= 'D'; j++)
            {
                mg.addEdge(i, j, true);
            }
        }
        // Assert
        assertEquals(16, mg.edgeCount());
        assertEquals(4, mg.size());

        assertEquals('A', (char)mg.remove('A'));
        assertFalse(mg.contains('A'));
        assertEquals(9, mg.edgeCount());
        assertEquals(3, mg.size());

        assertEquals('B', (char)mg.remove('B'));
        assertFalse(mg.contains('B'));
        assertEquals(4, mg.edgeCount());
        assertEquals(2, mg.size());

        assertEquals('C', (char)mg.remove('C'));
        assertFalse(mg.contains('C'));
        assertEquals(1, mg.edgeCount());
        assertEquals(1, mg.size());

        assertEquals('D', (char)mg.remove('D'));
        assertFalse(mg.contains('D'));
        assertEquals(0, mg.edgeCount());
        assertEquals(0, mg.size());

        assertTrue(mg.isEmpty());
    }


    // Test MG clear

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_clear_basic()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>();
        mg.add("A");
        mg.add("B");
        mg.add("C");
        mg.add("D");
        mg.addEdge("A", "A", true);
        mg.addEdge("B", "B", true);
        mg.addEdge("C", "C", true);
        mg.addEdge("D", "D", true);
        // Assert
        assertEquals(4, mg.size());
        assertEquals(4, mg.edgeCount());
        mg.clear();
        assertEquals(0, mg.size());
        assertEquals(0, mg.edgeCount());
        assertTrue(mg.isEmpty());
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_clear_onZeroCtor()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>(0);
        // Assert
        mg.clear();
        assertTrue(mg.isEmpty());
    }


    // Test MG isEmpty

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_isEmpty_empty()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>(0);
        // Assert
        assertTrue(mg.isEmpty());
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_isEmpty_false()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>(0);
        mg.add("A");
        // Assert
        assertFalse(mg.isEmpty());
    }

    @Test
    public void ar_test_DirectedGraph_MatrixGraph_isEmpty_true()
    {
        // Arrange
        DirectedGraph<String, Boolean> mg;
        // Act
        mg = new MatrixGraph<>(0);
        mg.add("A");
        mg.remove("A");
        // Assert
        assertTrue(mg.isEmpty());
    }

    //
    // Test ListGraph
    //


    // Test LG ctor

    @Test
    public void ar_test_DirectedGraph_ListGraph_ctor_noarg()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertNotNull(lg);
        assertThat(lg, instanceOf(DirectedGraph.class));
        assertThat(lg, instanceOf(ListGraph.class));
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_ctor_witharg()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertNotNull(lg);
        assertThat(lg, instanceOf(DirectedGraph.class));
        assertThat(lg, instanceOf(ListGraph.class));
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_ctor_withargZero()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertNotNull(lg);
        assertThat(lg, instanceOf(DirectedGraph.class));
        assertThat(lg, instanceOf(ListGraph.class));
    }


    // Test LG add

    @Test
    public void ar_test_DirectedGraph_ListGraph_add_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.add(null); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_add_DVE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        // Assert
        assertThrows(DuplicateVertexException.class,
                () -> { lg.add("A"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_add_noGrow()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertEquals(0, lg.size());
        lg.add("A");
        assertEquals(1, lg.size());
        assertEquals("A", lg.get("A").getLabel());
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_add_grow()
    {
        // Arrange
        DirectedGraph<Integer, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        for (int i = 0; i < 21; i++)
        {
            lg.add(i);
        }
        // Assert
        assertEquals(21, lg.size());
        for (int i = 0; i < 21; i++)
        {
            assertEquals(i, (int)lg.get(i).getLabel());
        }
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_add_fromZero()
    {
        // Arrange
        DirectedGraph<Integer, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        for (int i = 0; i < 21; i++)
        {
            lg.add(i);
        }
        // Assert
        assertEquals(21, lg.size());
        for (int i = 0; i < 21; i++)
        {
            assertEquals(i, (int)lg.get(i).getLabel());
        }
    }


    // Test LG get

    @Test
    public void ar_test_DirectedGraph_ListGraph_get_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.get(null); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_get_NSVE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { lg.get("A"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_get_simple()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        Vertex<String> a;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        a = lg.get("A");
        // Assert
        assertThat(a, instanceOf(Vertex.class));
        assertEquals("A", a.getLabel());
    }


    // Test LG contains

    @Test
    public void ar_test_DirectedGraph_ListGraph_contains_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.contains(null); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_contains_false()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        // Assert
        assertFalse(lg.contains("B"));
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_contains_true()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        // Assert
        assertTrue(lg.contains("A"));
    }


    // Test LG addEdge

    @Test
    public void ar_test_DirectedGraph_ListGraph_addEdge_IAE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.addEdge(null, "B", false); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_addEdge_IAE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.addEdge("A", null, false); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_addEdge_IAE_label()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.addEdge("A", "B", null); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_addEdge_DEE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.addEdge("A", "B", false);
        // Assert
        assertThrows(DuplicateEdgeException.class,
                () -> { lg.addEdge("A", "B", true); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_addEdge_valid()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.addEdge("A", "B", false);
        // Assert
        assertTrue(lg.containsEdge("A", "B"));
    }


    // Test LG containsEdge

    @Test
    public void ar_test_DirectedGraph_ListGraph_containsEdge_IAE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.containsEdge(null, "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_containsEdge_IAE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.containsEdge("A", null); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_containsEdge_NSVE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { lg.containsEdge("C", "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_containsEdge_NSVE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { lg.containsEdge("A", "D"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_containsEdge_true()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.addEdge("A", "B", false);
        // Assert
        assertTrue(lg.containsEdge("A", "B"));
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_containsEdge_false()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertFalse(lg.containsEdge("A", "B"));
    }


    // Test LG getEdge

    @Test
    public void ar_test_DirectedGraph_ListGraph_getEdge_IAE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.getEdge(null, "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_getEdge_IAE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.getEdge("A", null); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_getEdge_NSVE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { lg.getEdge("C", "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_getEdge_NSVE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { lg.getEdge("A", "D"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_getEdge_NSEE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(NoSuchEdgeException.class,
                () -> { lg.getEdge("A", "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_getEdge_simple()
    {
        // Arrange
        DirectedGraph<String, String> lg;
        Edge<String, String> e;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.addEdge("A", "B", "ab");
        e = lg.getEdge("A", "B");
        // Assert
        assertThat(e, instanceOf(Edge.class));
        assertEquals("ab", e.getLabel());
    }


    // Test removeEdge

    @Test
    public void ar_test_DirectedGraph_ListGraph_removeEdge_IAE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.removeEdge(null, "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_removeEdge_IAE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.removeEdge("A", null); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_removeEdge_NSVE_source()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { lg.removeEdge("C", "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_removeEdge_NSVE_dest()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { lg.removeEdge("A", "D"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_removeEdge_NSEE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertThrows(NoSuchEdgeException.class,
                () -> { lg.removeEdge("A", "B"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_removeEdge_simple()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        // Assert
        assertFalse(lg.containsEdge("A", "B"));
        lg.addEdge("A", "B", true);
        assertTrue(lg.containsEdge("A", "B"));
        assertTrue(lg.getEdge("A", "B").getLabel());
        assertTrue(lg.removeEdge("A", "B"));
        assertFalse(lg.containsEdge("A", "B"));
    }


    // Test LG size


    // Test LG degree

    @Test
    public void ar_test_DirectedGraph_ListGraph_degree_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.degree(null); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_degree_NSVE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { lg.degree("A"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_degree_outgoing()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.add("C");
        lg.add("D");
        // Assert
        assertEquals(0, lg.degree("A"));
        lg.addEdge("A", "A", true);
        assertEquals(1, lg.degree("A"));
        lg.addEdge("A", "B", true);
        assertEquals(2, lg.degree("A"));
        lg.addEdge("A", "C", true);
        assertEquals(3, lg.degree("A"));
        lg.addEdge("A", "D", true);
        assertEquals(4, lg.degree("A"));
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_degree_incoming()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.add("C");
        lg.add("D");
        // Assert
        assertEquals(0, lg.degree("A"));
        lg.addEdge("B", "A", true);
        assertEquals(0, lg.degree("A"));
        lg.addEdge("C", "A", true);
        assertEquals(0, lg.degree("A"));
        lg.addEdge("D", "A", true);
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_degree_zero()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        // Assert
        assertEquals(0, lg.degree("A"));
    }


    // Test LG edgeCount

    @Test
    public void ar_test_DirectedGraph_ListGraph_edgeCount_empty()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertEquals(0, lg.edgeCount());
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_edgeCount_noEdges()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.add("C");
        lg.add("D");
        // Assert
        assertEquals(0, lg.edgeCount());
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_edgeCount_full()
    {
        // Arrange
        DirectedGraph<Integer, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        for (int i = 0; i < 4; i++)
        {
            lg.add(i);
        }

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                lg.addEdge(i, j, true);
            }
        }
        // Assert
        assertEquals(16, lg.edgeCount());
    }



    // Test LG vertices

    @Test
    public void ar_test_DirectedGraph_ListGraph_vertices_ctor()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        Iterator<Vertex<String>> iter;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.add("C");
        lg.add("D");
        iter = lg.vertices();
        // Assert
        assertNotNull(iter);
        assertThat(iter, instanceOf(Iterator.class));
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_vertices_empty()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        Iterator<Vertex<String>> iter;
        // Act
        lg = new ListGraph<>();
        iter = lg.vertices();
        // Assert
        assertNotNull(iter);
        assertThat(iter, instanceOf(Iterator.class));
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_vertices_simple()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        Iterator<Vertex<String>> iter;
        Vertex<String> v;
        HashMap<String, Boolean> hm = new HashMap<>();
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        hm.put("A", false);
        lg.add("B");
        hm.put("B", false);
        lg.add("C");
        hm.put("C", false);
        lg.add("D");
        hm.put("D", false);
        iter = lg.vertices();
        // Assert
        for (int i = 0; i < 4; i++)
        {
            v = iter.next();
            assertTrue(hm.containsKey(v.getLabel()));
            hm.remove(v.getLabel());
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_vertices_withRemove()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        Iterator<Vertex<String>> iter;
        Vertex<String> v;
        HashMap<String, Boolean> hm = new HashMap<>();
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        hm.put("A", false);
        lg.add("B");
        hm.put("B", false);
        lg.add("C");
        hm.put("C", false);
        lg.add("D");
        hm.put("D", false);
        lg.add("E");
        lg.remove("E");
        iter = lg.vertices();
        // Assert
        for (int i = 0; i < 4; i++)
        {
            v = iter.next();
            assertTrue(hm.containsKey(v.getLabel()));
            hm.remove(v.getLabel());
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_vertices_failSafe()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        Iterator<Vertex<String>> iter;
        Vertex<String> v;
        HashMap<String, Boolean> hm = new HashMap<>();
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        hm.put("A", false);
        lg.add("B");
        hm.put("B", false);
        lg.add("C");
        hm.put("C", false);
        lg.add("D");
        hm.put("D", false);
        iter = lg.vertices();
        lg.add("E");
        lg.remove("A");
        // Assert
        for (int i = 0; i < 4; i++)
        {
            v = iter.next();
            assertTrue(hm.containsKey(v.getLabel()));
            hm.remove(v.getLabel());
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }


    // Test LG edges

    @Test
    public void ar_test_DirectedGraph_ListGraph_edges_ctor()
    {
        // Arrange
        DirectedGraph<Integer, Boolean> lg;
        Iterator<Edge<Integer, Boolean>> iter;
        // Act
        lg = new ListGraph<>();
        for (int i = 0; i < 4; i++)
        {
            lg.add(i);
        }

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                lg.addEdge(i, j, true);
            }
        }
        iter = lg.edges();
        // Assert
        assertNotNull(iter);
        assertThat(iter, instanceOf(Iterator.class));
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_edges_simple()
    {
        // Arrange
        DirectedGraph<Character, Character> lg;
        Iterator<Edge<Character, Character>> iter;
        Edge<Character, Character> e;
        HashMap<String, Character> hm = new HashMap<>();
        // Act
        char c = 'a';
        lg = new ListGraph<>();
        for (char i = 'A'; i <= 'D'; i++)
        {
            lg.add(i);
        }

        for (char i = 'A'; i <= 'D'; i++)
        {
            for (char j = 'A'; j <= 'D'; j++)
            {
                lg.addEdge(i, j, c);
                hm.put(String.valueOf(i) + String.valueOf(j), c++);
            }
        }
        iter = lg.edges();
        // Assert
        for (char i = 'A'; i <= 'D'; i++)
        {
            for (char j = 'A'; j <= 'D'; j++)
            {
                e = iter.next();
                assertTrue(hm.containsKey(String.valueOf(i) + String.valueOf(j)));
                hm.remove(String.valueOf(i) + String.valueOf(j));
            }
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_edges_withRemove()
    {
        // Arrange
        DirectedGraph<Character, Character> lg;
        Iterator<Edge<Character, Character>> iter;
        Edge<Character, Character> e;
        HashMap<String, Character> hm = new HashMap<>();
        // Act
        char c = 'a';
        lg = new ListGraph<>();
        for (char i = 'A'; i <= 'E'; i++)
        {
            lg.add(i);
        }

        for (char i = 'A'; i <= 'E'; i++)
        {
            for (char j = 'A'; j <= 'E'; j++)
            {
                lg.addEdge(i, j, c);
                if (i != j && i != 'E' &&  j != 'E')
                {
                    hm.put(String.valueOf(i) + String.valueOf(j), c++);
                }
            }
        }
        lg.removeEdge('A', 'A');
        lg.removeEdge('B', 'B');
        lg.removeEdge('C', 'C');
        lg.removeEdge('D', 'D');
        lg.remove('E');
        iter = lg.edges();
        // Assert
        for (char i = 'A'; i <= 'D'; i++)
        {
            for (char j = 'A'; j <= 'D'; j++)
            {
                if (i != j)
                {
                    e = iter.next();
                    assertTrue(hm.containsKey(String.valueOf(i) + String.valueOf(j)));
                    hm.remove(String.valueOf(i) + String.valueOf(j));
                }
            }
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_edges_failSafe()
    {
        // Arrange
        DirectedGraph<Character, Character> lg;
        Iterator<Edge<Character, Character>> iter;
        Edge<Character, Character> e;
        HashMap<String, Character> hm = new HashMap<>();
        // Act
        lg = new ListGraph<>();
        char c = 'a';
        for (char i = 'A'; i <= 'D'; i++)
        {
            lg.add(i);
        }

        for (char i = 'A'; i <= 'D'; i++)
        {
            for (char j = 'A'; j <= 'D'; j++)
            {
                lg.addEdge(i, j, c);
                hm.put(String.valueOf(i) + String.valueOf(j), c++);
            }
        }
        iter = lg.edges();
        lg.add('E');
        lg.addEdge('E', 'A', 'a');
        lg.remove('B');
        // Assert
        for (char i = 'A'; i <= 'D'; i++)
        {
            for (char j = 'A'; j <= 'D'; j++)
            {
                e = iter.next();
                assertTrue(hm.containsKey(String.valueOf(i) + String.valueOf(j)));
                hm.remove(String.valueOf(i) + String.valueOf(j));
            }
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }


    // Test LG adjacent

    @Test
    public void ar_test_DirectedGraph_ListGraph_adjacent_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.adjacent(null); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_adjacent_NSVE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { lg.adjacent("A"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_adjacent_empty()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        Iterator<Vertex<String>> iter;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.add("C");
        lg.add("D");
        iter = lg.adjacent("A");
        // Assert
        assertNotNull(iter);
        assertThat(iter, instanceOf(Iterator.class));
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_adjacent_self()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        Iterator<Vertex<String>> iter;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.add("C");
        lg.addEdge("A", "A", true);
        lg.addEdge("B", "A", true);
        iter = lg.adjacent("A");
        // Assert
        assertEquals("A", iter.next().getLabel());
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_adjacent_simple()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        Iterator<Vertex<String>> iter;
        Vertex<String> v;
        HashMap<String, Boolean> hm = new HashMap<>();
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.add("C");
        lg.addEdge("A", "A", true);
        hm.put("A", false);
        lg.addEdge("A", "B", true);
        hm.put("B", false);
        lg.addEdge("A", "C", true);
        hm.put("C", false);
        iter = lg.adjacent("A");
        // Assert
        for (int i = 0; i < 3; i++)
        {
            v = iter.next();
            assertTrue(hm.containsKey(v.getLabel()));
            hm.remove(v.getLabel());
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_adjacent_failSafe()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        Iterator<Vertex<String>> iter;
        Vertex<String> v;
        HashMap<String, Boolean> hm = new HashMap<>();
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.add("C");
        lg.addEdge("A", "A", true);
        hm.put("A", false);
        lg.addEdge("A", "B", true);
        hm.put("B", false);
        lg.addEdge("A", "C", true);
        hm.put("C", false);
        iter = lg.adjacent("A");
        lg.add("D");
        lg.addEdge("A", "D", true);
        // Assert
        for (int i = 0; i < 3; i++)
        {
            v = iter.next();
            assertTrue(hm.containsKey(v.getLabel()));
            hm.remove(v.getLabel());
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_adjacent_remove()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        Iterator<Vertex<String>> iter;
        Vertex<String> v;
        HashMap<String, Boolean> hm = new HashMap<>();
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.add("C");
        lg.addEdge("A", "A", true);
        hm.put("A", false);
        lg.addEdge("A", "B", true);
        hm.put("B", false);
        lg.addEdge("A", "C", true);
        hm.put("C", false);
        lg.remove("B");
        iter = lg.adjacent("A");
        // Assert
        for (int i = 0; i < 2; i++)
        {
            v = iter.next();
            assertTrue(hm.containsKey(v.getLabel()));
            hm.remove(v.getLabel());
        }
        assertThrows(NoSuchElementException.class,
                () -> { iter.next(); });
    }


    // Test LG remove

    @Test
    public void ar_test_DirectedGraph_ListGraph_remove_IAE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> { lg.remove(null); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_remove_NSVE()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertThrows(NoSuchVertexException.class,
                () -> { lg.remove("A"); });
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_remove_simple()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        // Assert
        assertEquals("A", lg.remove("A"));
        assertTrue(lg.isEmpty());
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_remove_edges()
    {
        // Arrange
        DirectedGraph<Character, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        for (char i = 'A'; i <= 'D'; i++)
        {
            lg.add(i);
        }

        for (char i = 'A'; i <= 'D'; i++)
        {
            for (char j = 'A'; j <= 'D'; j++)
            {
                lg.addEdge(i, j, true);
            }
        }
        // Assert
        assertEquals(16, lg.edgeCount());
        assertEquals(4, lg.size());

        assertEquals('A', (char)lg.remove('A'));
        assertFalse(lg.contains('A'));
        assertEquals(9, lg.edgeCount());
        assertEquals(3, lg.size());

        assertEquals('B', (char)lg.remove('B'));
        assertFalse(lg.contains('B'));
        assertEquals(4, lg.edgeCount());
        assertEquals(2, lg.size());

        assertEquals('C', (char)lg.remove('C'));
        assertFalse(lg.contains('C'));
        assertEquals(1, lg.edgeCount());
        assertEquals(1, lg.size());

        assertEquals('D', (char)lg.remove('D'));
        assertFalse(lg.contains('D'));
        assertEquals(0, lg.edgeCount());
        assertEquals(0, lg.size());

        assertTrue(lg.isEmpty());
    }


    // Test LG clear

    @Test
    public void ar_test_DirectedGraph_ListGraph_clear_basic()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.add("B");
        lg.add("C");
        lg.add("D");
        lg.addEdge("A", "A", true);
        lg.addEdge("B", "B", true);
        lg.addEdge("C", "C", true);
        lg.addEdge("D", "D", true);
        // Assert
        assertEquals(4, lg.size());
        assertEquals(4, lg.edgeCount());
        lg.clear();
        assertEquals(0, lg.size());
        assertEquals(0, lg.edgeCount());
        assertTrue(lg.isEmpty());
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_clear_onZeroCtor()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        lg.clear();
        assertTrue(lg.isEmpty());
    }


    // Test LG isEmpty

    @Test
    public void ar_test_DirectedGraph_ListGraph_isEmpty_empty()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        // Assert
        assertTrue(lg.isEmpty());
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_isEmpty_false()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        // Assert
        assertFalse(lg.isEmpty());
    }

    @Test
    public void ar_test_DirectedGraph_ListGraph_isEmpty_true()
    {
        // Arrange
        DirectedGraph<String, Boolean> lg;
        // Act
        lg = new ListGraph<>();
        lg.add("A");
        lg.remove("A");
        // Assert
        assertTrue(lg.isEmpty());
    }
}
**/
