import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int accountNum;
	private String accountName;
	private String username;
	private String password;
	private double balance;
	private List<Transaction> transactions;

	public Account(int accountNum, String accountName, String username, String password, double balance) {
		this.accountNum = accountNum;
		this.accountName = accountName;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.transactions = new ArrayList<>();
	}

	public int getAccountNum() {
		return accountNum;
	}

	public String getAccountName() {
		return accountName;
	}

	public void withdraw(int amount) {
		balance -= amount;

		Transaction t = new Transaction(new Date(), "withdrawal", (double) amount, getBalance());
		this.transactions.add(t);

		System.out.printf("Account %d has withdrawn %d from account. New balance is %f\n", accountNum, amount,
				this.balance);
	}

	public void deposit(int amount) {
		balance += amount;

		Transaction t = new Transaction(new Date(), "deposit", (double) amount, getBalance());
		this.transactions.add(t);

		System.out.printf("Account %d successfully deposited %d to account. New balance is %f\n", accountNum, amount,
				this.balance);
	}

	public double getBalance() {
		return balance;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public List<Transaction> getTransactions(Date from, Date to) {
		return transactions.stream()
				.filter(t -> !(t.getDateOfTransaction().before(from) || t.getDateOfTransaction().after(to)))
				.collect(Collectors.toList());
	}

}