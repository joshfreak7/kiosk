package ch.juventus.se.kiosk.model;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 16.01.2018
 */
public class CashRegister {
    private double balance;

    public CashRegister(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
