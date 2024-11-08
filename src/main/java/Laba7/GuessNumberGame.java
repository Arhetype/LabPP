package Laba7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class GuessNumberGame extends JFrame {

    private JTextField minField, maxField, guessField;
    private JButton guessButton;
    private JButton higherButton, lowerButton, correctButton;
    private JButton changeColorButton; // Новая кнопка для смены цвета фона
    private JLabel infoLabel;
    private JRadioButton metalButton, nimbusButton, windowsButton, classicButton;

    private int min, max;
    private int currentGuess;
    private int lowerBound, upperBound;
    private boolean cheating = false;

    public GuessNumberGame() {
        setTitle("Игра в 'Больше-меньше'");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Минимальное значение:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        minField = new JTextField(10);
        panel.add(minField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Максимальное значение:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        maxField = new JTextField(10);
        panel.add(maxField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Загадонное число:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        guessField = new JTextField(10);
        panel.add(guessField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        guessButton = new JButton("Попробовать угадать");
        guessButton.addActionListener(e -> startGuessing());
        panel.add(guessButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        higherButton = new JButton("Больше");
        higherButton.addActionListener(e -> adjustBounds("higher"));
        higherButton.setEnabled(false);
        panel.add(higherButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        lowerButton = new JButton("Меньше");
        lowerButton.addActionListener(e -> adjustBounds("lower"));
        lowerButton.setEnabled(false);
        panel.add(lowerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        correctButton = new JButton("Верно!");
        correctButton.addActionListener(e -> {
            infoLabel.setText("Правильно! Хотите сыграть еще раз?");
            disableGuessing();
        });
        correctButton.setEnabled(false);
        panel.add(correctButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        infoLabel = new JLabel("Введите диапазон и предположение");
        panel.add(infoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        ButtonGroup lafGroup = new ButtonGroup();
        metalButton = new JRadioButton("Metal");
        nimbusButton = new JRadioButton("Nimbus");
        windowsButton = new JRadioButton("Windows");
        classicButton = new JRadioButton("Classic", true);  // Default selection

        metalButton.addActionListener(e -> changeLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"));
        nimbusButton.addActionListener(e -> changeLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"));
        windowsButton.addActionListener(e -> changeLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"));
        classicButton.addActionListener(e -> changeLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()));

        lafGroup.add(metalButton);
        lafGroup.add(nimbusButton);
        lafGroup.add(windowsButton);
        lafGroup.add(classicButton);

        panel.add(new JLabel("Выберите тему:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(metalButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(nimbusButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(windowsButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(classicButton, gbc);

        // Добавляем кнопку для смены цвета фона
        gbc.gridx = 0;
        gbc.gridy = 10;
        changeColorButton = new JButton("Сменить цвет фона");
        changeColorButton.addActionListener(this::changeBackgroundColor);
        panel.add(changeColorButton, gbc);

        add(panel);
    }

    // Логика смены цвета фона
    private void changeBackgroundColor(ActionEvent e) {
        Random rand = new Random();
        Color color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        getContentPane().setBackground(color);
    }

    private void startGuessing() {
        try {
            min = Integer.parseInt(minField.getText());
            max = Integer.parseInt(maxField.getText());
            lowerBound = min;
            upperBound = max;
            currentGuess = (lowerBound + upperBound) / 2;

            infoLabel.setText("Это число " + currentGuess + "?");
            enableGuessing();
        } catch (NumberFormatException e) {
            infoLabel.setText("Введите корректные значения");
        }
    }

    private void adjustBounds(String adjustment) {
        if (adjustment.equals("higher")) {
            lowerBound = currentGuess + 1;
        } else if (adjustment.equals("lower")) {
            upperBound = currentGuess - 1;
        }
        if (lowerBound <= upperBound) {
            currentGuess = (lowerBound + upperBound) / 2;
            infoLabel.setText("Это число " + currentGuess + "?");
        } else {
            infoLabel.setText("Жульничество! Загаданное число вне диапазона");
            cheating = true;
            disableGuessing();
        }
    }

    private void enableGuessing() {
        guessField.setEnabled(false);
        guessButton.setEnabled(false);
        higherButton.setEnabled(true);
        lowerButton.setEnabled(true);
        correctButton.setEnabled(true);
    }

    private void disableGuessing() {
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
        higherButton.setEnabled(false);
        lowerButton.setEnabled(false);
        correctButton.setEnabled(false);
    }

    private void changeLookAndFeel(String lafClassName) {
        try {
            UIManager.setLookAndFeel(lafClassName);
            SwingUtilities.updateComponentTreeUI(this);
            pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessNumberGame());
    }
}
