package education.multithreading.concurrent.monad;

import java.util.Optional;

public class OptionalRules {
    public static void main(String[] args) {
        Optional<Integer> optK = Optional.of(42);
        Optional<Integer> optP = optK.map(x -> x + 1);
        System.out.println(optP);
    }
}
