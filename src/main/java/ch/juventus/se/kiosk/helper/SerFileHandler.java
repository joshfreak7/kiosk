package ch.juventus.se.kiosk.helper;

import ch.juventus.se.kiosk.model.article.Article;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class SerFileHandler implements FileHandler{

    private File inputFile, outputFile;

    public SerFileHandler(File inputFile, File outputFile){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public List<Article> readArticles() {
        Article a;
        List<Article> articles = new ArrayList<>();
        FileInputStream fileIn;
        ObjectInputStream objIn;
        try {
            fileIn = new FileInputStream(inputFile);
            objIn = new ObjectInputStream(fileIn);
            int size = objIn.readInt();
            for(int i=0; i < size; i++) {
                a = (Article) objIn.readObject();
                articles.add(a);
            }
            objIn.close();
            fileIn.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return articles;
    }

    @Override
    public void writeArticles(List<Article> articles) {
        FileOutputStream fileOut = null;
        ObjectOutputStream objOut = null;
        try {
            fileOut = new FileOutputStream(outputFile);
            objOut = new ObjectOutputStream(fileOut);
            objOut.writeInt(articles.size());
            for(Article a : articles) {
                objOut.writeObject(a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objOut != null) {
                    objOut.close();
                }
                if(fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
