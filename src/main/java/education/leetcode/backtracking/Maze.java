package education.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {

    List<String> result = new ArrayList<>();

    boolean[][] grid = {
            {true, false, false, false},
            {false, false, false, false},
            {false, false, false, false}
    };

    int[][] trace = new int[grid.length][grid[0].length];

    void pathRet(String path, int row, int column, int step) {
        if (row == grid.length - 1 && column == grid[0].length - 1) {
            result.add(path);
            trace[row][column] = step;
            grid[row][column] = false;

            for (int i = 0; i < trace.length; i++) {
                System.out.println(Arrays.toString(trace[i]));
            }
            System.out.println(path);


            return;
        }

        grid[row][column] = true;
        trace[row][column] = step;

        if (row < grid.length - 1 && !grid[row + 1][column]) {
            pathRet(path + 'D', row + 1, column, step + 1);
        }

        if (row > 0 && !grid[row - 1][column]) {
            pathRet(path + 'U', row - 1, column, step + 1);
        }

        if (column < grid[0].length - 1 && !grid[row][column + 1]) {
            pathRet(path + 'R', row, column + 1, step + 1);
        }

        if (column > 0 && !grid[row][column - 1]) {
            pathRet(path + 'L', row, column - 1, step + 1);
        }

        grid[row][column] = false;
        trace[row][column] = 0;

    }

    public static void main(String[] args) {
        boolean[][] grid = {
                {true, false, false},
                {false, false, false},
                {false, false, false}
        };

        Maze maze = new Maze();
        maze.pathRet("", 0, 0, 1);
        System.out.println(maze.result);
    }
}
