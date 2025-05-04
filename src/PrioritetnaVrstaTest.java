import org.junit.jupiter.api.*;

import java.security.PublicKey;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrioritetnaVrstaTest {

    private PrioritetnaVrsta<String> pv;

    public PrioritetnaVrstaTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() {
        pv = new PrioritetnaVrsta<>();
    }

    @AfterEach
    public void tearDown() {
    }

    // testi dodajanja
    @Test
    public void testAddOne() {
        pv.add("Test");
    }

    @Test
    public void testAddMultiple() {
        pv.add("Test1");
        pv.add("Test2");
    }

    @Test
    public void testAddOverflow() {
        pv = new PrioritetnaVrsta<>(2);
        pv.add("Test1");
        pv.add("Test2");
        pv.add("Test3");
    }

    // testi brisanja
    @Test
    public void testRemoveFirstEmpty() {
        assertThrows(java.util.NoSuchElementException.class, () -> {
            pv.removeFirst();
        });
    }

    @Test
    public void testRemoveFirstOne() {
        pv.add("Test");
        assertEquals("Test", pv.removeFirst());
    }

    @Test
    public void testRemoveFirstMultiple() {
        pv.add("Test1");
        pv.add("Test5");
        pv.add("Test2");
        pv.add("Test4");
        pv.add("Test3");
        assertEquals("Test5", pv.removeFirst());
        assertEquals("Test4", pv.removeFirst());
        assertEquals("Test3", pv.removeFirst());
        assertEquals("Test2", pv.removeFirst());
        assertEquals("Test1", pv.removeFirst());
    }

    // metoda get

    @Test
    public void testGetFirstEmpty() {
        assertThrows(java.util.NoSuchElementException.class, () -> {
            pv.getFirst();
        });
    }

    @Test
    public void testGetFirstOne() {
        pv.add("Test");
        assertEquals("Test", pv.getFirst());
    }

    @Test
    public void testGetFirstMultiple() {
        pv.add("Test1");
        assertEquals("Test1", pv.getFirst());
        pv.add("Test3");
        pv.add("Test2");
        assertEquals("Test3", pv.getFirst());
        assertEquals("Test3", pv.getFirst());
    }

    // testiranje metode za globino

    @Test
    public void testDepthEmpty() {
        assertEquals(0, pv.depth());
    }

    @Test
    public void testDepthOne() {
        pv.add("Test1");
        assertEquals(1, pv.depth());
    }

    @Test
    public void testDepthMultiple() {
        pv.add("Test1");
        assertEquals(1, pv.depth());
        pv.add("Test5");
        assertEquals(2, pv.depth());
        pv.add("Test2");
        assertEquals(2, pv.depth());
        pv.add("Test4");
        assertEquals(3, pv.depth());
        pv.add("Test3");
        assertEquals(3, pv.depth());
        pv.add("Test6");
        assertEquals(3, pv.depth());
        pv.add("Test8");
        assertEquals(3, pv.depth());
        pv.add("Test7");
        assertEquals(4, pv.depth());
    }

    // test metode size

    @Test
    public void testSizeEmpty() {
        assertEquals(0, pv.size());
    }

    @Test
    public void testSizeOne() {
        pv.add("Test");
        assertEquals(1, pv.size());
    }

    @Test
    public void testSizeMultiple() {
        assertEquals(0, pv.size());
        pv.add("Test");
        assertEquals(1, pv.size());
        pv.add("Test1");
        assertEquals(2, pv.size());
        pv.add("Test2");
        assertEquals(3, pv.size());
    }

    // test metode isEmpty

    @Test
    public void testIsEmptyEmpty() {
        assertTrue(pv.isEmpty());
    }

    @Test
    public void testIsEmptyOne() {
        pv.add("Test");
        assertFalse(pv.isEmpty());
    }

    @Test
    public void testIsEmptyMultiple() {
        pv.add("Test");
        pv.add("Test1");
        pv.add("Test2");
        assertFalse(pv.isEmpty());
    }

    @Test
    public void testExistsOnEmpty() {
        assertFalse(pv.exists("Test"));
    }

    @Test
    public void testExistsBasicExists() {
        pv.add("Test");
        assertTrue(pv.exists("Test"));
    }

    @Test
    public void testExistsBasicNotExists() {
        pv.add("Test1");
        assertFalse(pv.exists("Test"));
    }

    @Test
    public void testExistsMultipleWordsExists() {
        pv.add("Test1");
        pv.add("Test2");
        pv.add("Test3");
        assertTrue(pv.exists("Test2"));
        assertTrue(pv.exists("Test3"));
        assertTrue(pv.exists("Test1"));
    }

    @Test
    public void testExistsMultipleWordsNotExists() {
        pv.add("Test1");
        pv.add("Test2");
        pv.add("Test3");
        assertFalse(pv.exists("Test4"));
    }

    @Test
    public void testRemoveOnEmpty() {
        assertThrows(java.util.NoSuchElementException.class, () -> {
            pv.remove("Test");
        });
    }

    @Test
    public void testRemoveNonExistent() {
        pv.add("Test1");
        pv.add("Test2");
        pv.add("Test3");
        assertNull(pv.remove("NonExistent"));
    }

    @Test
    public void testRemoveExistentBasic() {
        pv.add("Test1");

        String removed = pv.remove("Test1");
        assertEquals("Test1", removed);
        assertFalse(pv.exists("Test1"));
        assertEquals(0, pv.size());
    }

    @Test
    public void testRemoveExistentMultiple() {
        pv.add("Test1");
        pv.add("Test2");
        pv.add("Test3");
        pv.add("Test4");
        pv.add("Test5");
        pv.add("Test6");

        String removed = pv.remove("Test6");
        assertEquals("Test6", removed);
        assertFalse(pv.exists("Test6"));
        assertEquals(5, pv.size());
    }

    @Test
    void testAsListOnEmpty() {
        List<String> result = pv.asList();

        assertEquals(List.of(), result);
    }

    @Test
    void testAsListBasic() {
        pv.add("c");
        pv.add("a");
        pv.add("b");

        List<String> result = pv.asList();

        assertEquals(List.of("c", "a", "b"), result);
    }
}