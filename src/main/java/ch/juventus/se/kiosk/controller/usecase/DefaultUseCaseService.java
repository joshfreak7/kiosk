package ch.juventus.se.kiosk.controller.usecase;

import ch.juventus.se.kiosk.error.InsufficientFundsException;
import ch.juventus.se.kiosk.error.NotMyEmployeeException;
import ch.juventus.se.kiosk.error.ToYoungForThisException;
import ch.juventus.se.kiosk.helper.ExcelFileHandler;
import ch.juventus.se.kiosk.helper.FileHandler;
import ch.juventus.se.kiosk.helper.ListHandler;
import ch.juventus.se.kiosk.helper.SerFileHandler;
import ch.juventus.se.kiosk.model.Address;
import ch.juventus.se.kiosk.model.article.Article;
import ch.juventus.se.kiosk.model.person.Customer;
import ch.juventus.se.kiosk.model.Kiosk;
import ch.juventus.se.kiosk.model.person.Employee;
import ch.juventus.se.kiosk.model.person.Supplier;
import ch.juventus.se.kiosk.model.article.Price;
import ch.juventus.se.kiosk.model.article.Tobacco;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class DefaultUseCaseService implements UseCaseService {

    private static final String NEW_LINE = System.getProperty("line.separator");

    @Override
    public void customerBuysArticles() {
        StringBuffer sb = new StringBuffer();

        sb.append("Create Kiosk:"+NEW_LINE);
        Kiosk kiosk = new Kiosk("Testkiosk", new Address("Testingstreet 123",8000, "Zürich"),350.00, new Supplier(new ArrayList()));
        sb.append(kiosk+NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Create Customer:"+NEW_LINE);
        Customer customer = new Customer("Another", "Customer", 25, 50.00);
        sb.append(customer + NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Add articles to customer's basket:"+NEW_LINE);
        customer.getBasket().add(new Tobacco("Zigis", new Price("CHF", 7.10), 16));
        customer.getBasket().add(new Tobacco("Zigis", new Price("CHF", 7.10), 16));
        sb.append(new ListHandler(customer.getBasket()).getFormattedList()+NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Add articles to customer's basket:"+NEW_LINE);
        customer.getBasket().add(new Tobacco("AndereTabak", new Price("CHF", 7.10), 18));
        sb.append(new ListHandler(customer.getBasket()).getFormattedList());
        sb.append(NEW_LINE);


        sb.append("Checkout customer:"+NEW_LINE);
        try{
            kiosk.checkout(customer);
        }catch(InsufficientFundsException|ToYoungForThisException ife_tyft) {
            sb.append(ife_tyft.getMessage());
        }
        sb.append("\tCustomer's basket: " + new ListHandler(customer.getBasket()).getFormattedList()+NEW_LINE);
        sb.append("\tKiosk cash: " + kiosk.getCashRegister()+NEW_LINE);
        sb.append("\tKiosk articles: " + new ListHandler(kiosk.getArticles()).getFormattedList()+NEW_LINE);
        sb.append(NEW_LINE);

        String text = sb.toString();
        // TODO: Textfile erstellen
        System.out.println(text);
    }

    @Override
    public void employeeOrdersArticles() {

        StringBuffer sb = new StringBuffer();

        sb.append("Create Kiosk:"+NEW_LINE);
        sb.append("\t..."+NEW_LINE);
        sb.append(NEW_LINE);

        // Order articles
        sb.append("Order articles:"+NEW_LINE);
        sb.append("Kiosk:"+NEW_LINE);
        sb.append("\t..."+NEW_LINE);

        String text = sb.toString();
        // TODO: Textfile erstellen
    }

    @Override
    public void employeeDoesInventory() {
        File inventoryIn = new File("C:\\Users\\Joni\\IdeaProjects\\kiosk\\src\\main\\java\\ch\\juventus\\se\\kiosk\\kioskArticles.ser");
        File inventoryOut = inventoryIn;
        FileHandler fh = new SerFileHandler(inventoryIn, inventoryOut);
        List<Article> inventory = fh.readArticles();

        File inventoryInFromExcel = new File("C:\\Users\\Joni\\IdeaProjects\\kiosk\\src\\main\\java\\ch\\juventus\\se\\kiosk\\inventoryList.xlsx");
        File inventoryOutToExcel = inventoryInFromExcel;
        fh = new ExcelFileHandler(inventoryIn, inventoryOutToExcel);

        StringBuffer sb = new StringBuffer();

        sb.append("Create Kiosk:"+NEW_LINE);
        Kiosk kiosk = new Kiosk("Testkiosk", new Address("Testingstreet 123",8000, "Zürich"),350.00, new Supplier(new ArrayList()));
        kiosk.setArticles(inventory);
        sb.append(kiosk+NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Do inventory."+NEW_LINE+"See " + inventoryOutToExcel.getPath());
        Employee empl = new Employee("Test", "Angestellter", "E0123");
        kiosk.getEmployees().add(empl);
        try {
            kiosk.doInventory(empl, fh);
        } catch (NotMyEmployeeException e) {
            sb.append(e.getMessage());
        }
        sb.append("Inventory done.");

        String text = sb.toString();
        // TODO: Textfile erstellen
    }
}
