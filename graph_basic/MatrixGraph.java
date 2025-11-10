import java.util.*;

public class MatrixGraph {
    private int[][] adjacencyMatrix;
    private int vertices;
    private Map<Integer, Integer> vertexIndexMap;
    private Map<Integer, Integer> indexVertexMap;

    public MatrixGraph(int maxVertices) {
        this.adjacencyMatrix = new int[maxVertices][maxVertices];
        this.vertices = 0;
        this.vertexIndexMap = new HashMap<>();
        this.indexVertexMap = new HashMap<>();
    }

    private int getVertexIndex(int vertex) {
        if (!vertexIndexMap.containsKey(vertex)) {
            vertexIndexMap.put(vertex, vertices);
            indexVertexMap.put(vertices, vertex);
            vertices++;
        }
        return vertexIndexMap.get(vertex);
    }

    public void addEdge(int u, int v) {
        int uIndex = getVertexIndex(u);
        int vIndex = getVertexIndex(v);

        adjacencyMatrix[uIndex][vIndex] = 1;
        adjacencyMatrix[vIndex][uIndex] = 1;
    }

    public void dfs(int start) {
        if (!vertexIndexMap.containsKey(start)) {
            System.out.println("Вершина " + start + " не найдена в графе");
            return;
        }

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        System.out.print("DFS (начиная с " + start + "): ");

        int startIndex = getVertexIndex(start);
        stack.push(startIndex);

        while (!stack.isEmpty()) {
            int currentIndex = stack.pop();

            if (!visited.contains(currentIndex)) {
                visited.add(currentIndex);
                int currentVertex = indexVertexMap.get(currentIndex);
                System.out.print(currentVertex + " ");

                for (int i = adjacencyMatrix.length - 1; i >= 0; i--) {
                    if (adjacencyMatrix[currentIndex][i] == 1 && !visited.contains(i)) {
                        stack.push(i);
                    }
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MatrixGraph matrixGraph = new MatrixGraph(10);

        int[][] edges = {
                {1, 2}, {1, 3}, {1, 4},
                {2, 1}, {2, 3}, {2, 5},
                {3, 1}, {3, 4}, {3, 2},
                {4, 1}, {4, 3},
                {5, 2}
        };

        for (int[] edge : edges) {
            matrixGraph.addEdge(edge[0], edge[1]);
        }

        matrixGraph.dfs(1);
        matrixGraph.dfs(5);
    }
}