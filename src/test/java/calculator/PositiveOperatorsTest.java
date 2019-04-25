package calculator;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PositiveOperatorsTest {

    @Test
    public void testAddition () {
        BigDecimal a = BigDecimal.valueOf(1565124632);
        BigDecimal b = BigDecimal.valueOf(561225462);
        BigDecimal expResult = BigDecimal.valueOf(2126350094);
        BigDecimal result = Operators.getEnum("+").apply(a, b);
        assertThat(result, equalTo(expResult));
    }

    @Test
    public void testSubtract () {
        BigDecimal a = BigDecimal.valueOf(1565124632);
        BigDecimal b = BigDecimal.valueOf(561225462);
        BigDecimal expResult = BigDecimal.valueOf(1003899170);
        BigDecimal result = Operators.getEnum("-").apply(a, b);
        assertThat(result, equalTo(expResult));
    }

    @Test
    public void testMultiply () {
        BigDecimal a = BigDecimal.valueOf(1565124632);
        BigDecimal b = BigDecimal.valueOf(561225462);
        BigDecimal expResult = BigDecimal.valueOf(878387794681779984L);
        BigDecimal result = Operators.getEnum("*").apply(a, b);
        assertThat(result, equalTo(expResult));
    }

    @Test
    public void testDivide () {
        BigDecimal a = BigDecimal.valueOf(1565124632);
        BigDecimal b = BigDecimal.valueOf(561225462);
        BigDecimal expResult = BigDecimal.valueOf(2.7887626951608265).setScale(5, RoundingMode.HALF_UP);
        BigDecimal result = Operators.getEnum("/").apply(a, b);
        assertThat(result, equalTo(expResult));
    }

    @Test
    public void testPow () {
        BigDecimal a = BigDecimal.valueOf(15651);
        BigDecimal b = BigDecimal.valueOf(65);
        BigDecimal expResultGreater = new BigDecimal("4.418104091534241E+272");
        BigDecimal result = Operators.getEnum("^").apply(a, b);
        assertThat(result, equalTo(expResultGreater));
    }

}