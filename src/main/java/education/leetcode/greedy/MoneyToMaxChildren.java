package education.leetcode.greedy;

public class MoneyToMaxChildren {

    public int distMoney(int money, int children) {
        int eights = 0;
//        while (money - 8 >= children - 1 && children + 8 <= money && children > 0) {
//            if (!(money - 8 == 4 && children == 2)) {
//                money -= 8;
//                children--;
//                eights++;
//            }
//        }
        while (money >= children) {
            if ((money - 8 >= 0 && money - 8 >= children - 1)) {
                if (!(money - 8 == 4 && children == 2)) {
                    if (!(children == 1 && money > 8)) {
                        money -= 8;
                        children--;
                        eights++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else  {
                break;
            }
        }
        return eights;
    }

    public static void main(String[] args) {
        MoneyToMaxChildren ref = new MoneyToMaxChildren();
        System.out.println(ref.distMoney(50, 6));
        System.out.println(ref.distMoney(36, 6));
        System.out.println(ref.distMoney(20, 3));
        System.out.println(ref.distMoney(2, 3));
        System.out.println(ref.distMoney(9, 3));
        System.out.println(ref.distMoney(10, 3));
        System.out.println(ref.distMoney(16, 2));
        System.out.println(ref.distMoney(200, 30));
    }
}
