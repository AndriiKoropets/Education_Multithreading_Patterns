package education.multithreading.concurrent.java8;

import java.util.Optional;
import java.util.stream.Stream;

public class App07 {

    public static void main(String[] args) {
        //monada
        Optional<Integer> sum = Stream.iterate(1, k -> k + 1)
                .parallel()
                .limit(0)
                .reduce((x, y) -> x - y);
        System.out.println(sum);
    }
}
