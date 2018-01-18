package ch.juventus.se.kiosk.model;

import ch.juventus.se.kiosk.error.InsufficientFundsException;
import ch.juventus.se.kiosk.error.NotMyEmployeeException;
import ch.juventus.se.kiosk.error.ToYoungForThisException;
import ch.juventus.se.kiosk.helper.FileHandler;
import ch.juventus.se.kiosk.helper.ListHandler;
import ch.juventus.se.kiosk.model.article.Article;
import ch.juventus.se.kiosk.model.person.Customer;
import ch.juventus.se.kiosk.model.person.Employee;
import ch.juventus.se.kiosk.model.person.Supplier;

import java.util.ArrayList;
import java.util.List;

/**
 * Kiosk sells Articles to customers. Employees working at the Kiosk.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Kiosk {
    private String name;
    private Address address;
    private boolean isOpen;
    private List<Employee> employees;
    private List<Article> articles;
    private double cashRegister;
    private Supplier supplier;


    public Kiosk(String name, Address address, double cashRegister, Supplier supplier) {
        this.name = name;
        this.address = address;
        this.cashRegister = cashRegister;
        this.supplier = supplier;
        this.employees = new ArrayList<>();
        this.articles = new ArrayList<>();
        this.isOpen = true;
    }

    public Kiosk(String name, Address address, boolean isOpen, List<Employee> employees, List<Article> articles, double cashRegister, Supplier supplier) {
        this.name = name;
        this.address = address;
        this.isOpen = isOpen;
        this.employees = employees;
        this.articles = articles;
        this.cashRegister = cashRegister;
        this.supplier = supplier;
    }

    public void checkout(Customer customer) throws InsufficientFundsException, ToYoungForThisException {
        ListHandler lh = new ListHandler(customer.getBasket());
        List<Article> toYoung = lh.checkAgeRating(customer.getAge());
        if(!toYoung.isEmpty()) {
            customer.getBasket().removeAll(toYoung);
            throw new ToYoungForThisException("Dear " + customer.getFullName() + ", you're to young to buy the following Article(s): \n" + toYoung.toString());
        }
        double totalCost = lh.getTotalAmount();
        if(customer.getCash() >= totalCost) {
            customer.setCash(customer.getCash() - totalCost);
            cashRegister += totalCost;
            articles.removeAll(customer.getBasket());
            customer.getBasket().clear();
        } else {
            throw new InsufficientFundsException("Dear " + customer.getFullName() + ", you do NOT have enough cash! Please put some articles back!\n");
        }
    }

    public void doInventory(Employee empl, FileHandler fh) throws NotMyEmployeeException{
        if(!employees.contains(empl)) {
            throw new NotMyEmployeeException("You're not an Employee of this Kiosk! Therefore, you cannot do the inventory for this Kiosk!");
        } else {
            // TODO Logging
            empl.doInventory(articles, fh);
        }
    }

    /**
     * Close or open the kiosk. Only Employees of this kiosk can do so.
     * @param open describes whether the kiosk has opened or closed.
     */
    public void setOpen(Employee empl, boolean open) throws NotMyEmployeeException {
        if(!employees.contains(empl)) {
            throw new NotMyEmployeeException("You're not an Employee of this Kiosk! Therefore, you cannot open/close this Kiosk!");
        } else {
            // TODO Logging
            isOpen = open;
        }
    }

    @Override
    public String toString(){
        return "\tName: " + this.name
                + "\n\tAddress: " + this.address.toString()
                + "\n\tCash: " + this.cashRegister
                + "\n\tEmployees: " + this.employees.toString()
                + "\n\tArticles: " + new ListHandler(articles).getFormattedList();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public double getCashRegister() {
        return cashRegister;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen() { return isOpen; }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setCashRegister(double cashRegister) {
        this.cashRegister = cashRegister;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
