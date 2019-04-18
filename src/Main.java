import calculator.Calculator;
import exceptions.CalculatorException;

public class Main {

    public static void main(String[] args) {

        String expression = "5 * (10 + 81 / (5 - 4) * 9) + (8 / (9 - 5) - 11,5)";

        try {
            System.out.println(expression + " = " + new Calculator().calculate(expression));
        } catch (CalculatorException e) {
            System.err.println(e.getMessage());
        }
    }

}