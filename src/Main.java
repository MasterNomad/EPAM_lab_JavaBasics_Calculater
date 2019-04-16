import calculator.Calculator;

public class Main {

    public static void main(String[] args) {
//        Calculator2 calculator2 = new Calculator2();
//        String expression = "5 * (10 + 81 / (5 + 2) * 9) + (8 / (9 - 5) - 11.5)";
//        System.out.print(calculator2.calculate(expression));
        System.out.println(new Calculator().calculate("5 * (10 + 81 / (5 + 4) * 9) + (8 / (9 - 5) - 11.5)"));
    }

}