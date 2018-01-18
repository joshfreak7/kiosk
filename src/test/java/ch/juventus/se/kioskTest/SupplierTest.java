package ch.juventus.se.kioskTest;

import ch.juventus.se.kiosk.model.person.Supplier;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class SupplierTest {
    private Supplier supplier;

    @Before
    public void setUp() {
        System.out.println("Testing setup");
        supplier = new Supplier(new ArrayList<>());
    }

    @Test
    public void testConstructor() {
        assertNotNull(supplier.getFirstName());
        assertNotNull(supplier.getLastName());
        assertNotNull(supplier.getStack());
        assertEquals(0, supplier.getStack().size());
    }
}
