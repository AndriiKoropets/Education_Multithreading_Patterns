package education.multithreading.concurrent.actor_akka.map_reduce;

public interface Reducer<T> {
    T reduce(T left, T right);
}
