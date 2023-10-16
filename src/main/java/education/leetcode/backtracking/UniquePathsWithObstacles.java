package education.leetcode.backtracking;

import java.util.HashMap;
import java.util.Map;

public class UniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        Map<String, Integer> memo = new HashMap<>();
        return findPathsWithObstacles(obstacleGrid, 0, 0, memo);
    }

    private int findPathsWithObstacles(int[][] obstacleGrid, int i, int j, Map<String, Integer> memo) {
        String key = i + "," + j;
        if (memo.containsKey(key)) {
            return  memo.get(key);
        }
        if (obstacleGrid[i][j] == 1) {
            memo.put(key, 0);
            return 0;
        }
        if (i == obstacleGrid.length - 1 && j == obstacleGrid[i].length - 1) {
            return 1;
        }
        if (i == obstacleGrid.length - 1) {
            memo.put(key, findPathsWithObstacles(obstacleGrid, i, j + 1, memo));
        } else if (j == obstacleGrid[i].length - 1) {
            memo.put(key, findPathsWithObstacles(obstacleGrid, i + 1, j, memo));
        }else {
            memo.put(key, findPathsWithObstacles(obstacleGrid, i + 1, j, memo)
                    + findPathsWithObstacles(obstacleGrid, i, j + 1, memo));
        }

        return memo.get(key);
    }

    public static void main(String[] args) {
        UniquePathsWithObstacles obj = new UniquePathsWithObstacles();
//        int[][] input = {{0,0,0,0,1,0,0},{0,1,0,0,0,0,0},{0,0,0,0,0,0,0}};
        int[][] input = {{0,0,0,0,0},{0,0,0,0,1},{0,1,0,0,0},{0,0,1,0,0},{0,0,0,0,0}};
        System.out.println(obj.uniquePathsWithObstacles(input));
    }
}
