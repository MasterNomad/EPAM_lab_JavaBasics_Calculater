package calculator;

import exceptions.WrongBracketsExeption;
import exceptions.WrongSymbolException;

import java.util.List;
import java.util.Stack;

public class Validator {

    public void validate(List<String> elements) throws WrongBracketsExeption, WrongSymbolException {
        checkSymbols(elements);
        checkBrackets(elements);
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void checkSymbols(List<String> elements) throws WrongSymbolException {
        for (String element : elements) {
            if (isNotAllow(element)) {
                throw new WrongSymbolException();
            }
        }
    }

    private void checkBrackets(List<String> elements) throws WrongBracketsExeption {
        Stack<String> bracketStack = new Stack<>();

        for (String element : elements) {
            if (element.equals("(")) {
                bracketStack.push(element);
            }
            if (element.equals(")") && (bracketStack.isEmpty() || !bracketStack.pop().equals("("))) {
                throw new WrongBracketsExeption();
            }
        }
        if (!bracketStack.isEmpty()) {
            throw new WrongBracketsExeption();
        }
    }

    private boolean isNotAllow(String str) {
        return !(isNumeric(str) || isSeparator(str));
    }

    private boolean isSeparator(String str) {
        return str.length() <= 1 && Separators.getSeparatorsString().contains(str);
    }
}
