package education.multithreading.concurrent.actor_akka.worker_up;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.actor.AbstractActor;
//import akka.actor.UntypedActor;

public class WorkerUp extends UntypedAbstractActor{
    @Override
    public void onReceive(Object msg) throws Throwable {
        if (msg instanceof String) {
            String response = ((String) msg).toUpperCase();
            ActorRef sender = getSender();
            sender.tell(response, getSelf());
            sender.tell("X", getSelf());
            sender.tell("YYY", getSelf());
        } else {
            unhandled(msg);
        }
    }
}
