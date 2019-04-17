package calculator;

import java.util.HashMap;
import java.util.Set;

class Separators {

    private static final HashMap<String, Integer> SEPARATORS = new HashMap<>();
    static {
        SEPARATORS.put("(", 0);
        SEPARATORS.put(")", 1);
        SEPARATORS.put("+", 2);
        SEPARATORS.put("-", 2);
        SEPARATORS.put("*", 3);
        SEPARATORS.put("/", 3);
        SEPARATORS.put("^", 4);
    }

    static Set<String> getSeparators() {
        return SEPARATORS.keySet();
    }

    static int getPriority(String separator) {
        return SEPARATORS.get(separator);
    }
}
