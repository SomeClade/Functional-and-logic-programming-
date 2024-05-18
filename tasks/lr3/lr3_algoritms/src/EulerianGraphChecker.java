import java.util.*;

public class EulerianGraphChecker {
    public static void main(String[] args) {
        int V = 8; // Количество вершин
        int[][] edges = {
                {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7},
                {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7},
                {2, 4}, {2, 5}, {2, 6}, {2, 7},
                {3, 4}, {3, 5}, {3, 6}, {3, 7},
                {4, 6}, {4, 7},
                {5, 6}, {5, 7}
        };

        if (isEulerian(V, edges)) {
            System.out.println("The graph is Eulerian.");
        } else {
            System.out.println("The graph is not Eulerian.");
        }
    }

    private static boolean isEulerian(int V, int[][] edges) {
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
}
