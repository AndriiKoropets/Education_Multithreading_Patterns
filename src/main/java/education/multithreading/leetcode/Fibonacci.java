package education.multithreading.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    public Long fibonacci(long n) {
        if (n <= 2) return 1L;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public Long fibonacciDynamic(long n, Map<Long, Long> memo) {
        if (memo.containsKey(n)) return memo.get(n);
        if (n <= 2) return 1L;
        memo.put(n, fibonacciDynamic(n - 1, memo) + fibonacciDynamic(n - 2, memo));

        return memo.get(n);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fibonacci(5));
        System.out.println(fibonacci.fibonacci(6));
        System.out.println(fibonacci.fibonacci(7));
        System.out.println(fibonacci.fibonacciDynamic(8, new HashMap<>()));
        System.out.println(fibonacci.fibonacci(10));
        System.out.println(fibonacci.fibonacciDynamic(150L, new HashMap<>()));
    }
}
