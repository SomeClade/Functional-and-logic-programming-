package CombObjects;
import java.io.FileWriter;
import java.io.IOException;

public abstract class CombinatorialGenerator {
    protected int n;
    protected int k;

    public CombinatorialGenerator(int n, int k) {
        this.n = n;
        this.k = k;
    }

    public abstract void generate(FileWriter writer) throws IOException;

    public static class CombinationsWithRepetition extends CombinatorialGenerator {
        public CombinationsWithRepetition(int n, int k) {
            super(n, k);
        }

        @Override
        public void generate(FileWriter writer) throws IOException {
            int[] combination = new int[k];
            generateInitialCombination(combination, k);
            combinationsWithRepetition(0, combination, writer);
        }

        private void generateInitialCombination(int[] combination, int k) {
            for (int i = 0; i < k; i++) {
                combination[i] = 0;
            }
        }

        private void combinationsWithRepetition(int index, int[] combination, FileWriter writer) throws IOException {
            if (index == k) {
                for (int value : combination) {
                    writer.write((char) ('a' + value));
                }
                writer.write('\n');
                return;
            }
            for (int i = 0; i < n; i++) {
                combination[index] = i;
                combinationsWithRepetition(index + 1, combination, writer);
            }
        }
    }

    public static class NonRecursiveCombinations extends CombinatorialGenerator {
        public NonRecursiveCombinations(int n, int k) {
            super(n, k);
        }

        @Override
        public void generate(FileWriter writer) throws IOException {
            int[] combination = new int[k];
            for (int i = 0; i < k; i++) {
                combination[i] = i;
            }

            while (true) {
                for (int value : combination) {
                    writer.write((char) ('a' + value));
                }
                writer.write("\n");

                int idx = k - 1;
                while (idx >= 0 && combination[idx] == n - k + idx) {
                    idx--;
                }

                if (idx < 0) break;

                combination[idx]++;
                for (int i = idx + 1; i < k; i++) {
                    combination[i] = combination[i - 1] + 1;
                }
            }
        }
    }
}
