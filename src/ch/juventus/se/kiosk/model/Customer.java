package ch.juventus.se.kiosk.model;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Customer extends Person {
    private int age;
    private double basket;

    public Customer(String firstName, String lastName, int age, double basket) {
        super(firstName, lastName);
        this.age = age;
        this.basket = basket;
    }

    public int getAge() {
        return age;
    }

    public double getBasket() {
        return basket;
    }
}
