package education.leetcode.greedy;

public class BalancedStringSplit {

    public int balancedStringSplit(String s) {
        int balance = 0;
        int result = 0;
        for (char c : s.toCharArray()){
            if (c == 'R') {
                balance++;
            } else {
                balance--;
            }
            if (balance == 0) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        BalancedStringSplit ref = new BalancedStringSplit();
        System.out.println(ref.balancedStringSplit("RLRRLLRLRL"));
        System.out.println(ref.balancedStringSplit("RLRRRLLRLL"));
        System.out.println(ref.balancedStringSplit("LLLLRRRR"));
    }
}
