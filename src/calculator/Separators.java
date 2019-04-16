package calculator;

public enum Separators {

    OPEN_BRACKET(0, "(") {},
    CLOSE_BRACKET(1, ")") {},
    PLUS(2, "+") {},
    MINUS(2, "-") {},
    TIMES(3, "*") {},
    DIVIDE(3, "/") {},
    POWER(4, "^") {};

    int priority;
    String value;

    Separators(int priority, String value) {
        this.priority = priority;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getPriority() {
        return priority;
    }


    public static Separators getEnum(String value) {
        for (Separators separator : values())
            if (separator.getValue().equals(value)) return separator;
        throw new IllegalArgumentException();
    }

    public static String getSeparatorsString() {
        StringBuilder result = new StringBuilder();

        for (Separators separator : Separators.values() ){
            result.append(separator.getValue());
        }

        return result.toString();
    }

}
