import java.util.*;

public class DijkstraAlgorithm {

    static class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    static class NodeDistance implements Comparable<NodeDistance> {
        int node;
        int distance;

        NodeDistance(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeDistance other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static void dijkstra(List<List<Edge>> graph, int start) {
        int n = graph.size();
        int[] distances = new int[n];
        int[] previous = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);
        distances[start] = 0;

        PriorityQueue<NodeDistance> pq = new PriorityQueue<>();
        pq.offer(new NodeDistance(start, 0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            int currentNode = current.node;

            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            for (Edge edge : graph.get(currentNode)) {
                int neighbor = edge.target;
                int newDistance = distances[currentNode] + edge.weight;

                if (newDistance < distances[neighbor]) {
                    distances[neighbor] = newDistance;
                    previous[neighbor] = currentNode;
                    pq.offer(new NodeDistance(neighbor, newDistance));
                }
            }
        }

        printResults(distances, previous, start);
    }

    private static void printResults(int[] distances, int[] previous, int start) {
        System.out.println("Кратчайшие пути из вершины " + start + ":");
        System.out.println("Вершина | Расстояние | Путь");
        System.out.println("--------|------------|------");

        for (int i = 1; i < distances.length; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.printf("%7d | %10s | %s%n", i, "∞", "Нет пути");
            } else {
                System.out.printf("%7d | %10d | %s%n",
                        i, distances[i], getPath(previous, i));
            }
        }
    }

    private static String getPath(int[] previous, int node) {
        List<Integer> path = new ArrayList<>();

        for (int current = node; current != -1; current = previous[current]) {
            path.add(current);
        }

        Collections.reverse(path);
        return path.toString();
    }

    public static void main(String[] args) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(1).add(new Edge(2, 10));  // 1 → 2 (10)
        graph.get(1).add(new Edge(4, 30));  // 1 → 4 (30)
        graph.get(2).add(new Edge(3, 50));  // 2 → 3 (50)
        graph.get(3).add(new Edge(5, 10));  // 3 → 5 (10)
        graph.get(4).add(new Edge(3, 20));  // 4 → 3 (20)
        graph.get(4).add(new Edge(5, 60));  // 4 → 5 (60)

        System.out.println("Структура графа:");
        System.out.println("1 → 2 (10)");
        System.out.println("1 → 4 (30)");
        System.out.println("2 → 3 (50)");
        System.out.println("3 → 5 (10)");
        System.out.println("4 → 3 (20)");
        System.out.println("4 → 5 (60)");
        System.out.println();

        dijkstra(graph, 1);

        System.out.println("\nАнализ сложности:");
        System.out.println("Вершин (V): " + (graph.size() - 1));
        System.out.println("Рёбер (E): " +
                graph.stream().mapToInt(List::size).sum());
        System.out.println("Временная сложность: O((V+E)logV) = O(" +
                ((graph.size()-1 + graph.stream().mapToInt(List::size).sum()) +
                        "×log" + (graph.size()-1) + ")"));
        System.out.println("Пространственная сложность: O(V+E)");
    }
}