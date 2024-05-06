package calculator.model;

import calculator.EquationFormatException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AncientRomeModel {
    private int[] nums;
    private String operation;
    private boolean isArabic;
    private static Map<Integer, String> numberMap;

    /* Инициализация карты римских цифр. Можно убрать ограничение на ввод цифр и расширить карту */
    static {
        numberMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        numberMap.put(100, "C");
        numberMap.put(90, "XC");
        numberMap.put(50, "L");
        numberMap.put(40, "XL");
        numberMap.put(10, "X");
        numberMap.put(9, "IX");
        numberMap.put(5, "V");
        numberMap.put(4, "IV");
        numberMap.put(1, "I");
    }

    public String computeToString(String equation) throws EquationFormatException {
        nums = new int[2];
        operation = null;
        parseEquation(equation);

        /* Ограничение на ввод цифр */
        if (nums[0] < 1 || nums[0] > 10 || nums[1] < 1 || nums[1] > 10)
            throw new EquationFormatException();
        if (!isArabic)
            return convertArabicToRome(compute(equation));
        return String.valueOf(compute(equation));
    }

    private int compute(String equation) throws EquationFormatException {
        int result = 0;
        switch (operation) {
            case "+":
                result = nums[0] + nums[1];
                break;
            case "-":
                result = nums[0] - nums[1];
                break;
            case "/":
                result = nums[0] / nums[1];
                break;
            case "*":
                result = nums[0] * nums[1];
        }
        if (!isArabic && result < 1)
            throw new EquationFormatException();
        return result;
    }


    protected void parseEquation(String equation) throws EquationFormatException {
        if (equation == null || equation.isEmpty())
            throw new EquationFormatException();
        equation = equation.replaceAll(" ", "");
        if (isArabicEquation(equation)) {
            isArabic = true;
            initArabicTypes(equation);
        } else if (isRomeEquation(equation)) {
            isArabic = false;
            initRomeTypes(equation);
        } else throw new EquationFormatException();
    }

    private boolean isArabicEquation(String equation) {
        for (int i = 0; i < equation.length(); i++) {
            if ((equation.charAt(i) > '9' || equation.charAt(i) < '0') && equation.charAt(i) != '*'
                    && equation.charAt(i) != '/' && equation.charAt(i) != '+' && equation.charAt(i) != '-')
                return false;
        }
        return true;
    }

    private boolean isRomeEquation(String equation) {
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) != '*' && equation.charAt(i) != '/' && equation.charAt(i) != '+'
                    && equation.charAt(i) != '-' && equation.charAt(i) != 'I'
                    && equation.charAt(i) != 'X' && equation.charAt(i) != 'V')
                return false;
        }
        return true;
    }

    private void initArabicTypes(String equation) throws EquationFormatException {
        try {
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(equation);
            int i = 0;
            while (matcher.find()) {
                nums[i] = Integer.parseInt(matcher.group());
                if (nums[i++] == 0)
                    throw new EquationFormatException();
            }
            operation = matcher.replaceAll("");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new EquationFormatException();
        }
    }

    private void initRomeTypes(String equation) throws EquationFormatException {
        try {
            /* Ограничение на ввод цифр */
            Pattern pattern = Pattern.compile("[IVX]+");
            Matcher matcher = pattern.matcher(equation);
            int i = 0;
            String[] romeNumbers = new String[2];
            while (matcher.find()) {
                romeNumbers[i++] = matcher.group();
            }

            nums[0] = convertRomeToArabic(romeNumbers[0], Integer.MAX_VALUE);
            nums[1] = convertRomeToArabic(romeNumbers[1], Integer.MAX_VALUE);
            operation = matcher.replaceAll("");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new EquationFormatException();
        }
    }

    public int convertRomeToArabic(String s, int previous) throws EquationFormatException {
        int result = 0;
        if (!s.isEmpty()) {
            Iterator<Map.Entry<Integer, String>> iterator = numberMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, String> pair = iterator.next();
                if (s.startsWith(pair.getValue()) && previous >= pair.getKey())
                    return convertRomeToArabic(s.substring(pair.getValue().length()), pair.getKey()) + pair.getKey();
            }
        }
        if (!s.isEmpty())
            throw new EquationFormatException();
        return result;
    }

    public String convertArabicToRome(int number) {
        StringBuilder sb = new StringBuilder();
        if (number > 0) {
            Iterator<Map.Entry<Integer, String>> iterator = numberMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, String> pair = iterator.next();
                if (number >= pair.getKey()) {
                    return sb.append(pair.getValue()).append(convertArabicToRome(number - pair.getKey())).toString();
                }
            }
        }
        return sb.toString();
    }
}