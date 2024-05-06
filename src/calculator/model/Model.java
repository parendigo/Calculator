package calculator.model;

import calculator.EquationFormatException;

import java.util.EmptyStackException;
import java.util.Stack;

public class Model {
    private Stack<Double> nums = new Stack<>();
    private Stack<Character> operations = new Stack<>();
    private Stack<Integer> parentheses = new Stack<>();

    public double compute(String equation) throws EquationFormatException {
        clearStacks();
        parseEquation(equation);
        double result = 0.0;
        try {
            parentheses.push(0);
            for (int i = 0; i < equation.length(); i++) {
                if (Character.isDigit(equation.charAt(i)) || equation.charAt(i) == '.') {
                    int startOfNumber = i;
                    while (i < equation.length() && (Character.isDigit(equation.charAt(i)) || equation.charAt(i) == '.')) {
                        i++;
                    }
                    nums.push(Double.valueOf(equation.substring(startOfNumber, i)));
                    if (!operations.isEmpty() && (operations.peek() == '*' || operations.peek() == '/' || operations.peek() == '%'))
                        doMath();
                    i--;
                } else if (equation.charAt(i) == '-') {
                    if (i == 0 || !Character.isDigit(equation.charAt(i - 1)))
                        nums.push(0.0);
                    operations.push('-');
                } else if (equation.charAt(i) == '*' || equation.charAt(i) == '/' || equation.charAt(i) == '%'
                        || equation.charAt(i) == '+') {
                    operations.push(equation.charAt(i));
                } else if (equation.charAt(i) == '(') {
                    if (i > 0 && (Character.isDigit(equation.charAt(i - 1)) || equation.charAt(i - 1) == ')'))
                        operations.push('*');
                    parentheses.push(operations.size());
                } else if (equation.charAt(i) == ')') {
                    doMath();
                    parentheses.pop();
                }
            }
            doMath();
            result = nums.pop();
        } catch (EmptyStackException e) {
            throw e;
        }
        return result;
    }

    protected void doMath() throws EmptyStackException {
        while (parentheses.isEmpty() || operations.size() > parentheses.peek()) {
            double second = nums.pop();
            double first = nums.pop();
            char operation = operations.pop();
            switch (operation) {
                case '*':
                    nums.push(first * second);
                    break;
                case '/':
                    nums.push(first / second);
                    break;
                case '%':
                    nums.push(first % second);
                    break;
                case '+':
                    nums.push(first + second);
                    break;
                case '-':
                    nums.push(first - second);
            }
        }
    }

    protected void parseEquation(String equation) throws EquationFormatException {
        equation = equation.replaceAll(" ", "");
        for (int i = 0; i < equation.length(); i++) {
            if ((equation.charAt(i) > '9' || equation.charAt(i) < '0') && equation.charAt(i) != '.' && equation.charAt(i) != '*'
                    && equation.charAt(i) != '/' && equation.charAt(i) != '%' && equation.charAt(i) != '+' && equation.charAt(i) != '-'
                    && equation.charAt(i) != '(' && equation.charAt(i) != ')')
                throw new EquationFormatException();
        }
    }

    public double factorial(double d) {
        if (d > 26)
            return -1;
        if (d < 2)
            return d;
        return d * factorial(d - 1);
    }

    public double squareRoot(double d) {
        return Math.sqrt(d);
    }

    public double getPow2(double d) {
        return Math.pow(d, 2);
    }

    private void clearStacks() {
        nums.clear();
        operations.clear();
        parentheses.clear();
    }
}