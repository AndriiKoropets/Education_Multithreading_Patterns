package education.leetcode.backtracking;

public class Maximum69Number {
    public int maximum69Number (int num) {
        int length = numLength(num);
        int left = 10;
        int right = (int) Math.pow(10, length - 1);
        for (int i = 0; i < length; i++) {
            int curTry = num;
            int digit = curTry / right % left;
            int nine = 9 * right;
            int six = 6 * right;
            right = right / 10;
            if (digit == 6) {
                curTry = num - six + nine;
                if (num < curTry) {
                    return curTry;
                }
            }
        }
        return num;
    }

    public int maximum69NumberUsingStirngBuilder (int num) {
        return Integer.parseInt(String.valueOf(num).replaceFirst("6", "9"));
    }

    private int numLength(int num) {
        int ten = 10;
        int length = 1;
        while (num / ten != 0) {
            length++;
            ten *= 10;
        }
        return length;
    }

    public static void main(String[] args) {
        Maximum69Number ref = new Maximum69Number();
        System.out.println(ref.maximum69Number(9669));
        System.out.println(ref.maximum69Number(9999));
        System.out.println(ref.maximum69Number(666));
        System.out.println(ref.maximum69Number(696));
        System.out.println(ref.maximum69Number(966));
        System.out.println(ref.maximum69Number(9996));
        System.out.println(ref.maximum69Number(6));
        System.out.println(ref.maximum69Number(9));

        System.out.println(ref.maximum69NumberUsingStirngBuilder(696));
    }
}
