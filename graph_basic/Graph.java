import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<GraphNode> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<GraphNode> nodes) {
        this.nodes = nodes;
    }

    public void addNode(GraphNode node) {
        this.nodes.add(node);
    }
}