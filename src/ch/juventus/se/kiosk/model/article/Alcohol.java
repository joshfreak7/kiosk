package ch.juventus.se.kiosk.model.article;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Alcohol extends Drink {

    private static final int AGE = 18;

    public Alcohol(String title, Price price, double volume, int count) {
        super(title, price, volume, count);
    }

    @Override
    public String toString(){
        return super.toString()
                + " (" + this.getClass().getSimpleName()
                + " age rating " + AGE
                + ", volume in dl " + getVolumeInDl() + ")";
    }

    public static int getAGE() {
        return AGE;
    }
}
