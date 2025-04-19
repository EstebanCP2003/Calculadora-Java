

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class CalculadoraGUI {

    private JFrame frame;
    private JTextField textField;
    private StringBuilder currentText = new StringBuilder();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                CalculadoraGUI window = new CalculadoraGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CalculadoraGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Calculadora");
        frame.setBounds(100, 100, 300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.getContentPane().add(textField, BorderLayout.NORTH);
        textField.setColumns(10);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        String[] botones = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String texto : botones) {
            JButton button = new JButton(texto);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("=")) {
                try {
                    String result = evaluarExpresion(currentText.toString());
                    textField.setText(result);
                    currentText.setLength(0);
                    currentText.append(result);
                } catch (Exception ex) {
                    textField.setText("Error");
                    currentText.setLength(0);
                }
            } else if (command.equals("C")) {
                currentText.setLength(0);
                textField.setText("");
            } else {
                currentText.append(command);
                textField.setText(currentText.toString());
            }
        }
    }

    public String evaluarExpresion(String expr) {
        try {
            double result = eval(expr);
            return String.valueOf(result);
        } catch (Exception e) {
            return "Error";
        }
    }

    // Evaluador simple de expresiones matemáticas con soporte para + - * /
    private double eval(String expression) {
        char[] tokens = expression.toCharArray();
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int i = 0;

        while (i < tokens.length) {
            if (Character.isWhitespace(tokens[i])) {
                i++;
                continue;
            }

            if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.') {
                StringBuilder sbuf = new StringBuilder();
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.'))
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.empty() && tienePrecedencia(tokens[i], ops.peek()))
                    values.push(aplicarOperacion(ops.pop(), values.pop(), values.pop()));
                ops.push(tokens[i]);
                i++;
            } else {
                throw new IllegalArgumentException("Carácter inválido: " + tokens[i]);
            }
        }

        while (!ops.empty())
            values.push(aplicarOperacion(ops.pop(), values.pop(), values.pop()));

        return values.pop();
    }

    private boolean tienePrecedencia(char op1, char op2) {
        return (op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-');
    }

    private double aplicarOperacion(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': 
                if (b == 0) throw new ArithmeticException("División por cero");
                return a / b;
        }
        return 0;
    }
}
