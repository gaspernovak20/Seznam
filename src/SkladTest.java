import org.junit.jupiter.api.*;

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
    public void testPopOnEmptyStack() {
        assertThrows(java.util.NoSuchElementException.class, () -> {
            instance.pop();
        });
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
    public void testTopOnEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> {
            instance.top();
        });
    }

    @Test
    public void testTopWithOneElement() {
        instance.push("a");
        assertEquals(instance.top(), "a");
        instance.pop();
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testTopWithThreeElements() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertEquals(instance.top(), "c");
        instance.pop();
        assertEquals(instance.top(), "b");
        instance.pop();
        assertEquals(instance.top(), "a");
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
        assertThrows(NoSuchElementException.class, () -> {
            instance.search("a");
        });
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
        assertThrows(NoSuchElementException.class, () -> {
            instance.isTop("a");
        });
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

}