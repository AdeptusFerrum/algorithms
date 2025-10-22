import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    private boolean isNew;
    private List<GraphNode> links;

    public GraphNode() {
        this.links = new ArrayList<>();
        this.isNew = true;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public List<GraphNode> getLinks() {
        return links;
    }

    public void setLinks(List<GraphNode> links) {
        this.links = links;
    }

    public void addLink(GraphNode node) {
        this.links.add(node);
    }
}