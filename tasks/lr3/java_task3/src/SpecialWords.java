import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SpecialWords {

    public static void generateWords(String currentWord, Set<Character> usedChars, int doubleLetterCount, int tripleLetterCount, BufferedWriter writer) throws IOException {
// Проверяем, достигли ли мы слова длиной 7 букв
        if (currentWord.length() == 7) {
            if (doubleLetterCount == 1 && tripleLetterCount == 1) {
                writer.write(currentWord);
                writer.newLine();
            }
            return;
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            int newDoubleLetterCount = doubleLetterCount;
            int newTripleLetterCount = tripleLetterCount;
            int count = countLetter(currentWord, ch);

            if (count == 2) {
                continue;
            } else if (count == 1) {
                if (doubleLetterCount == 0) {
                    newDoubleLetterCount = 1;
                } else {
                    continue;
                }
            } else if (count == 0) {
                if (usedChars.contains(ch)) {
                    continue;
                }
            } else if (count == 3) {
                continue;
            } else if (count == 2 && tripleLetterCount == 0) {
                newTripleLetterCount = 1;
            }

            Set<Character> newUsedChars = new HashSet<>(usedChars);
            if (count == 0) {
                newUsedChars.add(ch);
            }
            generateWords(currentWord + ch, newUsedChars, newDoubleLetterCount, newTripleLetterCount, writer);
        }
    }

    private static int countLetter(String word, char letter) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("RecursiveSpecialWords.txt"))) {
            generateWords("", new HashSet<>(), 0, 0, writer);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}