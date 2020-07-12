package education.multithreading.concurrent.actor_akka.hash_map;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import education.multithreading.concurrent.actor_akka.worker_up.Callback;

import static akka.actor.ActorRef.noSender;

public class AppMap {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("demo");
        ActorRef container = system.actorOf(Props.create(Container.class), "container");
        ActorRef callback = system.actorOf(Props.create(Callback.class), "callback");

//        container.tell(msg("put", "keyA", "valueA"), noSender());
//        container.tell(msg("put", "keyB", "valueB"), noSender());
//        container.tell(msg("put", "keyC", "valueC"), noSender());
//
//        container.tell(msg("remove", "keyB"), noSender());
//
//        container.tell(msg("get", "keyA"), callback);
//        container.tell(msg("get", "keyB"), callback);
//        container.tell(msg("get", "keyC"), callback);
//
//        System.in.read();
//        System.shutdown();

    }
}
