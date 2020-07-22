package education.multithreading.jmm;

public class App04 {
    static boolean run = true;// програма гарантовано завершиться... synchronized-with

    public static void main(String[] args) throws InterruptedException {
        Thread newThread = new Thread(() -> {
            run = false;
        });
        newThread.start();

        newThread.join();//потік main блокується допоки інший потік не виконається
//        while (newThread.isAlive());//програма гарантовано завершиться також
        while (run);
        System.out.println(Runtime.getRuntime().availableProcessors());//визначає, кількість ядер hiperthreading intel
    }
}

//join and isAlive дають hb ребро назад

//написати програму, яка визначить чи SMP чи NUMA архітектура
//написати програму, яка визначає кількість ядер
