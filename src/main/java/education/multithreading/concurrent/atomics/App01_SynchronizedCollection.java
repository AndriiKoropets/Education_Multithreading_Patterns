package education.multithreading.concurrent.atomics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class App01_SynchronizedCollection {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        Collections.synchronizedCollection(new ArrayList<>());
    }
}
