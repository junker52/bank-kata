package rmf.kata.bank;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountTest {

    private Account clientAccount;

    @Before
    public void setUp() {
        this.clientAccount = new Account();
    }

    @Test
    public void shouldHaveEmptyBalance_WhenStartup() {
        assertEquals(0.0, clientAccount.getBalance(),  0);
    }

    @Test
    public void shouldHave10IntoBalance_WhenDipositing10() {
        this.clientAccount.deposit(10.0);
        assertEquals(10.0, this.clientAccount.getBalance(), 0);
    }

    @Test
    public void shouldHave25IntoBalance_WhenDipositing10AndDipositing15() {
        this.clientAccount.deposit(10.0);
        this.clientAccount.deposit(15.0);
        assertEquals(25.0, this.clientAccount.getBalance(),  0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowException_WhenWithdrawal10OnStartUp() {
        this.clientAccount.withdrawal(10.0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowException_WhenDeposit10AndWithdrawal11() {
        this.clientAccount.deposit(10.0);
        this.clientAccount.withdrawal(11.0);
    }

    @Test
    public void shouldHaveBalance10_WhenDeposit30AndWithdrawal20() {
        this.clientAccount.deposit(30.0);
        this.clientAccount.withdrawal(20.0);
        assertEquals(10.0, this.clientAccount.getBalance(), 0);
    }

    @Test
    public void shouldPrintZeroOperations_WhenStartingAccount() {
        String result = this.clientAccount.printOperations();
        assertEquals("", result);
    }

    @Test
    public void shouldPrintOneOperations_WhenDepositing10AndPrintingOperations() {
        this.clientAccount.deposit(10.0);
        String result = this.clientAccount.printOperations();
        assertTrue(result.contains(this.line("DEPOSIT", 10.0, 10.0)));
    }

    @Test
    public void shouldPrintTwoOperations_WhenDepositing10AndWithdrawal5PrintingOperations() {
        this.clientAccount.deposit(20.0);
        this.clientAccount.withdrawal(5.0);
        String result = this.clientAccount.printOperations();
        assertTrue(result.contains(this.line("DEPOSIT", 20.0, 20.0)));
        assertTrue(result.contains(this.line("WITHDRAWAL", 5.0, 15.0)));
    }

    @Test
    public void shouldPrintFiveOperations_WhenExecutingFiveOperations() {
        this.clientAccount.deposit(15.0);
        this.clientAccount.withdrawal(5.0);
        this.clientAccount.withdrawal(10.0);
        this.clientAccount.deposit(10.0);
        this.clientAccount.deposit(5.0);
        String result = this.clientAccount.printOperations();
        assertTrue(result.contains(this.line("DEPOSIT", 15.0, 15.0)));
        assertTrue(result.contains(this.line("WITHDRAWAL", 5.0, 10.0)));
        assertTrue(result.contains(this.line("WITHDRAWAL", 10.0, 0.0)));
        assertTrue(result.contains(this.line("DEPOSIT", 10.0, 10.0)));
        assertTrue(result.contains(this.line("DEPOSIT", 5.0, 15.0)));
    }


    private String line(String operation, double amount, double balance) {
        return String.format("---> %s of %.1f - DATE : %s - BALANCE : %.1f", operation, amount, this.toDay(), balance);
    }

    private String toDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(new Date());
    }
}
