package ch.juventus.se.kiosk.controller;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class PrintDemo {
    public void printCount(){
        try{
            for(int i = 5; i>0; i--){
                System.out.println("Counter --- " + i);
            }
        } catch(Exception e){
            System.out.println("Thread interrupted.");
        }
    }
}
