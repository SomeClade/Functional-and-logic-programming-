import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NonRecursiveSpecialWords {

    public static void main(String[] args) {
        String fileName = "noRecursiveSpecialWords.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            List<String> patterns = generatePatterns();

            for (char doubleChar = 'a'; doubleChar <= 'z'; doubleChar++) {
                for (char tripleChar = 'a'; tripleChar <= 'z'; tripleChar++) {
                    if (doubleChar != tripleChar) {
                        for (String pattern : patterns) {
                            generateWordsRecursively(pattern, 0, new char[7], doubleChar, tripleChar, writer, new boolean[26]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Рекурсивная функция для генерации слов
    private static void generateWordsRecursively(String pattern, int patternIndex, char[] currentWord, char doubleChar, char tripleChar, FileWriter writer, boolean[] usedChars) throws IOException {
// Базовый случай: если шаблон полностью обработан
        if (patternIndex == pattern.length()) {
            fillAndWriteWords(currentWord, 0, doubleChar, tripleChar, writer, usedChars); // Заполняем оставшиеся буквы
            return;
        }

// Рекурсивный случай: выбираем букву в зависимости от шаблона
        char charToAdd = pattern.charAt(patternIndex) == 'D' ? doubleChar : tripleChar;
        currentWord[patternIndex] = charToAdd;
        generateWordsRecursively(pattern, patternIndex + 1, currentWord, doubleChar, tripleChar, writer, usedChars);
    }

    // Рекурсивная функция для заполнения оставшихся букв и записи слова в файл
    private static void fillAndWriteWords(char[] currentWord, int index, char doubleChar, char tripleChar, FileWriter writer, boolean[] usedChars) throws IOException {
// Базовый случай: если слово полностью сформировано
        if (index >= currentWord.length) {
            writer.write(new String(currentWord) + "\n");
            return;
        }

// Если текущая позиция уже заполнена (часть шаблона), переходим к следующей
        if (currentWord[index] != '\u0000') {
            fillAndWriteWords(currentWord, index + 1, doubleChar, tripleChar, writer, usedChars);
            return;
        }

// Рекурсивный случай: пробуем все возможные буквы
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (ch != doubleChar && ch != tripleChar && !usedChars[ch - 'a']) {
                currentWord[index] = ch;
                usedChars[ch - 'a'] = true;
                fillAndWriteWords(currentWord, index + 1, doubleChar, tripleChar, writer, usedChars);
                usedChars[ch - 'a'] = false; // Сброс состояния для следующих вызовов
            }
        }
        currentWord[index] = '\u0000'; // Сброс текущей позиции для других перестановок
    }
    private static List<String> generatePatterns() {
        List<String> patterns = new ArrayList<>();
// Генерация шаблонов с использованием двух символов: D для двойной буквы, T для тройной
        String basePattern = "DDTTT";
        do {
            patterns.add(basePattern);
            basePattern = nextPermutation(basePattern);
        } while (basePattern != null);
        return patterns;
    }

    private static void generateWords(String pattern, char doubleChar, char tripleChar, FileWriter writer) throws IOException {
// Создаем массив для построения слова
        char[] chars = new char[7];
        int index = 0;
// Заполняем шаблон
        for (char p : pattern.toCharArray()) {
            if (p == 'D') {
                chars[index++] = doubleChar;
            } else {
                chars[index++] = tripleChar;
            }
        }
// Заполняем оставшиеся позиции уникальными буквами
        for (char c = 'a'; c <= 'z'; c++) {
            if (c != doubleChar && c != tripleChar) {
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == '\u0000') { // Проверяем, свободна ли позиция
                        chars[i] = c;
                        if (i < chars.length - 1) continue;
// Если все позиции заполнены, пишем слово
                        writer.write(new String(chars) + "\n");
                        chars[i] = '\u0000'; // Очищаем для следующего цикла
                        break; // Продолжаем с следующей буквы
                    }
                }
            }
        }
    }

    private static String nextPermutation(String str) {
// Функция для генерации следующей перестановки строки
        char[] array = str.toCharArray();
        int n = array.length;
        int i = n - 2;
// Найдем первый элемент, который меньше следующего элемента с конца строки
        while (i >= 0 && array[i] >= array[i + 1]) {
            i--;
        }

        if (i >= 0) {
// Найдем элемент, который больше найденного элемента
            int j = n - 1;
            while (array[j] <= array[i]) {
                j--;
            }
// Поменяем их местами
            swap(array, i, j);
// Перевернем последовательность после замененного элемента
            reverse(array, i + 1, n - 1);
            return new String(array);
        } else {
            return null; // Больше перестановок нет
        }
    }

    private static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void reverse(char[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }
}
