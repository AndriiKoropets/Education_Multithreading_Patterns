package education.leetcode.backtracking;

import java.util.HashMap;
import java.util.Map;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        Map<String, Integer> paths = new HashMap<>();
        return findPath(m, n, paths);
    }

    private int findPath(int m, int n, Map<String, Integer> memo) {
        final String key = m + "," + n;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (m == 1 || n == 1) {
            memo.put(key, 1);
            return 1;
        }
        memo.put(key, findPath(m - 1, n, memo) + findPath(m, n - 1, memo));

        return memo.get(key);
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(3, 7));
    }


}
