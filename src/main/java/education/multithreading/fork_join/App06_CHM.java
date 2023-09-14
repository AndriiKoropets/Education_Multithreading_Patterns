package education.multithreading.fork_join;

import java.util.concurrent.ConcurrentHashMap;

public class App06_CHM {
    public static void main(String[] args) {
        ConcurrentHashMap<Long, Long> map = new ConcurrentHashMap<>();
        int cpuCount = Runtime.getRuntime().availableProcessors();

        Long result0 = map.search(cpuCount, (key, value) -> key.equals(value) ? value : null);

        Long result1 = map.reduceKeys(cpuCount, (key0, key1) -> key0 + key1);
    }
}
