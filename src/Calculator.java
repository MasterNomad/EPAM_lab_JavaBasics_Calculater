import exceptions.WrongSymbolException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public String calculate(String expression) {
        List<String> elements;
        try {
            elements = parseExpression(expression.replaceAll("\\s+", ""));
            elements = getPostfixNotation(elements);
        } catch (WrongSymbolException e) {
            return "Ошибка! Введён недопустимый символ!";
        }

        return expression + " = " + calculatePostfixNotation(elements).toString();
    }

    private Double calculatePostfixNotation(List<String> elements) {
        Stack<Double> stack = new Stack<>();

        for (String element : elements) {
            if (isNumeric(element)) {
                stack.push(Double.parseDouble(element));
            } else {
                double tmpVar = stack.pop();
                switch (element) {
                    case "+":
                        stack.push(stack.pop() + tmpVar);
                        break;
                    case "-":
                        stack.push(stack.pop() - tmpVar);
                        break;
                    case "*":
                        stack.push(stack.pop() * tmpVar);
                        break;
                    case "/":
                        stack.push(stack.pop() / tmpVar);
                        break;
                    case "^":
                        stack.push(Math.pow(stack.pop(), tmpVar));
                        break;
                }
            }
        }
        double result = stack.pop();
        return stack.isEmpty() ? result : stack.pop() + result;
    }

    private List<String> getPostfixNotation(List<String> elements) {
        Stack<String> stack = new Stack<>();
        Stack<String> tmpStack = new Stack<>();

        Map<Character, Integer> priority = new HashMap<>();
        priority.put('(', 0);
        priority.put(')', 1);
        priority.put('+', 2);
        priority.put('-', 2);
        priority.put('*', 3);
        priority.put('/', 3);
        priority.put('^', 4);

        for (String element : elements) {
            if (isNumeric(element)) {
                stack.push(element);
            } else {
                if (tmpStack.empty()) {
                    tmpStack.push(element);
                } else {
                    int elementPriority = priority.get(element.charAt(0));
                    while (!tmpStack.empty() && elementPriority != 0 && priority.get(tmpStack.peek().charAt(0)) >= elementPriority) {
                        stack.push(tmpStack.pop());
                    }
                    if (!tmpStack.empty() && priority.get(tmpStack.peek().charAt(0)) == 0 && priority.get(element.charAt(0)) == 1) {
                        tmpStack.pop();
                    } else {
                        tmpStack.push(element);
                    }
                }
            }
        }
        return stack;
    }

    private List<String> parseExpression(String expression) throws WrongSymbolException {
        Stack<String> stack = new Stack<>();

        Pattern operatorPattern = Pattern.compile("[\\-+*()^/]");
        Matcher operatorMatcher = operatorPattern.matcher(expression);

        int i = 0;
        String tmpStr;

        while (operatorMatcher.find()) {
            tmpStr = expression.substring(i, operatorMatcher.start());

            if (isNumeric(tmpStr)) {
                stack.push(tmpStr);
            } else if (tmpStr.length() == 0 || operatorPattern.matcher(tmpStr).matches()) {
                stack.push(operatorMatcher.group());
            } else {
                throw new WrongSymbolException();
            }

            i = operatorMatcher.end();
        }

        return stack;
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
