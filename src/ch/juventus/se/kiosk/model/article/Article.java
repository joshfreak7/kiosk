package ch.juventus.se.kiosk.model.article;

/**
 * Article class stores information about the article which can be purchased at the kiosk
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public abstract class Article {

    private String title;
    private Price price;
    private int count;

    public Article(String title, Price price, int count) {
        this.title = title;
        this.price = price;
        this.count = count;
    }

    @Override
    public String toString(){
        return this.count + " x " + this.title + ": " + this.price;
    }

    public String getTitle() {
        return title;
    }

    public Price getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}
