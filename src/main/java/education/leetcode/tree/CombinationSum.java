package education.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    List<List<Integer>> result = new ArrayList<>();
    Integer min;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        findMin(candidates);
        createCombinations(candidates, new ArrayList<>(), target);
        return result;
    }

    private void findMin(int[] candidates) {
        min = candidates[0];
        for (int i = 1; i < candidates.length; i++) {
            if (min > candidates[i]) {
                min = candidates[i];
            }
        }
    }

    private void createCombinations(int[] candidates, List<Integer> combination, int rest) {
        if (rest == 0) {
            combination.sort(Integer::compare);
            if (!result.contains(combination)) {
                result.add(new ArrayList<>(combination));
            }
            return;
        }
        if (rest < min) {
            return;
        }

        for (Integer curNode : candidates) {
            combination.add(curNode);
            createCombinations(candidates, combination, rest - curNode);
            combination.remove(curNode);
        }
    }

    public static void main(String[] args) {
        CombinationSum ref = new CombinationSum();

//        int[] candidates = new int[]{2,3,6,7};
//        int[] candidates = new int[]{2,3,5};
        int[] candidates = new int[]{3,5,7,10,9,4,8};

        ref.combinationSum(candidates, 40);
        System.out.println(ref.min);
        System.out.println(ref.result);
        System.out.println(ref.result.size());
    }


}
