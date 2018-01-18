package ch.juventus.se.kiosk.controller.usecase;

import ch.juventus.se.kiosk.error.InsufficientFundsException;
import ch.juventus.se.kiosk.error.KioskClosedException;
import ch.juventus.se.kiosk.error.NotMyEmployeeException;
import ch.juventus.se.kiosk.error.ToYoungForThisException;
import ch.juventus.se.kiosk.helper.*;
import ch.juventus.se.kiosk.model.Address;
import ch.juventus.se.kiosk.model.article.*;
import ch.juventus.se.kiosk.model.person.Customer;
import ch.juventus.se.kiosk.model.Kiosk;
import ch.juventus.se.kiosk.model.person.Employee;
import ch.juventus.se.kiosk.model.person.Supplier;

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
        sb.append("******* USECASE: customerBuysArticles *******"+NEW_LINE);

        // Import Articles for Kiosk
        File inventoryIn = new File("C:\\Users\\Joni\\IdeaProjects\\kiosk\\src\\main\\java\\ch\\juventus\\se\\kiosk\\kioskArticles.ser");
        FileHandler fh = new SerFileHandler(inventoryIn, null);
        List<Article> inventory = fh.readArticles();

        sb.append("Create Kiosk:"+NEW_LINE);
        Kiosk kiosk = new Kiosk("Testkiosk", new Address("Testingstreet 123",8000, "Zürich"),350.00, new Supplier(new ArrayList()));
        //kiosk.getArticles().addAll(inventory);
        kiosk.setArticles(inventory);

        //Add employee, could be loaded through a file as well
        Employee empl = new Employee("Test", "Angestellter", "E0123");
        kiosk.getEmployees().add(empl);
        try {
            kiosk.setOpen(empl, true);
        } catch (NotMyEmployeeException e) {
            sb.append(e.getMessage());
        }
        sb.append(kiosk+NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Create Customer:"+NEW_LINE);
        Customer customer = new Customer("Another", "Customer", 25, 50.00);
        sb.append(customer + NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Add articles to customer's basket:"+NEW_LINE);
        //customer.getBasket().add(kiosk.getArticles().get(1));
        customer.getBasket().add(new Snack("Zweifel Paprika", new Price("CHF", 6.70), SnackType.SALTY));
        customer.getBasket().add(new Snack("Zweifel Paprika", new Price("CHF", 6.70), SnackType.SALTY));
        customer.getBasket().add(new Softdrink("RedBull", new Price("CHF", 3.50), 250));
        customer.getBasket().add(new Softdrink("RedBull", new Price("CHF", 3.50), 250));
        customer.getBasket().add(new Softdrink("RedBull", new Price("CHF", 3.50), 250));
        sb.append(new ListHandler(customer.getBasket()).getFormattedList()+NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Add more articles to customer's basket:"+NEW_LINE);
        customer.getBasket().add(new Snack("Salami", new Price("CHF", 5.8), SnackType.SANDWICH));
        sb.append(new ListHandler(customer.getBasket()).getFormattedList());
        sb.append(NEW_LINE);


        sb.append("Checkout customer:"+NEW_LINE);
        try{
            kiosk.checkout(customer);
        }catch(KioskClosedException|InsufficientFundsException|ToYoungForThisException ife_tyft) {
            sb.append(ife_tyft.getMessage());
        }
        sb.append("\tCustomer's basket: " + new ListHandler(customer.getBasket()).getFormattedList()+NEW_LINE);
        sb.append("\tKiosk cash: " + kiosk.getCashRegister()+NEW_LINE);
        sb.append("\tKiosk articles: \n" + new ListHandler(kiosk.getArticles()).getFormattedList()+NEW_LINE);
        sb.append(NEW_LINE);

        // Save to TextFile
        String text = sb.toString();
        File outputUC = new File("C:\\Users\\Joni\\IdeaProjects\\kiosk\\src\\main\\java\\ch\\juventus\\se\\kiosk\\customerBuysArticles.txt");
        TextFileHandler tfh = new TextFileHandler(null, outputUC);
        tfh.writeUseCase(text);

        System.out.println(text);
    }

    @Override
    public void employeeOrdersArticles() {
        StringBuffer sb = new StringBuffer();
        sb.append("******* USECASE: employeeOrdersArticles *******"+NEW_LINE);

        // Import Articles for Kiosk
        File inventoryIn = new File("C:\\Users\\Joni\\IdeaProjects\\kiosk\\src\\main\\java\\ch\\juventus\\se\\kiosk\\kioskArticles.ser");
        FileHandler fh = new SerFileHandler(inventoryIn, null);
        List<Article> inventoryKiosk = fh.readArticles();

        // Import Articles for Supplier
        inventoryIn = new File("C:\\Users\\Joni\\IdeaProjects\\kiosk\\src\\main\\java\\ch\\juventus\\se\\kiosk\\supplierArticles.ser");
        fh = new SerFileHandler(inventoryIn, null);
        List<Article> stackSupplier = fh.readArticles();

        sb.append("Create Kiosk:"+NEW_LINE);
        Kiosk kiosk = new Kiosk("Testkiosk", new Address("Testingstreet 123",8000, "Zürich"),350.00, new Supplier(new ArrayList()));
        //kiosk.getArticles().addAll(inventory);
        kiosk.setArticles(inventoryKiosk);

        //Could be loaded through a file as well
        Employee empl = new Employee("Test", "Angestellter", "E0123");
        kiosk.getEmployees().add(empl);
        sb.append(kiosk+NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Supplier:"+NEW_LINE);
        Supplier suppl = new Supplier(stackSupplier);
        kiosk.setSupplier(suppl);
        sb.append(suppl+NEW_LINE);

        // Order articles
        sb.append("Order articles:"+NEW_LINE);
        List<Article> tobeOrdered = new ArrayList<>();
        tobeOrdered.add(new Snack("Zweifel Paprika", new Price("CHF", 6.70), SnackType.SALTY));
        tobeOrdered.add(new Snack("Zweifel Paprika", new Price("CHF", 6.70), SnackType.SALTY));
        tobeOrdered.add(new Softdrink("RedBull", new Price("CHF", 3.50), 250));
        tobeOrdered.add(new Softdrink("RedBull", new Price("CHF", 3.50), 250));
        tobeOrdered.add(new Softdrink("RedBull", new Price("CHF", 3.50), 250));
        sb.append(new ListHandler(tobeOrdered).getFormattedList()+NEW_LINE);
        try {
            kiosk.orderArticles(empl, tobeOrdered);
        } catch (NotMyEmployeeException|InsufficientFundsException e) {
            sb.append(e.getMessage());
        }
        sb.append("Kiosk:"+NEW_LINE);
        sb.append(kiosk+NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Supplier:"+NEW_LINE);
        sb.append(kiosk.getSupplier()+NEW_LINE);
        sb.append(NEW_LINE);

        // Save to TextFile
        String text = sb.toString();
        File outputUC = new File("C:\\Users\\Joni\\IdeaProjects\\kiosk\\src\\main\\java\\ch\\juventus\\se\\kiosk\\employeeOrdersArticles.txt");
        TextFileHandler tfh = new TextFileHandler(null, outputUC);
        tfh.writeUseCase(text);

        System.out.println(text);
    }

    @Override
    public void employeeDoesInventory() {
        StringBuffer sb = new StringBuffer();
        sb.append("******* USECASE: employeeDoesInventory *******"+NEW_LINE);

        // Import Articles first
        File inventoryIn = new File("C:\\Users\\Joni\\IdeaProjects\\kiosk\\src\\main\\java\\ch\\juventus\\se\\kiosk\\kioskArticles.ser");
        File inventoryOut = inventoryIn;
        FileHandler fh = new SerFileHandler(inventoryIn, null);
        List<Article> inventory = fh.readArticles();

        File inventoryInFromExcel = new File("C:\\Users\\Joni\\IdeaProjects\\kiosk\\src\\main\\java\\ch\\juventus\\se\\kiosk\\inventoryList.xlsx");
        File inventoryOutToExcel = inventoryInFromExcel;
        fh = new ExcelFileHandler(inventoryIn, inventoryOutToExcel);

        sb.append("Create Kiosk:"+NEW_LINE);
        Kiosk kiosk = new Kiosk("Testkiosk", new Address("Testingstreet 123",8000, "Zürich"),350.00, new Supplier(new ArrayList()));
        kiosk.setArticles(inventory);

        //Add employee, could be loaded through a file as well
        Employee empl = new Employee("Test", "Angestellter", "E0123");
        kiosk.getEmployees().add(empl);
        sb.append(kiosk+NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Do inventory."+NEW_LINE+"See " + inventoryOutToExcel.getPath() + NEW_LINE);
        try {
            kiosk.doInventory(empl, fh);
        } catch (NotMyEmployeeException e) {
            sb.append(e.getMessage());
        }
        sb.append("Inventory done.");

        // Save to TextFile
        String text = sb.toString();
        File outputUC = new File("C:\\Users\\Joni\\IdeaProjects\\kiosk\\src\\main\\java\\ch\\juventus\\se\\kiosk\\employeeDoesInventory.txt");
        TextFileHandler tfh = new TextFileHandler(null, outputUC);
        tfh.writeUseCase(text);

        System.out.println(text);
    }
}
