package ch.juventus.se.kiosk.controller;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class T1 implements Runnable{
    private Chat m;
    private String[] s1 = {"Hi", "How are you?", "I am also doing fine!"};

    public T1(Chat m){
        this.m = m;
        new Thread(this, "Question").start();
    }

    public void run(){
        for(int i = 0; i<s1.length; i++){
            m.Question("Question: " + s1[i]);
        }
    }
}
