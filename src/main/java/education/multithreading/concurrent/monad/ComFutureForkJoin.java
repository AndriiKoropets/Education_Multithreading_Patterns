package education.multithreading.concurrent.monad;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ComFutureForkJoin {

    public static void main(String[] args) throws InterruptedException, IOException {
        {
            CompletableFuture<String> f0 = supplyAsync(() -> {
                sleep(3000);
                System.out.println(Thread.currentThread().isDaemon());
                return "A";
            });

            CompletableFuture<String> f1 = supplyAsync(() -> {
                sleep(2000);
                System.out.println(Thread.currentThread().isDaemon());
                return "B";
            });

//        f0.acceptEitherAsync(f1, System.out::println);
            f0.thenAcceptBothAsync(f1, (a, b) -> System.out.println(a + "#" + b));

//            System.in.read();

            new Thread(() -> {
                System.out.println("Here");
                System.out.println(Thread.currentThread().isDaemon());
            }).start();
        }

    }

    private static void sleep(long dt) {
        try {
            Thread.sleep(dt);
        } catch (InterruptedException ignore){}
    }
}
