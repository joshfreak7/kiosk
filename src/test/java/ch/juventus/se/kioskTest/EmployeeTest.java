package ch.juventus.se.kioskTest;

import ch.juventus.se.kiosk.model.Employee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class EmployeeTest {

    @Before
    public void setUp() {
        System.out.println("Testing setup");
        Employee empl1 = new Employee("E1234", "Tester", "lastName");
    }

    @Test
    public void testSomething() {
        assertTrue(1==1);
    }
}
