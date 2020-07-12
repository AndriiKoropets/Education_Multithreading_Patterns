package education.multithreading.concurrent.actor_akka.sum_kernel;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import education.multithreading.concurrent.actor_akka.worker_up.Callback;

public class SumApp {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo_1");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");
        ActorRef kernel = system.actorOf(Props.create(SumKernel.class, callback), "sumKernel");

        kernel.tell(new int[]{0,10}, ActorRef.noSender());

    }
}
