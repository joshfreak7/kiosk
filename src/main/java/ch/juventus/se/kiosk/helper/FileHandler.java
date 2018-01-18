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
    List<Article> readArticles();
    void writeArticles(List<Article> articles);
}
