package CombObjects;

import java.io.FileWriter;
import java.io.IOException;

public abstract class PlacementGenerator {
    protected int n;
    protected int k;

    public PlacementGenerator(int n, int k) {
        this.n = n;
        this.k = k;
    }

    public abstract void generate(FileWriter writer) throws IOException;

    public static class NonRecursivePlacementsWithoutRepetition extends PlacementGenerator {

        public NonRecursivePlacementsWithoutRepetition(int n, int k) {
            super(n, k);
        }

        @Override
        public void generate(FileWriter writer) throws IOException {
            int[] elements = new int[k];
            for (int i = 0; i < k; i++) elements[i] = i + 1;

            while (true) {
                for (int i = 0; i < k; i++) {
                    writer.write((char)('a' + elements[i] - 1) + (i < k - 1 ? "," : "\n"));
                }

                int i;
                for (i = k - 1; i >= 0 && elements[i] == n - k + i + 1; i--);
                if (i < 0) break;

                elements[i]++;
                for (int j = i + 1; j < k; j++) elements[j] = elements[j - 1] + 1;
            }
        }
    }

    public static class RecursivePlacementsWithoutRepetition extends PlacementGenerator {

        public RecursivePlacementsWithoutRepetition(int n, int k) {
            super(n, k);
        }

        private void placementsWithoutRepetition(String current, boolean[] used, FileWriter writer) throws IOException {
            if (current.length() == k) {
                writer.write(current + "\n");
                return;
            }
            for (int i = 0; i < n; i++) {
                if (!used[i]) {
                    used[i] = true;
                    placementsWithoutRepetition(current + (char)('a' + i), used, writer);
                    used[i] = false;
                }
            }
        }

        @Override
        public void generate(FileWriter writer) throws IOException {
            placementsWithoutRepetition("", new boolean[n], writer);
        }
    }
}

