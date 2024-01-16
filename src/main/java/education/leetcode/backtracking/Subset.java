package education.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subset {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        result.add(Collections.emptyList());
        findSubsets(0, nums, new ArrayList<>());
        return result;
    }

    private void findSubsets(int start, int[] nums, List<Integer> subset) {
        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
            result.add(new ArrayList<>(subset));
            findSubsets(i + 1, nums, subset);
            subset.remove(Integer.valueOf(nums[i]));
        }
    }

    public static void main(String[] args) {
        Subset ref = new Subset();
        System.out.println(ref.subsets(new int[]{1,2,3,4,5,6,7,8,9,10}));
        System.out.println(ref.result.size());
    }
}
