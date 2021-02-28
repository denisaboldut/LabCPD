import java.util.LinkedList;
import java.util.Queue;

public class Data {

    Queue<String> documents = new LinkedList<>();

    public synchronized void produce(String document) {
        System.out.println("Producing: " + document);
        documents.add(document);
    }

    public String consume() {
        String docConsumed = documents.poll();
        System.out.println("Consuming: " + docConsumed);
        return docConsumed;
    }

    public boolean isEmpty() {
        return documents.isEmpty();
    }
}