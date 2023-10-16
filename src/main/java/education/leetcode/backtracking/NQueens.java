package education.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    List<List<String>> result = new ArrayList<>();

    public int totalNQueens(int n) {
        boolean[][] trace = new boolean[n][n];
        int[][] queens = new int[n][n];
        findQueensPlacements(0, trace, queens, n);
        return result.size();
    }

//    public List<List<String>> solveNQueens(int n) {
//        boolean[][] trace = new boolean[n][n];
//        int[][] queens = new int[n][n];
//        findQueensPlacements(0, trace, queens, n);
//        return result;
//    }

    private void findQueensPlacements(int row, boolean[][] trace, int[][] queens, int n) {
        int currentNumberOfQueens = numberOfQueens(queens);
        if (currentNumberOfQueens == n) {
            addFoundSolution(queens, n);
            return;
        }

        int freeCells = getFreeCells(trace);
        if (freeCells == 0 || freeCells < n - currentNumberOfQueens) {
            return;
        }

        if (!oneQueenPerRow(queens, trace)) {
            return;
        }

        for (int i = row; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!trace[i][j]) {
                    queens[i][j] = 1;
                    updateBoardState(queens, trace);
                    if (oneQueenPerRow(queens, trace)) {
                        if (i == n - 1) {
                            findQueensPlacements(i, trace, queens, n);
                        } else {
                            findQueensPlacements(i + 1, trace, queens, n);
                        }
                    }
                    queens[i][j] = 0;
                    updateBoardState(queens, trace);
                }
            }
        }
    }

    private boolean oneQueenPerRow(int[][] queens, boolean[][] trace) {
        for (int i = 0; i < queens.length; i++) {
            boolean rowState = true;
            boolean queenPresent = false;
            for (int j = 0; j < queens[0].length; j++) {
                rowState = rowState && trace[i][j];
                if (queens[i][j] == 1) {
                    queenPresent = true;
                }
            }
            if (rowState & !queenPresent) {
                return false;
            }
        }

        return true;
    }

    private void addFoundSolution(int[][] queens, int n) {
        List<String> foundSolution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(queens[i][j] == 1 ? "Q" : ".");
            }
            foundSolution.add(sb.toString());
        }
        if (!result.contains(foundSolution)) {
            result.add(foundSolution);
        }

    }

    private void updateBoardState(int[][] queens, boolean[][] trace) {
        initializeTrace(trace);
        for (int i = 0; i < queens.length; i++) {
            for (int j = 0; j < queens[0].length; j++) {
                if (queens[i][j] == 1) {
                    updateTrace(i, j, trace);
                }
            }
        }
    }

    private void updateTrace(int i, int j, boolean[][] trace) {
        trace[i][j] = true;
        //up
        if (i > 0) {
            for (int k = i; k >= 0; k--) {
                trace[k][j] = true;
            }
        }
        //down
        if (i < trace.length - 1) {
            for (int k = i; k < trace.length; k++) {
                trace[k][j] = true;
            }
        }
        //left
        if (j > 0) {
            for (int k = j; k >= 0; k--) {
                trace[i][k] = true;
            }
        }
        //right
        if (j < trace.length - 1) {
            for (int k = j; k < trace.length; k++) {
                trace[i][k] = true;
            }
        }
        //left-up diagonal
        int a = i;
        int b = j;
        while (a >= 0 && b >= 0) {
            trace[a][b] = true;
            a--;
            b--;
        }
        //right-up diagonal
        int c = i;
        int d = j;
        while (c >= 0 && d < trace.length) {
            trace[c][d] = true;
            c--;
            d++;
        }
        //left-down diagonal
        int e = i;
        int f = j;
        while (e < trace.length && f >= 0) {
            trace[e][f] = true;
            e++;
            f--;
        }
        //right-down diagonal
        int g = i;
        int h = j;
        while (g < trace.length && h < trace.length) {
            trace[g][h] = true;
            g++;
            h++;
        }
    }

    private void initializeTrace(boolean[][] trace) {
        for (int i = 0; i < trace.length; i++) {
            for (int j = 0; j < trace[0].length; j++) {
                trace[i][j] = false;
            }
        }
    }

    private int numberOfQueens(int[][] board) {
        int queens = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    queens++;
                }
            }
        }
        return queens;
    }

    private int getFreeCells(boolean[][] trace) {
        int free = 0;
        for (int i = 0; i < trace.length; i++) {
            for (int j = 0; j < trace[0].length; j++) {
                if (!trace[i][j]) {
                    free++;
                }
            }
        }
        return free;
    }

    public static void main(String[] args) {
        NQueens ref = new NQueens();
        long start = System.currentTimeMillis();
        System.out.println(ref.solveNQueens(9));
        System.out.println(ref.result.size());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
