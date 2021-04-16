import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Test {

    @org.junit.Test
    public void reserveLocationForOneMonthAndMultipleMonths() throws IOException, ClassNotFoundException {
        Customer customer = new Customer();
        Customer customer2 = new Customer();
        customer.connect();
        customer2.connect();

        customer.viewLocations();
        customer.viewAvailabilityInAllLocationsByMonth("May");
        customer.reserveLocationForOneMonth("DefaultLocation", "May");
        customer.viewAvailabilityInAllLocationsByMonth("May");

        List<String> months = new ArrayList<String>();
        months.add("May");
        months.add("April");
        months.add("June");
        customer2.reserveLocationForMonths("DefaultLocation",months);

        customer2.viewLocationsAndAvailability();

        customer.disconnect();
        customer2.disconnect();
    }

    @org.junit.Test
    public void addLocationAndBook() throws IOException, ClassNotFoundException {
        Host host = new Host();
        host.connect();

        host.addLocation("Location3");
        host.addLocation("Location4");
        host.addLocation("Location5");
        host.viewLocationsInCity();

        Customer customer = new Customer();
        customer.connect();

        customer.viewLocations();
        customer.viewAvailabilityInAllLocationsByMonth("May");
        customer.reserveLocationForOneMonth("DefaultLocation", "May");
        customer.reserveLocationForOneMonth("DefaultLocation", "May");
        customer.reserveLocationForOneMonth("Location3", "May");
        customer.viewAvailabilityInAllLocationsByMonth("May");

        host.disconnect();
        customer.disconnect();
    }
}
