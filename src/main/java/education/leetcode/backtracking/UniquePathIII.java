package education.leetcode.backtracking;

public class UniquePathIII {

    int longestPaths;
    int result = 0;
    int finishRow;
    int finishColumn;

    public int uniquePathsIII(int[][] grid) {
        int startRow = 0;
        int startColumn = 0;
        int obstacles = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    startRow = i;
                    startColumn = j;
                }
                if (grid[i][j] == 2) {
                    finishRow = i;
                    finishColumn = j;
                }
                if (grid[i][j] == -1) {
                    obstacles++;
                }
            }
        }

        boolean[][] trace = new boolean[grid.length][grid[0].length];
        longestPaths = grid.length * grid[0].length - obstacles;
        findPath(startRow, startColumn, grid, trace, 1);
        return result;
    }

    private void findPath(int curtRow, int curColumn, int[][] grid, boolean[][] trace, int step) {
        if (curtRow == finishRow && curColumn == finishColumn) {
            if (step == longestPaths) {
                result++;
            }
            return;
        }

        trace[curtRow][curColumn] = true;
        //down
        if (curtRow < grid.length - 1 && !trace[curtRow + 1][curColumn] && grid[curtRow + 1][curColumn] != -1) {
            findPath(curtRow + 1, curColumn, grid, trace, step + 1);
        }
        //up
        if (curtRow > 0 && !trace[curtRow - 1][curColumn] && grid[curtRow - 1][curColumn] != -1) {
            findPath(curtRow - 1, curColumn, grid, trace, step + 1);
        }
        //right
        if (curColumn < grid[0].length - 1 && !trace[curtRow][curColumn + 1] && grid[curtRow][curColumn +1] != -1) {
            findPath(curtRow, curColumn + 1, grid, trace, step + 1);
        }
        //left
        if (curColumn > 0 && !trace[curtRow][curColumn - 1] && grid[curtRow][curColumn - 1] != -1) {
            findPath(curtRow, curColumn - 1, grid, trace, step + 1);
        }

        trace[curtRow][curColumn] = false;
    }

    public static void main(String[] args) {
//        int[][] grid = {
//                {1,0,0,0},
//                {0,0,0,0},
//                {0,0,2,-1}
//        };

//        int[][] grid = {
//                {1,0,0,0},
//                {0,0,0,0},
//                {0,0,0,2}
//        };

        int[][] grid = {
                {0,1},
                {2,0}
        };
        UniquePathIII ref = new UniquePathIII();
        System.out.println(ref.uniquePathsIII(grid));
    }
}
