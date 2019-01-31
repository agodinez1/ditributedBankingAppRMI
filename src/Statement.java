import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Statement implements StatementInterface, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int accountNum;
	private String accountName;
	private Date startDate;
	private Date endDate;
	private List<Transaction> transactions;

	public Statement(int accountNum, String accountName, Date startDate, Date endDate, List<Transaction> transactions) {
		super();
		this.accountNum = accountNum;
		this.accountName = accountName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.transactions = transactions;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public String getAccountName() {
		return accountName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	@Override
	public String toString() {
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/M/yyyy");
		
		return "Statement for accountNum=" + accountNum + ", accountName=" + accountName + "\n startDate=" + dateformat.format(startDate)
				+ ", endDate=" + dateformat.format(endDate) + "\n Transactions = \n" + transactions;
		
	}
}