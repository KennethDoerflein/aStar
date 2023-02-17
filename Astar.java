// reference material https://stackabuse.com/graphs-in-java-a-star-algorithm/

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Astar {

    private static node goalNode;
    private static node startNode;

    public static node aStarAlgo(node start, node target) {
        start.setfValue(start.getgValue() + target.gethValue());
        PriorityQueue<node> closedList = new PriorityQueue<node>();
        PriorityQueue<node> openList = new PriorityQueue<node>();
        openList.add(start);
        while (openList.size() > 0) {
            node currentNode = openList.poll();
            //System.out.println("Current node: " + currentNode.getName());
            if (currentNode.getName() == goalNode.getName()) {
                return currentNode;
            }
            for (node.Path path : currentNode.getNeighbors()
            ) {
                node currentNeighbor = path.getNode();
                //System.out.println("Current Neighbor" + currentNeighbor.getName());
                int pathWeight = currentNode.getgValue() + path.getWeight();
                //System.out.println(pathWeight);

                if ((!openList.contains(currentNeighbor) && !closedList.contains(currentNeighbor)) || pathWeight < currentNeighbor.getgValue()) {
                    currentNeighbor.setParent(currentNode);
                    //System.out.println("Setting neighbor " + currentNeighbor.getName() + "'s parent to " + currentNeighbor.getParent().getName());
                    currentNeighbor.setgValue(pathWeight);
                    currentNeighbor.setfValue(currentNeighbor.getgValue() + currentNeighbor.gethValue());
                    if (pathWeight < currentNeighbor.getgValue() && closedList.contains(currentNeighbor)) {
                        //System.out.println("Removing " + currentNeighbor.getName() + " from closed list");
                        closedList.remove(currentNeighbor);
                        //System.out.println("Adding " + currentNeighbor.getName() + " to open list");
                        openList.add(currentNeighbor);
                    } else {
                        openList.add(currentNeighbor);
                    }
                }
            }
            closedList.add(currentNode);
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<node> allNodes = new ArrayList<>();
        node s, a, b, c, e, f, h, g;

        // s is the start node
        allNodes.add(s = new node('s', 10));

        allNodes.add(a = new node('a', 9));

        allNodes.add(b = new node('b', 6));

        allNodes.add(c = new node('c', 8));

        allNodes.add(e = new node('e', 2));

        allNodes.add(f = new node('f', 15));

        allNodes.add(h = new node('h', 4));

        // g is the target
        allNodes.add(g = new node('g', 8));

        // Add neighbors to nodes
        s.addNeighbor(3, a);
        s.addNeighbor(4, b);

        a.addNeighbor(2, b);
        a.addNeighbor(2, c);
        a.addNeighbor(3, s);

        b.addNeighbor(2, a);
        b.addNeighbor(3, s);
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

        startNode = s;
        goalNode = g;
        startNode.setgValue(0);
        goalNode.sethValue(0);
        node solution = aStarAlgo(startNode, goalNode);
        //System.out.println(solution.getName());
        System.out.println("\n\nThe total Path Cost from start to goal: " + solution.getgValue());
        node currentElementInPath = solution;
        ArrayList<node> fullPath = new ArrayList<node>();
        while (currentElementInPath != null) {

            //System.out.println(currentElementInPath.getName());
            fullPath.add(0, currentElementInPath);
            currentElementInPath = currentElementInPath.getParent();
            //System.out.println(currentElementInPath.getName());
        }
        //System.out.println(solution.getName());
        System.out.println("\nPath from start to goal:");
        for (node currentNode : fullPath
        ) {
            System.out.println(currentNode.getName());
        }
        System.out.println("\n");
    }
}
