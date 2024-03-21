package WindowFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
        setSize(600, 400);
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btnGenerateCombinationsWithRepetition);
        buttonPanel.add(btnGenerateNonRecursiveCombinations);
        buttonPanel.add(btnGenerateRecursivePlacements);
        buttonPanel.add(btnGenerateNonRecursivePlacements);

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

        String filename = method.equals("withRepetition") ? "combinations_with_repetition.txt" :
                method.equals("nonRecursive") ? "non_recursive_combinations.txt" :
                        method.equals("recursivePlacements") ? "recursive_placements_without_repetition.txt" :
                                "non_recursive_placements_without_repetition.txt";
        displayFileContent(filename);
    }

    private void displayFileContent(String filename) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            textAreaResults.setText(content);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JTextArea getTextArea() {
        return textAreaResults;
    }
}
