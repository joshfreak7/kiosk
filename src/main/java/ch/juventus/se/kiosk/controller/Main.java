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

        File inputFile = new File("C:\\Users\\Joni\\IdeaProjects\\kiosk\\src\\main\\java\\ch\\juventus\\se\\kiosk\\articlesIN.json");
        File outputFile = new File("C:\\Users\\Joni\\IdeaProjects\\kiosk\\src\\main\\java\\ch\\juventus\\se\\kiosk\\kioskArticles.ser");

        FileHandler fh = new SerFileHandler(inputFile, outputFile);

        List<Article> articles = new ArrayList<>();
        articles.add(new Softdrink("RedBull", new Price("CHF", 3.50), 250));
        articles.add(new Snack("Zweifel Chips", new Price("CHF", 6.70), SnackType.SALTY));
        fh.writeArticles(articles);

        UseCaseService useCaseService = new DefaultUseCaseService();

        //useCaseService.customerBuysArticles();

        //useCaseService.employeeOrdersArticles();

        useCaseService.employeeDoesInventory();

        /*FileHandler fh = new FileHandler(new File("C:\\Users\\Joni\\Desktop\\input.txt"), new File("C:\\Users\\Joni\\Desktop\\output.txt"));
        fh.write(fh.read());*/


        /*FileReader fr = null;
        FileWriter fw = null;
        int i;
        try {
            fr = new FileReader("C:\\Users\\Joni\\Desktop\\input.txt");
            fw = new FileWriter("C:\\Users\\Joni\\Desktop\\output.txt");

            while((i=fr.read()) != -1){
                System.out.println((char)i);
                fw.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fw != null){
                try {
                    fw.flush();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/



        /*Kiosk kiosk = new Kiosk("TestKiosk", new Address("Mustergasse", 12, 1234, "Entenhausen"),70.00);

        System.out.println("Before withdraw: " + kiosk.getBalance());

        try {
            kiosk.withdraw(100.00);
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        }

        System.out.println("After withdraw: " + kiosk.getBalance());*/

        /*List<Employee> employees = new ArrayList<Employee>();

        employees.add(new Employee("Hans", "Muster"));
        employees.add(new Employee("Peter", "Kuster"));
        employees.add(new Employee("Peter", "Keller"));
        employees.add(new Employee("Lisa", "Muster"));

        Collections.sort(employees);

        for(int i = 0 ; i < employees.size(); i++){
            System.out.println(employees.get(i).toString());
        }*/


        /*System.out.println("Anna is a palindrom: " + palindrom("Anna"));
        System.out.println("Korb is a palindrom: " + palindrom("Korb"));
        System.out.println("Mein Computer is a palindrom: " + palindrom("Mein Computer"));
        System.out.println("!+Level+! is a palindrom: " + palindrom("!+Level+!"));
        System.out.println("Anna Bob Anna is a palindrom: " + palindrom("Anna Bob Anna"));
        System.out.println("Kamel is a palindrom: " + palindrom("Kamel"));
        System.out.println("Stein is a palindrom: " + palindrom("Stein"));
        System.out.println("Sugus is a palindrom: " + palindrom("Sugus"));
        System.out.println("Anananas is a palindrom: " + palindrom("Anananas"));
        System.out.println("Anna Bob Anna is a palindrom: " + palindrom("Anna Bob Anna"));*/

    }

    /*public static boolean palindrom(String name){
        name = name.toLowerCase();

        if( name.length() < 2 ){
            return true;
        }

        if( name.charAt(0) == name.charAt( name.length() - 1 ) ){
            return palindrom( name.substring( 1, name.length() - 1 ) );
        }

        return false;
    }*/

    /*public void withdraw(double amount) throws InsufficientFundsException {
        if(amount <= kiosk.getBalance()){
            balance -= amount;
        } else {
            throw new InsufficientFundsException("Not enough balance available: Balance: " + balance +
                    " Amount: " + amount);
        }
    }*/



}

