package Calculator;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        Conv conv = new Conv();
        String[] actions = {"+", "-", "*", "/"};  //обычные знаки действия
        String[] regexActions = {"\\+", "-", "\\*", "/"};  //экранированные знаки действия. для деления выражения на две части

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите арифметическое выражение: ");
        String exp = scan.nextLine();


        //определяем введённое действие
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }

        }


        //если введено НЕ арифметическое действие, завершить программу
        if (actionIndex == -1) {
            System.out.println("Некорректное выражение");
            return;
        }
        //10  строка //сплит делит строчку
        String[] data = exp.split(regexActions[actionIndex]);

        //определяем оба ли числа римские (арабские)
        if (conv.isRoman(data[0]) == conv.isRoman(data[1])) {
            int a, b;

            //определяем римские числа
            boolean isRoman = conv.isRoman(data[0]);
            if (isRoman) {
                //если римские перевести в арабские
                a = conv.RomanToInt(data[0]);
                b = conv.RomanToInt(data[1]);

                if (a == -1 || b == -1) {
                    System.out.println("Римское число не может быть отрицательным");
                    return;
                }
            } else {
                //конвертируем арабские числа из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }


            // арифметические действия
            int result;
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b == 0) {
                        System.out.println("Деление на ноль невозможно");
                        return;
                    }


                default:
                    result = a / b;
                    break;

            }
            //если римские, то результат в римском числе
            if (isRoman) {
                System.out.println(conv.intToRoman(result));
            } else {
                //если числа арабские, реультат в арабском числе
                System.out.println(result);
            }


            } else {
            System.out.println("Числа должны быть в одном формате");
        }

    }

}


