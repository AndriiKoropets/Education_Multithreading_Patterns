package education.multithreading.concurrent.actor_akka.sum_kernel;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

import java.util.ArrayList;
import java.util.List;

public class SumKernel extends UntypedAbstractActor {
    private final ActorRef master;
    private final List<Integer> state = new ArrayList<>(2);
    private ActorRef slave1;
    private ActorRef slave2;

    public SumKernel (ActorRef master) {
        this.master = master;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof int[]) {
            int from = ((int[]) message)[0];
            int to = ((int[]) message)[1];
            if (to - from > 3) {
                (slave1 = getContext().actorOf(Props.create(SumKernel.class, getSelf())))
                        .tell(new int[]{from, (from + to) >>> 1}, getSelf());

                (slave2 = getContext().actorOf(Props.create(SumKernel.class, getSelf())))
                        .tell(new int[]{((from+to) >>> 1) + 1, to}, getSelf());
            } else {
                master.tell(calc(from, to), getSelf());
            }
        }else if (message instanceof Integer){
            state.add((Integer)message);
            if (state.size() == 2) {
                master.tell(state.get(0) + state.get(1), getSelf());

                //Актор виконав свою роботу і відправляємо йому команду знищення
                slave1.tell(PoisonPill.getInstance(), getSelf());
                slave2.tell(PoisonPill.getInstance(), getSelf());
            }

        }else {
            unhandled(message);
        }
    }

    private int calc(int from, int to) {
        int result = 0;
        for (int k = from; k <= to; k++) {
            result += k;
        }
        return result;
    }
}
