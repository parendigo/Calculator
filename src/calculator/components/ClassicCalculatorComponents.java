package calculator.components;

import javax.swing.*;
import java.awt.*;

public class ClassicCalculatorComponents extends AbstractCalculatorComponents {

    public ClassicCalculatorComponents() {
        super(new JPanel(), new JFormattedTextField());
    }

    /* Захардкодил цвет кнопки '='. Будет ломаться, если добавить кнопок */
    @Override
    public void setEqualsColor(Color color) {
        getPanel().getComponent(25).setBackground(color);
    }

    @Override
    public JPanel getCalculatorPanel() {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 5;
        gbc.weighty = 5;
        getPanel().setLayout(gbl);
        getPanel().add(getTextPane(), gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 3;
        getPanel().add(getBackButton(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        getPanel().add(getPow(), gbc);
        gbc.gridx = 1;
        getPanel().add(getLeftParentheses(), gbc);
        gbc.gridx = 2;
        getPanel().add(getRightParentheses(), gbc);
        gbc.gridx = 3;
        getPanel().add(getResetButton(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        getPanel().add(getSqrtX(), gbc);
        gbc.gridx = 1;
        getPanel().add(getFactorial(), gbc);
        gbc.gridx = 2;
        getPanel().add(getRemainder(), gbc);
        gbc.gridx = 3;
        getPanel().add(getDevision(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        getPanel().add(getNumber7(), gbc);
        gbc.gridx = 1;
        getPanel().add(getNumber8(), gbc);
        gbc.gridx = 2;
        getPanel().add(getNumber9(), gbc);
        gbc.gridx = 3;
        getPanel().add(getMultiply(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        getPanel().add(getNumber4(), gbc);
        gbc.gridx = 1;
        getPanel().add(getNumber5(), gbc);
        gbc.gridx = 2;
        getPanel().add(getNumber6(), gbc);
        gbc.gridx = 3;
        getPanel().add(getMinus(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        getPanel().add(getNumber1(), gbc);
        gbc.gridx = 1;
        getPanel().add(getNumber2(), gbc);
        gbc.gridx = 2;
        getPanel().add(getNumber3(), gbc);
        gbc.gridx = 3;
        getPanel().add(getPlus(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        getPanel().add(getPrefix(), gbc);
        gbc.gridx = 1;
        getPanel().add(getNumber0(), gbc);
        gbc.gridx = 2;
        getPanel().add(getDot(), gbc);
        gbc.gridx = 3;
        getPanel().add(getEquals(), gbc);
        return getPanel();
    }

    private Component getLeftParentheses() {
        JButton leftParentheses = new JButton("(");
        return leftParentheses;
    }

    private Component getRightParentheses() {
        JButton rightParentheses = new JButton(")");
        return rightParentheses;
    }

    private Component getPow() {
        JButton powButton = new JButton("x\u00B2");
        powButton.setName("^");
        return powButton;
    }

    private Component getSqrtX() {
        JButton sqrtX = new JButton("\u221Ax");
        return sqrtX;
    }

    private Component getFactorial() {
        JButton factorial = new JButton("n!");
        factorial.setName("!");
        return factorial;
    }

    private Component getPrefix() {
        JButton prefix = new JButton("+/-");
        prefix.setName("-");
        return prefix;
    }

    private Component getDot() {
        JButton button = new JButton(".");
        return button;
    }
}