package calculator;

import org.junit.Test;

public class NegativeParserTest {

    private Parser parser = new Parser();

    @Test(expected = NullPointerException.class)
    public void parseStringToPostfixNotationList() {
        String test = "5 * ( 10 + 81 /(5-4^2)*9)+(8/(9-5)-11.5)";
        parser.parseStringToPostfixNotationList(test);
    }
}