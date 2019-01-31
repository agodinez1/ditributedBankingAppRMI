import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private Date dateOfTransaction;
	private String transactionType;
	private double amount;
	private double newBalance;
	
	public Transaction(Date dateOfTransaction, String transactionType, double amount, double newBalance) {
		this.dateOfTransaction = dateOfTransaction;
		this.transactionType = transactionType;
		this.amount = amount;
		this.newBalance = newBalance;
	}
	
	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public double getAmount() {
		return amount;
	}
	
	public double getNewBalance() {
		return newBalance;
	}

	@Override
	public String toString() {
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/M/yyyy");
		
		return "[dateOfTransaction=" + dateformat.format(dateOfTransaction) + " transactionType=" + transactionType
				+ " amount=" + amount + " newBalance=" + newBalance + "]\n";
	}
	
}
