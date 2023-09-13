package education.multithreading.fork_join;

import java.util.stream.LongStream;

public class App04_stream_1_non_assoc {
    public static void main(String[] args) {
        long result = LongStream.range(0, 1000_000)
                .parallel()
                .filter(x -> x % 3 != 0)
                .filter(x -> x % 5 != 0)
                .reduce(0, (x, y) -> 31 * x + y);
        System.out.println(result);
    }
}
