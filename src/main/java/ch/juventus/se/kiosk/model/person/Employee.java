package ch.juventus.se.kiosk.model.person;

import ch.juventus.se.kiosk.helper.FileHandler;
import ch.juventus.se.kiosk.model.article.Article;
import org.apache.log4j.Logger;

import java.util.List;

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

    public Employee(String firstName, String lastName, String employeeId) {
        super(firstName, lastName);
        this.employeeId = employeeId;
    }

    /**
     * Employee does inventory. Checks what articles kiosk still have.
     * @param inventoryList The inventory currently available for the kiosk
     * @param fh The fileHandler to export inventoryList
     */
    public void doInventory(List<Article> inventoryList, FileHandler fh) {
        logger.info("Employee: " + toString() + " does inventory.");
        fh.writeArticles(inventoryList);
        logger.info("Employee: " + toString() + " has done inventory.");
    }

    public String getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString(){
        return "EmployeeID: " + employeeId + ", " + super.toString();
    }


}
