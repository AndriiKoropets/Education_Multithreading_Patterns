package education.multithreading.concurrent.actor_akka.hash_map;

import akka.actor.UntypedAbstractActor;

import java.util.Arrays;

public class Callback extends UntypedAbstractActor {
    public void onReceive(Object msg) throws Throwable {
        if (msg instanceof Object[]) {
            System.out.println(Arrays.toString((Object[]) msg));
        } else {
            System.out.println("result:" + msg);
        }

    }
}
