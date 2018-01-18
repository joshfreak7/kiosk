package ch.juventus.se.kioskTest;

import ch.juventus.se.kiosk.model.person.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class CustomerTest {
    private Customer customer;

    @Before
    public void setUp() {
        System.out.println("Testing setup");
        customer = new Customer("Test", "Customer", 10, 20.00);
    }

    @Test
    public void testConstructor() {
        assertNotNull(customer.getBasket());
        assertNotNull(customer.getAge());
        assertNotNull(customer.getCash());
        assertNotNull(customer.getFirstName());
        assertNotNull(customer.getLastName());
        assertNotEquals(" ", customer.getFullName());
        assertEquals(0, customer.getBasket().size());
    }
}
