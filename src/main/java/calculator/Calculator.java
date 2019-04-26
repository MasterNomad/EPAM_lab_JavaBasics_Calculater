package calculator;


import exceptions.NotAllowSymbolException;
import exceptions.NotEnoughSymbolsException;
import exceptions.WrongBracketsException;
import exceptions.WrongSymbolOrderException;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class Calculator {

    public BigDecimal calculate(String expression) throws NotAllowSymbolException, WrongSymbolOrderException, WrongBracketsException {
        String optimizedExpression = optimizeString(expression);
        Validator validator = new Validator();
        validator.checkAllowSymbols(optimizedExpression);
        validator.checkSymbolsOrder(optimizedExpression);
        validator.checkBrackets(optimizedExpression);
        List<String> postfixNotation = new Parser().parseStringToPostfixNotationList(optimizedExpression);
        BigDecimal result;
        try {
            result = calculatePostfixNotation(postfixNotation);
        } catch (EmptyStackException e) {
            throw new NotEnoughSymbolsException("Ошибка! Введеного выражения недостаточно для вычисления");
        }
        return result;
    }

    private BigDecimal calculatePostfixNotation(List<String> elements) {
        Stack<BigDecimal> resultStack = new Stack<>();
        Validator validator = new Validator();

        for (String element : elements) {
            if (validator.isNumeric(element)) {
                resultStack.push(new BigDecimal(element));
            } else {
                BigDecimal tmpVar = resultStack.pop();
                resultStack.push(Operators.getEnum(element).apply(resultStack.pop(), tmpVar));
            }
        }

        return resultStack.pop();
    }

    private String optimizeString(String expression) {
        expression = expression.replaceAll("\\s+", "");
        expression = expression.replaceAll(",", ".");
        expression = expression.replaceAll("^\\+", "0+");
        expression = expression.replaceAll("^-", "0-");
        expression = expression.replaceAll("\\(\\+", "(0+");
        expression = expression.replaceAll("\\(-", "(0-");

        return expression;
    }

}
