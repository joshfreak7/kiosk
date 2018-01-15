package ch.juventus.se.kiosk.model;

import ch.juventus.se.kiosk.error.InsufficientFundsException;

/**
 * Kiosk sells Articles to customers. Employees working at the Kiosk.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Kiosk {
    private double balance;
    private String name;
    private Address address;

    public Kiosk(String name, Address address, double balance) {
        this.name = name;
        this.address = address;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }
}
