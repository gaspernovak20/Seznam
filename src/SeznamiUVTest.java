import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

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
    public void testUseWrongArgument() {
        assertEquals("Error: please specify a correct data structure type (pv, sk, bst)", uv.processInput("use none"));
    }

    @Test
    public void testUseNoArgument() {
        assertEquals("Error: please specify a data structure type (pv, sk, bst)", uv.processInput("use"));
    }


    //    test Add
    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testAddBasic(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("OK", uv.processInput("add Test1"));
    }

    //    test Add
    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testAddNoArgument(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("Error: please specify a string", uv.processInput("add"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testAddMultipleWords(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("OK", uv.processInput("add \"Test with multiple words\""));
        assertEquals("1", uv.processInput("size"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testAddMultipleWordsWrong(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("Error: Wrong string", uv.processInput("add \"Test with multiple words"));
        assertEquals("0", uv.processInput("size"));
    }

    //  test removeFirst
    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst"})
    public void testRemoveFirstOnEmpty(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("Error: Data structure is empty", uv.processInput("removeFirst"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst"})
    public void testRemoveFirstBasic(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("Test1", uv.processInput("removeFirst"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst"})
    public void testRemoveFirstMultipleWords(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("OK", uv.processInput("add \"Test with multiple words\""));
        assertEquals("1", uv.processInput("size"));
        assertEquals("Test with multiple words", uv.processInput("removeFirst"));
        assertEquals("0", uv.processInput("size"));
    }
    //  test Reset

    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testResetOnEmpty(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("OK", uv.processInput("reset"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst"})
    public void testResetOnFull(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("OK", uv.processInput("reset"));
        assertEquals("Error: Data structure is empty", uv.processInput("removeFirst"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testAddCountOne(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("OK", uv.processInput("add Test"));
        assertTimeoutPreemptively(ofMillis(100), () -> {
            uv.processInput("size");
        });
    }

    //    test Exists

    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testExistsOnEmpty(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("Data structure is empty", uv.processInput("exists Test1"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testExistsNothing(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("Please specify a string", uv.processInput("exists"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testExistsExistsBasic(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test1");
        assertEquals("Element exists in data structure", uv.processInput("exists test1"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testExistsExistsMultiple(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test1");
        uv.processInput("add test2");
        uv.processInput("add test3");
        assertEquals("Element exists in data structure", uv.processInput("exists test3"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testExistsDoesntExistsBasic(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test1");
        assertEquals("Element does not exist in data structure", uv.processInput("exists test2"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testExistsDoesntExistsMultiple(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test1");
        uv.processInput("add test2");
        uv.processInput("add test3");
        assertEquals("Element does not exist in data structure", uv.processInput("exists test4"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testGetFirstOnEmpty(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("Error: Data structure is empty", uv.processInput("getFirst"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testGetFirstBasic(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test1");
        assertEquals("test1", uv.processInput("getFirst"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv"})
    public void testSKPVGetFirstMultiple(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test2");
        uv.processInput("add test1");
        uv.processInput("add test3");
        uv.processInput("add test4");
        assertEquals("test4", uv.processInput("getFirst"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"bst", "23"})
    public void testBSTGetFirstMultiple(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test2");
        uv.processInput("add test1");
        uv.processInput("add test3");
        uv.processInput("add test4");
        assertEquals("test2", uv.processInput("getFirst"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testIsEmptyOnEmpty(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("Data structure is empty", uv.processInput("isEmpty"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testIsEmptyBasic(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test1");
        assertEquals("Data structure is not empty", uv.processInput("isEmpty"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testIsEmptyMultiple(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test1");
        uv.processInput("add test2");
        uv.processInput("add test3");
        assertEquals("Data structure is not empty", uv.processInput("isEmpty"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testDepthOnEmpty(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("0", uv.processInput("depth"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"pv", "bst", "23"})
    public void testDepthBasic(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test1");
        assertEquals("1", uv.processInput("depth"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"bst"})
    public void testBSTDepthMultipleLeft(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add d");
        uv.processInput("add c");
        uv.processInput("add b");
        uv.processInput("add a");
        assertEquals("4", uv.processInput("depth"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"bst"})
    public void testBSTDepthMultipleRight(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add a");
        uv.processInput("add b");
        uv.processInput("add c");
        uv.processInput("add d");
        assertEquals("4", uv.processInput("depth"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"pv"})
    public void testPQDepthMultiple(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add a");
        uv.processInput("add b");
        uv.processInput("add c");
        uv.processInput("add d");
        assertEquals("3", uv.processInput("depth"));
    }

    //    test Remove
    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst"})
    public void testRemoveOnEmpty(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("Data structure is empty", uv.processInput("remove test4"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst"})
    public void testRemoveNothing(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("Please specify a string", uv.processInput("remove"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst"})
    public void testRemoveFoundBasic(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test1");
        assertEquals("test1", uv.processInput("remove test1"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst"})
    public void testRemoveFoundMultiple(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test1");
        uv.processInput("add test2");
        uv.processInput("add test3");
        assertEquals("test3", uv.processInput("remove test3"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst"})
    public void testRemoveNotFoundBasic(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test1");
        assertEquals("Element not found", uv.processInput("remove test2"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst"})
    public void testRemoveNotFoundMultiple(String seznam) {
        uv.processInput("use " + seznam);
        uv.processInput("add test1");
        uv.processInput("add test2");
        uv.processInput("add test3");
        assertEquals("Element not found", uv.processInput("remove test4"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"sk", "pv", "bst", "23"})
    public void testAsListOnEmpty(String seznam) {
        uv.processInput("use " + seznam);
        assertEquals("[]", uv.processInput("asList"));
    }

    @Test
    void testSkladAsListBasic() {
        uv.processInput("use sk");
        uv.processInput("add a");
        uv.processInput("add b");
        uv.processInput("add c");

        assertEquals("[c, b, a]", uv.processInput("asList"));
    }

    @Test
    void testPQAsListBasic() {
        uv.processInput("use pv");
        uv.processInput("add a");
        uv.processInput("add b");
        uv.processInput("add c");

        assertEquals("[c, a, b]", uv.processInput("asList"));
    }

    @Test
    void testBSTAsListBasic() {
        uv.processInput("use bst");
        uv.processInput("add b");
        uv.processInput("add a");
        uv.processInput("add c");

        assertEquals("[a, b, c]", uv.processInput("asList"));
    }

    @Test
    void test23AsListBasic() {
        uv.processInput("use 23");
        uv.processInput("add b");
        uv.processInput("add a");
        uv.processInput("add c");

        assertEquals("[b, a, c]", uv.processInput("asList"));
    }


}
