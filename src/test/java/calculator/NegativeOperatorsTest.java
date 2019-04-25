package calculator;

import org.junit.Test;

import java.math.BigDecimal;

public class NegativeOperatorsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        BigDecimal a = BigDecimal.valueOf(1565124632);
        BigDecimal b = BigDecimal.valueOf(0);
        Operators.getEnum("/").apply(a, b);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetEnum() {
        Operators.getEnum("asf");
    }
}