import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorApp().setVisible(true));
    }
}

class CalculatorApp extends JFrame {
    public CalculatorApp() {
        setTitle("Multi-Mode Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        CalculatorPanel calculatorPanel = new CalculatorPanel();
        add(calculatorPanel);
        pack();
        setLocationRelativeTo(null);
    }
}

class CalculatorPanel extends JPanel {
    private JTextArea displayArea;
    private JLabel resultDisplay;
    private JPanel buttonPanel;
    private JPanel modePanel;
    private Calculator calculator;
    private String currentMode = "SIMPLE";
    private JButton modeSimple, modeBusiness, modeScientific;

    public CalculatorPanel() {
        calculator = new Calculator();
        setLayout(new BorderLayout(2, 2));
        setBackground(new Color(20, 20, 20));

        // Mode Selection Panel
        modePanel = createModePanel();
        add(modePanel, BorderLayout.NORTH);

        // Display Panel - Main display with history and result
        JPanel displayPanel = createDisplayPanel();
        add(displayPanel, BorderLayout.CENTER);

        // Button Panel
        buttonPanel = new JPanel();
        updateButtonPanel("SIMPLE");
        add(new JScrollPane(buttonPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.SOUTH);
    }

    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // History/Expression display
        displayArea = new JTextArea(2, 25);
        displayArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(30, 30, 30));
        displayArea.setForeground(new Color(180, 180, 180));
        displayArea.setLineWrap(true);
        displayArea.setText("0");
        displayArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(displayArea, BorderLayout.NORTH);

        // Result display
        resultDisplay = new JLabel("0");
        resultDisplay.setFont(new Font("Segoe UI", Font.BOLD, 32));
        resultDisplay.setForeground(Color.WHITE);
        resultDisplay.setHorizontalAlignment(JLabel.RIGHT);
        resultDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(resultDisplay, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createModePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panel.setBackground(new Color(40, 40, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel modeLabel = new JLabel("Mode:");
        modeLabel.setForeground(Color.WHITE);
        modeLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(modeLabel);

        modeSimple = createModeButton("Standard", "SIMPLE");
        modeBusiness = createModeButton("Business", "BUSINESS");
        modeScientific = createModeButton("Scientific", "SCIENTIFIC");

        panel.add(modeSimple);
        panel.add(modeBusiness);
        panel.add(modeScientific);

        updateModeButtons();
        return panel;
    }

    private JButton createModeButton(String label, String mode) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btn.setFocusPainted(false);
        btn.addActionListener(e -> switchMode(mode));
        return btn;
    }

    private void switchMode(String mode) {
        currentMode = mode;
        resultDisplay.setText("0");
        displayArea.setText("0");
        calculator.reset();
        updateButtonPanel(mode);
        updateModeButtons();
    }

    private void updateModeButtons() {
        Color activeColor = new Color(0, 120, 215);
        Color inactiveColor = new Color(60, 60, 60);

        modeSimple.setBackground(currentMode.equals("SIMPLE") ? activeColor : inactiveColor);
        modeBusiness.setBackground(currentMode.equals("BUSINESS") ? activeColor : inactiveColor);
        modeScientific.setBackground(currentMode.equals("SCIENTIFIC") ? activeColor : inactiveColor);

        modeSimple.setForeground(Color.WHITE);
        modeBusiness.setForeground(Color.WHITE);
        modeScientific.setForeground(Color.WHITE);
        modeSimple.setOpaque(true);
        modeBusiness.setOpaque(true);
        modeScientific.setOpaque(true);
        modeSimple.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        modeBusiness.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        modeScientific.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    private void updateButtonPanel(String mode) {
        buttonPanel.removeAll();
        if (mode.equals("SIMPLE")) {
            buttonPanel.setLayout(new GridLayout(5, 4, 3, 3));
            addSimpleCalculatorButtons();
        } else if (mode.equals("BUSINESS")) {
            buttonPanel.setLayout(new GridLayout(6, 4, 3, 3));
            addBusinessCalculatorButtons();
        } else if (mode.equals("SCIENTIFIC")) {
            buttonPanel.setLayout(new GridLayout(7, 6, 3, 3));
            addScientificCalculatorButtons();
        }
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(new Color(20, 20, 20));
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void addSimpleCalculatorButtons() {
        String[][] buttons = {
                {"C", "DEL", "%", "/"},
                {"7", "8", "9", "*"},
                {"4", "5", "6", "-"},
                {"1", "2", "3", "+"},
                {"0", ".", "=", ""}
        };
        for (String[] row : buttons) {
            for (String label : row) {
                if (!label.isEmpty()) {
                    addButton(label);
                }
            }
        }
    }

    private void addBusinessCalculatorButtons() {
        String[][] buttons = {
                {"C", "DEL", "%", "/"},
                {"7", "8", "9", "*"},
                {"4", "5", "6", "-"},
                {"1", "2", "3", "+"},
                {"0", ".", "=", ""},
                {"M+", "M-", "MR", "MC"}
        };
        for (String[] row : buttons) {
            for (String label : row) {
                if (!label.isEmpty()) {
                    addButton(label);
                }
            }
        }
    }

    private void addScientificCalculatorButtons() {
        String[][] buttons = {
                {"C", "DEL", "%", "/", "^", "sqrt"},
                {"7", "8", "9", "*", "log", "ln"},
                {"4", "5", "6", "-", "sin", "cos"},
                {"1", "2", "3", "+", "tan", "!"},
                {"0", ".", "(", ")", "pi", "e"},
                {"M+", "M-", "MR", "MC", "=", ""}
        };
        for (String[] row : buttons) {
            for (String label : row) {
                if (!label.isEmpty()) {
                    addButton(label);
                }
            }
        }
    }

    private void addButton(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);

        // Color coding
        if (label.equals("=")) {
            btn.setBackground(new Color(0, 180, 90));
            btn.setForeground(Color.WHITE);
        } else if (label.equals("C")) {
            btn.setBackground(new Color(220, 50, 50));
            btn.setForeground(Color.WHITE);
        } else if (label.equals("DEL")) {
            btn.setBackground(new Color(200, 100, 0));
            btn.setForeground(Color.WHITE);
        } else if (label.matches("[+\\-*/%^]") || label.matches("(log|ln|sin|cos|tan|sqrt|!)")) {
            btn.setBackground(new Color(0, 120, 215));
            btn.setForeground(Color.WHITE);
        } else if (label.matches("(M[+\\-R]|MC|pi|e|\\(|\\))")) {
            btn.setBackground(new Color(100, 100, 100));
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(new Color(60, 60, 60));
            btn.setForeground(Color.WHITE);
        }

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(Math.min(255, btn.getBackground().getRed() + 30),
                        Math.min(255, btn.getBackground().getGreen() + 30),
                        Math.min(255, btn.getBackground().getBlue() + 30)));
            }
            public void mouseExited(MouseEvent e) {
                String lbl = btn.getText();
                if (lbl.equals("=")) {
                    btn.setBackground(new Color(0, 180, 90));
                } else if (lbl.equals("C")) {
                    btn.setBackground(new Color(220, 50, 50));
                } else if (lbl.equals("DEL")) {
                    btn.setBackground(new Color(200, 100, 0));
                } else if (lbl.matches("[+\\-*/%^]") || lbl.matches("(log|ln|sin|cos|tan|sqrt|!)")) {
                    btn.setBackground(new Color(0, 120, 215));
                } else if (lbl.matches("(M[+\\-R]|MC|pi|e|\\(|\\))")) {
                    btn.setBackground(new Color(100, 100, 100));
                } else {
                    btn.setBackground(new Color(60, 60, 60));
                }
            }
        });

