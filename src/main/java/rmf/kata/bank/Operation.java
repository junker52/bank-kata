package rmf.kata.bank;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Operation {

    private String operation;
    private double amount;
    private double balance;

    public Operation(String operation, double amount, double balance) {
        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("---> %s of %.1f - DATE : %s - BALANCE : %.1f", operation, amount, this.toDay(), balance);
    }

    private String toDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(new Date());
    }
}
