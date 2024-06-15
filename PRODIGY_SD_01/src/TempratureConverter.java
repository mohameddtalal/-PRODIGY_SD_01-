import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TempratureConverter extends JFrame {
    private JTextField inputField;
    private JComboBox<String> unitComboBox;
    private JLabel resultFahrenheit;
    private JLabel resultCelsius;
    private JLabel resultKelvin;
    private JButton convertButton;

    public TempratureConverter() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel inputLabel = new JLabel("Enter Temperature:");
        inputLabel.setBounds(20, 20, 150, 25);
        add(inputLabel);

        inputField = new JTextField();
        inputField.setBounds(180, 20, 150, 25);
        add(inputField);

        JLabel unitLabel = new JLabel("Select Unit:");
        unitLabel.setBounds(20, 60, 150, 25);
        add(unitLabel);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitComboBox = new JComboBox<>(units);
        unitComboBox.setBounds(180, 60, 150, 25);
        add(unitComboBox);

        convertButton = new JButton("Convert");
        convertButton.setBounds(20, 100, 310, 25);
        add(convertButton);

        resultFahrenheit = new JLabel("Fahrenheit: ");
        resultFahrenheit.setBounds(20, 140, 200, 25);
        add(resultFahrenheit);

        resultCelsius = new JLabel("Celsius: ");
        resultCelsius.setBounds(20, 180, 200, 25);
        add(resultCelsius);

        resultKelvin = new JLabel("Kelvin: ");
        resultKelvin.setBounds(20, 220, 200, 25);
        add(resultKelvin);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        try {
            double inputTemp = Double.parseDouble(inputField.getText());
            String selectedUnit = (String) unitComboBox.getSelectedItem();

            double celsius, fahrenheit, kelvin;

            switch (selectedUnit) {
                case "Celsius":
                    celsius = inputTemp;
                    fahrenheit = celsiusToFahrenheit(celsius);
                    kelvin = celsiusToKelvin(celsius);
                    break;
                case "Fahrenheit":
                    fahrenheit = inputTemp;
                    celsius = fahrenheitToCelsius(fahrenheit);
                    kelvin = celsiusToKelvin(celsius);
                    break;
                case "Kelvin":
                    kelvin = inputTemp;
                    celsius = kelvinToCelsius(kelvin);
                    fahrenheit = celsiusToFahrenheit(celsius);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + selectedUnit);
            }

            resultFahrenheit.setText("Fahrenheit: " + fahrenheit);
            resultCelsius.setText("Celsius: " + celsius);
            resultKelvin.setText("Kelvin: " + kelvin);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number");
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TempratureConverter().setVisible(true);
            }
        });
    }
}
