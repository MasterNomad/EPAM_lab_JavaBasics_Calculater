package calculator;


import exceptions.NotAllowSymbolException;
import exceptions.WrongBracketsException;
import exceptions.WrongSymbolOrderException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void checkSymbolsOrder(String expression) throws WrongSymbolOrderException {
        Matcher matcher = Pattern.compile("[.^/*\\-+]{2,}|[.^/*\\-+]\\)+|\\(+[.^/*\\-+]").matcher(expression);
        if (matcher.find()) {
            throw new WrongSymbolOrderException("Ошибка! Введён запрещённый порядок символов: " + matcher.group());
        }
    }

    public void checkAllowSymbols(String expression) throws NotAllowSymbolException {
        Matcher matcher = Pattern.compile("[^." + Separators.getRegex() + "+0-9]").matcher(expression);
        if (matcher.find()) {
            throw new NotAllowSymbolException("Ошибка! Введён запрещённый символ: " + matcher.group());
        }
    }

    public void checkBrackets(String expression) throws WrongBracketsException {
        int bracketCounter = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                bracketCounter++;
            }
            if (expression.charAt(i) == ')') {
                bracketCounter--;
            }
        }
        if (bracketCounter != 0) {
            throw new WrongBracketsException("Ошибка! Неверное кол-во или положение скобок!");
        }
    }
}