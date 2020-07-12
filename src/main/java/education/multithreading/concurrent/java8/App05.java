package education.multithreading.concurrent.java8;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class App05 {

    public static void main(String[] args) {


//        Supplier<Double> s = Math::random;
//        Consumer<Double> c;
//        Function<String, Double> function = Double::valueOf;
//        Predicate<Double> predicate = arg -> arg > 1;

        //filter: changes the count but does not change the elem
        //map: changes the elem but does not change the count
        //flatmap


        Function<String, Stream<String >> f = s -> Arrays.asList(s.split(" ")).stream();

        Arrays.asList("a", "b bb", "c cc ccc")
            .stream()
                .flatMap(f)
                .forEach(System.out::println);


    }
}
