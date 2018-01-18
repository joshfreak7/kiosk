package ch.juventus.se.kiosk.helper;

import ch.juventus.se.kiosk.model.article.Article;

import java.io.*;
import java.util.List;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class TextFileHandler implements FileHandler {

    private File inputFile, outputFile;

    public TextFileHandler(File inputFile, File outputFile){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    /**
     * Reads articles from a Text File.
     * @return Returns a List of Articles
     */
    public List<Article> readArticles() {
        return null;
    }

    /**
     * Saves articles to a Text File.
     * @param articles List of articles to be saved.
     */
    public void writeArticles(List<Article> articles) {
    }

    public void writeUseCase(String output) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(outputFile);
            fw.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.flush();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
