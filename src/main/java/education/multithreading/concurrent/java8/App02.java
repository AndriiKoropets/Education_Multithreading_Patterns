package education.multithreading.concurrent.java8;

public class App02 {
    public static void main(String[] args) {
        I0 ref0 = App02::printHello;
        I1 ref1 = App02::printHello;
    }

    private static int printHello() {
        System.out.println("Hello world");
        return 42;
    }

    private static int printHello(int xxx) {
        System.out.println("Hello world");
        return 42;
    }
}

interface I0 {
    int f();
}

interface I1 {
    void g(int ago);
}

interface Parent {
    void g();
}

interface Child extends Parent {
    void g();
}

