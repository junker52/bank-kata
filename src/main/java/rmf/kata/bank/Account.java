package rmf.kata.bank;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private double balance;

    private List<Operation> operations = new ArrayList<Operation>();

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
        this.operations.add(new Operation("DEPOSIT", amount, this.balance));
    }

    public void withdrawal(double amount) {
        if (amount > this.balance) {
            throw new UnsupportedOperationException("No enough founds");
        }
        this.balance -= amount;
        this.operations.add(new Operation("WITHDRAWAL", amount, this.balance));
    }

    public String printOperations() {
        StringBuilder result = new StringBuilder("");
        for (Operation operation: this.operations) {
            result.append(operation.toString());
            result.append("\n");
        }
        return result.toString();
    }
}
