package calculator;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PositiveParserTest {

    private Parser parser = new Parser();

    @Test
    public void parseStringToPostfixNotationList() {
        String test = "5*(10+81/(5-4^2)*9)+(8/(9-5)-11.5)";
        List<String> expResult = Arrays.asList("5 10 81 5 4 2 ^ - / 9 * + * 8 9 5 - / 11.5 - +".split(" "));
        List<String> result = parser.parseStringToPostfixNotationList(test);
        assertThat(result, containsInRelativeOrder(expResult.toArray()));
    }
}