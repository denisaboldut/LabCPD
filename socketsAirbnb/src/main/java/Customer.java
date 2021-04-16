import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Customer {

    Socket socket;
    private List<Location> locationList;
    private ObjectOutputStream  objectOutputStream;
    private  ObjectInputStream objectInputStream;

    public void connect() throws ClassNotFoundException {
        try {
            socket = new Socket(Server.IP,Server.PORT);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            objectOutputStream = new ObjectOutputStream(outputStream);
            objectInputStream = new ObjectInputStream(inputStream);
            locationList = (List<Location>) objectInputStream.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Customer connected");
    }


    public void disconnect()  {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Customer disconnected");
    }


    public void viewAvailabilityInAllLocationsByMonth( String month) {
        for (Location location : locationList) {
            System.out.println(!location.getAvailabilityByMonth(month));
        }
    }

    public void viewLocations() {
        for (Location location : locationList) {
            System.out.println(location.getName());
        }
    }

    public void reserveLocationForOneMonth(String locationName, String month) throws IOException, ClassNotFoundException {

        for (Location location : locationList) {
            System.out.println(location.getName());
            if (location.getName().equals(locationName)) {
                if (location.getAvailabilityByMonth(month)) {
                    System.out.println("Location is already reserved this month");
                } else {
                    location.setMonthReservation(month);
                    System.out.println("Location " + locationName + " successfully booked in " + month);
                }
            }
        }
    }

    public void reserveLocationForMonths(String locationName, List<String> months) {
        for (Location location : locationList) {
            if (location.getName().equals(locationName)) {
                location.setMultipleMonthsReservation(months);
            }
        }
    }

    public void viewLocationsAndAvailability() {
        for (Location location : locationList) {
            Iterator it = location.getMonthAndAvailability().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                System.out.println("Month: " + pair.getKey() + " - already occupied: " + pair.getValue());
                it.remove();
            }
        }
    }
}
