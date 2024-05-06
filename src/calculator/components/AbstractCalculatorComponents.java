package calculator.components;

import calculator.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class AbstractCalculatorComponents {
    private JPanel panel;
    private JFormattedTextField textPane;

    public AbstractCalculatorComponents(JPanel panel, JFormattedTextField textPane) {
        this.panel = panel;
        this.textPane = textPane;
    }

    public void setBorder(Border border) {
        getPanel().setBorder(border);
        Component[] components = getPanel().getComponents();
        ((JFormattedTextField) getPanel().getComponent(0)).setBorder(border);
        for (int i = 1; i < components.length; i++) {
            ((JButton) components[i]).setBorder(border);
        }
    }

    public void resize(Font font) {
        getPanel().setFont(font);
        Component[] components = getPanel().getComponents();
        for (int i = 0; i < components.length; i++) {
            components[i].setFont(font);
        }
    }

    public void setControllerToAll(Controller controller) {
        getPanel().addKeyListener(controller);
        Component[] components = getPanel().getComponents();
        components[0].addKeyListener(controller);
        for (int i = 1; i < components.length; i++) {
            components[i].addKeyListener(controller);
            ((JButton) components[i]).addActionListener(controller);
        }
    }

    public void setFocusForAll(boolean b) {
        getPanel().setFocusable(b);
        Component[] components = getPanel().getComponents();
        for (int i = 1; i < components.length; i++) {
            components[i].setFocusable(b);
        }
    }

    public void setTextColor(Color color) {
        getPanel().getComponent(0).setBackground(color);
    }

    public void setAllButtonsColor(Color color) {
        Component[] components = getPanel().getComponents();
        for (int i = 1; i < components.length; i++) {
            components[i].setBackground(color);
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public JFormattedTextField getTextPane() {
        return textPane;
    }

    protected Component getRemainder() {
        JButton remainder = new JButton("%");
        return remainder;
    }

    protected Component getNumber7() {
        JButton button = new JButton("7");
        return button;
    }

    protected Component getNumber8() {
        JButton button = new JButton("8");
        return button;
    }

    protected Component getNumber9() {
        JButton button = new JButton("9");
        return button;
    }

    protected Component getNumber4() {
        JButton button = new JButton("4");
        return button;
    }

    protected Component getNumber5() {
        JButton button = new JButton("5");
        return button;
    }

    protected Component getNumber6() {
        JButton button = new JButton("6");
        return button;
    }

    protected Component getNumber1() {
        JButton button = new JButton("1");
        return button;
    }

    protected Component getNumber2() {
        JButton button = new JButton("2");
        return button;
    }

    protected Component getNumber3() {
        JButton button = new JButton("3");
        return button;
    }

    protected Component getNumber0() {
        JButton button = new JButton("0");
        return button;
    }

    protected Component getMultiply() {
        JButton button = new JButton("*");
        return button;
    }

    protected Component getDevision() {
        JButton button = new JButton("/");
        return button;
    }

    protected Component getMinus() {
        JButton button = new JButton("-");
        return button;
    }

    protected Component getPlus() {
        JButton button = new JButton("+");
        return button;
    }

    protected Component getEquals() {
        JButton button = new JButton("=");
        return button;
    }

    protected Component getResetButton() {
        JButton resetButton = new JButton("C");
        return resetButton;
    }

    protected Component getBackButton() {
        JButton backButton = new JButton("<|");
        return backButton;
    }

    public abstract void setEqualsColor(Color color);

    public abstract JPanel getCalculatorPanel();
}