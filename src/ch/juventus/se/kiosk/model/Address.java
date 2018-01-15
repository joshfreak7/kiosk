package ch.juventus.se.kiosk.model;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Address {
    private String street, location;
    private int number, plz;

    public Address(String street, int number, int plz, String location){
        this.street = street;
        this.number = number;
        this.plz = plz;
        this.location = location;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getLocation() {
        return location;
    }

    public int getPlz() {
        return plz;
    }
}
