package calculator;

import exceptions.NotAllowSymbolException;
import exceptions.WrongBracketsException;
import exceptions.WrongSymbolOrderException;
import org.junit.Test;

public class NegativeValidatorTest {

    private Validator validator = new Validator();

    @Test(expected = WrongSymbolOrderException.class)
    public void checkSymbolsOrder() {
        String test = "(8+5/)+5";
        validator.checkSymbolsOrder(test);
    }

    @Test(expected = NotAllowSymbolException.class)
    public void checkAllowSymbols() {
        String test = "(ad8+5)+5";
        validator.checkAllowSymbols(test);
    }

    @Test(expected = WrongBracketsException.class)
    public void checkBrackets() {
        String test = ")(8+5)+5";
        validator.checkBrackets(test);
    }
}