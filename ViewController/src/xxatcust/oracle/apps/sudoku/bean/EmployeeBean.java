package xxatcust.oracle.apps.sudoku.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import xxatcust.oracle.apps.sudoku.viewmodel.ux.Employee;

public class EmployeeBean {
    private List<Employee> allEmployees;
    private ChildPropertyTreeModel empTreeModel;

    public EmployeeBean() {

        super();
    }

    public void setAllEmployees(List<Employee> allEmployees) {
        this.allEmployees = allEmployees;
    }

    public List<Employee> getAllEmployees() {

        return allEmployees;
    }

    public ChildPropertyTreeModel getEmpTreeModel() {
        List<Employee> directList = new ArrayList<Employee>();
        directList.add(new Employee("Ken", "New York",
                                    new ArrayList<Employee>()));
        Employee manager1 = new Employee("John", "London", directList);
        directList = new ArrayList<Employee>();
        directList.add(new Employee("Ramesh", "London",
                                    new ArrayList<Employee>()));
        Employee manager2 = new Employee("Ravi", "Bangalore", directList);
        directList = new ArrayList<Employee>();
        directList.add(new Employee("Rakesh", "Pune",
                                    new ArrayList<Employee>()));
        Employee manager3 = new Employee("Raju", "Pune", directList);

        allEmployees = new ArrayList<Employee>();
        allEmployees.add(manager1);
        allEmployees.add(manager2);
        allEmployees.add(manager3);
        empTreeModel = new ChildPropertyTreeModel(allEmployees, "emp");
        return empTreeModel;
    }
}
