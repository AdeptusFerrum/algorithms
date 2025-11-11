import java.util.*;

class Graph {
    private int V;
    private LinkedList<Integer> adj[];

    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
        visited[v] = true;

        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            int i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        stack.push(v);
    }

    void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        System.out.print("Топологическая сортировка: ");
        while (!stack.empty()) {
            System.out.print((stack.pop() + 1) + " ");
        }
    }
}

public class Topology {
    public static void main(String args[]) {
        Graph g = new Graph(7);

        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(2, 6);
        g.addEdge(3, 5);
        g.addEdge(5, 4);
        g.addEdge(5, 6);
        g.addEdge(4, 6);

        g.topologicalSort();
    }
}