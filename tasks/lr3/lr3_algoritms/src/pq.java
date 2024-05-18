import java.util.*;

public class pq {
    private final int p; // Количество вершин
    private final int q; // Количество рёбер

    public pq(int p, int q) {
        this.p = p;
        this.q = q;
    }

    public void generateAllLabeledGraphs() {
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < p; j++) {
                if (i != j) {
                    edges.add(new int[]{i, j});
                }
            }
        }

        List<int[][]> edgeCombinations = new ArrayList<>();
        generateEdgeCombinations(edges, new int[q], 0, 0, edgeCombinations);

        int numLabels = p; // Количество меток равно количеству вершин
        List<int[]> labelPermutations = new ArrayList<>();
        generateLabelPermutations(new int[p], 0, numLabels, labelPermutations);

        List<int[][]> labeledGraphs = new ArrayList<>();
        for (int[][] edgeCombination : edgeCombinations) {
            for (int[] labels : labelPermutations) {
                if (isValidLabeling(edgeCombination, labels)) {
                    int[][] labeledGraph = new int[q][3];
                    for (int i = 0; i < q; i++) {
                        labeledGraph[i][0] = edgeCombination[i][0];
                        labeledGraph[i][1] = edgeCombination[i][1];
                        labeledGraph[i][2] = labels[edgeCombination[i][0]]; // Метка начальной вершины
                    }
                    labeledGraphs.add(labeledGraph);
                }
            }
        }

        System.out.println("Total number of labeled graphs: " + labeledGraphs.size());
        for (int i = 0; i < labeledGraphs.size(); i++) {
            System.out.println("Graph " + (i + 1) + ":");
            for (int[] edge : labeledGraphs.get(i)) {
                System.out.println("(" + edge[0] + " -> " + edge[1] + ") with label " + edge[2]);
            }
            System.out.println();
        }
    }

    private void generateEdgeCombinations(List<int[]> edges, int[] current, int start, int index, List<int[][]> result) {
        if (index == q) {
            int[][] graphEdges = new int[q][2];
            for (int i = 0; i < q; i++) {
                graphEdges[i] = edges.get(current[i]);
            }
            result.add(graphEdges);
            return;
        }

        for (int i = start; i <= edges.size() - (q - index); i++) {
            current[index] = i;
            generateEdgeCombinations(edges, current, i + 1, index + 1, result);
        }
    }

    private void generateLabelPermutations(int[] current, int index, int numLabels, List<int[]> result) {
        if (index == current.length) {
            result.add(current.clone());
            return;
        }

        for (int label = 1; label <= numLabels; label++) {
            current[index] = label;
            generateLabelPermutations(current, index + 1, numLabels, result);
        }
    }

    private boolean isValidLabeling(int[][] edges, int[] labels) {
        for (int[] edge : edges) {
            if (labels[edge[0]] == labels[edge[1]]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices (p): ");
        int p = scanner.nextInt();

        System.out.print("Enter the number of edges (q): ");
        int q = scanner.nextInt();

        pq generator = new pq(p, q);
        generator.generateAllLabeledGraphs();
    }
}
