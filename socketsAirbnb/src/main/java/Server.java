import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    public static String IP = "127.0.0.1";
    public static int PORT = 1212;
    private ServerSocket serverSocket;
    private List<Location> locationList = Collections.synchronizedList(new ArrayList<Location>());


    public void start() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
               Socket socket = serverSocket.accept();
                new ServerClientsHandler(socket, locationList).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server stopped");
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

}
