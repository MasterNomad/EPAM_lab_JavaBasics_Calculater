import calculator.NegativeCalculatorTest;
import calculator.NegativeOperatorsTest;
import calculator.NegativeParserTest;
import calculator.NegativeValidatorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({NegativeOperatorsTest.class, NegativeParserTest.class,
        NegativeValidatorTest.class, NegativeCalculatorTest.class})
public class NegativeSuit {
}
