package WindowFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class MainFrame extends JFrame {
    private final JTextField textFieldN;
    private final JTextField textFieldK;
    private final JTextArea textAreaResults;


    public interface CombinationPressed {
        void onCombination(int n, int k, String method);
    }


    public CombinationPressed onCombinationButtonPressed;

    public MainFrame() {
        setTitle("Combinatorial Generator");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel labelN = new JLabel("Enter N:");
        textFieldN = new JTextField(5);
        JLabel labelK = new JLabel("Enter K:");
        textFieldK = new JTextField(5);

        inputPanel.add(labelN);
        inputPanel.add(textFieldN);
        inputPanel.add(labelK);
        inputPanel.add(textFieldK);

        JButton btnGenerateCombinationsWithRepetition = new JButton("Combinations With Repetition");
        btnGenerateCombinationsWithRepetition.addActionListener(e -> generateButtonAction("withRepetition"));

        JButton btnGenerateNonRecursiveCombinations = new JButton("Non-Recursive Combinations");
        btnGenerateNonRecursiveCombinations.addActionListener(e -> generateButtonAction("nonRecursive"));

        JButton btnGenerateRecursivePlacements = new JButton("Recursive Placements Without Repetition");
        btnGenerateRecursivePlacements.addActionListener(e -> generateButtonAction("recursivePlacements"));

        JButton btnGenerateNonRecursivePlacements = new JButton("Non-Recursive Placements Without Repetition");
        btnGenerateNonRecursivePlacements.addActionListener(e -> generateButtonAction("nonRecursivePlacements"));

        JButton btnGenerateRecursiveSubsets = new JButton("Generate Recursive Subsets");
        btnGenerateRecursiveSubsets.addActionListener(e -> generateButtonAction("recursiveSubsets"));

        JButton btnGenerateNonRecursiveSubsets = new JButton("Generate Non-Recursive Subsets");
        btnGenerateNonRecursiveSubsets.addActionListener(e -> generateButtonAction("nonRecursiveSubsets"));

        JButton btnGenerateNonRecursiveSpecialWords = new JButton("Generate Non-Recursive Special Words");
        btnGenerateNonRecursiveSpecialWords.addActionListener(e -> generateButtonAction("NonRecursiveSpecialWords"));

        JButton btnGenerateRecursiveSpecialWords = new JButton("Generate Recursive Special Words");
        btnGenerateRecursiveSpecialWords.addActionListener(e -> generateButtonAction("RecursiveSpecialWords"));




        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btnGenerateCombinationsWithRepetition);
        buttonPanel.add(btnGenerateNonRecursiveCombinations);
        buttonPanel.add(btnGenerateRecursivePlacements);
        buttonPanel.add(btnGenerateNonRecursivePlacements);
        buttonPanel.add(btnGenerateRecursiveSubsets);
        buttonPanel.add(btnGenerateNonRecursiveSubsets);
        buttonPanel.add(btnGenerateNonRecursiveSpecialWords);
        buttonPanel.add(btnGenerateRecursiveSpecialWords);
        add(buttonPanel);


        textAreaResults = new JTextArea(10, 50);
        textAreaResults.setMargin(new Insets(5, 5, 5, 5));
        textAreaResults.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaResults);

        add(inputPanel);
        add(buttonPanel);
        add(scrollPane);
    }

    private void generateButtonAction(String method) {
        int n, k;
        try {
            n = Integer.parseInt(textFieldN.getText());
            k = Integer.parseInt(textFieldK.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid integers for N and K.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (onCombinationButtonPressed != null) {
            onCombinationButtonPressed.onCombination(n, k, method);
        }


        String filename;
        switch (method) {
            case "withRepetition":
                filename = "combinations_with_repetition.txt";
                break;
            case "nonRecursive":
                filename = "non_recursive_combinations.txt";
                break;
            case "recursivePlacements":
                filename = "recursive_placements_without_repetition.txt";
                break;
            case "nonRecursivePlacements":
                filename = "non_recursive_placements_without_repetition.txt";
                break;
            case "recursiveSubsets":
                filename = "recursive_subsets.txt";
                break;
            case "nonRecursiveSubsets":
                filename = "non_recursive_subsets.txt";
                break;
            case "NonRecursiveSpecialWords":
                filename = "non_recursive_special_words.txt";
                break;
            case "RecursiveSpecialWords":
                filename = "recursive_special_words.txt";
                break;
            default:
                filename = "output.txt";
                break;
        }
        displayFileContent(filename);
    }


    private void displayFileContent(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile(); // Создает файл, если он не существует
            }
            String content = new String(Files.readAllBytes(file.toPath()));
            textAreaResults.setText(content);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JTextArea getTextArea() {
        return textAreaResults;
    }
}
