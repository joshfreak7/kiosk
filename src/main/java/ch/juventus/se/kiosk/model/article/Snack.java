package ch.juventus.se.kiosk.model.article;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Snack extends Article {

    private SnackType cat;

    public Snack(String title, Price price, SnackType cat) {
        super(title, price);
        this.cat = cat;
    }

    @Override
    public String toString(){
        return super.toString()
                + " (" + this.getClass().getSimpleName()
                + " type " + this.cat + ")";
    }
}