        btn.addActionListener(e -> handleButtonClick(label));
        buttonPanel.add(btn);
    }

    private void handleButtonClick(String label) {
        try {
            switch (label) {
                case "C":
                    calculator.reset();
                    resultDisplay.setText("0");
                    displayArea.setText("0");
                    break;
                case "DEL":
                    String current = resultDisplay.getText();
                    if (current.equals("Error")) {
                        resultDisplay.setText("0");
                    } else if (current.length() > 1) {
                        resultDisplay.setText(current.substring(0, current.length() - 1));
                    } else {
                        resultDisplay.setText("0");
                    }
                    break;
                case "=":
                    double result = calculator.calculate(resultDisplay.getText());
                    displayArea.setText(resultDisplay.getText());
                    resultDisplay.setText(formatResult(result));
                    calculator.setLastResult(result);
                    break;
                case "M+":
                    calculator.memoryAdd(Double.parseDouble(resultDisplay.getText()));
                    break;
                case "M-":
                    calculator.memorySub(Double.parseDouble(resultDisplay.getText()));
                    break;
                case "MR":
                    resultDisplay.setText(formatResult(calculator.memoryRecall()));
                    break;
                case "MC":
                    calculator.memoryClear();
                    break;
                case "sin":
                    double sinVal = Math.sin(Math.toRadians(Double.parseDouble(resultDisplay.getText())));
                    resultDisplay.setText(formatResult(sinVal));
                    break;
                case "cos":
                    double cosVal = Math.cos(Math.toRadians(Double.parseDouble(resultDisplay.getText())));
                    resultDisplay.setText(formatResult(cosVal));
                    break;
                case "tan":
                    double tanVal = Math.tan(Math.toRadians(Double.parseDouble(resultDisplay.getText())));
                    resultDisplay.setText(formatResult(tanVal));
                    break;
                case "log":
                    double logVal = Math.log10(Double.parseDouble(resultDisplay.getText()));
                    resultDisplay.setText(formatResult(logVal));
                    break;
                case "ln":
                    double lnVal = Math.log(Double.parseDouble(resultDisplay.getText()));
                    resultDisplay.setText(formatResult(lnVal));
                    break;
                case "sqrt":
                    double sqrtVal = Math.sqrt(Double.parseDouble(resultDisplay.getText()));
                    resultDisplay.setText(formatResult(sqrtVal));
                    break;
                case "!":
                    long num = Long.parseLong(resultDisplay.getText());
                    double factVal = factorial(num);
                    resultDisplay.setText(formatResult(factVal));
                    break;
                case "pi":
                    resultDisplay.setText(formatResult(Math.PI));
                    break;
                case "e":
                    resultDisplay.setText(formatResult(Math.E));
                    break;
                default:
                    if (resultDisplay.getText().equals("0") && !label.equals(".")) {
                        resultDisplay.setText(label);
                    } else if (label.equals(".") && resultDisplay.getText().contains(".")) {
                        // Do nothing
                    } else if (label.matches("[+\\-*/()^%]")) {
                        resultDisplay.setText(resultDisplay.getText() + label);
                    } else {
                        resultDisplay.setText(resultDisplay.getText() + label);
                    }
                    break;
            }
        } catch (NumberFormatException e) {
            resultDisplay.setText("Error");
        } catch (Exception e) {
            resultDisplay.setText("Error");
        }
    }

    private double factorial(long n) {
        if (n < 0) throw new IllegalArgumentException("Negative factorial");
        if (n > 20) throw new IllegalArgumentException("Factorial too large");
        if (n <= 1) return 1;
        double result = 1;
        for (long i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private String formatResult(double result) {
        if (Double.isInfinite(result) || Double.isNaN(result)) {
            return "Error";
        }
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%.10g", result);
        }
    }
}

