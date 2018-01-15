package ch.juventus.se.kiosk.controller;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class ThreadDemo extends Thread {
    private Thread t;
    private String threadName;
    PrintDemo PD;

    ThreadDemo(String name, PrintDemo pd) {
        threadName = name;
        PD = pd;
    }

    public void run(){
        synchronized (PD){
            PD.printCount();
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null){
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
