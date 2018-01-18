package ch.juventus.se.kiosk.helper;

import ch.juventus.se.kiosk.model.article.Article;

import java.io.File;
import java.util.List;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public interface FileHandler {
    /**
     * Reads articles from a File.
     * @return Returns a List of Articles
     */
    List<Article> readArticles();

    /**
     * Saves articles to a File.
     * @param articles List of articles to be saved.
     */
    void writeArticles(List<Article> articles);
}
