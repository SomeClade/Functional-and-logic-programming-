import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class placementsWithoutRepetition {

    // Рекурсивный метод для создания размещений
    public static void placementsWithoutRepetition(int n, int k, String current, boolean[] used, FileWriter writer) throws IOException {
        if (current.length() == k) {
            writer.write(current + "\n");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                used[i] = true;
                placementsWithoutRepetition(n, k, current + (i + 1), used, writer); // Fixed: append the number as a string
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        String fileName = "placementsWithoutRepetition.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            // Генерация размещений
            placementsWithoutRepetition(3, 2, "", new boolean[3], writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
