package xxatcust.oracle.apps.sudoku.viewmodel.ux;

import java.util.List;

public class Employee {
    private String name;
    private String location;
    private List<Employee> directs;

    public Employee(String name, String loc, List<Employee> directs) {
        this.name = name;
        this.location = loc;
        this.directs = directs;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Employee> getDirects() {
        return directs;
    }
}
