package education.leetcode.greedy;

public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) {
            return true;
        }
        if (flowerbed.length == 1) {
            return flowerbed[0] == 0 && n == 1;
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] != 1) {
                if (i == 0) {
                    if (flowerbed[i+1] == 0) {
                        flowerbed[i] = 1;
                        n--;
                    }
                } else if (i == flowerbed.length - 1) {
                    if (flowerbed[i - 1] == 0) {
                        flowerbed[i] = 1;
                        n--;
                    }
                } else {
                    if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                        flowerbed[i] = 1;
                        n--;
                    }
                }
            }
            if (n == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CanPlaceFlowers ref = new CanPlaceFlowers();
        int[] flowers = new int[]{0,0,0,0,1,0,0,0};
        System.out.println(ref.canPlaceFlowers(flowers, 0));
    }
}
