package education.multithreading.concurrent.actor_akka.map_reduce;

public interface Mapper<T, R> {
    R map(T arg);
}
