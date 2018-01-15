package ch.juventus.se.kiosk.model;
/**
 * Article class stores information about the article which can be purchased at the kiosk
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public abstract class Article {

    private String title;
    private double price;
    private Category cat;

    public Article(String title, double price, Category cat) {
        this.title = title;
        this.price = price;
        this.cat = cat;
    }

    @Override
    public String toString(){
        return "Article: " + this.title + "\nCategory: "+ this.cat + "\nPrice: " + this.price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public Category getCat() {
        return cat;
    }
}
