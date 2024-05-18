import java.util.*;

public class EulerianGraphGenerator {
    private final int V = 8; // Количество вершин

    public void generateAllEulerianGraphs() {
        List<int[]> allEdges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                allEdges.add(new int[]{i, j});
            }
        }

        int maxEdges = V * (V - 1) / 2; // Максимальное количество рёбер
        List<int[][]> eulerianGraphs = new ArrayList<>();

        for (int k = 1; k <= maxEdges; k++) {
            generateCombinations(allEdges, new int[k], 0, 0, eulerianGraphs);
        }

        System.out.println("Total number of Eulerian graphs: " + eulerianGraphs.size());
        for (int i = 0; i < eulerianGraphs.size(); i++) {
            System.out.println("Graph " + (i + 1) + ":");
            for (int[] edge : eulerianGraphs.get(i)) {
                System.out.println("(" + edge[0] + " -- " + edge[1] + ")");
            }
            System.out.println();
        }
    }

    private void generateCombinations(List<int[]> allEdges, int[] current, int start, int index, List<int[][]> eulerianGraphs) {
        if (index == current.length) {
            int[][] edges = new int[current.length][2];
            for (int i = 0; i < current.length; i++) {
                edges[i] = allEdges.get(current[i]);
            }
            if (isEulerian(edges)) {
                eulerianGraphs.add(edges);
            }
            return;
        }

        for (int i = start; i <= allEdges.size() - (current.length - index); i++) {
            current[index] = i;
            generateCombinations(allEdges, current, i + 1, index + 1, eulerianGraphs);
        }
    }

    private boolean isEulerian(int[][] edges) {
        int[] degree = new int[V];
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        for (int d : degree) {
            if (d % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        EulerianGraphGenerator generator = new EulerianGraphGenerator();
        generator.generateAllEulerianGraphs();
    }
}
