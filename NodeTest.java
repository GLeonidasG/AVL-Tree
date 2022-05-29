public class NodeTest {
    private static void testOK() {
        System.out.println("TEST OK");
    }

    private static <T, K> void testNOK(T expectation, K results) {
        System.out.println("TEST NOT OK:\n Expects: " + expectation + "\nReturned: " + results);
    }


    public static void runTests() {
    }
}
