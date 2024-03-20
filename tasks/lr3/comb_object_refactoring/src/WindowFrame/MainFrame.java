package WindowFrame;

import CombObjects.CombinatorialGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class MainFrame extends JFrame {
    public interface CombinationPressed {
        public void onCombination(int n, int k);
    }

    private final JTextField textFieldCombinationsN;
    private final JTextField textFieldCombinationsK;
    public CombinationPressed OnCombinationButtonPressed;

    public MainFrame() {
        setTitle("Main Window");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textFieldCombinationsN = new JTextField(5);
        getContentPane().add(textFieldCombinationsN);

        textFieldCombinationsK = new JTextField(5);
        getContentPane().add(textFieldCombinationsK);


        JButton newButton = new JButton("Combination");
        newButton.setPreferredSize(new Dimension(100, 30));
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String nValue = textFieldCombinationsN.getText();
                String kValue = textFieldCombinationsK.getText();

                try {
                    int n = Integer.parseInt(nValue);
                    int k = Integer.parseInt(kValue);

                    try (FileWriter writer = new FileWriter("combinations_with_repetition.txt")) {
                        CombinatorialGenerator generator = new CombinatorialGenerator.CombinationsWithRepetition(n, k);
                        generator.generate(writer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    OnCombinationButtonPressed.onCombination(n, k);

                    JOptionPane.showMessageDialog(null, "Button clicked! Generator created with N=" + n + " and K=" + k);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter valid integers in both fields.");
                }
            }
        });

        getContentPane().add(newButton);

        setLayout(new FlowLayout());

    }

    public String getEnteredText() {
        return textFieldCombinationsN.getText();
    }
}