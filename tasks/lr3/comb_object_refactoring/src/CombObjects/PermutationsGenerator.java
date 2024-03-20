package CombObjects;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class PermutationsGenerator {

    public abstract void generateAndWritePermutations(FileWriter writer) throws IOException;

    public static class RecursivePermutations extends PermutationsGenerator {
        private List<Character> chars;

        public RecursivePermutations(List<Character> chars) {
            this.chars = chars;
        }

        @Override
        public void generateAndWritePermutations(FileWriter writer) throws IOException {
            permute(new ArrayList<>(), new ArrayList<>(chars), writer);
        }

        private void permute(List<Character> output, List<Character> input, FileWriter writer) throws IOException {
            if (input.isEmpty()) {
                for (char c : output) {
                    writer.write(c);
                }
                writer.write('\n');
                return;
            }
            for (int i = 0; i < input.size(); i++) {
                List<Character> newInput = new ArrayList<>(input);
                List<Character> newOutput = new ArrayList<>(output);
                newOutput.add(newInput.remove(i));
                permute(newOutput, newInput, writer);
            }
        }
    }

    public static class NonRecursivePermutations extends PermutationsGenerator {
        private char[] chars;

        public NonRecursivePermutations(char[] chars) {
            this.chars = chars;
        }

        @Override
        public void generateAndWritePermutations(FileWriter writer) throws IOException {
            Arrays.sort(chars); // Начать с сортированной последовательности
            do {
                writer.write(chars);
                writer.write('\n');
            } while (nextPermutation(chars));
        }

        private void swap(char[] arr, int i, int j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        private void reverse(char[] arr, int i, int j) {
            while (i < j) {
                swap(arr, i++, j--);
            }
        }

        private boolean nextPermutation(char[] arr) {
            int i = arr.length - 2;
            while (i >= 0 && arr[i] >= arr[i + 1]) {
                i--;
            }
            if (i < 0) {
                return false;
            }
            int j = arr.length - 1;
            while (arr[j] <= arr[i]) {
                j--;
            }
            swap(arr, i, j);
            reverse(arr, i + 1, arr.length - 1);
            return true;
        }
    }
}

