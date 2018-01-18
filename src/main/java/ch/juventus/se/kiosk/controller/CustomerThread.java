package ch.juventus.se.kiosk.controller;

import ch.juventus.se.kiosk.model.person.Customer;

/**
 * Thread implementation of customer. Not implemented yet.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class CustomerThread extends Thread{
    private Thread t;
    private Customer customer;

    public CustomerThread(Customer customer) {
        this.customer = customer;
        System.out.println("Creating Thread: " + this.customer.toString());
    }

    public void run() {
        System.out.println("Running Thread: " + customer.toString());
    }

    public void start() {
        System.out.println("Starting Thread: " + customer.toString());
        if(t == null) {
            t = new Thread(this, customer.toString());
            t.start();
        }
    }
}
