package ch.juventus.se.kiosk.model.article;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Tobacco extends Article {

    private static final int AGE = 16;

    public Tobacco(String title, Price price, int count) {
        super(title, price, count);
    }

    @Override
    public String toString(){
        return super.toString()
                + " (" + this.getClass()
                + " age rating " + AGE + ")";
    }

    public static int getAGE() {
        return AGE;
    }
}
