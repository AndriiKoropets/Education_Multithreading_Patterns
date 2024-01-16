package education.leetcode.greedy;

public class LatestTime {

    public String maximumTime(String time) {
        StringBuilder sb = new StringBuilder(time);
        if (sb.charAt(0) == '?') {
            if (sb.charAt(1) == '?' || sb.charAt(1) <= '3') {
                sb.setCharAt(0, '2');
            } else {
                sb.setCharAt(0, '1');
            }
        }

        if (sb.charAt(1) == '?' && sb.charAt(0) <= '1') {
            sb.setCharAt(1, '9');
        } else if (sb.charAt(1) == '?' && sb.charAt(0) == '2') {
            sb.setCharAt(1, '3');
        }

        if (sb.charAt(3) == '?') {
            sb.setCharAt(3, '5');
        }

        if (sb.charAt(4) == '?') {
            sb.setCharAt(4, '9');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LatestTime ref = new LatestTime();
        System.out.println(ref.maximumTime("2?:?0"));
        System.out.println(ref.maximumTime("??:?0"));
        System.out.println(ref.maximumTime("??:??"));
        System.out.println(ref.maximumTime("??:2?"));
        System.out.println(ref.maximumTime("?5:2?"));
        System.out.println(ref.maximumTime("?3:??"));
        System.out.println(ref.maximumTime("?2:??"));
        System.out.println(ref.maximumTime("?1:??"));
        System.out.println(ref.maximumTime("?0:??"));
        System.out.println(ref.maximumTime("?9:??"));
    }
}
