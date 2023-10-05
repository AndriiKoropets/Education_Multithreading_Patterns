package education.multithreading.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class DigitsPhone {

    Map<Character, List<String>> phone = Map.of(
            '2', asList("a", "b", "c"),
            '3', asList("d", "e", "f"),
            '4', asList("g", "h", "i"),
            '5', asList("j", "k", "l"),
            '6', asList("m", "n", "o"),
            '7', asList("p", "q", "r", "s"),
            '8', asList("t", "u", "v"),
            '9', asList("w", "x", "y", "z")
    );

    public List<String> letterCombinations(String digits) {
        if ("".equals(digits)) {
            return Collections.emptyList();
        }
        List<Character> numbers = digits.chars().mapToObj(e -> (char)e).toList();
        List<List<String>> phoneMessage = numbers.stream().map(n -> phone.get(n)).toList();
        List<String> output = phoneMessage.get(0);
        for (int i = 1; i < phoneMessage.size(); i++) {
            output = mergeLists(output, phoneMessage.get(i));
        }
        return output;
    }

    private List<String> mergeLists(List<String> l1, List<String> l2) {
        List<String> output = new ArrayList<>();

        for (var i : l1) {
            for (var j : l2) {
                output.add(i + j);
            }
        }

        return output;
    }

    public static void main(String[] args) {
        DigitsPhone obj = new DigitsPhone();
        System.out.println(obj.letterCombinations("2345"));
    }
}
