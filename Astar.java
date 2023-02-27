// reference material https://stackabuse.com/graphs-in-java-a-star-algorithm/

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Astar {

    private static node goalNode;
    private static node startNode;

    public static node aStarAlgo(node start, node target) {
        start.setfValue(start.getgValue() + target.gethValue()); // set start nodes f value, f = h + g
        PriorityQueue<node> closedList = new PriorityQueue<node>(); // contains nodes whose neighbors have been added to open list path
        PriorityQueue<node> openList = new PriorityQueue<node>(); // contains all nodes we visited, path/cost not calculated yet
        openList.add(start); // add open node to start list
        while (openList.size() > 0) { // loop while we have something in the open list
            // PriorityQueue with overridden comparable sorts listed so best f value is first in list
            node currentNode = openList.poll(); // get and remove first node from open list
            //System.out.println("Current node: " + currentNode.getName());
            if (currentNode.getName() == goalNode.getName()) { // check if current node is the goal
                return currentNode; // return goal node when found
            }
            for (node.Path path : currentNode.getNeighbors() // foreach loop the goes through currentNode's neighbors
            ) {
                node currentNeighbor = path.getNode(); // get the current neighbor from the path
                //System.out.println("Current Neighbor" + currentNeighbor.getName());
                int pathWeight = currentNode.getgValue() + path.getWeight(); // new path weight is current nodes g value + path cost
                //System.out.println(pathWeight);

                // if the currentNeighbor is not in the open list or the closed list or the new pathWeight is less than currentNeighbors g value
                if ((!openList.contains(currentNeighbor) && !closedList.contains(currentNeighbor)) || pathWeight < currentNeighbor.getgValue()) {
                    currentNeighbor.setParent(currentNode); // set the currentNeighbor's parent to the 'currentNode' declared before the foreach loop of neighbors
                    //System.out.println("Setting neighbor " + currentNeighbor.getName() + "'s parent to " + currentNeighbor.getParent().getName());
                    currentNeighbor.setgValue(pathWeight); // set neighbors g value to new pathWeight that was  calculated above
                    currentNeighbor.setfValue(currentNeighbor.getgValue() + currentNeighbor.gethValue()); // update the currentNeighbor's f value based on updated g value

                    // if new path weight is better than previous and its in the closed list
                    if (pathWeight < currentNeighbor.getgValue() && closedList.contains(currentNeighbor)) {
                        //System.out.println("Removing " + currentNeighbor.getName() + " from closed list");
                        closedList.remove(currentNeighbor); // take the currentNeighbor our of the closed list
                        //System.out.println("Adding " + currentNeighbor.getName() + " to open list");
                        openList.add(currentNeighbor); // add currentNeighbor to the open list
                    } else {
                        openList.add(currentNeighbor); // add currentNeighbor to the open list
                    }
                }
            }
            closedList.add(currentNode); // add the parent node of currentNeighbor to the closed list
        }
        return null; // return null if not found
    }

    public static void main(String[] args) {
        ArrayList<node> allNodes = new ArrayList<>(); // create array no add all nodes to, unused here
        node s, a, b, c, e, f, h, g; // declare all nodes that will be used

        // s is the start node
        allNodes.add(s = new node('s', 10)); // Create node s with a heuristic of 10, also adds to allNodes array

        allNodes.add(a = new node('a', 9)); // Create node a with a heuristic of 9, also adds to allNodes array

        allNodes.add(b = new node('b', 6)); // Create node b with a heuristic of 6, also adds to allNodes array

        allNodes.add(c = new node('c', 8)); // Create node c with a heuristic of 8, also adds to allNodes array

        allNodes.add(e = new node('e', 2)); // Create node e with a heuristic of 2, also adds to allNodes array

        allNodes.add(f = new node('f', 15)); // Create node f with a heuristic of 15, also adds to allNodes array

        allNodes.add(h = new node('h', 4)); // Create node h with a heuristic of 4, also adds to allNodes array

        // g is the target
        allNodes.add(g = new node('g', 8)); // Create node g with a heuristic of 8, also adds to allNodes array

        // Add neighbors to nodes
        s.addNeighbor(3, a); // add neighbor a with a cost of 3 to node s
        s.addNeighbor(4, b); // add neighbor b with a cost of 4 to node s

        a.addNeighbor(2, b); // add neighbor b with a cost of 2 to node a
        a.addNeighbor(2, c); // add neighbor c with a cost of 2 to node a
        a.addNeighbor(3, s); // add neighbor s with a cost of 3 to node a

        b.addNeighbor(2, a);
        b.addNeighbor(4, s);
        b.addNeighbor(3, h);
        b.addNeighbor(10, e);

        c.addNeighbor(4, h);
        c.addNeighbor(2, a);

        h.addNeighbor(5, g);
        h.addNeighbor(3, b);
        h.addNeighbor(4, c);

        e.addNeighbor(5, f);
        e.addNeighbor(10, b);

        f.addNeighbor(20, g);
        f.addNeighbor(5, e);

        g.addNeighbor(5, h);
        g.addNeighbor(20, f);

        startNode = s; // set the start node to s
        goalNode = g; // set the goal node to g
        startNode.setgValue(0); // change g of start node to 0
        goalNode.sethValue(0); // change heuristic of goal to 0

        node solution = aStarAlgo(startNode, goalNode); // call algorithm function with start and goal nodes, save goal node returned
        //System.out.println(solution.getName());

        node currentElementInPath = solution; // set returned node to new node for printing
        ArrayList<node> fullPath = new ArrayList<node>(); // create array for path

        // while we don't run out of nodes
        while (currentElementInPath != null) {
            //System.out.println(currentElementInPath.getName());
            fullPath.add(0, currentElementInPath); // add current node ot path
            currentElementInPath = currentElementInPath.getParent(); // get that nodes parent and set as current node
            //System.out.println(currentElementInPath.getName());
        }
        //System.out.println(solution.getName());
        System.out.println("\nPath from start to goal:");

        // print out path
        for (node currentNode : fullPath
        ) {
            System.out.println(currentNode.getName());
        }
        System.out.println("\nThe total Path Cost from start to goal: " + solution.getgValue());
        //System.out.println("\n");
    }
}
