package calculator;

import calculator.model.AncientRomeModel;
import calculator.model.Model;

import javax.swing.*;
import java.awt.event.*;
import java.util.EmptyStackException;

public class Controller extends KeyAdapter implements ComponentListener, ActionListener {
    private View view;
    private Model model = new Model();
    private AncientRomeModel ancientRomeModel = new AncientRomeModel();

    public Controller(View view) {
        this.view = view;
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentResized(ComponentEvent e) {
        view.changeFont();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '=' || e.getKeyChar() == '\n') {
            computeAndPrint();
            e.consume();
        } else if (view.getMode() == 1) {
            switch (e.getKeyChar()) {
                case '!':
                    factorial();
                    e.consume();
                    break;
                case '^':
                    pow2();
                    e.consume();
                    break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String temp = button.getName();
        if (button.getText().equals("<|"))
            //backspace button
            view.deleteLast();
        else if (button.getText().equals("C"))
            //rest calculator
            view.reset();
        else if (button.getText().equals("x\u00B2")) {
            // mathPow last
            pow2();
        } else if (button.getText().equals("\u221Ax")) {
            //mathSqrt
            sqrt();
        } else if (button.getText().equals("n!")) {
            //math factorial
            factorial();
        } else if (button.getText().equals("+/-")) {
            // print (-[number])
            String result = view.deleteLastNumberAndGetIt();
            if (result != null && !result.isEmpty())
                view.typeText("(-" + result + ")");
        } else if (button.getText().equals("=")) {
            //main compute
            if (!view.getText().isEmpty())
                computeAndPrint();
        } else if (temp == null)
            view.typeText(button.getText());
        else view.typeText(button.getText());
    }

    private void computeAndPrint() {
        try {
            if (!view.getText().isEmpty()) {
                if (view.getMode() == 1) {
                    double result = model.compute(view.getText());
                    if (result * 10 % 10 == 0)
                        view.typeNewText(String.valueOf((long) result));
                    else view.typeNewText(String.valueOf(result));
                } else if (view.getMode() == 2) {
                    String result = ancientRomeModel.computeToString(view.getText());
                    view.typeNewText(result);
                }
            }
        } catch (EquationFormatException ignored) {
            JOptionPane.showMessageDialog(view, "В уравнении ошибки, которые\nя не могу исправить\uD83D\uDE05");
        } catch (EmptyStackException e) {
            JOptionPane.showMessageDialog(view, "Случайно не забыл цифры или значения написать?\uD83D\uDE42");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Уупс\uD83D\uDE05 я не справился\nс твоей задачей\uD83E\uDEE0");
            e.printStackTrace();
        }
    }

    private void printNumber(double number) {
        if (number * 10 % 10 == 0)
            view.typeText(String.valueOf((int) number));
        else view.typeText(String.valueOf(number));
    }

    private void pow2() {
        String currentNumber = view.deleteLastNumberAndGetIt();
        if (currentNumber != null && !currentNumber.isEmpty()) {
            double result = model.getPow2(Double.parseDouble(currentNumber));
            printNumber(result);
        }
    }

    private void sqrt() {
        String currentNumber = view.deleteLastNumberAndGetIt();
        if (currentNumber != null && !currentNumber.isEmpty()) {
            double result = model.squareRoot(Double.parseDouble(currentNumber));
            printNumber(result);
        }
    }

    private void factorial() {
        String currentNumber = view.deleteLastNumberAndGetIt();
        if (currentNumber != null && !currentNumber.isEmpty()) {
            double result = model.factorial(Double.parseDouble(currentNumber));
            if ((int) result == -1)
                JOptionPane.showMessageDialog(view, "Больше 26 факториал я не могу посчитать :(");
            printNumber(result);
        }
    }

    public class ModController implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getMode() == 1)
                view.setMode(2);
            else if (view.getMode() == 2)
                view.setMode(1);
        }
    }
}