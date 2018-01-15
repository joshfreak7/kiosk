package ch.juventus.se.kiosk.controller;

import ch.juventus.se.kiosk.model.Address;
import ch.juventus.se.kiosk.model.Employee;
import ch.juventus.se.kiosk.model.Kiosk;
import ch.juventus.se.kiosk.error.InsufficientFundsException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException{

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


        /*RunnableDemo R1 = new RunnableDemo("Thread-1");
        R1.start();

        RunnableDemo R2 = new RunnableDemo("Thread-2");
        R2.start();*/


        /*PrintDemo PD = new PrintDemo();

        ThreadDemo T1 = new ThreadDemo("Thread - 1 ", PD);
        ThreadDemo T2 = new ThreadDemo("Thread - 2", PD);

        T1.start();
        T2.start();


        try{
            T1.join();
            T2.join();
        } catch(Exception e){
            System.out.println("Interrupted");
        }*/

        /*Chat m = new Chat();
        new T1(m);
        new T2(m);*/

        String res1 = "resource 1";
        String res2 = "resource 2";
        String res3 = "resource 3";

        Thread t1 = new Thread(new SyncThread(res1,res2), "t1");
        Thread t2 = new Thread(new SyncThread(res2,res3), "t2");
        Thread t3 = new Thread(new SyncThread(res3,res1), "t3");

        t1.start();
        Thread.sleep(5000);
        t2.start();
        Thread.sleep(5000);
        t3.start();

    }

    public static boolean palindrom(String name){
        name = name.toLowerCase();

        if( name.length() < 2 ){
            return true;
        }

        if( name.charAt(0) == name.charAt( name.length() - 1 ) ){
            return palindrom( name.substring( 1, name.length() - 1 ) );
        }

        return false;
    }

    /*public void withdraw(double amount) throws InsufficientFundsException {
        if(amount <= kiosk.getBalance()){
            balance -= amount;
        } else {
            throw new InsufficientFundsException("Not enough balance available: Balance: " + balance +
                    " Amount: " + amount);
        }
    }*/



}

