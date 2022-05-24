
class NodeMocker {
    public static Node linearTree(int levels) {
        int keyNode = levels * 10;
        Node node = new Node(keyNode);
        for (int i = keyNode - 10; i > 0; i -= 10) {
            node.insertChild(new Node(i), null);
        }
        return node;
    }

    public static Node complextTree() {
        int[] keyArray = { 40, 30, 60, 35, 55 };
        Node node = new Node(50);
        for (int key : keyArray) {
            node.insertChild(new Node(key), null);
        }
        return node;
    }
}

public class NodeTest {
    private static int numberExpectation = 0;
    private static Node node; 

    private static void testOK() {
        System.out.println("TEST OK");
    }

    private static <T, K> void testNOK(T expectation, K results) {
        System.out.println("TEST NOT OK:\n Expects: " + expectation + "\nReturned: " + results);
    }

    private static void test1() {
        numberExpectation = 5;
        node = NodeMocker.linearTree(5);
        System.out.println("Should return 5 for retrieveHeight on a " + numberExpectation + " levels node");
        if (node.retrieveHeight() == 5)
            testOK();
        else
            testNOK(numberExpectation, node.retrieveHeight());

    }

    private static void test2() {
        node = NodeMocker.complextTree();
        numberExpectation = 4;
        System.out.println("Should return 4 for retrieveHeight on a " + numberExpectation + " levels node");
        if (node.retrieveHeight() == 4)
            testOK();
        else
            testNOK(numberExpectation, node.retrieveHeight());
    }

    private static void test3() {
        Node rootNode = new Node(50);
        Node childNode = new Node(40);
        System.out.println("Should insert the child node into the left node of the parent node");
        rootNode.insertChild(childNode, null);
        if (rootNode.getRightNode() == null)
            testOK();
        else
            testNOK(null, rootNode.getRightNode());
        if (rootNode.getLeftNode().getKey() == 40)
            testOK();
        else
            testNOK(40, rootNode.getLeftNode().getKey());
    }

    private static void test4() {
        System.out.println("Should match the level tree for each node");
        Node rootNode = new Node(50);
        int[] keyList = {40, 60, 55, 35, 30, 70};
        int[] indexList = {1, 1, 2, 2, 3, 2};
        for (int key: keyList) {
            rootNode.insertChild(new Node(key), null);
        }
        for (int i = 0; i< keyList.length; i++) {
            int currentIndex = rootNode.searchKey(keyList[i]);
            if (currentIndex == indexList[i]) System.out.println("TEST OK for key: " + keyList[i] + " at level: "+currentIndex);
        }
    }

    public static void runTests() {
        // test1();
        // test2();
        // test3();
        test4();

    }
}
