package education.multithreading.fork_join;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class App03_stream_0 {
    public static void main(String[] args) {
        //good
        {
            long result = LongStream.range(0, 1000_000)
                    .parallel()
                    .filter(x -> x % 3 != 0)
                    .filter(x -> x % 5 != 0)
//                    .filter(x -> (x % 3 != 0) & (x % 5 != 0))
                    .sum();
            System.out.println(result);
        }
        //bad: low speed generation + boxing/unboxing
        {
            long result = Stream.iterate(0, k -> k + 1)
                    .parallel()
                    .filter(x -> x % 3 != 0)
                    .filter(x -> x % 5 != 0)
                    .reduce(0, (x, y) -> x + y);
            System.out.println(result);
        }
    }
}
