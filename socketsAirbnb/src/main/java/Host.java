import java.io.*;
import java.net.Socket;
import java.util.List;

public class Host{
    private Socket socket;
    private ObjectOutputStream  objectOutputStream;
    private  ObjectInputStream objectInputStream;
    private List<Location> locationList;

    public void connect() throws IOException, ClassNotFoundException {
        socket = new Socket(Server.IP, Server.PORT);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectInputStream = new ObjectInputStream(inputStream);

        locationList = (List<Location>) objectInputStream.readObject();

        System.out.println(locationList.toString());
    }





    public void disconnect()  {
        try {
            socket.close();
            objectInputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Host disconnected");
    }

    public void addLocation(String locationName) throws IOException {
        Location newLocation = new Location(locationName);
        locationList.add(newLocation);
        System.out.println("Location "+locationName +" successfully added");
        objectOutputStream.writeObject(locationList);
    }

    public void viewLocationsInCity(){
        for (Location location : locationList) {
                System.out.println(location.getName());
            }

    }
}
