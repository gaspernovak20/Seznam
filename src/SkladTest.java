import org.junit.jupiter.api.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SkladTest {

    Sklad<String> instance;

    @BeforeEach
    void setUp() {
        instance = new Sklad<String>();
    }

    @Test
    void push() {
        String a = "Test";
        instance.push(a);
    }

    @Test
    void pop() {
        String a = "Test";
        instance.push(a);
        String b = instance.pop();
        assertEquals("Test", b);
    }

    @Test
    public void testWithTwoElements() {
        String a = "Prvi test";
        String b = "Drugi test";
        instance.push(a);
        instance.push(b);
        assertEquals(b, instance.pop());
        assertEquals(a, instance.pop());
    }

    @Test
    public void testRemoveFirstOnEmptyStack() {
        assertThrows(java.util.NoSuchElementException.class, () -> instance.removeFirst());
    }

    @Test
    public void testIsEmptyOnEmpty() {
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testIsEmptyOnFull() {
        instance.push("Test");
        assertFalse(instance.isEmpty());
    }

    @Test
    public void testGetFirstOnEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> instance.getFirst());
    }

    @Test
    public void testGetFirstWithOneElement() {
        instance.push("a");
        assertEquals(instance.getFirst(), "a");
        instance.pop();
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testGetFirstWithThreeElements() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertEquals(instance.getFirst(), "c");
        instance.pop();
        assertEquals(instance.getFirst(), "b");
        instance.pop();
        assertEquals(instance.getFirst(), "a");
        instance.pop();
        assertTrue(instance.isEmpty());
    }


    @Test
    public void testSizeOnEmptyStack() {
        assertEquals(instance.size(), 0);
    }

    @Test
    public void testSizeOneElement() {
        instance.push("a");
        assertEquals(instance.size(), 1);
    }

    @Test
    public void testSizeThreeElements() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertEquals(instance.size(), 3);
        instance.pop();
        assertEquals(instance.size(), 2);
        instance.pop();
        assertEquals(instance.size(), 1);
        instance.pop();
        assertEquals(instance.size(), 0);
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testSearchEmpty() {
        Sklad<String> instance = new Sklad<>();
        assertThrows(NoSuchElementException.class, () -> instance.search("a"));
    }

    @Test
    public void testSearchOneElement() {
        instance.push("a");
        assertEquals(instance.search("a"), 0);
    }

    @Test
    public void testSearchNotFound() {
        instance.push("a");
        assertEquals(instance.search("b"), -1);
    }

    @Test
    public void testSearchMultipleElFirst() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertEquals(instance.search("c"), 0);
    }

    @Test
    public void testSearchMultipleElLast() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertEquals(instance.search("a"), 2);
    }


    @Test
    public void testSearchMultipleElNotFound() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertEquals(instance.search("d"), -1);
    }

    @Test
    public void testIsTopEmpty() {
        assertThrows(NoSuchElementException.class, () -> instance.isTop("a"));
    }

    @Test
    public void testIsTopTrue() {
        instance.push("a");
        assertTrue(instance.isTop("a"));
    }

    @Test
    public void testIsTopFalse() {
        instance.push("a");
        assertFalse(instance.isTop("b"));
    }

    @Test
    public void testIsTopMultipleTrue() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertTrue(instance.isTop("c"));
        assertEquals(instance.search("a"), 2);
        assertEquals(instance.search("b"), 1);
        assertEquals(instance.search("c"), 0);
    }

    @Test
    public void testIsTopMultipleFalse() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertFalse(instance.isTop("a"));
        assertEquals(instance.search("a"), 2);
        assertEquals(instance.search("b"), 1);
        assertEquals(instance.search("c"), 0);
    }

    @Test
    public void testExistsOnEmpty() {
        assertFalse(instance.exists("a"));
    }

    @Test
    public void testExistsBasicExists() {
        instance.push("a");
        assertTrue(instance.exists("a"));
    }

    @Test
    public void testExistsBasicNotExists() {
        instance.push("a");
        assertFalse(instance.exists("b"));
    }

    @Test
    public void testExistsMultipleWordsExists() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertTrue(instance.exists("c"));
        assertTrue(instance.exists("b"));
        assertTrue(instance.exists("a"));
    }

    @Test
    public void testExistsMultipleWordsNotExists() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertFalse(instance.exists("d"));
    }

    @Test
    public void testRemoveOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> instance.remove("a"));
    }

    @Test
    public void testRemoveBasicExists() {
        instance.add("a");
        assertEquals(instance.remove("a"), "a");
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testRemoveBasicNotExists() {
        instance.push("a");
        assertNull(instance.remove("b"));
        assertEquals(instance.size(), 1);
    }

    @Test
    public void testRemoveMultipleWordsExists() {
        instance.add("a");
        instance.add("b");
        instance.add("c");
        assertEquals(instance.remove("b"), "b");
        assertEquals(instance.search("c"), 0);
        assertEquals(instance.search("a"), 1);
        assertEquals(instance.remove("a"), "a");
        assertEquals(instance.search("c"), 0);
    }

    @Test
    public void testRemoveMultipleWordsNotExists() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertNull(instance.remove("d"));
        assertEquals(instance.size(), 3);
    }

    @Test
    public void testDepthOnEmptyStack() {
        assertEquals(instance.depth(), 0);
    }

    @Test
    public void testDepthOneElement() {
        instance.push("a");
        assertEquals(instance.depth(), 1);
    }

    @Test
    public void testDepthThreeElements() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertEquals(instance.depth(), 3);
    }


    @Test
    void testAsListOnEmpty() {
        List<String> result = instance.asList();

        // pričakujemo vrstni red: 'c', 'b', 'a'
        assertEquals(List.of(), result);
    }

    @Test
    void testAsListBasic() {
        instance.push("a");
        instance.push("b");
        instance.push("c");

        List<String> result = instance.asList();

        assertEquals(List.of("c", "b", "a"), result);
    }

}