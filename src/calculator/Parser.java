package calculator;

import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Parser {

    public List<String> parseStringToPostfixNotationList(String expression) {
        Stack<String> resultStack = new Stack<>();
        Stack<String> tmpStack = new Stack<>();
        Validator validator = new Validator();
        StringTokenizer stringTokenizer = new StringTokenizer(expression, Separators.getSeparatorsString(), true);

        while (stringTokenizer.hasMoreTokens()) {
            String element = stringTokenizer.nextToken();

            if (validator.isNumeric(element)) {
                resultStack.push(element);
            } else {
                if (tmpStack.empty()) {
                    tmpStack.push(element);
                } else {
                    int elementPriority = Separators.getEnum(element).getPriority();
                    while (!tmpStack.empty() && elementPriority != 0 && Separators.getEnum(tmpStack.peek()).getPriority() >= elementPriority) {
                        resultStack.push(tmpStack.pop());
                    }
                    if (!tmpStack.empty() && Separators.getEnum(tmpStack.peek()).getPriority() == 0 && elementPriority == 1) {
                        tmpStack.pop();
                    } else {
                        tmpStack.push(element);
                    }
                }
            }
        }
        return resultStack;
    }

}
