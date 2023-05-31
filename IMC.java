import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IMCCalculator extends JFrame {
    private JTextField weightTextField;
    private JTextField heightTextField;
    private JLabel resultLabel;

    public IMCCalculator() {
        setTitle("Calculadora de IMC");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel weightLabel = new JLabel("Peso (Kg):");
        weightTextField = new JTextField();
        JLabel heightLabel = new JLabel("Altura (Cm):");
        heightTextField = new JTextField();

        inputPanel.add(weightLabel);
        inputPanel.add(weightTextField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton calculateButton = new JButton("Calcular");
        calculateButton.addActionListener(new CalculateButtonListener());
        buttonPanel.add(calculateButton);

        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultLabel = new JLabel();

        resultPanel.add(resultLabel);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(resultPanel, BorderLayout.NORTH);

        add(mainPanel);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double weight = Double.parseDouble(weightTextField.getText());
                double height = Double.parseDouble(heightTextField.getText()) / 100; // Convertendo para metros

                double bmi = weight / (height * height);

                String result;
                if (bmi < 17) {
                    result = "Muito abaixo do peso";
                } else if (bmi >= 17 && < 18.49) {
                    result = "Abaixo do peso";
                } else if (bmi >= 18.5 && < 24.99) {
                    result = "Peso normal";
                } else if (bmi >= 25 && < 29.99) {
                    result = "Acima do peso";
				}else if (bmi >= 30 && < 34.99) {
                    result = "Obesidade I";
				}else if (bmi >= 35 && < 39.99) {
                    result = "Obesidade II (severa)";
				}else if (bmi >= 40) {
                    result = "Obesidade III (mórbida)";
                }

                resultLabel.setText("Seu IMC é " + String.format("%.2f", bmi) + ". Classificação: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Valores inválidos. Preencha corretamente.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                IMCCalculator calculator = new IMCCalculator();
                calculator.setVisible(true);
            }
        });
    }
}
