package education.multithreading.concurrent.monad;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CompletableFutureExample {

    public static void main(String[] args) throws IOException {
        CompletableFuture<String> f0 = supplyAsync(
                () -> {for (long k = 0; k < Long.MAX_VALUE - 1; k++); return "42";}
//                () -> {for (long k = 0; k < 10 - 1; k++); return "42";}
        );
        CompletableFuture<Integer> f1 = f0.thenApply(Integer::valueOf);
        CompletableFuture<Double> f2 = f1.thenApply(x -> Math.PI * x * x);
        f2.thenAccept(System.out::println);

        System.out.println("end");

        WeakReference<Integer> ref = new WeakReference<>(42);
        System.out.println(ref.get());

        supplyAsync(() -> "42")
                .thenApply(Integer::valueOf)
                .thenApply(x -> Math.PI * x * x)
                .thenAccept(System.out::println);

        System.in.read();


    }
}
