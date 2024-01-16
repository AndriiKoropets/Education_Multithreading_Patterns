package education.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllPathsFromSourceToTarget {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int end = graph.length - 1;
        List<Integer> foundPath = new ArrayList<>(Collections.singleton(0));
        findPathsStack(graph, 0, end, foundPath);
        return result;
    }

    private void findPathsStack(int[][] graph, int start, int end, List<Integer> foundPath) {
        if (start == end) {
            result.add(new ArrayList<>(foundPath));
            return;
        }

        for (int i : graph[start]) {
            foundPath.add(i);
            findPathsStack(graph, i, end, foundPath);
            foundPath.remove(foundPath.size() - 1);
        }
    }

//    private void findPaths(int[][] graph) {
//        Stack<Integer> stack = new Stack<>();
//        stack.push(0);
//        while (!stack.isEmpty()) {
//            int neighbour = stack.pop();
//            System.out.println(neighbour);
//            for (int i : graph[neighbour]) {
//                stack.push(i);
//            }
//        }
//    }
//
//    private void findPathsStack(int[] graph, int start, int end, List<Integer> foundPath) {
//        if (start == end) {
//            foundPath.add(end);
//            result.add(foundPath);
//            return;
//        }
//
//        for (int i = start; i <= graph.length; i++) {
//            foundPath.add(i);
//            findPathsStack(graph, graph[i], end, foundPath);
//            foundPath.remove(Integer.valueOf(i));
//        }
//    }
//
//
//    private Map<Integer, Integer> toMap(int[][] graph) {
//        Map<Integer, Integer> resMap = new HashMap<>();
//        for (int i = 0; i < graph.length; i++) {
//            for (int j = 0; j < graph[0].length; j++) {
//                if (!resMap.containsKey(i)) {
//
//                }
//            }
//        }
//        return resMap;
//    }

    public static void main(String[] args) {
        AllPathsFromSourceToTarget ref = new AllPathsFromSourceToTarget();
//        int[][] graph = new int[][]{{1,2},{3},{3},{}};
        int[][] graph = new int[][]{{4,3,1},{3,2,4},{3},{4},{}};
        System.out.println(ref.allPathsSourceTarget(graph));

    }
}
