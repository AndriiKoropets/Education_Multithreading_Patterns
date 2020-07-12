package education.multithreading.concurrent.actor_akka.hash_map;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.actor.dsl.Creators;

import java.util.HashMap;
import java.util.Map;

public class Bucket extends UntypedAbstractActor {
    private Map<String, Object> data = new HashMap<>();
    public void onReceive(Object msg) throws Throwable {
        Object[] msgArr = (Object[]) msg;
        String command = (String) msgArr[0];
        String key = (String) msgArr[1];
        switch (command) {
            case "put":
                Object value = msgArr[2];
                data.put(key, value);
                break;
            case "remove":
                data.remove(key);
                break;
            case "get"://{"get", key, originalSender} -> {"get/result", key, value, originalSender}
                ActorRef originalSender = (ActorRef) msgArr[2];
//                Object[] response = msg("get/result", key, data.get(key), originalSender);
//                getSender().tell(response, getSelf());
                break;
        }
    }
}
