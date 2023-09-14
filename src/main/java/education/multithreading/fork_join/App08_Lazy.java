package education.multithreading.fork_join;

import java.util.stream.Stream;

public class App08_Lazy {
    public static void main(String[] args) {
        Stream<Integer> strem0 = Stream.generate(() -> {
            System.out.println("get()");
            return 0;
        });

        Stream<Integer> stream1 = strem0.filter(x -> x != 42);
        Stream<String> stream2 = stream1.map(x -> "" + x);
        Stream<String> stream3 = stream2.limit(3);

        stream3.forEach(System.out::println);

    }
}
