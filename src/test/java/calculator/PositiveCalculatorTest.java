package calculator;

import calculator.Calculator;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PositiveCalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void calculate() {
        String test = "5 * (11 + 81 / 3^2) - 11,5";
        BigDecimal expResult = new BigDecimal("88.50000");
        BigDecimal result = calculator.calculate(test);
        assertThat(result, equalTo(expResult));
    }
}