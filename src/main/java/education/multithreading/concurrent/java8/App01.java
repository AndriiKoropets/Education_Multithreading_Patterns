package education.multithreading.concurrent.java8;

import java.util.stream.Stream;

public class App01 {
    public static void main(String[] args) {
        Stream<Long> stream = Stream.iterate(2L, k -> k+3);
        stream.limit(100).forEach(System.out::println);
    }
}
