package education.multithreading.concurrent.task_executors;

import org.apache.tomcat.jni.Error;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo07_Callable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Callable<Integer> task0 = () -> 42;

        Future<Integer> future0 = executorService.submit(task0);

        Callable<Integer> task1 = () -> {
            while (true){
             if (Thread.interrupted()) {
                 throw new InterruptedException();
             }
            }
        };

        Future<Integer> future1 = executorService.submit(task1);


//        Thread.sleep(1000);
//        System.out.println("future0 is done: " + future0.isDone());
//        System.out.println("future1 is done: " + future1.isDone());
//
//        System.out.println("future0 get:");
//        System.out.println(future0.get());
//        System.out.println("future1 get:");
//        System.out.println(future1.get());
//        System.out.println("Finish");

//        List<Future<Integer>> result = executorService.invokeAll(Arrays.asList(task0, task1));
        Integer result = executorService.invokeAny(Arrays.asList(task0, task1));
        System.out.println(result);
        executorService.shutdownNow();
    }
}