class Calculator {
    private double memory = 0;
    private double lastResult = 0;

    public void reset() {
        memory = 0;
        lastResult = 0;
    }

    public double calculate(String expression) throws Exception {
        try {
            return evaluateExpression(expression);
        } catch (Exception e) {
            throw new Exception("Invalid expression");
        }
    }

    private double evaluateExpression(String expr) {
        return new ExpressionEvaluator(expr).evaluate();
    }

    public void memoryAdd(double value) {
        memory += value;
    }

    public void memorySub(double value) {
        memory -= value;
    }

    public void memoryClear() {
        memory = 0;
    }

    public double memoryRecall() {
        return memory;
    }

    public void setLastResult(double result) {
        lastResult = result;
    }
}

class ExpressionEvaluator {
    private String expr;
    private int pos = 0;

    public ExpressionEvaluator(String expr) {
        this.expr = expr.replaceAll("\\s+", "");
    }

    public double evaluate() {
        double result = parseExpression();
        if (pos < expr.length()) {
            throw new RuntimeException("Unexpected character at position " + pos);
        }
        return result;
    }

    private double parseExpression() {
        double result = parseTerm();
        while (pos < expr.length() && (expr.charAt(pos) == '+' || expr.charAt(pos) == '-')) {
            char op = expr.charAt(pos++);
            double right = parseTerm();
            result = op == '+' ? result + right : result - right;
        }
        return result;
    }

    private double parseTerm() {
        double result = parseFactor();
        while (pos < expr.length() && (expr.charAt(pos) == '*' || expr.charAt(pos) == '/' || expr.charAt(pos) == '%')) {
            char op = expr.charAt(pos++);
            double right = parseFactor();
            if (op == '*') {
                result = result * right;
            } else if (op == '/') {
                if (right == 0) throw new RuntimeException("Division by zero");
                result = result / right;
            } else {
                result = result % right;
            }
        }
        return result;
    }

    private double parseFactor() {
        double result = parsePower();
        return result;
    }

    private double parsePower() {
        double result = parseUnary();
        if (pos < expr.length() && expr.charAt(pos) == '^') {
            pos++;
            double right = parseUnary();
            result = Math.pow(result, right);
        }
        return result;
    }

    private double parseUnary() {
        if (pos < expr.length() && expr.charAt(pos) == '-') {
            pos++;
            return -parseUnary();
        } else if (pos < expr.length() && expr.charAt(pos) == '+') {
            pos++;
            return parseUnary();
        }
        return parsePrimary();
    }

    private double parsePrimary() {
        if (pos < expr.length() && expr.charAt(pos) == '(') {
            pos++;
            double result = parseExpression();
            if (pos < expr.length() && expr.charAt(pos) == ')') {
                pos++;
            }
            return result;
        }
        return parseNumber();
    }

    private double parseNumber() {
        double result = 0;
        while (pos < expr.length() && Character.isDigit(expr.charAt(pos))) {
            result = result * 10 + (expr.charAt(pos++) - '0');
        }

        if (pos < expr.length() && expr.charAt(pos) == '.') {
            pos++;
            double fraction = 0.1;
            while (pos < expr.length() && Character.isDigit(expr.charAt(pos))) {
                result += (expr.charAt(pos++) - '0') * fraction;
                fraction *= 0.1;
            }
        }
        return result;
    }
}