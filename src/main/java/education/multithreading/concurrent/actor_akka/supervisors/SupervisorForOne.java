package education.multithreading.concurrent.actor_akka.supervisors;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedAbstractActor;
import scala.concurrent.duration.Duration;

public class SupervisorForOne extends UntypedAbstractActor {

    private static SupervisorStrategy strategy =
            new OneForOneStrategy(10, Duration.create("1 minute"), t -> {
                if (t instanceof ArithmeticException) {
                    return SupervisorStrategy.resume();// призупинений актор продовжує свою роботу зі зберіганням стану... черга повідомлень(mailbox) зберігається... реінкарнація
                } else if (t instanceof NullPointerException) {
                    return SupervisorStrategy.restart();// об'єкт буде знищений і створений заново, але при цьому його черга повідомлень(mailbox) зберігається... реінкарнація
                } else if (t instanceof IllegalAccessException) {
                    return SupervisorStrategy.stop();// знищуємо всіх потомків
                } else {
                    return SupervisorStrategy.escalate();// ексепшн обробляється вище
                }
            });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object msg) throws Throwable {
        if (msg instanceof Props) {
            ActorRef response = getContext().actorOf((Props) msg, "child");
            getSender().tell(response, getSelf());
        } else {
            unhandled(msg);
        }
    }
}
