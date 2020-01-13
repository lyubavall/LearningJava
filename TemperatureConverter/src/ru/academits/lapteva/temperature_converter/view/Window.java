package ru.academits.lapteva.temperature_converter.view;

import ru.academits.lapteva.temperature_converter.model.Converter;
import ru.academits.lapteva.temperature_converter.model.ConverterFromCelsius;
import ru.academits.lapteva.temperature_converter.model.ConverterFromFahrenheit;
import ru.academits.lapteva.temperature_converter.model.ConverterFromKelvin;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JRadioButton inK;
    private JRadioButton inC;
    private JRadioButton inF;

    public void display() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.out.println("Exception in LookAndFeel");
            }

            JFrame frame = new JFrame("Temperature converter");

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int frameWidth = (int) (screenSize.getWidth() * 0.4);
            int frameHeight = (int) (screenSize.getHeight() * 0.4);

            frame.setSize(frameWidth, frameHeight);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);

            JPanel centerPanel = new JPanel();
            frame.add(centerPanel, BorderLayout.CENTER);

            JButton convertButton = new JButton("convert");
            convertButton.setPreferredSize(new Dimension(200, 80));

            JPanel buttonPanel = new JPanel(new GridBagLayout());
            buttonPanel.add(convertButton);

            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
            centerPanel.add(buttonPanel, Component.CENTER_ALIGNMENT);

            JPanel westPanel = new JPanel();
            westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
            westPanel.setPreferredSize(new Dimension((int) (frameWidth * 0.3), (int) (frameHeight * 0.3)));
            frame.add(westPanel, BorderLayout.LINE_START);

            JPanel eastPanel = new JPanel();
            eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
            eastPanel.setPreferredSize(new Dimension((int) (frameWidth * 0.3), (int) (frameHeight * 0.3)));
            frame.add(eastPanel, BorderLayout.LINE_END);

            JPanel topPanel = new JPanel();
            topPanel.setPreferredSize(new Dimension(frameWidth, (int) (frameHeight * 0.1)));

            JLabel wesTitle = new JLabel("Enter a temperature:");
            westPanel.add(wesTitle);

            JLabel eastTitle = new JLabel("Result:");
            eastPanel.add(eastTitle);

            JTextField inputT = new JTextField();
            westPanel.add(inputT);
            //inputT.setSize(20, 50);

            JTextField outputT = new JTextField();
            eastPanel.add(outputT);

            JLabel convertFrom = new JLabel("Convert from");
            westPanel.add(convertFrom);

            JLabel convertTo = new JLabel("Convert to");
            eastPanel.add(convertTo);

            inK = new JRadioButton("\u212A", true);
            inC = new JRadioButton("\u2103");
            inF = new JRadioButton("\u2109");

            JRadioButton outK = new JRadioButton("\u212A", true);
            JRadioButton outC = new JRadioButton("\u2103");
            JRadioButton outF = new JRadioButton("\u2109");

            ButtonGroup groupInputT = new ButtonGroup();
            groupInputT.add(inK);
            groupInputT.add(inC);
            groupInputT.add(inF);

            ButtonGroup groupOutT = new ButtonGroup();
            groupOutT.add(outK);
            groupOutT.add(outC);
            groupOutT.add(outF);

            westPanel.add(inK);
            westPanel.add(inC);
            westPanel.add(inF);

            eastPanel.add(outK);
            eastPanel.add(outC);
            eastPanel.add(outF);

            convertButton.addActionListener(e -> {
                try {
                    String text = inputT.getText();
                    double value = Double.parseDouble(text);

                    if (outK.isSelected()) {
                        outputT.setText(Double.toString(getConverter().getKelvin(value)));
                    } else if (outC.isSelected()) {
                        outputT.setText(Double.toString(getConverter().getCelsius(value)));
                    } else {
                        outputT.setText(Double.toString(getConverter().getFahrenheit(value)));
                    }

                    outputT.setEditable(false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Need a numeric type");
                }
            });
        });
    }

    private Converter getConverter() {
        if (inK.isSelected()) {
            return new ConverterFromKelvin();
        }
        if (inC.isSelected()) {
            return new ConverterFromCelsius();
        }

        return new ConverterFromFahrenheit();
    }
}
