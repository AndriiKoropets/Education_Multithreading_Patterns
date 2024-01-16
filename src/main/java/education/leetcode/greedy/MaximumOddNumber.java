package education.leetcode.greedy;

public class MaximumOddNumber {

    public String maximumOddBinaryNumber(String s) {
        StringBuilder ss = new StringBuilder(s);
        int ones = 0;
        for (int i = 0; i < s.length(); i++) {
            if (ss.charAt(i) == '1') {
                ones++;
                ss.setCharAt(i, '0');
            }
        }

        int counter = 0;
        while (ones != 1) {
            ss.setCharAt(counter, '1');
            counter++;
            ones--;
        }

        ss.setCharAt(ss.length() - 1, '1');

        return ss.toString();
    }

    public static void main(String[] args) {
        MaximumOddNumber ref = new MaximumOddNumber();
        System.out.println(ref.maximumOddBinaryNumber("0101"));
        System.out.println(ref.maximumOddBinaryNumber("1"));
        System.out.println(ref.maximumOddBinaryNumber("0101"));
        System.out.println(ref.maximumOddBinaryNumber("0000000111000001010101010101"));
    }
}
