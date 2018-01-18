package ch.juventus.se.kioskTest;

import ch.juventus.se.kiosk.error.InsufficientFundsException;
import ch.juventus.se.kiosk.error.KioskClosedException;
import ch.juventus.se.kiosk.error.ToYoungForThisException;
import ch.juventus.se.kiosk.model.Address;
import ch.juventus.se.kiosk.model.Kiosk;
import ch.juventus.se.kiosk.model.article.*;
import ch.juventus.se.kiosk.model.person.Customer;
import ch.juventus.se.kiosk.model.person.Employee;
import ch.juventus.se.kiosk.model.person.Supplier;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class KioskTest {

    private Kiosk kiosk1, kiosk2;
    private Customer youngCustomer, oldCustomer;
    private Employee empl1, empl2;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        System.out.println("Testing setup");
        kiosk1 = new Kiosk("TestKiosk", new Address("Testingstreet 123", 1234, "Entenhausen"),
                1000.00, new Supplier(new ArrayList<>()));
        kiosk2 = new Kiosk("TestKioskOpened", new Address("Testingstreet 123", 1234, "Entenhausen"),
                true, new ArrayList<Employee>(), new ArrayList<Article>(), 1000.00, new Supplier(new ArrayList<Article>()));
        youngCustomer = new Customer("Test", "Customer", 15, 20.00);
        youngCustomer.getBasket().add(new Snack("Zältli", new Price("CHF", 1.20), SnackType.SWEET));
        youngCustomer.getBasket().add(new Tobacco("Zigi", new Price("CHF", 7.20), 16));
        oldCustomer = new Customer("Test2", "Customer2", 55, 150.00);
        oldCustomer.getBasket().add(new Newspaper("NZZ", new Price("CHF", 3.20), NewspaperType.LOCAL));
        oldCustomer.getBasket().add(new Tobacco("Zigi", new Price("CHF", 7.20), 16));
        oldCustomer.getBasket().add(new Tobacco("AnderiZigi", new Price("CHF", 7.50), 16));
        oldCustomer.getBasket().add(new Tobacco("NamalAnderiZigi", new Price("CHF", 8.0), 16));
        oldCustomer.getBasket().add(new Alcohol("Alk", new Price("CHF", 15.0), 500, 18));
        empl1 = new Employee();
        empl2 = new Employee("Not", "AMember", "E1234");
        kiosk2.getEmployees().add(empl1);
    }

    @Test
    public void testConstructors() {
        //Test First Constructor
        assertFalse(kiosk1.isOpen());
        assertNotNull(kiosk1.getArticles());
        assertNotNull(kiosk1.getEmployees());
        assertNotNull(kiosk1.getSupplier().getStack());
        assertNotNull(kiosk1.getSupplier().getFirstName());
        assertNotNull(kiosk1.getSupplier().getFirstName());
        assertNotEquals(" ", kiosk1.getSupplier().getFullName());
        assertEquals(0, kiosk1.getArticles().size());
        assertEquals(0, kiosk1.getEmployees().size());
        assertEquals(0, kiosk1.getSupplier().getStack().size());

        //Test Second Constructor
        assertTrue(kiosk2.isOpen());

    }

    @Test
    public void testCheckout() throws KioskClosedException, ToYoungForThisException, InsufficientFundsException {
        assertEquals(5, oldCustomer.getBasket().size());
        assertTrue(150.00 == oldCustomer.getCash());
        kiosk2.checkout(oldCustomer);
        assertEquals(0, oldCustomer.getBasket().size());
        assertTrue(109.1 == oldCustomer.getCash());
        assertTrue(1040.9 == kiosk2.getCashRegister());

        assertEquals(2, youngCustomer.getBasket().size());
        thrown.expect(ToYoungForThisException.class);
        kiosk2.checkout(youngCustomer);
        assertEquals(1, youngCustomer.getBasket().size()); // Propably not checked
    }
}
