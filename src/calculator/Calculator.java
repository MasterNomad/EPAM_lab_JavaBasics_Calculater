package calculator;

import exceptions.NotAllowSymbolException;
import exceptions.WrongBracketsException;
import exceptions.WrongSymbolOrderException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class Calculator {

    public BigDecimal calculate(String expression) throws NotAllowSymbolException, WrongSymbolOrderException, WrongBracketsException {
        String optimizeExpression = optimizeString(expression);
        Validator validator = new Validator();
        validator.checkAllowSymbols(optimizeExpression);
        validator.checkSymbolsOrder(optimizeExpression);
        validator.checkBrackets(optimizeExpression);
        List<String> postfixNotation = new Parser().parseStringToPostfixNotationList(optimizeExpression);
        return calculatePostfixNotation(postfixNotation);
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

        return expression;
    }

}
