package calculator;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PositiveOperatorsTest {

    @Test
    public void testAddition () {
        BigDecimal a = BigDecimal.valueOf(25);
        BigDecimal b = BigDecimal.valueOf(89);
        BigDecimal expResult = BigDecimal.valueOf(114);
        BigDecimal result = Operators.getEnum("+").apply(a, b);
        assertThat(result, equalTo(expResult));
    }

    @Test
    public void testSubtract () {
        BigDecimal a = BigDecimal.valueOf(83);
        BigDecimal b = BigDecimal.valueOf(17);
        BigDecimal expResult = BigDecimal.valueOf(66);
        BigDecimal result = Operators.getEnum("-").apply(a, b);
        assertThat(result, equalTo(expResult));
    }

    @Test
    public void testMultiply () {
        BigDecimal a = BigDecimal.valueOf(12);
        BigDecimal b = BigDecimal.valueOf(6);
        BigDecimal expResult = BigDecimal.valueOf(72);
        BigDecimal result = Operators.getEnum("*").apply(a, b);
        assertThat(result, equalTo(expResult));
    }

    @Test
    public void testDivide () {
        BigDecimal a = BigDecimal.valueOf(81);
        BigDecimal b = BigDecimal.valueOf(9);
        BigDecimal expResult = BigDecimal.valueOf(9).setScale(5, RoundingMode.HALF_UP);
        BigDecimal result = Operators.getEnum("/").apply(a, b);
        assertThat(result, equalTo(expResult));
    }

    @Test
    public void testPow () {
        BigDecimal a = BigDecimal.valueOf(5);
        BigDecimal b = BigDecimal.valueOf(3);
        BigDecimal expResultGreater = BigDecimal.valueOf(125.0);
        BigDecimal result = Operators.getEnum("^").apply(a, b);
        assertThat(result, equalTo(expResultGreater));
    }

}