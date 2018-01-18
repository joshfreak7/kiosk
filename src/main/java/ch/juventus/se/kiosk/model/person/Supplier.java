package ch.juventus.se.kiosk.model.person;

import ch.juventus.se.kiosk.helper.ListHandler;
import ch.juventus.se.kiosk.model.article.Article;
import ch.juventus.se.kiosk.model.person.Person;

import java.util.List;

/**
 * This class represents a supplier which delivers articles from Warehouse to the kiosk. Extends a simple Person.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class Supplier extends Person {
    private List<Article> stack;

    public Supplier(List<Article> stack) {
        super();
        this.stack = stack;
    }

    @Override
    public String toString(){
        return "\tName: " + super.getFullName()
                + "\n\tStack: \n" + new ListHandler(stack).getFormattedList();
    }

    public List<Article> getStack() {
        return stack;
    }
}
