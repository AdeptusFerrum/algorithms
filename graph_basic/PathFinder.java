import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    private static List<GraphNode> list;
    private static List<List<GraphNode>> paths;

    public static List<List<GraphNode>> find(Graph graph) {
        list = new ArrayList<>();
        paths = new ArrayList<>();

        for (GraphNode node : graph.getNodes()) {
            node.setNew(true);
        }

        if (!graph.getNodes().isEmpty()) {
            list.add(graph.getNodes().get(0));
        }

        boolean done = false;
        while (!done) {
            while (!list.isEmpty()) {
                internalFind(list.get(list.size() - 1));
            }

            done = true;
            for (GraphNode node : graph.getNodes()) {
                if (node.isNew()) {
                    list.add(node);
                    done = false;
                    break;
                }
            }
        }
        return paths;
    }

    private static void internalFind(GraphNode node) {
        node.setNew(false);

        for (GraphNode nextNode : node.getLinks()) {
            if (nextNode.isNew()) {
                list.add(nextNode);
                internalFind(nextNode);
            } else if (list.contains(nextNode)) {
                List<GraphNode> newPath = new ArrayList<>();
                int firstElement = list.indexOf(nextNode);

                for (int i = firstElement; i < list.size(); i++) {
                    newPath.add(list.get(i));
                }
                paths.add(newPath);
            }
        }
        list.remove(node);
    }
}
