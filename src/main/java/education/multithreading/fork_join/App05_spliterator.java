package education.multithreading.fork_join;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.LongConsumer;
import java.util.stream.StreamSupport;

public class App05_spliterator {
    public static void main(String[] args) {
        //seq : Collection -> Iterator
        //par : Spliterator -> Stream

        LongRange longRange = new LongRange(0, 1000_000);

        long result = StreamSupport
                .stream(longRange, true)
                .parallel()
                .filter(x -> x % 3 != 0)
                .filter(x -> x % 5 != 0)
                .reduce(0L, (x, y) -> x + y);

        System.out.println(result);
    }
}

class LongRange implements Spliterator.OfLong {
    private long from;
    private long to;

    public LongRange(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public OfLong trySplit() {
        if (to - from > 1) {
            return new LongRange(from, from = ((from + to) >>> 1));
        } else {
            return null;
        }
    }

    @Override
    public long estimateSize() {
        return to - from;
    }

    @Override
    public int characteristics() {
        return 0
//                | Spliterator.CONCURRENT
                | Spliterator.DISTINCT
                | Spliterator.IMMUTABLE
                | Spliterator.NONNULL
//                | Spliterator.ORDERED
                | Spliterator.SIZED
                | Spliterator.SORTED
                | Spliterator.SUBSIZED;
    }

    @Override
    public boolean tryAdvance(LongConsumer consumer) {
        if (to > from) { // [10, 20) -> [11, 20)
            consumer.accept(from++);
            return true;
        } else { // [20, 20) -> [20, 20), false
            return false;
        }
    }

    /**
    * If this Spliterator's source is SORTED by a Comparator,
    * returns that Comparator.
    * If the source is SORTED in natural order, returns null.
    * Otherwise, if the source is not SORTED, throws IllegalStateException.
    */
    public Comparator<? super Long> getComparator() {
        return null;
    }
}
