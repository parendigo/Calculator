package calculator;

import calculator.components.AbstractCalculatorComponents;
import calculator.components.AncientRomanComponents;
import calculator.components.ClassicCalculatorComponents;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View extends JFrame {
    private static final Color COLOR_BACKGROUND = new Color(0xFFFBF2);
    private static final Color COLOR_BUTTONS = new Color(0xFFF7E5);
    private static final Color COLOR_EQUALS = new Color(0xE6C986);
    private static final Color COLOR_MODS = new Color(0xFAEEDD);

    private static final int WIDTH = 315;
    private static final int HEIGHT = 450;

    private Controller controller;
    private AbstractCalculatorComponents components;
    private int mode = 1;
    private JButton button;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        addComponents();
        addKeyListener(controller);
        addComponentListener(controller);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /* Изменение размера кнопок, текста в зависимости от размера окна */
    protected void changeFont() {
        Dimension newSize = getBounds().getSize();
        Font font = new Font("Arial", Font.PLAIN, newSize.height / 20);
        components.resize(font);
        Font modFont = new Font("Arial", Font.PLAIN, newSize.height / 24);
        button.setFont(modFont);
    }

    /* Переключатель на римский калькулятор */
    protected void switchToAncientRomanCalculator() {
        components = new AncientRomanComponents();
        add(components.getCalculatorPanel(), BorderLayout.CENTER);
        components.setControllerToAll(controller);
        components.setFocusForAll(false);
        setColorTheme();
        changeFont();
    }
    /* Переключатель на обычный калькулятор */
    protected void switchToClassicCalculator() {
        components = new ClassicCalculatorComponents();
        add(components.getCalculatorPanel(), BorderLayout.CENTER);
        components.setControllerToAll(controller);
        components.setFocusForAll(false);
        setColorTheme();
        changeFont();
    }

    private void addComponents() {
        setTitle("Calculator");
        add(getModeButton(), BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.EAST);
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.SOUTH);
        switchToClassicCalculator();
        revalidate();
    }

    private void setColorTheme() {
        Component[] cos = getContentPane().getComponents();
        setBackground(COLOR_BACKGROUND);
        for (int i = 0; i < cos.length; i++) {
            cos[i].setBackground(COLOR_BACKGROUND);
        }
        components.setTextColor(COLOR_BACKGROUND);
        components.setAllButtonsColor(COLOR_BUTTONS);
        components.setEqualsColor(COLOR_EQUALS);
        components.setBorder(BorderFactory.createEtchedBorder(0, COLOR_EQUALS, COLOR_BACKGROUND));
    }

    protected void reset() {
        components.getTextPane().setText("");
    }

    protected void deleteLast() {
        String tempText = components.getTextPane().getText();
        if (!tempText.isEmpty())
            components.getTextPane().setText(tempText.substring(0, tempText.length() - 1));
    }

    protected void typeText(String s) {
        if (components.getTextPane().getText().equals("0"))
            components.getTextPane().setText(s);
        else components.getTextPane().setText(components.getTextPane().getText() + s);
    }

    protected String getText() {
        return components.getTextPane().getText();
    }

    protected void typeNewText(String s) {
        reset();
        typeText(s);
    }

    protected String deleteLastNumberAndGetIt() {
        Pattern pattern;
        if (mode == 2)
            pattern = Pattern.compile("[IVX]]+$");
        else pattern = Pattern.compile("\\d*\\.?\\d+$");
        Matcher matcher = pattern.matcher(components.getTextPane().getText());
        if (matcher.find()) {
            String result = matcher.group();
            String formatedText = matcher.replaceFirst("");
            components.getTextPane().setText(formatedText);
            return result;
        }
        return null;
    }

    private JPanel getModeButton() {
        JPanel panel = new JPanel();
        button = new JButton("режим");
        button.setBackground(COLOR_MODS);
        button.setBorder(BorderFactory.createEtchedBorder(0, COLOR_EQUALS, COLOR_BACKGROUND));
        button.setFocusable(false);
        button.addActionListener(controller.new ModController());
        panel.add(button);
        return panel;
    }

    /* Переключение режима калькулятора */
    public void setMode(int mode) {
        if (mode == 1) {
            getContentPane().remove(4);
            switchToClassicCalculator();
            this.mode = mode;
        } else if (mode == 2) {
            getContentPane().remove(4);
            switchToAncientRomanCalculator();
            this.mode = mode;
        }
        revalidate();
    }

    protected int getMode() {
        return mode;
    }
}