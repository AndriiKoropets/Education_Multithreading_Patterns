package education.multithreading.concurrent.monad;

import java.util.Optional;
import static java.util.stream.Stream.of;

/**
 * Редукція не на моноїді, а виключно на асоціативній операції призводить до
    монади Optional / Maybe
 */
public class MonoidAndMonad {
    public static void main(String[] args) {

        //Редукція на моноїді. Асоціативна операція + нейтральний елемент
        Integer sum0 = of(1, 2, 3).reduce(0, (x,y) -> x + y);
        System.out.println(sum0);

        //Монада  Optional / Just
        // Редукція на асоціативній операції
        Optional<Integer> sum1 = of(1, 2, 3).reduce((x,y) -> x + y);
        System.out.println(sum1);

        // Монада Optional/Nothing
        // Редукція на асоціативній операції
        Optional<Integer> sum2 = of(1, 2, 3).filter(x -> x > 10).reduce((x,y) -> x + y);
        System.out.println(sum2);
    }
}
