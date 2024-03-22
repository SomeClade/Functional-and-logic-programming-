package CombObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;



public abstract class AbstractWordGenerator {
    protected int length;

    public AbstractWordGenerator(int length) {
        this.length = length;
    }

    public abstract void generateWords(FileWriter writer) throws IOException;


    public static class RecursiveSpecialWordsGenerator extends AbstractWordGenerator {
        public RecursiveSpecialWordsGenerator() {
            super(7); // Здесь мы подтверждаем, что класс будет генерировать слова длиной 7.
        }

        @Override
        public void generateWords(FileWriter writer) throws IOException {

            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                // Начинаем с aaaaaaa и заканчиваем zzzzzzz
                char[] currentWord = new char[this.length];
                for (int i = 0; i < this.length; i++) {
                    currentWord[i] = 'a';
                }

                // Последовательно изменяем каждую букву в слове от 'a' до 'z'

                while (currentWord[0] <= 'z') { // Условие продолжения - пока первая буква не достигла 'z'
                    // Проверяем и записываем текущее слово
                    System.out.println("Текущее слово: " + currentWord);
                    String word = new String(currentWord);
                    writer.write(word);
                    bufferedWriter.newLine();

                    // Переходим к следующему слову
                    for (int i = this.length - 1; i >= 0; i--) {
                        if (currentWord[i] < 'z') {
                            currentWord[i]++;
                            break; // Прерываем цикл, если удалось увеличить букву
                        } else {
                            if (i != 0) {
                                currentWord[i] = 'a'; // Сбрасываем букву на 'a' и продолжаем для следующей позиции
                            } else {
                                // Случай, когда все буквы стали 'z' и мы на первой букве
                                currentWord[i] = '{'; // Устанавливаем символ за 'z', чтобы завершить внешний цикл
                            }
                        }
                    }
                }
            }
        }
    }

        public static class NonRecursiveSpecialWordsGenerator extends AbstractWordGenerator {
            public NonRecursiveSpecialWordsGenerator() {
                super(7);
            }

            @Override
            public void generateWords(FileWriter writer) throws IOException {
                List<String> patterns = generatePatterns();

                for (char doubleChar = 'a'; doubleChar <= 'z'; doubleChar++) {
                    for (char tripleChar = 'a'; tripleChar <= 'z'; tripleChar++) {
                        if (doubleChar != tripleChar) {
                            for (String pattern : patterns) {
                                boolean[] usedChars = new boolean[26]; // Отслеживаем использованные буквы
                                fillAndWriteWords(new char[7], 0, doubleChar, tripleChar, writer, usedChars, pattern);
                            }
                        }
                    }
                }
            }

            private void fillAndWriteWords(char[] currentWord, int index, char doubleChar, char tripleChar, FileWriter writer, boolean[] usedChars, String pattern) throws IOException {
                if (index >= currentWord.length) {
                    writer.write(new String(currentWord) + "\n");
                    return;
                }

                if (index < pattern.length()) {
                    char charToAdd = pattern.charAt(index) == 'D' ? doubleChar : tripleChar;
                    currentWord[index] = charToAdd;
                    fillAndWriteWords(currentWord, index + 1, doubleChar, tripleChar, writer, usedChars, pattern);
                } else {
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch != doubleChar && ch != tripleChar && !usedChars[ch - 'a']) {
                            currentWord[index] = ch;
                            usedChars[ch - 'a'] = true;
                            fillAndWriteWords(currentWord, index + 1, doubleChar, tripleChar, writer, usedChars, pattern);
                            usedChars[ch - 'a'] = false;
                        }
                    }
                }
            }

            private static List<String> generatePatterns() {
                List<String> patterns = new ArrayList<>();
                String basePattern = "DDTTT";
                do {
                    patterns.add(basePattern);
                    basePattern = nextPermutation(basePattern);
                } while (basePattern != null);
                return patterns;
            }

            private static String nextPermutation(String str) {
                char[] array = str.toCharArray();
                int i = array.length - 2;
                while (i >= 0 && array[i] >= array[i + 1]) {
                    i--;
                }
                if (i >= 0) {
                    int j = array.length - 1;
                    while (array[j] <= array[i]) {
                        j--;
                    }
                    swap(array, i, j);
                    reverse(array, i + 1, array.length - 1);
                    return new String(array);
                }
                return null;
            }

            private static void swap(char[] array, int i, int j) {
                char temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

            private static void reverse(char[] array, int start, int end) {
                while (start < end) {
                    swap(array, start++, end--);
                }
            }
        }
    }
