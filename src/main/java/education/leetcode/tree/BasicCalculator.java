package education.leetcode.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static java.util.Arrays.asList;

public class BasicCalculator {
    public int calculate(String s) {
        s = s.replace(" ", "");
        Set<Character> numbers = new HashSet<>(asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'));
        Stack<Integer> stack = new Stack<>();
        Character multiply = null;
        boolean minus = false;
        char[] array = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char curChar = array[i];
            if (curChar == '-') {
                minus = true;
            } else if (numbers.contains(curChar)) {
                int curNumber = 0;
                while ((i < array.length) && numbers.contains(array[i])) {
                    curNumber = curNumber * 10 + Integer.parseInt(String.valueOf(array[i]));
                    i++;
                }
                i--;
                if (minus) {
                    stack.push(-curNumber);
                    minus = false;
                }else if (multiply == null) {
                    stack.push(curNumber);
                } else {
                    if (multiply == '*') {
                        stack.push(stack.pop() * curNumber);
                    } else {
                        stack.push(stack.pop() / curNumber);
                    }
                    multiply = null;
                }
            } else if (curChar == '*' || curChar == '/') {
                if (curChar == '*') {
                    multiply = '*';
                } else {
                    multiply = '/';
                }
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }


    public int calculate2(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] array = s.toCharArray();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < array.length; i++) {
            char curDig = array[i];
            if (Character.isDigit(curDig)) {
                num = num * 10 + curDig - '0';
            }
            if (!Character.isDigit(curDig) && curDig != ' ' || i == array.length - 1) {
                switch (sign) {
                    case '+' -> stack.push(num);
                    case '-' -> stack.push(-num);
                    case '*' -> stack.push(stack.pop() * num);
                    case '/' -> stack.push(stack.pop() / num);
                }
                sign = curDig;
                num = 0;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        BasicCalculator ref = new BasicCalculator();
        System.out.println(ref.calculate2(" 3+5 / 2  - 18 * 20 - 10005 + 12*12*44*15/3*254"));
//        System.out.println(ref.calculate(" 3+5 / 2  - 18 * 20 - 10005 + 12*12*44*15/3*254"));
        int a = '3' - '0';

        System.out.println();
    }
}
