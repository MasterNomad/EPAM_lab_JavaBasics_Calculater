public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String expression = "5 * (10 - 81 / (5 + 2 ^ 2) * 9) + (8 / (9 - 5) - 11)";
        System.out.print(expression + " = " + calculator.calculate(expression));
    }

}