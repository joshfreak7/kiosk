package ch.juventus.se.kiosk.model.article;

/**
 * An article of type Softdrink. Extends a normal Drink.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Softdrink extends Drink {

    public Softdrink(String title, Price price, double volume) {
        super(title, price, volume);
    }

    @Override
    public String toString(){
        return super.toString()
                + " (" + this.getClass().getSimpleName()
                + " volume in dl " + getVolumeInDl() + ")";
    }
}
