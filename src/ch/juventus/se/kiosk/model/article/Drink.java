package ch.juventus.se.kiosk.model.article;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public abstract class Drink extends Article {

    private double volume;

    public Drink(String title, Price price, double volume, int count) {
        super(title, price, count);
        this.volume = volume;
    }

    public double getVolumeInDl() {
        return this.volume / 100;
    }

    @Override
    public String toString(){
        return super.toString();
    }

    public double getVolume() {
        return volume;
    }
}
