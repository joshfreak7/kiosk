package ch.juventus.se.kiosk.controller;

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
        sb.append("\t..."+NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Create Customer:"+NEW_LINE);
        sb.append("\t..."+NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Add articles to customer's basket:"+NEW_LINE);
        sb.append("\t..."+NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("Checkout customer:"+NEW_LINE);
        sb.append("\t..."+NEW_LINE);

        String text = sb.toString();
        // TODO: Textfile erstellen
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
