public class Employee extends Thread {

    private Data document;
    private String name;

    public Employee(Data document, String name, Integer id) {
        this.document = document;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                sleep(400);
                document.produce("document nr. "+i+" by " + name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
