package education.multithreading.concurrent.java8;

import java.util.Random;
import java.util.stream.Stream;

public class App06 {

    public static void main(String[] args) {
//        Stream<Double > s = Stream.generate(Math::random);
//        s.forEach(System.out::println);
//        System.out.println(s);


        //monoid
        //1) асоціативні операції
        //2) потребує нейтральний елемент
        int s = Stream
                .iterate(1, k -> k + 1)
                .parallel()
                .limit(1000)
                .reduce(0, (x, y) -> x - y);
        System.out.println(s);

    }
}
