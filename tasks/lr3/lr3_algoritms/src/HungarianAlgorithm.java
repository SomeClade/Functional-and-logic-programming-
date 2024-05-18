import java.util.Arrays;

public class HungarianAlgorithm {

    public static int[][] hungarianAlgorithm(int[][] costMatrix) {
        int n = costMatrix.length;
        int[][] assignment = new int[n][n];

        // Шаг 1: Вычитание минимальных элементов в строках
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (costMatrix[i][j] < min) {
                    min = costMatrix[i][j];
                }
            }
            for (int j = 0; j < n; j++) {
                costMatrix[i][j] -= min;
            }
        }

        // Шаг 2: Вычитание минимальных элементов в столбцах
        for (int j = 0; j < n; j++) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (costMatrix[i][j] < min) {
                    min = costMatrix[i][j];
                }
            }
            for (int i = 0; i < n; i++) {
                costMatrix[i][j] -= min;
            }
        }

        boolean[] rowCovered = new boolean[n];
        boolean[] colCovered = new boolean[n];
        boolean[][] stars = new boolean[n][n];
        boolean[][] primes = new boolean[n][n];

        while (true) {
            Arrays.fill(rowCovered, false);
            Arrays.fill(colCovered, false);
            findStars(costMatrix, stars, rowCovered, colCovered);

            int[] prime = findUncoveredZero(costMatrix, rowCovered, colCovered);
            while (prime[0] != -1) {
                primes[prime[0]][prime[1]] = true;
                int starCol = findStarInRow(stars, prime[0]);
                if (starCol != -1) {
                    rowCovered[prime[0]] = true;
                    colCovered[starCol] = false;
                } else {
                    augmentPath(stars, primes, prime);
                    break;
                }
                prime = findUncoveredZero(costMatrix, rowCovered, colCovered);
            }

            if (isOptimal(rowCovered, colCovered)) {
                break;
            }

            adjustMatrix(costMatrix, rowCovered, colCovered);
        }

        for (int i = 0; i < n; i++) {
            int starCol = findStarInRow(stars, i);
            if (starCol != -1) {
                assignment[i][starCol] = 1;
            }
        }

        // Вывод итоговой матрицы
        System.out.println("Итоговая матрица после всех преобразований:");
        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix[i].length; j++) {
                System.out.print(costMatrix[i][j] + " ");
            }
            System.out.println();
        }

        return assignment;
    }

    // Поиск звездных нулей
    private static void findStars(int[][] costMatrix, boolean[][] stars, boolean[] rowCovered, boolean[] colCovered) {
        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix[i].length; j++) {
                if (costMatrix[i][j] == 0 && !rowCovered[i] && !colCovered[j]) {
                    stars[i][j] = true;
                    rowCovered[i] = true;
                    colCovered[j] = true;
                }
            }
        }
        Arrays.fill(rowCovered, false);
        Arrays.fill(colCovered, false);
    }

    // Проверка оптимальности покрытия
    private static boolean isOptimal(boolean[] rowCovered, boolean[] colCovered) {
        for (boolean covered : rowCovered) {
            if (covered) {
                return true;
            }
        }
        for (boolean covered : colCovered) {
            if (covered) {
                return true;
            }
        }
        return false;
    }

    // Поиск непокрытого нуля
    private static int[] findUncoveredZero(int[][] costMatrix, boolean[] rowCovered, boolean[] colCovered) {
        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix[i].length; j++) {
                if (costMatrix[i][j] == 0 && !rowCovered[i] && !colCovered[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    // Корректировка матрицы
    private static void adjustMatrix(int[][] costMatrix, boolean[] rowCovered, boolean[] colCovered) {
        int minValue = findSmallestUncoveredValue(costMatrix, rowCovered, colCovered);
        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix[i].length; j++) {
                if (rowCovered[i]) {
                    costMatrix[i][j] += minValue;
                }
                if (!colCovered[j]) {
                    costMatrix[i][j] -= minValue;
                }
            }
        }
    }

    // Поиск минимального непокрытого значения
    private static int findSmallestUncoveredValue(int[][] costMatrix, boolean[] rowCovered, boolean[] colCovered) {
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < costMatrix.length; i++) {
            for (int j = 0; j < costMatrix[i].length; j++) {
                if (!rowCovered[i] && !colCovered[j] && costMatrix[i][j] < minValue) {
                    minValue = costMatrix[i][j];
                }
            }
        }
        return minValue;
    }

    // Поиск звездного нуля в строке
    private static int findStarInRow(boolean[][] stars, int row) {
        for (int j = 0; j < stars[row].length; j++) {
            if (stars[row][j]) {
                return j;
            }
        }
        return -1;
    }

    // Аугментирование пути
    private static void augmentPath(boolean[][] stars, boolean[][] primes, int[] prime) {
        boolean done = false;
        int i = prime[0];
        int j = prime[1];

        while (!done) {
            int starCol = findStarInRow(stars, i);
            if (starCol != -1) {
                stars[i][starCol] = false;
                stars[i][j] = true;
                j = starCol;

                int primeRow = findPrimeInCol(primes, j);
                if (primeRow != -1) {
                    i = primeRow;
                    primes[i][j] = false;
                } else {
                    done = true;
                }
            } else {
                stars[i][j] = true;
                done = true;
            }
        }
    }

    // Поиск простого нуля в столбце
    private static int findPrimeInCol(boolean[][] primes, int col) {
        for (int i = 0; i < primes.length; i++) {
            if (primes[i][col]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] costMatrix = {
                {9, 11, 14, 11, 7},
                {6, 15, 13, 13, 10},
                {12, 13, 6, 8, 8},
                {11, 9, 10, 12, 9},
                {7, 12, 14, 10, 14}
        };

        int[][] assignment = hungarianAlgorithm(costMatrix);

        System.out.println("Оптимальное назначение:");
        for (int i = 0; i < assignment.length; i++) {
            for (int j = 0; j < assignment[i].length; j++) {
                if (assignment[i][j] == 1) {
                    System.out.println("Строка " + i + " назначена столбцу " + j);
                }
            }
        }
    }
}
