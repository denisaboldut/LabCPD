import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer extends Thread {
    String text = " Text texT tExt ";
    BufferedWriter output = null;
    File file;
    String name;

    public Writer(File file, String name) {
        this.file = file;
        this.name = name;
    }

    public void writeToFile() throws IOException {

        synchronized (file.getCanonicalPath().intern()) {
            try {

                System.out.println(name + " writes");
                output = new BufferedWriter(new FileWriter(file, true));
                for (int i = 0; i < 3; i++)
                    output.write(text + " by " + name + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (output != null) {
                    output.close();
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            sleep(100);
            System.out.println("in " + name);
            writeToFile();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
