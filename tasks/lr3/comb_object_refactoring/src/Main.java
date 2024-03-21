import CombObjects.*;
import WindowFrame.MainFrame;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.onCombinationButtonPressed = (n, k, method) -> {
                String filename = getFilenameByMethod(method);
                try (FileWriter writer = new FileWriter(filename)) {
                    if (method.equals("withRepetition") || method.equals("nonRecursive")) {
                        generateCombination(writer, n, k, method);
                    } else {
                        generatePlacement(writer, n, k, method);
                    }
                    displayFileContent(frame.getTextArea(), filename);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            };
            frame.setVisible(true);
        });
    }

    private static String getFilenameByMethod(String method) {
        switch (method) {
            case "withRepetition":
                return "combinations_with_repetition.txt";
            case "nonRecursive":
                return "non_recursive_combinations.txt";
            case "recursivePlacements":
                return "recursive_placements_without_repetition.txt";
            case "nonRecursivePlacements":
                return "non_recursive_placements_without_repetition.txt";
            default:
                return "output.txt";
        }
    }

    private static void generateCombination(FileWriter writer, int n, int k, String method) throws IOException {
        CombinatorialGenerator generator;
        if (method.equals("withRepetition")) {
            generator = new CombinatorialGenerator.CombinationsWithRepetition(n, k);
        } else { // nonRecursive
            generator = new CombinatorialGenerator.NonRecursiveCombinations(n, k);
        }
        generator.generate(writer);
    }

    private static void generatePlacement(FileWriter writer, int n, int k, String method) throws IOException {
        PlacementGenerator placementGenerator;
        if (method.equals("recursivePlacements")) {
            placementGenerator = new PlacementGenerator.RecursivePlacementsWithoutRepetition(n, k);
        } else {
            placementGenerator = new PlacementGenerator.NonRecursivePlacementsWithoutRepetition(n, k);
        }
        placementGenerator.generate(writer);
    }

    private static void displayFileContent(JTextArea textArea, String filename) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            textArea.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
