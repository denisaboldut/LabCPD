import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Reader extends Thread {
    File file;
    String name;

    public Reader(File file, String name) {
        this.file = file;
        this.name = name;
    }

    public void readFile() throws IOException {
        synchronized (file.getCanonicalPath().intern()) {

            try {
                Scanner myReader = new Scanner(file);
                while (!myReader.hasNextLine()) {
                    System.out.println("Nothing to read");
                }
                while (myReader.hasNextLine()) {
                    System.out.println(name + " reads");
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            sleep(100);
            readFile();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
