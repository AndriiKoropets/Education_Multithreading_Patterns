package education.multithreading.concurrent.actor_akka.supervisors;

import akka.actor.UntypedAbstractActor;

public class Child extends UntypedAbstractActor {// синок папи прокурора
    private int state = 0;

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof Exception) {
            throw (Exception) o;
        } else if (o instanceof Integer) {
            state = (Integer) o;
        } else if (o.equals("get")) {
            getSender().tell(state, getSelf());
        } else {
            unhandled(o);
        }
    }
}
