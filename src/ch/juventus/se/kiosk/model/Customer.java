package ch.juventus.se.kiosk.model;

import ch.juventus.se.kiosk.error.InsufficientFundsException;
import ch.juventus.se.kiosk.model.article.Article;

import java.util.ArrayList;
import java.util.List;
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

    public void addArticleToBasket(Article article) throws InsufficientFundsException {
        if(cash >= article.getCost()) {
            cash -= article.getCost();
            if(!basket.contains(article))
                basket.add(article);
            else {
                Article a = basket.get(basket.indexOf(article));
                a.setCount(a.getCount() + article.getCount());
            }
        } else {
            throw new InsufficientFundsException("NOT ENOUGH CASH! Article(s) were NOT added to the basket!\n");
        }
    }

    public String getBasketFormatted() {
        return basket.stream().map(article -> "\t" + article.toString() + "\n").collect(Collectors.joining());
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

    public int getAge() {
        return age;
    }

    public List<Article> getBasket() {
        return basket;
    }
}
