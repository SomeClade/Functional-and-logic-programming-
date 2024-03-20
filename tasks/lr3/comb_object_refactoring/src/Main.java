import CombObjects.*;
import WindowFrame.MainFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });

        try (FileWriter writer = new FileWriter("combinations_with_repetition.txt")) {
            CombinatorialGenerator generator = new CombinatorialGenerator.CombinationsWithRepetition(3, 2);
            generator.generate(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("non_recursive_combinations.txt")) {
            CombinatorialGenerator generator = new CombinatorialGenerator.NonRecursiveCombinations(3, 2);
            generator.generate(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileWriter writer = new FileWriter("placementsWithoutRepetition.txt")) {
            PlacementGenerator generator = new PlacementGenerator.RecursivePlacementsWithoutRepetition(3, 2);
            generator.generate(writer);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        try (
                FileWriter writer = new FileWriter("NonRecursiveplacementswithoutrepetition.txt")) {
            PlacementGenerator generator = new PlacementGenerator.NonRecursivePlacementsWithoutRepetition(5, 3);
            generator.generate(writer);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        List<Integer> nums = Arrays.asList(1, 2, 3);

        SubsetGenerator recursiveGenerator = new SubsetGenerator.RecursiveSubsets(nums);
        List<List<Integer>> recursiveSubsets = recursiveGenerator.generateSubsets();
        try {
            SubsetGenerator.writeToFile(recursiveSubsets, "RecursiveSubsets.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        SubsetGenerator nonRecursiveGenerator = new SubsetGenerator.NonRecursiveSubsets(nums);
        List<List<Integer>> nonRecursiveSubsets = nonRecursiveGenerator.generateSubsets();
        try {
            SubsetGenerator.writeToFile(nonRecursiveSubsets, "NonRecursiveSubsets.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
