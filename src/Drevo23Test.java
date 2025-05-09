import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Drevo23Test {

    Drevo23<Integer> drevo23;

    @BeforeEach
    void setUp() {
        drevo23 = new Drevo23<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAddOnEmpty() {
        drevo23.add(8);
        assertEquals("(8)", drevo23.toString());
    }

    @Test
    void testAddEqualA() {
        drevo23.add(8);
        assertThrows(java.util.NoSuchElementException.class, () -> drevo23.add(8));
    }

    @Test
    void testAddLowerThenA() {
        drevo23.add(8);
        drevo23.add(2);
        assertEquals("(2,8)", drevo23.toString());
    }

    @Test
    void testAddHigherThenA() {
        drevo23.add(8);
        drevo23.add(9);
        assertEquals("(8,9)", drevo23.toString());
    }


    @Test
    void testAddEqualAB() {
        drevo23.add(8);
        drevo23.add(2);
        assertThrows(java.util.NoSuchElementException.class, () -> drevo23.add(2));
    }

    @Test
    void testAddAEqualB() {
        drevo23.add(8);
        drevo23.add(2);
        assertThrows(java.util.NoSuchElementException.class, () -> drevo23.add(8));
    }

    @Test
    void testAddLowerThenAB() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(1);
        assertEquals("(2)(1)(8)", drevo23.toString());
    }

    @Test
    void testAddHigherThenAB() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(9);
        assertEquals("(8)(2)(9)", drevo23.toString());
    }

    @Test
    void testAddHigherThenALowerThenB() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(9);
        assertEquals("(8)(2)(9)", drevo23.toString());
    }

    @Test
    void testAddToLeftChildA() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(9);
        drevo23.add(5);
        assertEquals("(8)(2,5)(9)", drevo23.toString());
    }

    @Test
    void testAddToRightChildA() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(9);
        drevo23.add(10);
        assertEquals("(8)(2)(9,10)", drevo23.toString());
    }

    @Test
    void testAddToLeftChildAB() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(1);
        drevo23.add(4);
        drevo23.add(0);
        assertEquals("(2,5)(0,1)(4)(8)", drevo23.toString());
    }

    @Test
    void testAddToLeftChildABSplit() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(1);
        drevo23.add(4);
        drevo23.add(0);
        drevo23.add(-1);
        assertEquals("(2)(0)(-1)(1)(5)(4)(8)", drevo23.toString());
    }

    @Test
    void testAddToCenterChildAB() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(1);
        drevo23.add(4);
        drevo23.add(3);
        assertEquals("(2,5)(1)(3,4)(8)", drevo23.toString());
    }

    @Test
    void testAddToCenterChildABSplit() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(6);
        drevo23.add(1);
        drevo23.add(4);
        drevo23.add(3);
        drevo23.add(5);
        assertEquals("(4)(2)(1)(3)(6)(5)(8)", drevo23.toString());
    }

    @Test
    void testAddToRightChildAB() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(1);
        drevo23.add(4);
        drevo23.add(9);
        assertEquals("(2,5)(1)(4)(8,9)", drevo23.toString());
    }

    @Test
    void testAddToRightChildABSplit() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(1);
        drevo23.add(4);
        drevo23.add(6);
        drevo23.add(10);
        drevo23.add(11);
        drevo23.add(12);
        assertEquals("(5)(2)(1)(4)(8,11)(6)(10)(12)", drevo23.toString());
    }

    @Test
    void removeFirstOnEmpty() {
        assertNull(drevo23.removeFirst());
    }

    @Test
    void removeFirstBasic() {
        drevo23.add(1);
        assertEquals(1, drevo23.removeFirst());
        assertTrue(drevo23.isEmpty());
    }

    @Test
    void removeFirstAB() {
        drevo23.add(2);
        drevo23.add(1);
        assertEquals(1, drevo23.removeFirst());
        assertFalse(drevo23.isEmpty());
    }

    @Test
    void getFirstOnEmpty() {
        assertNull(drevo23.getFirst());
    }

    @Test
    void getFirstBasic() {
        drevo23.add(8);
        assertEquals(8, drevo23.getFirst());
    }

    @Test
    void getFirstBasicAB() {
        drevo23.add(8);
        drevo23.add(2);
        assertEquals(2, drevo23.getFirst());
    }

    @Test
    void getFirstMultiple() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(3);
        assertEquals(3, drevo23.getFirst());
    }

    @Test
    void sizeOnEmpty() {
        assertEquals(0, drevo23.size());
    }

    @Test
    void sizeOnBasic() {
        drevo23.add(8);
        assertEquals(1, drevo23.size());
    }

    @Test
    void sizeOnBasicAB() {
        drevo23.add(8);
        drevo23.add(2);
        assertEquals(2, drevo23.size());
    }

    @Test
    void sizeOnMultiple() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(3);
        assertEquals(3, drevo23.size());
    }


    @Test
    void depthOnEmpty() {
        assertEquals(0, drevo23.depth());
    }

    @Test
    void depthBasic() {
        drevo23.add(8);
        assertEquals(1, drevo23.depth());
    }

    @Test
    void depthBasicAB() {
        drevo23.add(8);
        drevo23.add(2);
        assertEquals(1, drevo23.depth());
    }

    @Test
    void depthMultiple() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(3);
        assertEquals(2, drevo23.depth());
    }

    @Test
    void isEmptyOnEmpty() {
        assertTrue(drevo23.isEmpty());
    }

    @Test
    void isEmptyBasic() {
        drevo23.add(8);
        assertFalse(drevo23.isEmpty());
    }

    @Test
    void isEmptyBasicAB() {
        drevo23.add(8);
        drevo23.add(2);
        assertFalse(drevo23.isEmpty());
    }

    @Test
    void isEmptyMultiple() {
        drevo23.add(8);
        drevo23.add(2);
        assertFalse(drevo23.isEmpty());
    }

    @Test
    void removeOnEmpty() {
        assertNull(drevo23.remove(1));
    }

    @Test
    void removeOnly() {
        drevo23.add(1);
        assertEquals(1, drevo23.remove(1));
        assertTrue(drevo23.isEmpty());
    }

    @Test
    void removeAOnAB() {
        drevo23.add(1);
        drevo23.add(2);
        assertEquals(1, drevo23.remove(1));
        assertFalse(drevo23.exists(1));
        assertTrue(drevo23.exists(2));
    }

    @Test
    void removeBOnAB() {
        drevo23.add(1);
        drevo23.add(2);
        assertEquals(2, drevo23.remove(2));
        assertFalse(drevo23.exists(2));
        assertTrue(drevo23.exists(1));
    }

    @Test
    void remove22LeftRotation() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        assertEquals(2, drevo23.remove(2));
        assertEquals("(5,8)", drevo23.toString());
    }

    @Test
    void remove22RightRotation() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        assertEquals(8, drevo23.remove(8));
        assertEquals("(2,5)", drevo23.toString());
    }

    @Test
    void remove23LeftRotation() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(6);
        assertEquals(2, drevo23.remove(2));
        assertEquals("(6)(5)(8)", drevo23.toString());
    }

    @Test
    void remove23RightRotation() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(1);
        assertEquals(8, drevo23.remove(8));
        assertEquals("(2)(1)(5)", drevo23.toString());
    }

    @Test
    void remove32LeftRotation() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(6);
        drevo23.add(7);
        drevo23.add(9);
        assertEquals(2, drevo23.remove(2));
        assertEquals("(7)(5,6)(8,9)", drevo23.toString());
    }

    @Test
    void remove32CenterRightRotation() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(6);
        drevo23.add(7);
        assertEquals(6, drevo23.remove(6));
        assertEquals("(5)(2)(7,8)", drevo23.toString());
    }

    @Test
    void remove32RightRotation() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(6);
        drevo23.add(7);
        drevo23.add(1);
        assertEquals(8, drevo23.remove(8));
        assertEquals("(5)(1,2)(6,7)", drevo23.toString());
    }

    @Test
    void remove33LeftRotation() {
        drevo23.add(9);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(6);
        drevo23.add(10);
        drevo23.add(8);
        drevo23.add(11);
        assertEquals(2, drevo23.remove(2));
        assertEquals("(6,9)(5)(8)(10,11)", drevo23.toString());
    }

    @Test
    void remove33CenterRightRotation() {
        drevo23.add(9);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(6);
        drevo23.add(10);
        drevo23.add(1);
        drevo23.add(11);
        assertEquals(6, drevo23.remove(6));
        assertEquals("(2,9)(1)(5)(10,11)", drevo23.toString());
    }

    @Test
    void remove33RightRotation() {
        drevo23.add(9);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(6);
        drevo23.add(10);
        drevo23.add(8);
        drevo23.add(1);
        assertEquals(10, drevo23.remove(10));
        assertEquals("(5,8)(1,2)(6)(9)", drevo23.toString());
    }

    @Test
    void removeMultiple() {
//        8, 2, 5, 1, 4, 3, 6, 10, 7, 9, 11, 12, 13
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(1);
        drevo23.add(4);
        drevo23.add(3);
        drevo23.add(6);
        drevo23.add(10);
        drevo23.add(7);
        drevo23.add(9);
        drevo23.add(11);
        drevo23.add(12);
        drevo23.add(13);
//        2, 8, 5, 6, 12, 7, 9, 4
        assertEquals(2, drevo23.remove(2));
        assertEquals("(5,10)(3)(1)(4)(8)(6,7)(9)(12)(11)(13)", drevo23.toString());
        assertEquals(8, drevo23.remove(8));
        assertEquals("(5,10)(3)(1)(4)(7)(6)(9)(12)(11)(13)", drevo23.toString());
        assertEquals(5, drevo23.remove(5));
        assertEquals("(6)(3)(1)(4)(10,12)(7,9)(11)(13)", drevo23.toString());
        assertEquals(6, drevo23.remove(6));
        assertEquals("(7)(3)(1)(4)(10,12)(9)(11)(13)", drevo23.toString());
        assertEquals(12, drevo23.remove(12));
        assertEquals("(7)(3)(1)(4)(10)(9)(11,13)", drevo23.toString());
        assertEquals(7, drevo23.remove(7));
        assertEquals("(9)(3)(1)(4)(11)(10)(13)", drevo23.toString());
        assertEquals(9, drevo23.remove(9));
        assertEquals("(3,10)(1)(4)(11,13)", drevo23.toString());
        assertEquals(4, drevo23.remove(4));
        assertEquals("(3,11)(1)(10)(13)", drevo23.toString());
    }


    @Test
    void existsOnEmpty() {
        assertFalse(drevo23.exists(8));
    }

    @Test
    void existsBasicDoesNotExists() {
        drevo23.add(8);
        assertFalse(drevo23.exists(2));
    }

    @Test
    void existsBasicExists() {
        drevo23.add(8);
        assertTrue(drevo23.exists(8));
    }

    @Test
    void existsBasicABExists() {
        drevo23.add(8);
        drevo23.add(2);
        assertTrue(drevo23.exists(8));
    }

    @Test
    void existsBasicABDoesNotExists() {
        drevo23.add(8);
        drevo23.add(2);
        assertFalse(drevo23.exists(3));
    }


    @Test
    void existsMultipleExists() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(5);
        drevo23.add(1);
        drevo23.add(4);
        assertTrue(drevo23.exists(8));
    }

    @Test
    void existsMultipleNotExists() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(3);
        assertFalse(drevo23.exists(5));
    }

    @Test
    void asListOnEmpty() {
        assertEquals(List.of(), drevo23.asList());
    }

    @Test
    void asListBasic() {
        drevo23.add(8);
        assertEquals(List.of(8), drevo23.asList());
    }

    @Test
    void asListBasicAB() {
        drevo23.add(8);
        drevo23.add(2);
        assertEquals(List.of(2, 8), drevo23.asList());
    }

    @Test
    void asListMultiple() {
        drevo23.add(8);
        drevo23.add(2);
        drevo23.add(3);
        assertEquals(List.of(3, 2, 8), drevo23.asList());
    }


}