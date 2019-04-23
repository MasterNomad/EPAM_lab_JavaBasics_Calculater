package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Operators {

    PLUS("+") {
        @Override
        BigDecimal apply(BigDecimal x, BigDecimal y) {
            return x.add(y);
        }
    },
    MINUS("-") {
        @Override
        BigDecimal apply(BigDecimal x, BigDecimal y) {
            return x.subtract(y);
        }
    },
    TIMES("*") {
        @Override
        BigDecimal apply(BigDecimal x, BigDecimal y) {
            return x.multiply(y);
        }
    },
    DIVIDE("/") {
        @Override
        BigDecimal apply(BigDecimal x, BigDecimal y) {
            return x.divide(y, 5, RoundingMode.HALF_UP);
        }
    },
    POWER("^") {
        @Override
        BigDecimal apply(BigDecimal x, BigDecimal y) {
            return BigDecimal.valueOf(Math.pow(x.doubleValue(), y.doubleValue()));
        }
    };

    String value;

    Operators(String value) {
        this.value = value;
    }

    public static Operators getEnum(String value) {
        for (Operators operator : values())
            if (operator.getValue().equals(value)) return operator;
        throw new IllegalArgumentException();
    }

    abstract BigDecimal apply(BigDecimal x, BigDecimal y);

    public String getValue() {
        return value;
    }
}
