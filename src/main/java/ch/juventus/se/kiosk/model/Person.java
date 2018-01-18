package ch.juventus.se.kiosk.model;

/**
 * This class simply represents a Person with First and LastName.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public abstract class Person implements Comparable{
    private String firstName, lastName;

    public Person() {
        this("Hans", "Muster");
    }
    public Person(String firstName, String lastName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Override
    public String toString(){
        return "\tName: " + this.firstName + " " + this.lastName;
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        if(this.firstName.equals(p.getFirstName())){
            return this.lastName.compareTo(p.getLastName());
        }
        return this.firstName.compareTo(p.getFirstName());
    }

    public String getFullName() { return firstName + " " + lastName;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
