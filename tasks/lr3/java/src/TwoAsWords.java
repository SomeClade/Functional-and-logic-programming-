import java.io.FileWriter;
import java.io.IOException;

public class TwoAsWords {
    // Рекурсивная функция для создания слов
    public static void generateWords(int n, String current, int countA, FileWriter writer) throws IOException {
        if (current.length() == n) {
            if (countA == 2) {
                writer.write(current);
                writer.write('\n');
            }
            return;
        }
        if (countA < 2) {
            generateWords(n, current + 'a', countA + 1, writer);
        }
        for (char c = 'b'; c <= 'z'; c++) {
            if (current.indexOf(c) == -1) { // Ensure no repetition
                generateWords(n, current + c, countA, writer);
            }
        }
    }

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("two_as_words.txt")) {
            generateWords(5, "", 0, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
