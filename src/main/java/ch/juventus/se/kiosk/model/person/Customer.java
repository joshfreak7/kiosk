package ch.juventus.se.kiosk.model.person;

import ch.juventus.se.kiosk.helper.ListHandler;
import ch.juventus.se.kiosk.model.article.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Customer, which buys articles at the kiosk.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Customer extends Person {
    private int age;
    private List<Article> basket;
    private double cash;

    public Customer(String firstName, String lastName, int age, double cash) {
        super(firstName, lastName);
        this.age = age;
        this.cash = cash;
        this.basket = new ArrayList<>();
    }

    @Override
    public String toString(){
        return super.toString()
                + "\n\tAge: " + this.age
                + "\n\tBasket: " + new ListHandler(basket).getFormattedList();
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public int getAge() {
        return age;
    }

    public List<Article> getBasket() {
        return basket;
    }
}
