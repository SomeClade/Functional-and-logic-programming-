import java.util.*;

public class minpokr {
    private final int V; // Количество вершин
    private final List<List<Integer>> adj; // Список смежности

    // Конструктор
    public minpokr(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Добавление ребра
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v); // Так как граф неориентированный
    }

    // Метод для поиска всех простых циклов
    public List<List<Integer>> findAllSimpleCycles() {
        List<List<Integer>> cycles = new ArrayList<>();
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        for (int v = 0; v < V; v++) {
            dfs(v, visited, parent, cycles);
            Arrays.fill(visited, false); // Сброс visited для нового цикла
            Arrays.fill(parent, -1); // Сброс parent для нового цикла
        }

        return cycles;
    }

    // Рекурсивная функция для поиска циклов
    private void dfs(int v, boolean[] visited, int[] parent, List<List<Integer>> cycles) {
        visited[v] = true;

        for (int adjV : adj.get(v)) {
            if (!visited[adjV]) {
                parent[adjV] = v;
                dfs(adjV, visited, parent, cycles);
            } else if (parent[v] != adjV) {
                // Найден цикл
                List<Integer> cycle = new ArrayList<>();
                int current = v;
                cycle.add(current);
                while (current != adjV) {
                    current = parent[current];
                    cycle.add(current);
                }
                cycle.add(v);
                cycles.add(cycle);
            }
        }

        visited[v] = false; // Убираем вершину из стека вызовов
    }

    // Метод для построения минимального покрытия вершин
    public Set<Integer> findMinimumVertexCover() {
        boolean[] visited = new boolean[V];
        Set<Integer> vertexCover = new HashSet<>();

        for (int u = 0; u < V; u++) {
            if (!visited[u]) {
                for (int v : adj.get(u)) {
                    if (!visited[v]) {
                        // Включаем вершины u и v в покрытие
                        visited[u] = true;
                        visited[v] = true;
                        vertexCover.add(u);
                        vertexCover.add(v);
                        break;
                    }
                }
            }
        }

        return vertexCover;
    }

    public static void main(String[] args) {
        minpokr g = new minpokr(5);

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 1);

        // Поиск всех простых циклов
        List<List<Integer>> cycles = g.findAllSimpleCycles();

        System.out.println("Простые циклы в графе:");
        for (List<Integer> cycle : cycles) {
            for (int node : cycle) {
                System.out.print(node + " ");
            }
            System.out.println();
        }

        // Поиск минимального покрытия вершин
        Set<Integer> vertexCover = g.findMinimumVertexCover();

        System.out.println("Минимальное покрытие вершин:");
        for (int vertex : vertexCover) {
            System.out.print(vertex + " ");
        }
    }
}
