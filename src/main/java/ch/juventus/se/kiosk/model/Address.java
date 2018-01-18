package ch.juventus.se.kiosk.model;

/**
 * Address of the kiosk. Stored in a separate class. Separation of concerns.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Address {
    private String street, location;
    private int plz;

    public Address(String street, int plz, String location){
        this.street = street;
        this.plz = plz;
        this.location = location;
    }

    @Override
    public String toString(){
        return this.street + ", " + this.plz + " " + this.location;
    }

    public String getStreet() {
        return street;
    }

    public String getLocation() {
        return location;
    }

    public int getPlz() {
        return plz;
    }
}
