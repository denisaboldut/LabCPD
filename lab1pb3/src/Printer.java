public class Printer extends Thread {
    private Data document;

    public Printer(Data document) {
        this.document = document;
    }

    @Override
    public void run() {
        try{
            while(document.isEmpty()){
                System.out.println("Nothing to consume");
              sleep(500);
            }
            while (!document.isEmpty()) {
                sleep(200);
                String documentToPrint = document.consume();
            }
        }catch (InterruptedException e ){
            e.printStackTrace();
        }

    }
}
