package ch.juventus.se.kiosk.controller;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class T2 implements Runnable{
    private Chat m;
    private String[] s2 = {"Hi", "I am good, what about you?", "Great!"};

    public T2(Chat m){
        this.m = m;
        new Thread(this, "Answer").start();
    }

    public void run(){
        for(int i = 0; i<s2.length; i++){
            m.Answer("Answer: " + s2[i]);
        }
    }
}
