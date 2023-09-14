package education.multithreading.fork_join;

import java.util.Random;
import java.util.stream.Stream;

public class App05_sorted_distinct {
    public static void main(String[] args) {
        Random rnd = new Random();
        Stream<Integer> stream = Stream.generate(() -> rnd.nextInt(8));

        stream.sorted().limit(3).forEach(System.out::println);//OutOfMemoryError because of infinite stream
        stream.distinct().limit(3).forEach(System.out::println);
    }
}
