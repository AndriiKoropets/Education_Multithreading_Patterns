package education.multithreading.concurrent.java8;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App03 {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        Stream<Integer> stream0 = set.stream();
        Stream<String> stream1 = stream0.map(k -> "~" + k);

        List<String> list = stream1.collect(Collectors.toList());

        System.out.println(list);


        //this iterator is like a stream
        Iterator<Long> iter = new Iterator<Long>() {
            private long last = 0;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Long next() {
                return last++;
            }
        };
    }
}

@FunctionalInterface
interface I {
    void f();
    default void g() {}
}
