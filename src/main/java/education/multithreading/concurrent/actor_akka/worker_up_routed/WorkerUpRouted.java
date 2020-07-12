package education.multithreading.concurrent.actor_akka.worker_up_routed;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;

public class WorkerUpRouted extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            String response = ((String) message).toUpperCase();
            ActorRef sender = getSender();
            sender.tell(response, getSelf());
            System.out.println(">>" + getSelf());
            while (true);//вішаємо актор
        } else {
            unhandled(message);
        }
    }
}
