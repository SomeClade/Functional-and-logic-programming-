import java.io.FileWriter;
import java.io.IOException;

public class NonRecursiveTwoAsWords {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("non_recursive_two_as_words.txt")) {
            int length = 5;
            char[] chars = new char[length];
            // Генерация всех комбинаций
            for (int i = 0; i < length; i++) {
                for (int j = i + 1; j < length; j++) {
                    // Ставим 'a' на позиции i и j
                    for (int k = 0; k < length; k++) chars[k] = (k == i || k == j) ? 'a' : '0';
                    // Перебираем все возможные буквы для остальных позиций
                    for (char c1 = 'b'; c1 <= 'z'; c1++) {
                        if (i != 0) chars[0] = c1;
                        for (char c2 = 'b'; c2 <= 'z'; c2++) {
                            if (c2 != c1 && j != 1 && i != 1) chars[1] = c2;
                            for (char c3 = 'b'; c3 <= 'z'; c3++) {
                                if (c3 != c1 && c3 != c2 && j != 2 && i != 2) chars[2] = c3;
                                for (char c4 = 'b'; c4 <= 'z'; c4++) {
                                    if (c4 != c1 && c4 != c2 && c4 != c3 && j != 3 && i != 3) chars[3] = c4;
                                    for (char c5 = 'b'; c5 <= 'z'; c5++) {
                                        if (c5 != c1 && c5 != c2 && c5 != c3 && c5 != c4 && j != 4 && i != 4) {
                                            chars[4] = c5;

                                            // Проверяем, что слово содержит две 'a'
                                            int countA = 0;
                                            for (char ch : chars) if (ch == 'a') countA++;
                                            if (countA == 2) writer.write(new String(chars) + "\n");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
