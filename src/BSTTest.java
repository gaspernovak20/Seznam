import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    BST<String> bst;

    @BeforeEach
    void setUp() {
        bst = new BST<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testAddBasic() {
        bst = new BST<>();
        bst.add("m");
        assertTrue(bst.exists("m"));
    }

    @Test
    void testAddMultiple() {
        bst.add("m");
        bst.add("c");
        bst.add("t");
        bst.add("a");
        bst.add("e");

        assertTrue(bst.exists("m"));
        assertTrue(bst.exists("c"));
        assertTrue(bst.exists("t"));
        assertTrue(bst.exists("a"));
        assertTrue(bst.exists("e"));
    }

    @Test
    public void testAddDuplicate() {
        bst.add("m");
        assertThrows(IllegalArgumentException.class, () -> bst.add("m"));
    }

    @Test
    void removeFirstOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> bst.removeFirst());
    }

    @Test
    void removeFirstBasic() {
        bst.add("a");
        assertEquals("a", bst.removeFirst());
        assertEquals(0, bst.size());
    }

    @Test
    void testRemoveFirstMultiple() {
        bst.add("d");
        bst.add("b");
        bst.add("e");
        bst.add("a");
        bst.add("c");
        bst.removeFirst();
        assertFalse(bst.exists("d"));
        assertTrue(bst.exists("c"));
        assertTrue(bst.exists("e"));
    }

    @Test
    void testGetFirstOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> bst.getFirst());
    }

    @Test
    void testGetFirstBasic() {
        bst.add("a");
        assertEquals("a", bst.getFirst());
    }

    @Test
    void testGetFirstMultiple() {
        bst.add("b");
        bst.add("a");
        bst.add("c");
        bst.add("d");
        assertEquals("b", bst.getFirst());
    }

    @Test
    void testGetFirstDoesNotRemove() {
        bst.add("b");
        bst.add("a");
        bst.add("c");

        assertEquals("b", bst.getFirst());
        assertTrue(bst.exists("b"));
    }


    @Test
    public void testSizeOnEmpty() {
        assertEquals(0, bst.size());
    }

    @Test
    public void testSizeBasic() {
        bst.add("a");
        assertEquals(1, bst.size());
    }

    @Test
    public void testSizeAfterMultipleInserts() {
        bst.add("m");
        bst.add("c");
        bst.add("t");
        bst.add("a");
        bst.add("e");
        assertEquals(5, bst.size());
    }

    @Test
    public void testSizeAfterDuplicateInsertThrows() {
        bst.add("a");
        assertThrows(Exception.class, () -> bst.add("a"));
        assertEquals(1, bst.size());
    }

    @Test
    public void testSizeAfterDelete() {
        bst.add("m");
        bst.add("c");
        bst.add("t");
        assertEquals(3, bst.size());

        bst.remove("c");
        assertEquals(2, bst.size());
    }

    @Test
    public void testSizeAfterDeleteNonExistent() {
        bst.add("m");
        bst.add("c");
        bst.add("t");

        assertThrows(Exception.class, () -> bst.remove("x"));
        assertEquals(3, bst.size());
    }

    @Test
    void testDepthEmpty() {
        assertEquals(0, bst.depth());
    }

    @Test
    void testDepthSingleElement() {
        bst.add("a");
        assertEquals(1, bst.depth());
    }

    @Test
    void testDepthLeft() {
        bst.add("d");
        bst.add("c");
        bst.add("b");
        bst.add("a");
        assertEquals(4, bst.depth());
    }

    @Test
    void testDepthRight() {
        bst.add("a");
        bst.add("b");
        bst.add("c");
        bst.add("d");
        assertEquals(4, bst.depth());
    }

    @Test
    void testDepthWithBalanced() {
        bst.add("c");
        bst.add("b");
        bst.add("d");
        bst.add("a");
        bst.add("e");
        assertEquals(3, bst.depth());
    }

    @Test
    void testTreeIsInitiallyEmpty() {
        assertTrue(bst.isEmpty());
    }

    @Test
    void testTreeNotEmptyAfterAdd() {
        bst.add("x");
        assertFalse(bst.isEmpty());
    }

    @Test
    void testTreeEmptyAfterRemovingAllElements() {
        bst.add("m");
        bst.removeFirst(); // Removes the only node
        assertTrue(bst.isEmpty());
    }

    @Test
    void testRemoveOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> bst.remove("a"));
    }

    @Test
    void testRemoveBasic() {
        bst.add("m");
        assertEquals("m", bst.remove("m"));
        assertEquals(0, bst.size());
    }

    @Test
    public void testRemoveLeaf() {
        bst.add("m");
        bst.add("c");
        bst.add("t");
        bst.add("a");
        bst.add("e");
        bst.add("r");
        bst.add("z");
        bst.remove("a"); // a je list
        assertFalse(bst.exists("a"));
    }

    @Test
    public void testRemoveWithOneChild() {
        bst.add("m");
        bst.add("c");
        bst.add("t");
        bst.add("a");
        bst.add("e");
        bst.add("r");
        bst.add("z");
        bst.add("d"); // d bo otrok e
        bst.remove("e");
        assertFalse(bst.exists("e"));
        assertTrue(bst.exists("d"));
    }

    @Test
    public void testRemoveNodeWithTwoChildren() {
        bst.add("m");
        bst.add("c");
        bst.add("t");
        bst.add("a");
        bst.add("e");
        bst.add("r");
        bst.add("z");
        bst.remove("c"); // c ima levo: a, desno: e
        assertFalse(bst.exists("c"));
        assertTrue(bst.exists("a"));
        assertTrue(bst.exists("e"));
    }

    @Test
    public void testRemoveRootWithTwoChildren() {
        bst.add("m");
        bst.add("c");
        bst.add("t");
        bst.add("a");
        bst.add("e");
        bst.add("r");
        bst.add("z");
        bst.remove("m");
        assertFalse(bst.exists("m"));
        assertTrue(bst.exists("c"));
        assertTrue(bst.exists("t"));
    }

    @Test
    public void testRemoveUntilEmpty() {
        bst.add("m");
        bst.add("c");
        bst.add("t");
        bst.add("a");
        bst.add("e");
        bst.add("r");
        bst.add("z");
        bst.remove("a");
        bst.remove("c");
        bst.remove("e");
        bst.remove("m");
        bst.remove("r");
        bst.remove("t");
        bst.remove("z");
        assertFalse(bst.exists("m"));
        assertThrows(NoSuchElementException.class, () -> bst.remove("m"));
    }


    @Test
    public void testExistsOnEmptyTree() {
        assertFalse(bst.exists("a"));
    }

    @Test
    public void testExistsBasic() {
        bst.add("a");
        assertTrue(bst.exists("a"));
        assertFalse(bst.exists("b"));
    }

    @Test
    public void testExistsMultiple() {
        bst.add("m");
        bst.add("c");
        bst.add("t");
        bst.add("a");
        bst.add("f");

        assertTrue(bst.exists("m"));
        assertTrue(bst.exists("c"));
        assertTrue(bst.exists("a"));
        assertTrue(bst.exists("t"));
        assertFalse(bst.exists("z"));
    }
}