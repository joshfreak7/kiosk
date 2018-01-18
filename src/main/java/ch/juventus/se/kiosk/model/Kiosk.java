package ch.juventus.se.kiosk.model;

import ch.juventus.se.kiosk.error.InsufficientFundsException;
import ch.juventus.se.kiosk.error.KioskClosedException;
import ch.juventus.se.kiosk.error.NotMyEmployeeException;
import ch.juventus.se.kiosk.error.ToYoungForThisException;
import ch.juventus.se.kiosk.helper.FileHandler;
import ch.juventus.se.kiosk.helper.ListHandler;
import ch.juventus.se.kiosk.model.article.Article;
import ch.juventus.se.kiosk.model.person.Customer;
import ch.juventus.se.kiosk.model.person.Employee;
import ch.juventus.se.kiosk.model.person.Supplier;
import org.apache.log4j.Logger;

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
    final static Logger logger = Logger.getLogger(Kiosk.class);


    public Kiosk(String name, Address address, double cashRegister, Supplier supplier) {
        this.name = name;
        this.address = address;
        this.cashRegister = cashRegister;
        this.supplier = supplier;
        this.employees = new ArrayList<>();
        this.articles = new ArrayList<>();
        this.isOpen = false;
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

    /**
     * Checkout for customer to buy articles.
     * @param customer Customer, who wants to checkout
     * @throws KioskClosedException Thrown, when the kiosk is closed. Checkout not possible
     * @throws InsufficientFundsException Thrown, when the customer has not enough cash
     * @throws ToYoungForThisException Thrown, when the customer is to young for certain articles
     */
    public void checkout(Customer customer) throws KioskClosedException, InsufficientFundsException, ToYoungForThisException {
        logger.debug("Customer BasketSize before checkout: " + customer.getBasket().size());
        logger.debug("CashRegister of Kiosk: " + this.getName() + " before checkout has amount of " + cashRegister);
        if(isOpen) {
            ListHandler lh = new ListHandler(customer.getBasket());
            List<Article> toYoung = lh.checkAgeRating(customer.getAge());
            if (!toYoung.isEmpty()) {
                customer.getBasket().removeAll(toYoung);
                logger.debug("Customer: " + customer.toString() + " is to young to buy articles: " + toYoung);
                throw new ToYoungForThisException("Dear " + customer.getFullName() + ", you're to young to buy the following Article(s): \n" + toYoung.toString());
            }
            double totalCost = lh.getTotalAmount();
            if (customer.getCash() >= totalCost) {
                customer.setCash(customer.getCash() - totalCost);
                cashRegister += totalCost;
                customer.getBasket().stream().forEach(article -> articles.remove(article));
                customer.getBasket().clear();
            } else {
                logger.debug("Customer: " + customer.toString() + " has not enough cash: " + customer.getCash() + " Total Cost: " + totalCost);
                throw new InsufficientFundsException("Dear " + customer.getFullName() + ", you do NOT have enough cash! Please put some articles back!\n");
            }
        } else {
            throw new KioskClosedException("Dear " + customer.getFullName() + ", this kiosk is currently closed! Please come back later!\n");
        }
        logger.debug("Customer: " + customer.toString() + " BasketSize after checkout: " + customer.getBasket().size());
        logger.debug("CashRegister of Kiosk: " + this.getName() + " after checkout has amount of " + cashRegister);
    }

    /**
     * Do Inventory. Done by an employee of the kiosk.
     * @param empl Employee, who does the inventory
     * @param fh FileHandler to save inventory to excel
     * @throws NotMyEmployeeException Is thrown when the employee is not a member of the kiosk
     */
    public void doInventory(Employee empl, FileHandler fh) throws NotMyEmployeeException{
        if(!employees.contains(empl)) {
            logger.error("Employee: " + empl.toString() + " tried to do the inventory of Kiosk: " + this.getName());
            throw new NotMyEmployeeException("You're not an Employee of this Kiosk! Therefore, you cannot do the inventory for this Kiosk!");
        } else {
            empl.doInventory(articles, fh);
        }
    }

    /**
     * Close or open the kiosk. Only Employees of this kiosk can do so.
     * @param empl The Employee, who wants to open or close the Kiosk.
     * @param open describes whether the kiosk has opened or closed.
     * @throws NotMyEmployeeException Thrown when employee is not a member of the kiosk.
     */
    public void setOpen(Employee empl, boolean open) throws NotMyEmployeeException {
        if(!employees.contains(empl)) {
            logger.error("Employee: " + empl.toString() + " tried to open/close Kiosk: " + this.getName());
            throw new NotMyEmployeeException("You're not an Employee of this Kiosk! Therefore, you cannot open/close this Kiosk!");
        } else {
            logger.info("Employee: " + empl.toString() + " open/close Kiosk: " + this.getName());
            isOpen = open;
            logger.debug("Kiosk: " + this.getName() + " is open: " + isOpen);
        }
    }

    /**
     * Order new Articles when inventory runs out at kiosk. Done through an employee of the kiosk.
     * @param empl Employee, who orders the new Article.
     * @param articlesToBeOrdered Articles which should be ordered.
     * @throws NotMyEmployeeException Thrown when employee is not a member of the kiosk.
     */
    public void orderArticles(Employee empl, List<Article> articlesToBeOrdered) throws NotMyEmployeeException, InsufficientFundsException {
        logger.debug("CashRegister of Kiosk: " + this.getName() + " before ordering articles has amount of " + cashRegister);
        if(!employees.contains(empl)) {
            logger.error("Employee: " + empl.toString() + " tried to order articles for Kiosk: " + this.getName());
            throw new NotMyEmployeeException("You're not an Employee of this Kiosk! Therefore, you cannot order Articles for this Kiosk!");
        } else {
            ListHandler lh = new ListHandler(articlesToBeOrdered);
            double totalCost = lh.getTotalAmount();
            if (getCashRegister() >= totalCost) {
                setCashRegister(getCashRegister() - totalCost);
                articlesToBeOrdered.stream().forEach(article -> articles.add(article));
                articlesToBeOrdered.stream().forEach(article -> supplier.getStack().remove(article));
            } else {
                logger.error("Kiosk: " + this.getName() + " has not enough cash in the bank. CashRegister: " + cashRegister + " Total cost for orders: " + totalCost);
                throw new InsufficientFundsException("Dear Employee " + empl.getFullName() + ", your kiosk does NOT have enough cash! Please order less articles!\n");
            }
        }
        logger.debug("CashRegister of Kiosk: " + this.getName() + " after ordering articles has amount of " + cashRegister);
    }

    @Override
    public String toString(){
        return "\tName: " + this.name
                + "\n\tAddress: " + this.address.toString()
                + "\n\tCash: " + this.cashRegister
                + "\n\tEmployees: " + this.employees.toString()
                + "\n\tOpen: " + this.isOpen
                + "\n\tArticles: \n" + new ListHandler(articles).getFormattedList();
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
