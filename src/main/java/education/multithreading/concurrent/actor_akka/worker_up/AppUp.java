package education.multithreading.concurrent.actor_akka.worker_up;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.Scanner;

public class AppUp {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef worker = system.actorOf(Props.create(WorkerUp.class), "worker");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if ("exit".equals(line)) {
                system.terminate();
                return;
            }
            worker.tell(line, callback);
        }
    }
}
