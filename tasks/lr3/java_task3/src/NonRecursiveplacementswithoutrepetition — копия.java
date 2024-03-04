import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class NonRecursiveplacementswithoutrepetition {

    // Нерекурсивный метод для создания размещений
    public static void generatePermutations(int n, int k, FileWriter writer) throws IOException {
        int[] elements = new int[k];
        for (int i = 0; i < k; i++) elements[i] = i + 1;

        while (true) {

            for (int i = 0; i < k; i++) writer.write(elements[i] + (i < k - 1 ? "," : "\n"));


            int i;
            for (i = k - 1; i >= 0 && elements[i] == n - k + i + 1; i--);
            if (i < 0) break;

            elements[i]++;
            for (int j = i + 1; j < k; j++) elements[j] = elements[j - 1] + 1;
        }
    }

    public static void main(String[] args) {
        String fileName = "NonRecursiveplacementswithoutrepetition.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            generatePermutations(5, 3, writer); // Генерация размещений из 5 по 3
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
