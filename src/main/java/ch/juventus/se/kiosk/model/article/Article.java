package ch.juventus.se.kiosk.model.article;

import java.io.Serializable;

/**
 * Article class stores information about the article which can be purchased at the kiosk
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public abstract class Article implements Comparable, Serializable{

    private String title;
    private Price price;

    public Article(String title, Price price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString(){
        return this.title + ": " + this.price;
    }

    @Override
    public int compareTo(Object o) {
        Article a = (Article) o;
        return this.title.compareTo(a.getTitle());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        return title.equals(article.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    public String getTitle() {
        return title;
    }

    public Price getPrice() {
        return price;
    }

    public double getAmount() {
        return price.getAmount();
    }

    public String getCurrency() {
        return price.getCurrency();
    }
}
