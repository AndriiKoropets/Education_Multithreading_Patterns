package education.multithreading.concurrent.monad;

public class DoubleInfinity {
    public static void main(String[] args) {
        double a = 1;
        double b = a + 1;
        double c = b / 0;
        double d = c + 1;
        double e = 1 - d;
        double f = d + e; //nan
        double g = d / e; //nan
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
        System.out.println(g);
    }
}
