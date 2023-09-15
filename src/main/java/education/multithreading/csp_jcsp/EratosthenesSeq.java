package education.multithreading.csp_jcsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EratosthenesSeq {
    public static void main(String[] args) {
        int N = 1000;

        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, 2, N, true);
        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i; i * j <= N; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i]) {
                result.add(i);
            }
        }
        System.out.println(result);
    }
}
