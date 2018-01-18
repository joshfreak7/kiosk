package ch.juventus.se.kiosk.model.article;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Tobacco extends Article {

    //private static final int AGE = 16;
    private int ageRating;

    public Tobacco(String title, Price price, int ageRating) {
        super(title, price);
        this.ageRating = ageRating;
    }

    @Override
    public String toString(){
        return super.toString()
                + " (" + this.getClass().getSimpleName()
                + " age rating " + ageRating + ")";
    }

    public int getAgeRating() {
        return ageRating;
    }
}
