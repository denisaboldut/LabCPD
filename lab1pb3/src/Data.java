import java.util.concurrent.LinkedBlockingQueue;

public class Data {

    LinkedBlockingQueue<String> documents = new LinkedBlockingQueue<>();
    private boolean consuming = false;

    public synchronized void produce(String document) {
        while (consuming) {
            try {
                System.out.println("Employee producer is waiting");
                wait();
                System.out.println("Employee producer was notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        documents.add(document);
        System.out.println("Producing: " + document);

        consuming = true;
        notifyAll();

    }

    public synchronized void consume() {
        while (!consuming) {
            try {
                System.out.println("Printer consumer is waiting");
                wait();
                System.out.println("Printer consumer was notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String docConsumed = documents.poll();
        System.out.println("Consuming: " + docConsumed);

        consuming = false;
        notifyAll();
    }

    public boolean isEmpty() {
        return documents.isEmpty();
    }
}