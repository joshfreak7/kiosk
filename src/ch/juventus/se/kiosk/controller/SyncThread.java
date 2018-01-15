package ch.juventus.se.kiosk.controller;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class SyncThread implements Runnable {
    private String res1;
    private String res2;

    public SyncThread(String o1, String o2){
        this.res1 = o1;
        this.res2 = o2;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " acquiring lock on " + res1);
        synchronized (res1) {
            System.out.println(name + " acquired lock on " + res1);
            work();
            System.out.println(name + " acquiring lock on "+res2);
            synchronized (res2){
                System.out.println(name + " acquired lock on "+res2);
                work();
            }
            System.out.println(name + " released lock on " + res2);
        }
        System.out.println(name + " released lock on "+res1);
        System.out.println(name + " finished execution.");
    }

    private void work() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
