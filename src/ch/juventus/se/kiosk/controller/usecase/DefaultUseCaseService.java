package ch.juventus.se.kiosk.controller.usecase;

import ch.juventus.se.kiosk.error.InsufficientFundsException;
import ch.juventus.se.kiosk.model.Address;
import ch.juventus.se.kiosk.model.Customer;
import ch.juventus.se.kiosk.model.Kiosk;
import ch.juventus.se.kiosk.model.Supplier;
import ch.juventus.se.kiosk.model.article.Article;
import ch.juventus.se.kiosk.model.article.Price;
import ch.juventus.se.kiosk.model.article.Tobacco;

import java.util.ArrayList;

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
        sb.append(customer.getBasketFormatted()+NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Add articles to customer's basket:"+NEW_LINE);
        customer.getBasket().add(new Tobacco("AndereTabak", new Price("CHF", 7.10), 18));
        sb.append(customer.getBasketFormatted());
        sb.append(NEW_LINE);


        sb.append("Checkout customer:"+NEW_LINE);
        try{
            kiosk.checkout(customer);
        }catch(InsufficientFundsException ife) {
            sb.append(ife.getMessage());
        }

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

        StringBuffer sb = new StringBuffer();

        sb.append("Create Kiosk:"+NEW_LINE);
        sb.append("\t..."+NEW_LINE);
        sb.append(NEW_LINE);

        // TODO: Inventar in Excel File
        sb.append("Do inventory."+NEW_LINE+"See Path/to/file/filename.xlsx");

        String text = sb.toString();
        // TODO: Textfile erstellen
    }
}
