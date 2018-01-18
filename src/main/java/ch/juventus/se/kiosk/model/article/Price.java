package ch.juventus.se.kiosk.model.article;

import java.io.Serializable;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Price implements Comparable, Serializable{
    private String currency;
    private double amount;

    public Price(String currency, double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return this.currency + " " + this.amount;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Object o) {
        Price p = (Price) o;
        return (this.currency + this.amount).compareTo(p.getCurrency() + p.getAmount());
    }
}
