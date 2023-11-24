
import interface1.Deposit;
        import interface1.Pay;

public class Bank implements Deposit, Pay {
    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(int money) {
        balance += money;
    }

    @Override
    public void pay(int money) {
        balance -= money;
    }
}
