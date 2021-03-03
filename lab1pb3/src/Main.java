import java.util.ArrayList;
import java.util.List;
/*
* Intr-un birou sunt 8 functionari care din când în când tipăresc
*   la imprimantă documente, nu toți elaborează documentele în același ritm.
*   Fiindcă au o singură imprimantă în birou, poate tipări doar o singura
*   persoană la un moment dat. Să se simuleze functionarea biroului.
* */

public class Main {
    public static void start() {
        Data data = new Data();
        Printer printer = new Printer(data);

        List<Employee> employeeList = new ArrayList<>(8);

        for (int i = 0; i < 7; i++) {
            Employee employeeNew = new Employee(data, "Employee " + i, i);
            employeeList.add(employeeNew);
        }

        for (Employee employee : employeeList) {
            employee.start();
        }

        printer.start();
    }

    public static void main(String[] args) throws InterruptedException {
        start();
    }
}
