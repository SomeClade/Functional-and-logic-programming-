package CombObjects;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class SubsetGenerator {
    protected List<Integer> nums;

    public SubsetGenerator(List<Integer> nums) {
        this.nums = nums;
    }

    public abstract List<List<Integer>> generateSubsets();

    public static void writeToFile(List<List<Integer>> subsets, String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (List<Integer> subset : subsets) {
                writer.write(subset.toString() + "\n");
            }
        }
    }

    public static class RecursiveSubsets extends SubsetGenerator {

        public RecursiveSubsets(List<Integer> nums) {
            super(nums);
        }

        @Override
        public List<List<Integer>> generateSubsets() {
            List<List<Integer>> result = new ArrayList<>();
            generateSubsetsHelper(0, new ArrayList<>(), result);
            return result;
        }

        private void generateSubsetsHelper(int index, List<Integer> current, List<List<Integer>> result) {
            result.add(new ArrayList<>(current));

            for (int i = index; i < nums.size(); i++) {
                current.add(nums.get(i));
                generateSubsetsHelper(i + 1, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    public static class NonRecursiveSubsets extends SubsetGenerator {

        public NonRecursiveSubsets(List<Integer> nums) {
            super(nums);
        }

        @Override
        public List<List<Integer>> generateSubsets() {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>()); // Start with the empty set

            for (int num : nums) {
                int n = result.size();
                for (int i = 0; i < n; i++) {
                    List<Integer> newSubset = new ArrayList<>(result.get(i));
                    newSubset.add(num);
                    result.add(newSubset);
                }
            }

            return result;
        }
    }
}
