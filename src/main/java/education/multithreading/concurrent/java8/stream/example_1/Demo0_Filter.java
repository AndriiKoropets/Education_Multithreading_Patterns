package education.multithreading.concurrent.java8.stream.example_1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Demo0_Filter {

    private List<Long> list;

    public List<Long> oldSchool() {//13 фільтрів в 1 секунду
        List<Long> l = new ArrayList<>();
        for (Long v : list) {
            if ((v & 0xff) == 0) {
                l.add(v);
            }
        }
        return l;
    }

    public List<Long> orderedSequential() {
        list.stream().filter(x -> (x & 0xff) == 0)//10
                .collect(Collectors.toList());

        list.stream()
                .unordered()
                .filter(x -> (x & 0xff) == 0)
                .collect(Collectors.toList());//10

        list.parallelStream()
                .filter(x -> (x & 0xff) == 0)
                .collect(Collectors.toList());//20

        list.parallelStream()
                .unordered()
                .filter(x -> (x & 0xff) == 0)
                .collect(Collectors.toList());//26
        return null;
    }
}
