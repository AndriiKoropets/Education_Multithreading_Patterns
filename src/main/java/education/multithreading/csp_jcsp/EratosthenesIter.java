package education.multithreading.csp_jcsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;

public class EratosthenesIter implements Iterator<Integer> {
    private List<Integer> primes = new ArrayList<>(asList(2));

    @Override
    public Integer next() {
        int k = primes.get(primes.size() - 1);
        next_probe:
        while (true) {
            k++;
            for (int prime: primes) {
                if (k % prime == 0) {
                    continue next_probe;
                }
            }
            primes.add(k);
            return k;
        }
    }
    @Override
    public boolean hasNext() {
        return true;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        EratosthenesIter eratosthenesIter = new EratosthenesIter();
        System.out.println(eratosthenesIter.next());
        System.out.println(eratosthenesIter.next());
        System.out.println(eratosthenesIter.next());
        System.out.println(eratosthenesIter.next());
        System.out.println(eratosthenesIter.next());
        System.out.println(eratosthenesIter.next());
    }

}
