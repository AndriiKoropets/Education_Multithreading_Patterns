package education.leetcode.greedy;

public class ValidPalindrome {

    public boolean validPalindrome(String s) {
//        int left = 0;
//        int right = s.length() - 1;
//        int counter = 0;
//        int atMost = 1;

        if (s.length() % 2 == 0) {
            String left = s.substring(0, s.length() / 2);
            String right = s.substring(s.length() / 2);
            if (isEqual(left, right)) {
                return true;
            } else {
                for (int i = 0; i < left.length(); i++) {
                    if (left.charAt(i) != right.charAt(right.length() - i - 1)) {
                        String sub1 = left.substring(0, i) + left.substring(i + 1);
                        String sub2 = right.substring(0, right.length() - i - 1) + right.substring(right.length() - i);
                        return isEqual(sub1, right.substring(1)) || isEqual(sub2, left.substring(0, left.length() - 1));
                    }
                }
            }
            return isEqual(left, right) || isEqual(left.substring(0, left.length() - 1), right.substring(1));
        } else {
            String left = s.substring(0, s.length() / 2);
            String right = s.substring(s.length() / 2 + 1);
            char pivot = s.charAt(s.length() / 2);
            if (!isEqual(left, right)) {
                for (int i = 0; i < left.length(); i++) {
                    if (left.charAt(i) != right.charAt(right.length() - i - 1)) {
                        String sub1 = left.substring(0, i) + left.substring(i + 1) + pivot;
                        String sub2 = pivot + right.substring(0, right.length() - i - 1) + right.substring(right.length() - i);
                        return isEqual(sub1, right) || isEqual(sub2, left);
                    }
                }
            }
            return true;

//            if (left.equals(right)) {
//                return true;
//            } else {
//
//            }
        }
    }

    private boolean isEqual(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(s2.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }


//        while(counter < s.length()/2) {
//            if (s.charAt(left) != s.charAt(right)) {
//                if (atMost == 0) {
//                    return false;
//                }
//                if (s.charAt(left + 1) == s.charAt(right)) {
//                    left++;
//                } else if (s.charAt(left) == s.charAt(right - 1)) {
//                    right--;
//                } else {
//                    return false;
//                }
//                atMost--;
//            } else{
//                left++;
//                right--;
//            }
//            counter++;
//        }
//        return true;


    public static void main(String[] args) {
        ValidPalindrome ref = new ValidPalindrome();
        System.out.println(ref.validPalindrome("eedede"));
        System.out.println(ref.validPalindrome("abca"));
        System.out.println(ref.validPalindrome("abcca"));
        System.out.println("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga".length());
        System.out.println(ref.validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
        
    }
}