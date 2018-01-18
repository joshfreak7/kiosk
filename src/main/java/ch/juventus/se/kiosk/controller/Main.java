package ch.juventus.se.kiosk.controller;

import ch.juventus.se.kiosk.controller.usecase.DefaultUseCaseService;
import ch.juventus.se.kiosk.controller.usecase.UseCaseService;
import ch.juventus.se.kiosk.helper.FileHandler;
import ch.juventus.se.kiosk.helper.SerFileHandler;
import ch.juventus.se.kiosk.model.article.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        setUpArticles();

        UseCaseService useCaseService = new DefaultUseCaseService();

        useCaseService.customerBuysArticles();

        useCaseService.employeeOrdersArticles();

        useCaseService.employeeDoesInventory();

    }

    public static void setUpArticles(){
        File outputFile = new File("./kioskArticles.ser");
        File outputFileSuppl = new File("./supplierArticles.ser");
        FileHandler fh = new SerFileHandler(null, outputFile);

        List<Article> articles = new ArrayList<>();
        // Adding 10 salty Snacks
        for(int i = 0; i < 10; i++) {
            articles.add(new Snack("Zweifel Paprika", new Price("CHF", 6.70), SnackType.SALTY));
        }
        // Adding 20 sweet Snacks
        for(int i = 0; i < 20; i++) {
            articles.add(new Snack("Zimt Kaugummi", new Price("CHF", 1.50), SnackType.SWEET));
        }
        // Adding 5 Alcohol (AR 20)
        for(int i = 0; i < 5; i++) {
            articles.add(new Alcohol("Vodka 0.7L", new Price("CHF", 20.0), 700, 18));
        }
        // Adding 10 fruit Snacks
        for(int i = 0; i < 10; i++) {
            articles.add(new Snack("Apfel", new Price("CHF", 1.0), SnackType.FRUIT));
        }
        // Adding 15 Alcohol (AR 16)
        for(int i = 0; i < 10; i++) {
            articles.add(new Alcohol("Quöllfrisch 0.5L", new Price("CHF", 1.7), 500, 16));
        }
        // Adding 15 RedBull
        for(int i = 0; i < 15; i++) {
            articles.add(new Softdrink("RedBull", new Price("CHF", 3.50), 250));
        }
        // Adding 7 Tobacco (AR 16)
        for(int i = 0; i < 15; i++) {
            articles.add(new Tobacco("Winston BLUE", new Price("CHF", 8.10), 16));
        }
        // Adding 10 Newspaper
        for(int i = 0; i < 15; i++) {
            articles.add(new Newspaper("NZZ", new Price("CHF", 3.20), NewspaperType.LOCAL));
        }
        // Adding 5 sandwich Snacks
        for(int i = 0; i < 5; i++) {
            articles.add(new Snack("Salami", new Price("CHF", 5.8), SnackType.SANDWICH));
        }

        fh.writeArticles(articles);

        fh = new SerFileHandler(null, outputFileSuppl);
        fh.writeArticles(articles);
    }
}

