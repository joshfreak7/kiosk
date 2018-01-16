package ch.juventus.se.kiosk.model.article;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Softdrink extends Drink {

    public Softdrink(String title, Price price, double volume, int count) {
        super(title, price, volume, count);
    }

    @Override
    public String toString(){
        return super.toString()
                + " (" + this.getClass()
                + " volume in dl " + getVolumeInDl() + ")";
    }
}
