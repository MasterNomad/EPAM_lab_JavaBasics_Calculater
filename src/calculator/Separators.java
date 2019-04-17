package calculator;

import java.util.HashMap;
import java.util.Set;

class Separators {

    private HashMap<String, Integer> separators = new HashMap<>();

    Separators() {
        separators.put("(", 0);
        separators.put(")", 1);
        separators.put("+", 2);
        separators.put("-", 2);
        separators.put("*", 3);
        separators.put("/", 3);
        separators.put("^", 4);
    }

    Set<String> getSeparators() {
        return separators.keySet();
    }

    int getPriority(String separator) {
        return separators.get(separator);
    }
}
