package education.multithreading.concurrent.java8.stream.example_1;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExampleStreamSources<T> {
    public static void main(String[] args) {

    }

    public void sourceStream() {
        T[] arr = null;
        Stream<T> s = Arrays.stream(arr);

        T v0 = null;
        T v1 = null;
        T v2 = null;

//        Stream<T> ss = Stream.of(v0, v1, v2);

//        Stream<T> sss = Stream.builder()
//                .add(v0).add(v1).add(v2)
//                .build();

        IntStream ssss = IntStream.range(0,1000);


        //generators

        AtomicInteger init = new AtomicInteger(0);
        Stream<Integer> sa = Stream.generate(init::getAndIncrement);

        Stream<Integer> sb = Stream.iterate(0, i -> i+1);
    }
}
