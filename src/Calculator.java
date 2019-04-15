import exceptions.WrongSymbolException;
import exceptions.WrongSymbolOrderException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private final String ALLOWED_OPERATORS = "[\\-+*()^/]";

    public String calculate(String expression) {
        List<String> elements;
        double result;
        try {
            elements = parseExpression(expression.replaceAll("\\s+", ""));
            if (!checkBrackets(elements)) {
                return "Ошибка! Неверное положение или кол-во скобок!";
            }
            elements = optimizeOperators(elements);
            elements = getPostfixNotation(elements);
            result = calculatePostfixNotation(elements);
        } catch (WrongSymbolException e) {
            return "Ошибка! Введён недопустимый символ!";
        } catch (WrongSymbolOrderException e) {
            return "Ошибка! Неверный порядок операторов!\nОшибочный оператор: '" + e.getMessage() + "'";
        }

        return expression + " = " + result;
    }

    private List<String> optimizeOperators(List<String> elements) throws WrongSymbolOrderException {
        Stack<String> stack = new Stack<>();
        boolean operatorFlag = false;
        boolean minusFlag = false;

        for (String element : elements) {
            if (isNumeric(element)) {
                if (minusFlag) {
                    stack.push("-" + element);
                    minusFlag = false;
                } else {
                    stack.push(element);
                }
                operatorFlag = false;
            } else {
                if (element.equals("(")) {
                    stack.push(element);
                    continue;
                }
                if (operatorFlag) {
                    if (element.equals(")")) {
                        throw new WrongSymbolOrderException(stack.peek());
                    }
                    if (element.equals("-") && !minusFlag) {
                        minusFlag = true;
                    } else {
                        throw new WrongSymbolOrderException(element);
                    }
                } else {
                    if (stack.isEmpty()) {
                        if (element.equals("-")) {
                            minusFlag = true;
                            operatorFlag = true;
                            continue;
                        } else {
                            throw new WrongSymbolOrderException(element);
                        }
                    }
                    stack.push(element);
                    if (!element.equals(")")) {
                        operatorFlag = true;
                    }
                }
            }
        }
        return stack;
    }

    private boolean checkBrackets(List<String> elements) {
        Stack<String> bracketStack = new Stack<>();
        for (String element : elements) {
            if (element.equals("(")) {
                bracketStack.push(element);
            }
            if (element.equals(")") && (bracketStack.isEmpty() || !bracketStack.pop().equals("("))) {
                return false;
            }
        }
        return bracketStack.isEmpty();
    }

    private Double calculatePostfixNotation(List<String> elements) throws WrongSymbolException {
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
                    default:
                        throw new WrongSymbolException();
                }
            }
        }
        double result = stack.pop();
        return stack.isEmpty() ? result : stack.pop() + result;
    }

    private List<String> getPostfixNotation(List<String> elements) {
        Stack<String> stack = new Stack<>();
        Stack<String> tmpStack = new Stack<>();

        Map<String, Integer> priority = new HashMap<>();
        priority.put("(", 0);
        priority.put(")", 1);
        priority.put("+", 2);
        priority.put("-", 2);
        priority.put("*", 3);
        priority.put("/", 3);
        priority.put("^", 4);

        for (String element : elements) {
            if (isNumeric(element)) {
                stack.push(element);
            } else {
                if (tmpStack.empty()) {
                    tmpStack.push(element);
                } else {
                    int elementPriority = priority.get(element);
                    while (!tmpStack.empty() && elementPriority != 0 && priority.get(tmpStack.peek()) >= elementPriority) {
                        stack.push(tmpStack.pop());
                    }
                    if (!tmpStack.empty() && priority.get(tmpStack.peek()) == 0 && priority.get(element) == 1) {
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

        Pattern operatorPattern = Pattern.compile(ALLOWED_OPERATORS);
        Matcher operatorMatcher = operatorPattern.matcher(expression);

        int i = 0;
        while (operatorMatcher.find()) {
            String tmpStr = expression.substring(i, operatorMatcher.start());

            if (isNumeric(tmpStr)) {
                stack.push(tmpStr);
            } else if (tmpStr.length() != 0) {
                throw new WrongSymbolException();
            }
            stack.push(operatorMatcher.group());
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

    private boolean isBracket(String str) {
        return str.equals("(") || str.equals(")");
    }
}
