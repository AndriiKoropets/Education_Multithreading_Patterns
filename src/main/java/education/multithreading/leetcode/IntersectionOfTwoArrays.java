package education.multithreading.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> met = new HashSet<>(Arrays.stream(nums1).boxed().toList());
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            if (met.contains(nums2[i])) {
                result.add(nums2[i]);
            }
        }

        List<Integer> temp = result.stream().toList();

        int[] output = new int[result.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = temp.get(i);
        }


        return output;
    }

    public static void main(String[] args) {
        Queue<Integer> prior = new PriorityQueue<>();
        prior.add(9);
//        prior.add(9);
        prior.add(12);
        prior.add(7);
        prior.add(5);
        prior.add(13);
        System.out.println(prior);
        prior.remove(5);
        System.out.println(prior);
        prior.remove(7);
        System.out.println(prior);
        prior.add(15);

        System.out.println(prior);
    }
}
