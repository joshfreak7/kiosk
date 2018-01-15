package ch.juventus.se.kiosk.model;

import org.apache.log4j.Logger;

/**
 * Employee class stores information about the employee, who works at the Kiosk.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Employee implements Comparable{

    private String firstName;
    private String lastName;
    private final Logger logger = Logger.getLogger(Employee.class);

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        logger.info("Start of setUp");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return "Firstname: " + this.firstName + ", Lastname: " + this.lastName;
    }

    @Override
    public int compareTo(Object o) {
        Employee empl = (Employee) o;
        if(this.firstName.equals(empl.getFirstName())){
            return this.lastName.compareTo(empl.getLastName());
        }
        return this.firstName.compareTo(empl.getFirstName());
    }
}
