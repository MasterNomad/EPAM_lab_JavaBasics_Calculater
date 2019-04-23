package calculator;

import java.util.HashMap;

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

    static int getPriority(String separator) {
        return SEPARATORS.get(separator);
    }

    static String getRegex() {
        StringBuilder regex = new StringBuilder();
        for (String separator : SEPARATORS.keySet()) {
            if (separator.equals("-")) {
                regex.append("\\");
            }
            regex.append(separator);

        }
        return regex.toString();
    }
}
