import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Location implements Serializable {
    private String name;
    private HashMap<String, Boolean> monthAndAvailability = new HashMap<String, Boolean>();

    public Location(String name){
        this.name = name;
        initReservations();
    }

    public boolean getAvailabilityByMonth(String month) {
        return monthAndAvailability.get(month);
    }

    public void setMonthReservation(String month) {
        monthAndAvailability.put(month, true);
    }

    public void removeMonthReservation(String month) {
        monthAndAvailability.put(month, false);
    }

    private void initReservations() {
        monthAndAvailability.put("January", false);
        monthAndAvailability.put("February", false);
        monthAndAvailability.put("March", false);
        monthAndAvailability.put("April", false);
        monthAndAvailability.put("May", false);
        monthAndAvailability.put("June", false);
        monthAndAvailability.put("July", false);
        monthAndAvailability.put("August", false);
        monthAndAvailability.put("September", false);
        monthAndAvailability.put("October", false);
        monthAndAvailability.put("November", false);
        monthAndAvailability.put("December", false);
    }

    public void setMultipleMonthsReservation(List<String> months) {
        for (String month : months) {
            monthAndAvailability.put(month, true);
        }
    }

    public HashMap<String, Boolean> getMonthAndAvailability() {
        return monthAndAvailability;
    }

    public String getName() {
        return name;
    }
}
