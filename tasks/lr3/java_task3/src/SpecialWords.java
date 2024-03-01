import java.io.FileWriter;
import java.io.IOException;

public class SpecialWords {
    public static void generateSpecialWords(String current, int doubleLetter, int tripleLetter, FileWriter writer) throws IOException {
        if (current.length() == 7) {
            writer.write(current + "\n");
            return;
        }
        for (char c = 'a'; c <= 'z'; c++) {
            int count = countLetter(current, c);
            if ((count == 2 && doubleLetter < 1) || (count == 3 && tripleLetter < 1) || count == 0) {
                generateSpecialWords(current + c, doubleLetter + (count == 2 ? 1 : 0), tripleLetter + (count == 3 ? 1 : 0), writer);
            }
        }
    }

    private static int countLetter(String str, char letter) {
        return (int) str.chars().filter(ch -> ch == letter).count();
    }

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("special_words.txt")) {
            generateSpecialWords("", 0, 0, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
