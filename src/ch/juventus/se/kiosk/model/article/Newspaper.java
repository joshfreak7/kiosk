package ch.juventus.se.kiosk.model.article;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Newspaper extends Article {

    private NewspaperType cat;

    public Newspaper(String title, Price price, NewspaperType cat, int count) {
        super(title, price, count);
        this.cat = cat;
    }

    public NewspaperType getCat() {
        return cat;
    }
}
