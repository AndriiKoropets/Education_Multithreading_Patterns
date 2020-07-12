package education.multithreading.concurrent.monad;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.concurrent.Executors.newCachedThreadPool;

public class FutureInvoke {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = newCachedThreadPool();
        {
//            ExecutorService pool = newCachedThreadPool();
            List<Future<byte[]>> listBytesFuture = pool.invokeAll(asList(
                    () -> readAllBytes(get("/home/andrii/file1.txt")),
                    () -> readAllBytes(get("/home/andrii/file1.txt")),
                    () -> readAllBytes(get("/home/andrii/file1.txt"))
            ));

            System.out.println(listBytesFuture.get(0).isDone());
            System.out.println(listBytesFuture.get(1).isDone());
            System.out.println(listBytesFuture.get(2).isDone());
        }

        {
//            ExecutorService pool = newCachedThreadPool();
            byte[] bytes = pool.invokeAny(asList(
                    () -> readAllBytes(get("/home/andrii/file1.txt")),
                    () -> readAllBytes(get("/home/andrii/file1.txt")),
                    () -> readAllBytes(get("/home/andrii/file1.txt"))

            ));
            System.out.println(Arrays.toString(bytes));
            System.out.println(new String(bytes));
        }


    }
}
