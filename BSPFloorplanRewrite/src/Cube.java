public class Cube {
    private int x, y, width, height;
    private Cube leftChild , rightChild;
    private int door = 0;
    private boolean split = false;

    public Cube(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public Cube(int x, int y, int width, int height, int door) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.door = door;
    }

    public int getDoor() {
        return door;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setSplit() {
        split = true;
    }
    public Boolean getSplit() {
        return split;
    }

    //Unused Code might be needed if you change the way the BSP Tree is stored

    public Boolean hasChildren() {
        if (leftChild != null && rightChild != null) {
            return true;
        } else {
            return false;
        }
    }

    public Cube getRightChild() {
        return rightChild;
    }

    public Cube getLeftChild() {
        return leftChild;
    }

    public void setRightChild(Cube rC) {
        rightChild = rC;
    }

    public void setRightChild(int x, int y, int width, int height) {
        Cube rcTemp;
        rcTemp = new Cube(x, y, width, height);
        rightChild = rcTemp;
    }

    public void setLeftChild(Cube lC) {
        leftChild = lC;
    }

    public void setLeftChild(int x, int y, int width, int height) {
        Cube lcTemp;
        lcTemp = new Cube(x, y, width, height);
        leftChild = lcTemp;
    }
}