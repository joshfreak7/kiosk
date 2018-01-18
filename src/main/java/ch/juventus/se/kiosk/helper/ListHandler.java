package ch.juventus.se.kiosk.helper;

import ch.juventus.se.kiosk.model.article.Alcohol;
import ch.juventus.se.kiosk.model.article.Article;
import ch.juventus.se.kiosk.model.article.Tobacco;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is all about List Handling, Calculations, Searching, etc. of articles.
 * Another options could have been to extend/implement the default List from the util-Library.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class ListHandler {

    private Set<Article> articles;

    public ListHandler(List<Article> articles) {
        this.articles = new HashSet<>(articles);
    }

    /**
     * Pretty formatted List of articles.
     * @return String of the formatted List of articles
     */
    public String getFormattedList() {
        //return basket.stream().map(article -> "\t" + article.toString() + "\n").collect(Collectors.joining());
        //return basket.stream().map(article -> "\t" + Collections.frequency(basket, article.toString())
        //        + " x " + article.toString() + "\n").collect(Collectors.joining());
        return articles.stream().map(article -> "\t" + getArticleCount(article)
                + " x " + article.toString() + "\n").collect(Collectors.joining());
    }

    /**
     * Get the total price for a specific article.
     * @param a The article to be searched
     * @return Price of that specific article
     */
    public double getAmountPerArticle(Article a) {
        return getArticleCount(a) * a.getAmount();
    }

    /**
     * Get the count of a specific article in the list.
     * @param a The article to be searched
     * @return The count of that specific article
     */
    public int getArticleCount(Article a) {
        return Collections.frequency(articles, a);
    }

    /**
     * Get the total amount of all article prices in the list
     * @return Total amount of article prices
     */
    public double getTotalAmount(){
        return articles.stream().mapToDouble(Article::getAmount).sum();
    }

    public List<Article> checkAgeRating(int ageOfCustomer) {
        List<Article> toYoungForTheseArticles = new ArrayList<>();
        for (Article a : articles) {
            if(a instanceof Alcohol){
                Alcohol alc = (Alcohol) a;
                if(ageOfCustomer < alc.getAgeRating()) {
                    toYoungForTheseArticles.add(alc);
                }
            } else if(a instanceof Tobacco) {
                Tobacco tob = (Tobacco) a;
                if(ageOfCustomer < tob.getAgeRating()) {
                    toYoungForTheseArticles.add(tob);
                }
            }
        }
        return toYoungForTheseArticles;
    }
}
