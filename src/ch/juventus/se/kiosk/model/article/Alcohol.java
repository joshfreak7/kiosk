package ch.juventus.se.kiosk.model.article;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Alcohol extends Drink {

    //private static final int AGE = 18;
    private int ageRating;

    public Alcohol(String title, Price price, double volume, int ageRating) {
        super(title, price, volume);
        this.ageRating = ageRating;
    }

    @Override
    public String toString(){
        return super.toString()
                + " (" + this.getClass().getSimpleName()
                + " age rating " + ageRating
                + ", volume in dl " + getVolumeInDl() + ")";
    }

    public int getAgeRating() {
        return ageRating;
    }
}
