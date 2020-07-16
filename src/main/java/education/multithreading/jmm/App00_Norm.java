package education.multithreading.jmm;

public class App00_Norm {
    // якщо флаг volatile ця програма гарантовано зупиниться
    static volatile boolean run = true;
    static int data = 0;//happens-before
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (run);
            System.out.println(data);
        });
        thread.start();
        data = 1;//happens-before... інший потік завжди побачить data = 1
        run = false;//без volatile цей рядок компілятор може викинути
        // якщо volatile - то тоді значення передається між потоками



        //+int x1 = y1 + z1
        //+float f1 = f20 + f30
        //+float f2 = f40 + f50
        //float f3 = f60 + f70
        //float f4 = f80 + f90
        //+int x2 = y2 + z2 це обчіслюватиметься раніше ніж f3 та f4, для машини з 2 цілочисленими і 2 з плавочою комою
    }
}
