import org.junit.jupiter.api.*;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

class SeznamiUVTest {
    SeznamiUV uv;

    public SeznamiUVTest() {
    }

    @BeforeEach
    void setUp() {
        uv = new SeznamiUV();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void processInput() {
    }

    @Test
    public void testStackPushBasic() {
        System.out.println("testPushBasic");
        assertEquals("OK", uv.processInput("s_add Test1"));
    }

    @Test
    public void testStackPushMultipleWords() {
        System.out.println("testPushMultipleWords");
        assertEquals("OK", uv.processInput("s_add \"Test with multiple words\""));
        assertEquals("1", uv.processInput("s_size"));
    }

    @Test
    public void testStackPopBasic() {
        System.out.println("testPopBasic");
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("Test1", uv.processInput("s_removeFirst"));
    }

    @Test
    public void testStackPopMultipleWords() {
        System.out.println("testPopMultipleWords");
        assertEquals("OK", uv.processInput("s_add \"Test with multiple words\""));
        assertEquals("1", uv.processInput("s_size"));
        assertEquals("Test with multiple words", uv.processInput("s_removeFirst"));
        assertEquals("0", uv.processInput("s_size"));
    }

    @Test
    public void testStackPopNothing() {
        System.out.println("testPopNothing");
        assertEquals("Error: stack is empty", uv.processInput("s_removeFirst"));
    }

    @Test
    public void testStackResetOnEmpty() {
        System.out.println("testResetOnEmpty");
        assertEquals("OK", uv.processInput("s_reset"));
    }

    @Test
    public void testStackResetOnFull() {
        System.out.println("testResetOnFull");
        assertEquals("OK", uv.processInput("s_add Test"));
        assertEquals("OK", uv.processInput("s_reset"));
        assertEquals("Error: stack is empty", uv.processInput("s_removeFirst"));
    }

    @Test
    public void testStackCountOne() {
        System.out.println("testCountOne");
        assertEquals("OK", uv.processInput("s_add Test"));
        assertTimeoutPreemptively(ofMillis(100), () ->
        {
            uv.processInput("s_size");
        });
    }

    @Test
    public void testHeapAddNothing() {
        System.out.println("testHeapAddNothing");
        assertEquals("Error: please specify a string", uv.processInput("pq_add"));
    }


    @Test
    public void testHeapAddBasic() {
        System.out.println("testHeapAddBasic");
        assertEquals("OK", uv.processInput("pq_add Test1"));
    }

    @Test
    public void testHeapRemoveFirstOnEmpty() {
        System.out.println("testHeapRemoveFirstOnEmpty");
        assertEquals("Error: priority queue is empty", uv.processInput("pq_remove_first"));
    }

    @Test
    public void testHeapRemoveFirstMultipleWords() {
        System.out.println("testHeapRemoveFirstMultipleWords");
        uv.processInput("pq_add Test1");
        uv.processInput("pq_add Test5");
        uv.processInput("pq_add Test2");
        uv.processInput("pq_add Test4");
        uv.processInput("pq_add Test3");
        assertEquals("Test5", uv.processInput("pq_remove_first"));
        assertEquals("Test4", uv.processInput("pq_remove_first"));
        assertEquals("Test3", uv.processInput("pq_remove_first"));
        assertEquals("Test2", uv.processInput("pq_remove_first"));
        assertEquals("Test1", uv.processInput("pq_remove_first"));
    }

    @Test
    public void testHeapGetFirstOnEmpty() {
        System.out.println("testHeapGetFirstOnEmpty");
        assertEquals("Error: priority queue is empty", uv.processInput("pq_get_first"));
    }

    @Test
    public void testHeapGetFirstMultipleWords() {
        System.out.println("testHeapGetFirstMultipleWords");
        uv.processInput("pq_add Test1");
        uv.processInput("pq_add Test5");
        uv.processInput("pq_add Test2");
        uv.processInput("pq_add Test4");
        assertEquals("Test5", uv.processInput("pq_get_first"));
    }

    @Test
    public void testHeapSizeOnEmpty() {
        System.out.println("testHeapSizeOnEmpty");
        assertEquals("0", uv.processInput("pq_size"));
    }

    @Test
    public void testHeapSizeMultipleWords() {
        System.out.println("testHeapSizeMultipleWords");
        uv.processInput("pq_add Test1");
        uv.processInput("pq_add Test5");
        uv.processInput("pq_add Test2");
        uv.processInput("pq_add Test4");
        assertEquals("4", uv.processInput("pq_size"));
    }

    @Test
    public void testHeapDepthOnEmpty() {
        System.out.println("testHeapDepthOnEmpty");
        assertEquals("0", uv.processInput("pq_depth"));
    }

    @Test
    public void testHeapDepthMultipleWords() {
        System.out.println("testHeapSizeMultipleWords");
        uv.processInput("pq_add Test1");
        uv.processInput("pq_add Test5");
        uv.processInput("pq_add Test2");
        uv.processInput("pq_add Test4");
        assertEquals("3", uv.processInput("pq_depth"));
    }

    @Test
    public void testHeapIsEmptyOnEmpty() {
        System.out.println("testHeapIsEmptyOnEmpty");
        assertEquals("Priority queue is empty", uv.processInput("pq_isEmpty"));
    }

    @Test
    public void testHeapIsEmptyMultipleWords() {
        System.out.println("testHeapIsEmptyMultipleWords");
        uv.processInput("pq_add Test1");
        uv.processInput("pq_add Test5");
        uv.processInput("pq_add Test2");
        uv.processInput("pq_add Test4");
        assertEquals("Priority queue is not empty", uv.processInput("pq_isEmpty"));
    }


}