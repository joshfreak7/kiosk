package ch.juventus.se.kiosk.helper;

import ch.juventus.se.kiosk.model.article.Article;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class ListHandler {

    private List<Article> articles;

    public ListHandler(List<Article> articles) {
        this.articles = articles;
    }

    public String getFormattedList() {
        //return basket.stream().map(article -> "\t" + article.toString() + "\n").collect(Collectors.joining());
        //return basket.stream().map(article -> "\t" + Collections.frequency(basket, article.toString())
        //        + " x " + article.toString() + "\n").collect(Collectors.joining());
        Set<Article> uniqueSet = new HashSet<>(articles);
        return uniqueSet.stream().map(article -> "\t" + Collections.frequency(articles, article)
                + " x " + article.toString() + "\n").collect(Collectors.joining());
    }
}
