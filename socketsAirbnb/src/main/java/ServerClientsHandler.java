import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class ServerClientsHandler extends Thread {
    private Socket socket;

    private List<Location> locationList;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ServerClientsHandler(Socket socket, List<Location> locationList) {
        System.out.println("client connected");
        this.socket = socket;
        this.locationList = locationList;
    }

    @Override
    public void run() {
        try {

            OutputStream outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);

            InputStream inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);


            Location location = new Location("DefaultLocation");

            locationList.add(location);
            System.out.println("sending message");
            objectOutputStream.writeObject(locationList);


            for (;;) {
                try {

                    locationList = Collections.synchronizedList((List<Location>) objectInputStream.readObject());
                    objectOutputStream.writeObject(locationList);
                    for(Location newLocation: locationList){
                        System.out.println(newLocation.getName());
                    }
                }
                catch (EOFException exc) {
                    break;
                }
            }

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
