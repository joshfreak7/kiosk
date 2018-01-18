package ch.juventus.se.kioskTest;

import ch.juventus.se.kiosk.model.person.Employee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class EmployeeTest {

    private Employee empl1, empl2;
    @Before
    public void setUp() {
        System.out.println("Testing setup");
        empl1 = new Employee();
        empl2 = new Employee("Test", "Employee", "E1234");
    }

    @Test
    public void testConstructors() {
        // Test first Constructor
        assertNotNull(empl1.getEmployeeId());
        assertNotNull(empl1.getFirstName());
        assertNotNull(empl1.getLastName());
        assertNotEquals(" ", empl1.getFullName());

        // Test second Constructor
        assertNotNull(empl2.getEmployeeId());
        assertNotNull(empl2.getFirstName());
        assertNotNull(empl2.getLastName());
        assertNotEquals(" ", empl2.getFullName());
    }
}
