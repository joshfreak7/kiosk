package ch.juventus.se.kioskTest;

import ch.juventus.se.kiosk.error.InsufficientFundsException;
import ch.juventus.se.kiosk.error.KioskClosedException;
import ch.juventus.se.kiosk.error.ToYoungForThisException;
import ch.juventus.se.kiosk.model.Address;
import ch.juventus.se.kiosk.model.Kiosk;
import ch.juventus.se.kiosk.model.article.Article;
import ch.juventus.se.kiosk.model.article.Price;
import ch.juventus.se.kiosk.model.article.Tobacco;
import ch.juventus.se.kiosk.model.person.Customer;
import ch.juventus.se.kiosk.model.person.Employee;
import ch.juventus.se.kiosk.model.person.Supplier;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class ExceptionTest {

    private Kiosk kiosk1, kiosk2;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        System.out.println("Testing setup");
        kiosk1 = new Kiosk("TestKiosk", new Address("Testingstreet 123", 1234, "Entenhausen"),
                1000.00, new Supplier(new ArrayList<>()));
        kiosk2 = new Kiosk("TestKioskOpened", new Address("Testingstreet 123", 1234, "Entenhausen"),
                true, new ArrayList<Employee>(), new ArrayList<Article>(), 1000.00, new Supplier(new ArrayList<Article>()));
    }

    @Test
    public void testKioskClosedException() throws KioskClosedException, ToYoungForThisException, InsufficientFundsException{
        thrown.expect(KioskClosedException.class);
        Customer toLateOrToEarly = new Customer("Test", "Exception", 20, 100.00);
        kiosk1.checkout(toLateOrToEarly);
    }

    @Test
    public void testToYoungForThisException() throws KioskClosedException, ToYoungForThisException, InsufficientFundsException{
        Customer toYoung = new Customer("Test", "Exception", 15, 10.00);
        toYoung.getBasket().add(new Tobacco("Zigi", new Price("CHF", 7.20), 16));

        thrown.expect(ToYoungForThisException.class);
        kiosk2.checkout(toYoung);
    }

    @Test
    public void testInsufficientFundsException() throws KioskClosedException, ToYoungForThisException, InsufficientFundsException{
        Customer noMoney = new Customer("Test", "Exception", 20, 1.00);
        noMoney.getBasket().add(new Tobacco("Zigi", new Price("CHF", 7.20), 16));

        thrown.expect(InsufficientFundsException.class);
        kiosk2.checkout(noMoney);
    }
}
