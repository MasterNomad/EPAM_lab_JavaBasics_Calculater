package calculator;

import exceptions.NotEnoughSymbolsException;
import org.junit.Test;

public class NegativeCalculatorTest {

    private Calculator calculator = new Calculator();

    @Test (expected = NotEnoughSymbolsException.class)
    public void textEmptyCalculate() {
        String test = "";
        calculator.calculate(test);
    }

    @Test (expected = NotEnoughSymbolsException.class)
    public void textWrongCalculate() {
        String test = "+()";
        calculator.calculate(test);
    }
}