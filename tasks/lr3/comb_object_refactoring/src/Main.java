import CombObjects.*;
import WindowFrame.MainFrame;

import CombObjects.*;
import WindowFrame.MainFrame;

import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setSize(500, 400); // Adjust size as needed
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.OnCombinationButtonPressed = (n, k) -> {
                String filename = "combinations_with_repetition.txt";
                try (FileWriter writer = new FileWriter(filename)) {
                    CombinatorialGenerator generator = new CombinatorialGenerator.CombinationsWithRepetition(n, k);
                    generator.generate(writer);
                    displayFileContent(frame.getTextArea(), filename);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            };

            frame.setVisible(true);
        });

        generateNonInteractiveCombinations();
    }

    private static void displayFileContent(JTextArea textArea, String filename) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            textArea.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateNonInteractiveCombinations() {
        // Generate non-interactive combinations and placements
        List<Integer> nums = Arrays.asList(1, 2, 3);

        try (FileWriter writer = new FileWriter("non_recursive_combinations.txt")) {
            CombinatorialGenerator generator = new CombinatorialGenerator.NonRecursiveCombinations(3, 2);
            generator.generate(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("placementsWithoutRepetition.txt")) {
            PlacementGenerator generator = new PlacementGenerator.RecursivePlacementsWithoutRepetition(3, 2);
            generator.generate(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("NonRecursiveplacementswithoutrepetition.txt")) {
            PlacementGenerator generator = new PlacementGenerator.NonRecursivePlacementsWithoutRepetition(5, 3);
            generator.generate(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<List<Integer>> recursiveSubsets = new SubsetGenerator.RecursiveSubsets(nums).generateSubsets();
            SubsetGenerator.writeToFile(recursiveSubsets, "RecursiveSubsets.txt");

            List<List<Integer>> nonRecursiveSubsets = new SubsetGenerator.NonRecursiveSubsets(nums).generateSubsets();
            SubsetGenerator.writeToFile(nonRecursiveSubsets, "NonRecursiveSubsets.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
