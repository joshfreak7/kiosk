package ch.juventus.se.kiosk.model;

import ch.juventus.se.kiosk.error.InsufficientFundsException;
import ch.juventus.se.kiosk.model.article.Article;

import java.util.*;
import java.util.stream.Collectors;

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

    public String getBasketFormatted() {
        //return basket.stream().map(article -> "\t" + article.toString() + "\n").collect(Collectors.joining());
        //return basket.stream().map(article -> "\t" + Collections.frequency(basket, article.toString())
        //        + " x " + article.toString() + "\n").collect(Collectors.joining());
        Set<Article> uniqueSet = new HashSet<>(basket);
        return uniqueSet.stream().map(article -> "\t" + Collections.frequency(basket, article)
                + " x " + article.toString() + "\n").collect(Collectors.joining());
    }

    @Override
    public String toString(){
        return super.toString()
                + "\n\tAge: " + this.age
                + "\n\tBasket: " + getBasketFormatted();
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
