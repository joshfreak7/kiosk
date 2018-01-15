package ch.juventus.se.kiosk.model;

import java.util.Map;

/**
 * This class represents a supplier which delivers articles from Warehouse to the kiosk. Extends a simple Person.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Supplier extends Person {
    private Map<Integer, Article> stack;

    public Supplier() {
        super();
    }
}
