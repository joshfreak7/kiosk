package ch.juventus.se.kiosk.model;

import ch.juventus.se.kiosk.error.InsufficientFundsException;
import ch.juventus.se.kiosk.helper.ListHandler;
import ch.juventus.se.kiosk.model.article.Article;

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
        setOpen(false);
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

    public void checkout(Customer customer) throws InsufficientFundsException {
        double totalCost = customer.getBasket().stream().mapToDouble(a -> a.getCost()).sum();
        if(customer.getCash() >= totalCost) {
            customer.setCash(customer.getCash() - totalCost);
            cashRegister += totalCost;
            articles.removeAll(customer.getBasket());
            customer.getBasket().clear();
        } else {
            throw new InsufficientFundsException("Dear " + customer.getFullName() + ", you do NOT have enough cash! Please put some articles back!\n");
        }
    }

    /**
     * Close or open the kiosk.
     * @param open describes whether the kiosk is opened or closed.
     */
    public void setOpen(boolean open) { isOpen = open; }

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
}