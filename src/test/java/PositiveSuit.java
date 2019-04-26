import calculator.PositiveCalculatorTest;
import calculator.PositiveOperatorsTest;
import calculator.PositiveParserTest;
import calculator.PositiveValidatorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({PositiveCalculatorTest.class, PositiveOperatorsTest.class,
        PositiveParserTest.class, PositiveValidatorTest.class})
public class PositiveSuit {
}
