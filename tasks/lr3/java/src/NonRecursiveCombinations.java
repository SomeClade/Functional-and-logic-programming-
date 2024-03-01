import java.io.FileWriter;
import java.io.IOException;

public class NonRecursiveCombinations {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("non_recursive_combinations.txt")) {
            int n = 3; // Количество элементов
            int k = 2; // Размер сочетаний
            int[] combination = new int[k];
            while (combination[k - 1] < n) {
                for (int i = 0; i < k; ++i) {
                    writer.write((char) ('a' + combination[i]));
                }
                writer.write("\n");

                int t = k - 1;
                while (t != 0 && combination[t] == n - 1) t--;
                combination[t]++;
                for (int i = t + 1; i < k; i++) {
                    combination[i] = combination[t];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
