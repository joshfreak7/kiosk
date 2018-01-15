package ch.juventus.se.kiosk.model;

import org.apache.log4j.Logger;

/**
 * Employee class stores information about the employee, who works at the Kiosk. Extends a simple Person.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Employee extends Person{

    private String employeeId;
    private final Logger logger = Logger.getLogger(Employee.class);

    public Employee() {
        super();
        employeeId = "E1234";
    }
    public Employee(String employeeId, String firstName, String lastName) {
        super(firstName, lastName);
        this.employeeId = employeeId;
        logger.info("Start of setUp");
    }

    public String getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString(){
        return "EmployeeID: " + employeeId + "\n" + super.toString();
    }
}
