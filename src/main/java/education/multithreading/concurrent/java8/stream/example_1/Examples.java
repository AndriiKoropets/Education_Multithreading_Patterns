package education.multithreading.concurrent.java8.stream.example_1;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Examples {
    public static void main(String[] args) {
        String[] a = new String[]{"a", "b", "c"};
        System.out.println(Arrays.stream(a).collect(Collectors.joining(",")));
//        System.out.println(String.join(",", a));

    }
//не правильно. Не юзати в продакш!!!
    public int getSum(Stream<Integer> s) {
        int sum = 0;
//        s.forEach(i -> sum += i);//compiler error
        return sum;
    }

    public int getSumArray(Stream<Integer> s) {
        int[] sum = new int[1];
        s.forEach(i -> sum[0] += i);
        return sum[0];
    }

    //юзати reduce
    public int sum(Stream<Integer> s) {
        return s.reduce(0, (x,y) -> x + y);
    }

    public void collector() {
        Map<Integer, Integer> map = IntStream.range(0, 100)
                .boxed()
                .collect(Collectors.toConcurrentMap(k -> k%42, v -> v,  (a,b) -> b ));
    }
}
