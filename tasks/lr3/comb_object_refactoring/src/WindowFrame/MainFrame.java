package WindowFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class MainFrame extends JFrame {
    private final JTextField textFieldCombinationsN;
    private final JTextField textFieldCombinationsK;
    private final JTextArea textAreaResults;
    public CombinationPressed OnCombinationButtonPressed;

    public interface CombinationPressed {
        void onCombination(int n, int k);
    }

    public MainFrame() {
        setTitle("Main Window");
        setSize(400, 300); // Размер окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel labelN = new JLabel("Enter N:");
        textFieldCombinationsN = new JTextField(5);
        JLabel labelK = new JLabel("Enter K:");
        textFieldCombinationsK = new JTextField(5);

        JButton buttonGenerate = new JButton("Generate Combinations");
        buttonGenerate.setPreferredSize(new Dimension(200, 30));
        buttonGenerate.addActionListener(this::generateButtonAction);

        textAreaResults = new JTextArea(10, 30);
        textAreaResults.setMargin(new Insets(5, 5, 5, 5));
        textAreaResults.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaResults);

        getContentPane().add(labelN);
        getContentPane().add(textFieldCombinationsN);
        getContentPane().add(labelK);
        getContentPane().add(textFieldCombinationsK);
        getContentPane().add(buttonGenerate);
        getContentPane().add(scrollPane);
    }

    private void generateButtonAction(ActionEvent e) {
        String nValue = textFieldCombinationsN.getText();
        String kValue = textFieldCombinationsK.getText();
        try {
            int n = Integer.parseInt(nValue);
            int k = Integer.parseInt(kValue);
            if (OnCombinationButtonPressed != null) {
                OnCombinationButtonPressed.onCombination(n, k);
                // Отобразить содержимое файла в текстовом поле после генерации
                displayFileContent("combinations_with_repetition.txt");
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Please enter valid integers in both fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Метод для чтения и отображения содержимого файла в текстовой области
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
