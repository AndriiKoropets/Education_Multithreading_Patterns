package education.multithreading.concurrent.actor_akka.map_reduce;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class MRContainer extends UntypedAbstractActor {
    private static final int BUCKET_SIZE = 16;
    private final ActorRef[] buckets = new ActorRef[BUCKET_SIZE];
    private long lastId = 0;
    private final Map<Long, Object> results = new HashMap<>();
    private final Map<Long, Reducer> reducers = new HashMap<>();
    private final Map<Long, Integer> counts = new HashMap<>();
    private final Map<Long, ActorRef> callbacks = new HashMap<>();

    public MRContainer() {
        for (int k = 0; k < buckets.length; k++) {
            buckets[k] = getContext().actorOf(Props.create(MRBucket.class), "bucket-" + k);
        }
    }

    public void onReceive(Object message) throws Throwable {
        Object[] msgArr = (Object[]) message;
        String command = (String) msgArr[0];
        String key = null;
        if (asList("put", "get", "remove", "get/result").contains(command)) {
            key = (String) msgArr[1];
        }
        switch (command) {
            case "put":
            case "remove":
                buckets[key.hashCode()%16].tell(message, getSelf());
                break;
            case "get":
                Object[] nextGet = {"get", key, getSender()};
                buckets[key.hashCode() % 16].tell(nextGet, getSelf());
                break;
            case "get/result":
                String value = (String) msgArr[2];
                ActorRef originalSender = (ActorRef) msgArr[3];
                Object[] responseGet = {"get/result", key, value};
                originalSender.tell(responseGet, getSelf());
                break;
            case "map/reduce":
                String value = (String) msgArr[2];
                ActorRef originalSender = (ActorRef) msgArr[3];
                Object[] responseGet = {"get/result", key, value};
                originalSender.tell(responseGet, getSelf());
                break;
        }

    }
}
