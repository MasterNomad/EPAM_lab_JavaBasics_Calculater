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
        String test = "5 * (10 + 81 / (5 - 4^2) * 9) + (8 / (9 - 5) - 11,5)";
        BigDecimal expResult = new BigDecimal("-290.86380");
        BigDecimal result = calculator.calculate(test);
        assertThat(result, equalTo(expResult));
    }
}