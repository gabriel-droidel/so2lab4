import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private double num1, num2, result;
    private char operation;
    private boolean isResultDisplayed;
    private boolean isZeroDivision;
    private boolean operationSelected;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        // Initialize buttons here
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            // adding numbers to the panel
            buttonPanel.add(numberButtons[i]);
        }

        // make operation buttons
        char[] operations = {'+', '-', '*', '/', '=', 'C', '.'};
        operationButtons = new JButton[7];
        for (int i = 0; i < operations.length; i++) {
            operationButtons[i] = new JButton(String.valueOf(operations[i]));
            operationButtons[i].addActionListener(this);
            operationButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            buttonPanel.add(operationButtons[i]);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        String command = e.getActionCommand();
        // Clear display if error message is shown
        if (display.getText().equals("ERROR!")) {
            display.setText("");
        }

        // Handle number buttons
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            // check if the display needs to be cleared from previous calculation
            if (isResultDisplayed) {
                display.setText("");
                isResultDisplayed = false;
            }
            display.setText(display.getText() + command);
        }
        // Handle decimal
        else if (command.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }
        // Handle operation buttons
        else if (command.equals("+") || command.equals("*") || command.equals("-") || command.equals("/")) {
            if (!operationSelected) {
                num1 = Double.parseDouble(display.getText());
                operation = command.charAt(0);
                display.setText("");
                operationSelected = true;
            }
        }
        // Handle equals button
        else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());

            // Handle operations logic
            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        isZeroDivision = true;
                    } else {
                        result = num1 / num2;
                    }
                    break;
            }
            // Display result
            if (isZeroDivision) {
                display.setText("ERROR!");
            } else {
                display.setText(String.valueOf(result));
            }
            // Reset values for next calculation
            num1 = 0;
            num2 = 0;
            operation = '\0';
            isZeroDivision = false;
            isResultDisplayed = true;
            operationSelected = false;
        }
        // Handle Clear button
        else if (command.equals("C")) {
            num1 = 0;
            num2 = 0;
            result = 0;
            operation = '\0';
            display.setText("");
            isResultDisplayed = false;
            isZeroDivision = false;
            operationSelected = false;
        }
    }
}
