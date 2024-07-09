//Name - Ranujitha Sugathadasa
//Student ID - 20221296


public class Node {
    private Node prevNode;
    private int xPosition;
    private int yPosition;

    //Constructors
    public Node(int x, int y, Object o){
        this.prevNode = null;
        this.xPosition = x;
        this.yPosition = y;
    }
    public Node(Node prevNode, int x, int y) {
        this.prevNode = prevNode;
        this.xPosition = x;
        this.yPosition = y;
    }

    //Getters
    public Node getPrevNode() {
        return prevNode;
    }
    public int getx() {
        return xPosition;
    }
    public int gety() {
        return yPosition;
    }

    //Setters
    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }
    public void setx(int xPosition) {
        this.xPosition = xPosition;
    }
    public void sety(int yPosition) {
        this.yPosition = yPosition;
    }
}

