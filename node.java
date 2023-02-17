import java.util.ArrayList;

public class node implements Comparable<node>{
    @Override
    public int compareTo(node o) {
        return Integer.compare(this.fValue , o.getfValue());
    }

    public static class Path{
        private int weight;
        private node node;

        public Path (int weight, node newNeighbor){
            this.node = newNeighbor;
            this.weight = weight;
        }

        public node getNode() {
            return node;
        }

        public int getWeight() {
            return weight;
        }
    }
    private node parent = null;
    private char name;
    private int gValue = -1; // cost of reaching this node from the start node
    private int hValue = -1; // cost of reaching this node from the end node
    private int fValue;
    private ArrayList<Path> neighbors;

    public node(char name, int h) {
        this.name = name;
        this.hValue = h;
        //this.parents = new ArrayList<node>();
        this.neighbors = new ArrayList<Path>();
    }

    public int getfValue() {
        return fValue;
    }

    public ArrayList<Path> getNeighbors() {
        return neighbors;
    }

    public void setParent(node parent) {
        this.parent = parent;
        //System.out.println(name + parents.toString() + parents.get(parents.size()-1).getName());
    }

    public char getName() {
        return name;
    }

    public int getgValue() {
        return gValue;
    }

    public int gethValue() {
        return hValue;
    }

    public void setgValue(int gValue) {
        this.gValue = gValue;
    }

    public void sethValue(int hValue) {
        this.hValue = hValue;
    }

    public void setfValue(int fValue) {
        this.fValue = fValue;
    }

    public void addNeighbor(int weight, node neighbor) {
        Path newPath;
        this.neighbors.add(newPath = new Path(weight,neighbor));
    }

//    public boolean isGoal() {
//        return this.name == 'g';
//    }

    public node getParent() {
        return parent;
    }
}
