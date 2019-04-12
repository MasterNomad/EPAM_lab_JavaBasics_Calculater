import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public int calculate(String expression) {
        expression = expression.replaceAll("\\s+", "");
        List<String> elements = parseExpression(expression);
        elements = getPostfixNotation(elements);

        return calculatePostfixNotation(elements);
    }

    private int calculatePostfixNotation(List<String> elements) {
        Stack<Integer> stack = new Stack<>();

        for (String element : elements) {
            if (isNumeric(element)) {
                stack.push(Integer.parseInt(element));
            } else {
                int tmpVar = stack.pop();
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
                        stack.push((int) Math.pow(stack.pop(), tmpVar));
                        break;
                }
            }
        }
        int result = stack.pop();
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

    private List<String> parseExpression(String expression) {
        List<String> queue = new LinkedList<>();

        Pattern operatorPattern = Pattern.compile("[\\-+*()^/]");
        Matcher operatorMatcher = operatorPattern.matcher(expression);
        Pattern operandPattern = Pattern.compile("\\d+");

        int i = 0;
        String tmpStr;

        while (operatorMatcher.find()) {
            tmpStr = expression.substring(i, operatorMatcher.start());

            if (operandPattern.matcher(tmpStr).matches()) {
                queue.add(tmpStr);
            }
            queue.add(operatorMatcher.group());
            i = operatorMatcher.end();
        }

        return queue;
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
