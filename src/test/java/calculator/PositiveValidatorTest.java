package calculator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PositiveValidatorTest {

    private Validator validator = new Validator();

    @Test
    public void testIsNumericFalse() {
        String test = "(8+5/)+5";
        assertFalse(validator.isNumeric(test));
    }

    @Test
    public void testIsNumericTrue() {
        String test = "12563.45";
        assertTrue(validator.isNumeric(test));
    }

    @Test
    public void checkBrackets() {
        String test = "(8+5)+5";
        validator.checkBrackets(test);
    }
    @Test
    public void checkSymbolsOrder() {
        String test = "(8+5)+5";
        validator.checkSymbolsOrder(test);
    }

    @Test
    public void checkAllowSymbols() {
        String test = "(8+5)+5";
        validator.checkAllowSymbols(test);
    }


}