package education.multithreading.concurrent.monad;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.concurrent.Executors.newCachedThreadPool;

public class FutureMonad {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        {
            ExecutorService pool = newCachedThreadPool();

            Future<byte[]> futBytes0 = pool.submit(() -> readAllBytes(get("/home/andrii/docker-compose.yml")));
            Future<byte[]> futBytes1 = pool.submit(() -> readAllBytes(get("/home/andrii/file1.txt")));
            Future<byte[]> futBytes2 = pool.submit(() -> readAllBytes(get("/home/andrii/file.txt")));

            System.out.println(futBytes0.isDone());
            System.out.println(futBytes1.isDone());
            System.out.println(futBytes2.isDone());
            byte[] bytes = futBytes0.get();
            byte[] bytes1 = futBytes1.get();
            System.out.println(futBytes0.isDone());
            System.out.println(futBytes1.isDone());
            System.out.println(Arrays.toString(bytes));
            System.out.println(Arrays.toString(bytes1));
        }

    }
}
