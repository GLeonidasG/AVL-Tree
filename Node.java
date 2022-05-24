import java.util.Map;
import java.util.HashMap;

class IsBalanced {
    private boolean _isBalanced;
    private int _calculationResults;

    public IsBalanced(boolean isBalanced, int calculationResults){
        this._isBalanced = isBalanced;
        this._calculationResults = calculationResults;
    }
    public boolean isBalanced(){ return this._isBalanced; }
    public int calculationResults(){ return this._calculationResults; }
}

public class Node {
    private Node rightNode;
    private Node leftNode;
    private int key;

    public Node(int key) {
        this.key = key;
    }

    public Node(int key,Node node) {
        this.key = key;
        if (this.key < node.getKey()) {
            this.rightNode = node;
        } else {
            this.leftNode = node;
        }
    } 

    public int searchKey(int key) {
        int index = 0;
        if (key == this.key) return index;
        else if (key < this.key) return this.leftNode.searchKey(key, index + 1);
        else return this.rightNode.searchKey(key, index + 1);
    }

    public int searchKey(int key, int index) {
        if (key == this.key) return index;
        else if (key < this.key) return this.leftNode.searchKey(key, index + 1);
        else return this.rightNode.searchKey(key, index + 1);
    }

    public void insertChild(Node node, Node upperNode) throws Error {
        if (this.key < node.getKey()) {
            if (this.rightNode != null) {
                this.rightNode.insertChild(node, this);
            } else {
                this.rightNode = node;
            }
        } else {
            if (this.leftNode != null) {
                this.leftNode.insertChild(node, this);
            } else {
                this.leftNode = node;
            }
        }
        IsBalanced balanceResults = this.isBalanced();
        if (!balanceResults.isBalanced()) {
            if(balanceResults.calculationResults() > 1) this.rotateLeft(upperNode);
            else if(balanceResults.calculationResults() < -1) this.rotateRight(upperNode);
        }
    }

    public int retrieveHeight() {
        int initialHeight = 1;
        int leftHeight = 0;
        int rightHeight = 0;
        if (this.rightNode != null) rightHeight = this.rightNode.retrieveHeight(initialHeight);
        if (this.leftNode != null) leftHeight = this.leftNode.retrieveHeight(initialHeight);
        if (leftHeight > rightHeight) return leftHeight;
        else if(leftHeight < rightHeight) return rightHeight;
        else return initialHeight;
    }

    public int retrieveHeight(int initialHeight) {
        int leftHeight = 0;
        int rightHeight = 0;
        if (this.rightNode == null && this.leftNode == null) return initialHeight + 1;
        if(this.rightNode != null) rightHeight = this.rightNode.retrieveHeight(initialHeight + 1);
        if(this.leftNode != null) leftHeight = this.leftNode.retrieveHeight(initialHeight + 1);

        if(leftHeight > rightHeight) return leftHeight;
        else if (leftHeight < rightHeight) return rightHeight;
        else return initialHeight;

    }

    public Node getRightNode() { return this.rightNode; }
    public Node getLeftNode() { return this.leftNode; }
    public int getKey() { return this.key; }

    public void rotateLeft(Node upperNode){
        System.out.println("Coming soon");
    }

    public void rotateRight(Node upperNode){
        if (upperNode != null) {
            upperNode.rightNode = this.rightNode;
            this.rightNode.insertChild(this, upperNode);
            this.rightNode = null;
        } else {
            Node thisReplica = new Node(this.key);
            thisReplica.insertChild(this.leftNode, this.rightNode);
            this.leftNode = thisReplica;
            this.key = this.rightNode.key;
            this.rightNode = this.rightNode.rightNode;
        }
    }

    public IsBalanced isBalanced() {
        int leftHeight = 0;
        int rightHeight = 0;
        if (this.leftNode != null) leftHeight = this.leftNode.retrieveHeight();
        if (this.rightNode != null) rightHeight = this.rightNode.retrieveHeight();
        int calculationResults = leftHeight - rightHeight;
        boolean isBalanced = calculationResults <= 1 && calculationResults >= -1;
        IsBalanced results = new IsBalanced(isBalanced, calculationResults);
        return results;
    }

}