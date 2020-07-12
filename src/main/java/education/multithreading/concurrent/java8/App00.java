package education.multithreading.concurrent.java8;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class App00 {
    public static void main(String[] args) {
        new Thread(App00::printHello).start();

        new Thread(() -> System.out.println("Hello")).start();

        Arrays.asList("A", "BB", "CCC")
                .parallelStream()
                .map(String::length)
                .filter(k -> k%2 == 0)
                .forEach(System.out::println);

        List<String> list = Arrays.asList("A", "BB", "CCC", null);
        for (String s : list) {
            System.out.println(s);
        }

        for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
            System.out.println(iter.next());
        }
    }

    private static void printHello() {
        System.out.println("Hello");
    }


}
