package education.multithreading.concurrent.actor_akka.hash_map;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

public class Container extends UntypedAbstractActor {
    private final ActorRef[] buckets = new ActorRef[16];

    public Container() {
        for (int k = 0; k < buckets.length; k++) {
            buckets[k] = getContext().actorOf(Props.create(Bucket.class), "bucket-" + k);
        }
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        Object[] msgArg = (Object[]) message;
        String command = (String) msgArg[0];
        String key = (String) msgArg[1];
        switch (command) {
            case "put":
            case "remove":
                buckets[key.hashCode()%16].tell(message, getSelf());
                break;
            case "get":
                Object[] nextGet = {"get", key, getSender()};
                buckets[key.hashCode() % 16].tell(nextGet, getSender());
                break;
            case "get/result":
                String value = (String) msgArg[2];
                ActorRef originalSender = (ActorRef) msgArg[3];
                Object[] responseGet = {"get/result", key, value};
                originalSender.tell(responseGet, getSelf());
                break;
        }
    }
}
