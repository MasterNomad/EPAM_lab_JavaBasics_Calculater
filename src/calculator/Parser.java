package calculator;

import java.util.List;
import java.util.Stack;

class Parser {

    private Separators separators = new Separators();

    List<String> parseStringToPostfixNotationList(String expression) {
        Stack<String> resultStack = new Stack<>();
        Stack<String> tmpStack = new Stack<>();
        Validator validator = new Validator();

        String[] elements = expression.split(getRegex());

        for (String element : elements) {
            if (validator.isNumeric(element)) {
                resultStack.push(element);
            } else {
                if (tmpStack.empty()) {
                    tmpStack.push(element);
                } else {
                    int elementPriority = separators.getPriority(element);
                    int stackElementPriority = separators.getPriority(tmpStack.peek());

                    while (elementPriority != 0 && stackElementPriority >= elementPriority) {
                        resultStack.push(tmpStack.pop());
                        if (!tmpStack.empty()) {
                            stackElementPriority = separators.getPriority(tmpStack.peek());
                        } else {
                            break;
                        }
                    }
                    if (!tmpStack.empty() && separators.getPriority(tmpStack.peek()) == 0 && elementPriority == 1) {
                        tmpStack.pop();
                    } else {
                        tmpStack.push(element);
                    }
                }
            }
        }
        while (!tmpStack.isEmpty()) {
            resultStack.push(tmpStack.pop());
        }

        return resultStack;
    }

    private String getRegex() {
        StringBuilder regex = new StringBuilder();
        for (String separator : separators.getSeparators()) {
            if (separator.equals("-")) {
                regex.append("\\");
            }
            regex.append(separator);
        }

        return "(?<=[" + regex.toString() + "])|(?=[" + regex.toString() + "])";
    }
}
