import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CombinationsWithRepetition {
    // Рекурсивная функция для создания сочетаний с повторениями
    public static void combinationsWithRepetition(int n, int k, int start, int[] combination, FileWriter writer) throws IOException {
        if (k == 0) {
            for (int i = 0; i < combination.length; i++) {
                writer.write((char)('a' + combination[i]));
            }
            writer.write('\n');
            return;
        }
        for (int i = start; i < n; i++) {
            combination[combination.length - k] = i;
            combinationsWithRepetition(n, k - 1, i, combination, writer);
        }
    }

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("combinations_with_repetition.txt")) {
            int n = 3; // количество элементов (a, b, c)
            int k = 2; // длина сочетаний
            combinationsWithRepetition(n, k, 0, new int[k], writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
