package education.multithreading.concurrent.monad;

import java.util.Optional;
import static java.util.stream.Stream.of;

public class OptionalExamples {

    public static void main(String[] args) {
        {
            Optional<Integer> sum = of(1, 2, 3).reduce(Integer::sum);
            Optional<Integer> sqr = sum.map(x -> x * x);
            Optional<String> str = sqr.map(x -> "#" + x);
            System.out.println(str);

            if (str.isPresent()) {
                System.out.println(str.get());
            }
            str.ifPresent(System.out::println);
        }
        {
            Optional<Integer> sum = of(1, 2, 3).filter(x -> x > 10).reduce(Integer::sum);
            Optional<Integer> sqr = sum.map(x -> x*x);
            Optional<String> str = sqr.map(x -> "#" + x);
            System.out.println(str);

            //don't do like that
            if (str.isPresent()) {
                System.out.println(str.get());
            }
            //do that!!!
            str.ifPresent(System.out::println);
        }
    }
}
