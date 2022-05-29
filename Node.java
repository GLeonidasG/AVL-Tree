public class Node {
    private Node rightNode;
    private Node leftNode;
    private Node upperNode;
    private int key;

    // GETTERS AND SETTERS
    public Node getRightNode() {
        return this.rightNode;
    }

    public Node getLeftNode() {
        return this.leftNode;
    }

    public int getKey() {
        return this.key;
    }

    public void setUpperNode(Node node) {
        this.upperNode = node;
    }

    public String toString() {
        String currentNode = "Node: " + this.key + "\n Left child: " + this.leftNode.getKey() + "\nRight child: "
                + this.rightNode.getKey();
        return currentNode;
    }

    public Node(int key) {
        this.key = key;
    }

    public int searchKey(int key) {
        int index = 0;
        if (key == this.key)
            return index;
        else if (key < this.key)
            return this.leftNode.searchKey(key, index + 1);
        else if (key > this.key)
            return this.rightNode.searchKey(key, index + 1);
        else return -1;
    }

    public int searchKey(int key, int index) {
        if (key == this.key)
            return index;
        else if (key < this.key)
            return this.leftNode.searchKey(key, index + 1);
        else if (key > this.key)
            return this.rightNode.searchKey(key, index + 1);
        else return -1;
    }

    public void insertChild(Node node) {
        if (this.key < node.getKey()) {
            if (this.rightNode == null) {
                this.rightNode = node;
                this.rightNode.setUpperNode(this);
                this.rightNode.balanceNode();
            } else {
                System.out.println("Inserting " + node.getKey() + " into node: " + this.key);
                this.rightNode.insertChild(node);
            }
        }
        if (this.key > node.getKey()) {
            if (this.leftNode == null) {
                this.leftNode = node;
                this.leftNode.setUpperNode(this);
                this.leftNode.balanceNode();
            } else {
                System.out.println("Inserting " + node.getKey() + " into node: " + this.key);
                this.leftNode.insertChild(node);
            }
        }
        this.balanceNode();

    }

    public int retrieveHeight() {
        int rightHeight = 0,
                leftHeight = 0;

        if (this.rightNode != null)
            rightHeight = this.rightNode.retrieveHeight(1);
        if (this.leftNode != null)
            leftHeight = this.leftNode.retrieveHeight(1);

        if (leftHeight > rightHeight)
            return leftHeight;
        else
            return rightHeight;
    }

    public int retrieveHeight(int initialHeight) {
        int leftHeight = 0;
        int rightHeight = 0;
        if (this.rightNode == null && this.leftNode == null)
            return initialHeight;
        if (this.rightNode != null)
            rightHeight = this.rightNode.retrieveHeight(initialHeight + 1);
        if (this.leftNode != null)
            leftHeight = this.leftNode.retrieveHeight(initialHeight + 1);

        if (leftHeight > rightHeight)
            return leftHeight;
        else
            return rightHeight;

    }

    public Node getNode(int key) {
        if (this.key < key && this.rightNode != null)
            return this.rightNode.getNode(key);
        else if (this.key > key && this.leftNode != null)
            return this.leftNode.getNode(key);
        else
            return this;
    }

    public void rotateLeft() {
        System.out.println("Rotating left...");
        Node rightNode = this.rightNode;
        Node upperNode = this.upperNode;
        this.rightNode = null;
        this.upperNode = null;
        if (upperNode.key < this.key)
            upperNode.rightNode = rightNode;
        else
            upperNode.leftNode = rightNode;
        rightNode.setUpperNode(upperNode);
        rightNode.insertChild(this);

    }

    public void rotateRight() {
        System.out.println("Rotating right...");
        Node leftNode = this.leftNode;
        Node upperNode = this.upperNode;
        this.leftNode = null;
        this.upperNode = null;
        if (upperNode.key < this.key)
            upperNode.rightNode = leftNode;
        else
            upperNode.leftNode = leftNode;
        leftNode.setUpperNode(this.upperNode);

        leftNode.insertChild(this);

    }

    public void balanceNode() {
        System.out.println("Checking if there is any need of rebalance...");
        int leftHeight = 0;
        int rightHeight = 0;
        if (this.leftNode != null)
            leftHeight = this.leftNode.retrieveHeight(1);
        if (this.rightNode != null)
            rightHeight = this.rightNode.retrieveHeight(1);
        int calculationResults = leftHeight - rightHeight;
        boolean isBalanced = calculationResults <= 1 && calculationResults >= -1;
        if (!isBalanced && calculationResults < -1) {
            System.out.println("Balancing tree from node: " + this.key + "\nTree pending to the right...");
            this.rotateLeft();
        }
        if (!isBalanced && calculationResults > 1) {
            System.out.println("Balancing tree from node: " + this.key + "\nTree pending to the left...");
            this.rotateRight();
        }

    }

    private Node getChild(int key) {
        if (this.key < key)
            return this.rightNode;
        else
            return this.leftNode;
    }

    public void deleteChild(int key) {
        if (this.key != key) {

            Node childNode = this.getChild(key);
            if (childNode.getKey() == key) {
                Node leftNode = childNode.getLeftNode();
                Node rightNode = childNode.getRightNode();

                if (this.key < key)
                    this.rightNode = null;
                else
                    this.leftNode = null;

                leftNode.setUpperNode(null);
                rightNode.setUpperNode(null);
                if (leftNode != null)
                    this.insertChild(leftNode);
                if (rightNode != null)
                    this.insertChild(rightNode);
            } else
                childNode.deleteChild(key);
        } else {
            if (this.upperNode == null) System.out.println("Root node is being deleted..."); 

            Node leftNode = this.leftNode;
            while (leftNode.getRightNode() != null) {
                leftNode = leftNode.getRightNode();
            }
            leftNode.insertChild(this.rightNode);

            int leftKey = this.leftNode.getKey();
            this.key = leftKey;
            this.rightNode = this.leftNode.getRightNode();
            this.leftNode = this.leftNode.getLeftNode();
            this.rightNode.setUpperNode(this);
            this.leftNode.setUpperNode(this);
            
        }
        System.out.println("Node with key "+key+" deleted successfully...");
        System.out.println("Child nodes will be adopted by this node: "+this.key+"...");

    }

}