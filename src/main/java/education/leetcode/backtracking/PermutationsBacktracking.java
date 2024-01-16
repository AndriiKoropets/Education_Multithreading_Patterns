package education.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PermutationsBacktracking {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        getPermuted(nums, new ArrayList<>());
        return result;
    }

    private void getPermuted(int[] nums, List<Integer> cur) {
        if (cur.size() == nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }

        for (Integer curNumb : nums) {
            if (!cur.contains(curNumb)) {
                cur.add(curNumb);
                getPermuted(nums, cur);
                cur.remove(curNumb);
            }
        }
    }

    public static void main(String[] args) {
        PermutationsBacktracking permutation = new PermutationsBacktracking();
        System.out.println(permutation.permute(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(permutation.result.size());
    }
}
