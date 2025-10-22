import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        GraphNode node1 = new GraphNode();
        GraphNode node2 = new GraphNode();
        GraphNode node3 = new GraphNode();
        GraphNode node4 = new GraphNode();

        node1.addLink(node2);
        node2.addLink(node3);
        node3.addLink(node1);
        node3.addLink(node4);
        node4.addLink(node2);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        List<List<GraphNode>> cycles = PathFinder.find(graph);

        System.out.println("Найдено контуров: " + cycles.size());
        for (int i = 0; i < cycles.size(); i++) {
            System.out.println("Контур " + (i + 1) + ": " + cycles.get(i).size() + " вершин");
        }
    }
}