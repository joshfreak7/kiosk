package ch.juventus.se.kiosk.helper;

import ch.juventus.se.kiosk.model.article.Article;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Further extension to be able to load and save articles in JSON-Format. Feature for the future.
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class JsonFileHandler implements FileHandler {

    private File inputFile, outputFile;

    public JsonFileHandler(File inputFile, File outputFile){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public List<Article> readArticles() {
        List<Article> articles = new ArrayList<>();
        try {
            FileReader fr = new FileReader(inputFile);
            int c;
            String bla = "";
            while((c = fr.read()) != -1) {
                System.out.println((char)c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public void writeArticles(List<Article> articles) {
        /*Gson gson = new GsonBuilder().create();
        Article article = new Softdrink("Testing Article", new Price("CHF", 10.30), 120);

        try {
            gson.toJson(article, new FileWriter(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
