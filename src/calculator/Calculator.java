package calculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class Calculator {

    public String calculate(String expression) {

        expression = expression.replaceAll("\\s+", "");
        expression = expression.replaceAll(",", ".");

        List<String> postfixNotation = new Parser().parseStringToPostfixNotationList(expression);
        return calculatePostfixNotation(postfixNotation).toString();
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
        BigDecimal result = resultStack.pop();
        return resultStack.isEmpty() ? result : result.add(resultStack.pop());
    }

}
