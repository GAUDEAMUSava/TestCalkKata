import java.util.Scanner;
import java.util.HashMap;

public class Calc {
    public static void main(String[] args) {

        int result = 0;

        HashMap<String, Integer> roman = new HashMap<>();
        roman.put("I", 1);
        roman.put("II", 2);
        roman.put("III", 3);
        roman.put("IV", 4);
        roman.put("V", 5);
        roman.put("VI", 6);
        roman.put("VII", 7);
        roman.put("VIII", 8);
        roman.put("IX", 9);
        roman.put("X", 10);

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String expression = sc.nextLine();
        String[] terms = expression.split(" ");

        if (terms.length != 3) {
            throw new IllegalArgumentException("Введите корректное выражение: 'x + y', 'x - y', 'x * y', 'x / y'.");
        }

        String number1 = terms[0];
        String operator = terms[1];
        String number2 = terms[2];



        boolean isRoman = number1.matches("[IVX]+") && number2.matches("[IVX]+");
        boolean isArabic = number1.matches("\\d+") && number2.matches("\\d+");

        if (!(isRoman || isArabic)) {
            throw new IllegalArgumentException("Калькулятор должен работать только с арабскими или римскими цифрами одновременно.");
        }

        if (isArabic) {
            int num1 = Integer.parseInt(number1);
            int num2 = Integer.parseInt(number2);

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        throw new IllegalArgumentException("Делить на 0 нельзя.");
                    }
                    result = num1 / num2;
                    break;
                default:
                    throw new IllegalArgumentException("Некорректный оператор: " + operator);
            }

            System.out.println(result);
        } else { // Римские числа
            int num1;
            int num2;
            if (roman.containsKey(number1)) {
                num1 = roman.get(number1);
            } else {
                throw new IllegalArgumentException("Калькулятор принимает римские числа только от I до X включительно.");
            }

            if (roman.containsKey(number2)) {
                num2 = roman.get(number2);
            } else {
                throw new IllegalArgumentException("Калькулятор принимает римские числа только от I до X включительно.");
            }

            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                throw new IllegalArgumentException("Калькулятор принимает римские числа только от I до X включительно.");
            }

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                default:
                    throw new IllegalArgumentException("Некорректный оператор: " + operator);
            }

            if (result <= 0 && !(number1.equals("I") && number2.equals("I"))) {
                throw new IllegalArgumentException("В римской системе счисления нет отрицательных чисел или нуля.");
            }

            // Преобразование результата в римское число
            StringBuilder romanResult = new StringBuilder();
            while (result >= 100) {
                romanResult.append("C");
                result -= 100;
            }
            while (result >= 90) {
                romanResult.append("XC");
                result -= 90;
            }
            while (result >= 50) {
                romanResult.append("L");
                result -= 50;
            }
            while (result >= 40) {
                romanResult.append("XL");
                result -= 40;
            }
            while (result >= 10) {
                romanResult.append("X");
                result -= 10;
            }
            while (result >= 9) {
                romanResult.append("IX");
                result -= 9;
            }
            while (result >= 5) {
                romanResult.append("V");
                result -= 5;
            }
            while (result >= 4) {
                romanResult.append("IV");
                result -= 4;
            }
            while (result >= 1) {
                romanResult.append("I");
                result -= 1;
            }

            System.out.println(romanResult);
        }
    }
}
