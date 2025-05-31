import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;

public class PasswordGenerator extends JFrame implements ActionListener {

    JTextField lengthField;
    JCheckBox upperCheck, lowerCheck, numberCheck, symbolCheck;
    JButton generateButton;
    JTextArea resultArea;

    final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String lower = "abcdefghijklmnopqrstuvwxyz";
    final String numbers = "0123456789";
    final String symbols = "!@#$%^&*()-_=+[]{};:,.<>?/";

    public PasswordGenerator() {
        setTitle("Password Generator");
        setSize(400, 350);
        setLayout(new GridLayout(7, 1, 5, 5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        lengthField = new JTextField("12");
        upperCheck = new JCheckBox("Include Uppercase Letters");
        lowerCheck = new JCheckBox("Include Lowercase Letters");
        numberCheck = new JCheckBox("Include Numbers");
        symbolCheck = new JCheckBox("Include Special Characters");

        generateButton = new JButton("Generate Password");
        generateButton.addActionListener(this);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        add(new JLabel("Password Length:"));
        add(lengthField);
        add(upperCheck);
        add(lowerCheck);
        add(numberCheck);
        add(symbolCheck);
        add(generateButton);
        add(new JScrollPane(resultArea));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String charPool = "";
        if (upperCheck.isSelected()) charPool += upper;
        if (lowerCheck.isSelected()) charPool += lower;
        if (numberCheck.isSelected()) charPool += numbers;
        if (symbolCheck.isSelected()) charPool += symbols;

        int length;
        try {
            length = Integer.parseInt(lengthField.getText());
            if (length < 4 || length > 64) {
                JOptionPane.showMessageDialog(this, "Length must be between 4 and 64.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid number.");
            return;
        }

        if (charPool.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one character type.");
            return;
        }

        String password = generatePassword(charPool, length);
        resultArea.setText(password);
    }

    private String generatePassword(String charPool, int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }
        return password.toString();
    }

    public static void main(String[] args) {
        new PasswordGenerator();
    }
}
