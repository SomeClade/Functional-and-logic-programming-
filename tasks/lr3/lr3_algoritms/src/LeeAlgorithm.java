import java.util.*;

public class LeeAlgorithm {
    // Направления для движения: вправо, вниз, влево, вверх
    private static final int[] dRow = {0, 1, 0, -1};
    private static final int[] dCol = {1, 0, -1, 0};

    public static List<int[]> findShortestPath(int[][] grid, int[] start, int[] end) {
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];
        int[][] parent = new int[rows * cols][2]; // Родительская матрица для отслеживания пути

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        distance[start[0]][start[1]] = 0;
        parent[start[0] * cols + start[1]] = new int[]{-1, -1};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            // Если достигли конечной точки
            if (row == end[0] && col == end[1]) {
                return reconstructPath(parent, start, end, rows, cols);
            }

            // Перемещаемся во всех 4 направлениях
            for (int i = 0; i < 4; i++) {
                int newRow = row + dRow[i];
                int newCol = col + dCol[i];

                if (isValid(newRow, newCol, rows, cols) && !visited[newRow][newCol] && grid[newRow][newCol] == 0) {
                    queue.add(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                    distance[newRow][newCol] = distance[row][col] + 1;
                    parent[newRow * cols + newCol] = new int[]{row, col};
                }
            }
        }

        return new ArrayList<>(); // Если путь не найден, возвращаем пустой список
    }

    private static boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private static List<int[]> reconstructPath(int[][] parent, int[] start, int[] end, int rows, int cols) {
        LinkedList<int[]> path = new LinkedList<>();
        int row = end[0];
        int col = end[1];

        while (row != -1 && col != -1) {
            path.addFirst(new int[]{row, col});
            int[] p = parent[row * cols + col];
            row = p[0];
            col = p[1];
        }

        return path;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        int[] start = {0, 0};
        int[] end = {4, 4};

        List<int[]> path = findShortestPath(grid, start, end);

        if (!path.isEmpty()) {
            System.out.println("Кратчайший путь:");
            for (int[] cell : path) {
                System.out.println("(" + cell[0] + ", " + cell[1] + ")");
            }
        } else {
            System.out.println("Путь не найден");
        }
    }
}
