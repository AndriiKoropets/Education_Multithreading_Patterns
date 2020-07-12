package education.multithreading.concurrent.actor_akka.worker_up;

import akka.actor.UntypedAbstractActor;

import java.util.Arrays;

public class Callback extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Object[]) {
            System.out.println("<<" + getSelf());
            System.out.println("result = " + Arrays.toString((Object[]) message));
        } else {
            System.out.println("<<<<" + getSelf());
            System.out.println("result = " + message);
        }
//        System.out.println("result = " + message);
//        Thread.sleep(300);
    }
}
