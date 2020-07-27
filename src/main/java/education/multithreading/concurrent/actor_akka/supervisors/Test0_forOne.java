package education.multithreading.concurrent.actor_akka.supervisors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import static akka.pattern.Patterns.ask;

public class Test0_forOne {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef supervisor = system.actorOf(Props.create(SupervisorForOne.class), "supervisor");

        ActorRef child = (ActorRef) blockingAsk(supervisor, Props.create(Child.class));

        {
            child.tell(42, ActorRef.noSender());
            Integer state = (Integer) blockingAsk(child, "get");
            System.out.println("state = " + state);
        }
        {
            child.tell(new ArithmeticException(), ActorRef.noSender());
            Integer state = (Integer) blockingAsk(child, "get");
            System.out.println("state = " + state);
        }
        {
            child.tell(new NullPointerException(), ActorRef.noSender());
            Integer state = (Integer) blockingAsk(child, "get");
            System.out.println("state = " + state);
        }
    }

    public static Object blockingAsk(ActorRef actor, Object msg) throws Exception {
        Timeout timeout = new Timeout(Duration.create(5, "seconds"));
        Future<Object> future = ask(actor, msg, timeout);
        return Await.result(future, timeout.duration());
    }
}
