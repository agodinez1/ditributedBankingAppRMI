import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Remote Object 
 * 
 * @author Andre Godinez
 *
 */
@SuppressWarnings("deprecation")
public class Bank extends UnicastRemoteObject implements BankInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Account> accounts;
	private String sessionID;
	private boolean loggedIn;
	
	protected Bank(int port) throws RemoteException {
		super(port);

		// Instantiate of Accounts
		Account acc1 = new Account(1, "name1", "username1", "password1", 100);
		Account acc2 = new Account(2, "name2", "username2", "password2", 200);
		Account acc3 = new Account(3, "name3", "username3", "password3", 300);

		// Create list of Accounts and add
		List<Account> accountList = new ArrayList<>();
		accountList.add(acc1);
		accountList.add(acc2);
		accountList.add(acc3);

		this.accounts = accountList;
		this.loggedIn = false;
		this.sessionID = null;
	}

	public boolean validateSessionID(String sessionID) {
		Pattern pattern = Pattern.compile("\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b");
		Matcher matcher = pattern.matcher(sessionID);

		return matcher.matches();
	}

	/**
	 * Timer for session - changes loggedIn boolean value to false
	 */
	public void runTimer() {
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
			   loggedIn = false;
			   System.out.println("Session timeout.");
			  }
			}, 5*60*1000);
	}
	
	/**
	 * Log in function to the bank
	 *
	 */
	public String login(String username, String password) throws RemoteException, InvalidLogin {

		Account temp = accounts.stream().filter(account -> username.equals(account.getUsername()))
				.filter(account -> password.equals(account.getPassword())).findAny().orElse(null);

		if (temp != null) {
			this.sessionID = UUID.randomUUID().toString();

			System.out.println("Sample session ID: " + this.sessionID);
			this.loggedIn = true;
			runTimer();
			
			return "Sucessfully logged in. Session valid for 5 minutes.";
			
		} else {
			throw new InvalidLogin("Incorrect username/password. Failed to log in...");
		}
	}

	/**
	 * Deposit function.
	 */
	public String deposit(int accountnum, int amount) throws RemoteException, InvalidSessionException {

		if (this.sessionID != null) {
			if (validateSessionID(this.sessionID) && loggedIn) {

				Account temp = accounts.stream().filter(account -> accountnum == (account.getAccountNum())).findAny()
						.orElse(null);

				if (temp != null) {
					temp.deposit(amount);

					return "Sucessfully deposited " + amount + ".";
				} else {
					return "Invalid account number. Account does not exist.";
				}
			} else {
				return "Invalid session ID.";
			}
		}
		return "Expired/No sessionID. Please Log in.";
	}

	/**
	 * Withdraw function.
	 */
	public String withdraw(int accountnum, int amount) throws RemoteException, InvalidSessionException {

		if (this.sessionID != null) {
			if (validateSessionID(this.sessionID) && loggedIn) {

				Account temp = accounts.stream().filter(account -> accountnum == (account.getAccountNum())).findAny()
						.orElse(null);

				if (temp != null) {
					temp.withdraw(amount);

					return "Sucessfully withdrawn " + amount + ".";
				} else {
					return "Invalid account number. Account does not exist.";
				}
			} else {
				throw new InvalidSessionException("Expired/No sessionID. Please Log in.");
			}
		}
		throw new InvalidSessionException("Expired/No sessionID. Please Log in.");
	}

	/**
	 * Gets statement of a specific account
	 */
	public String getStatement(int accountNum, Date from, Date to) throws RemoteException, InvalidSessionException {

		if (this.sessionID != null) {
			if (validateSessionID(this.sessionID) && loggedIn) {

				Account temp = accounts.stream().filter(account -> accountNum == (account.getAccountNum())).findAny()
						.orElse(null);

				if (temp != null) {
					String accountName = temp.getAccountName();
					List<Transaction> transactions = temp.getTransactions(from, to);

					Statement statement = new Statement(accountNum, accountName, from, to, transactions);

					return statement.toString();

				} else {
					return "Invalid account number. Account does not exist.";
				}

			} else {
				throw new InvalidSessionException("Expired/No sessionID. Please Log in.");
			}
		}
		throw new InvalidSessionException("Expired/No sessionID. Please Log in.");
	}

	/**
	 * Checks balance of an account
	 */
	public String inquiry(int accountnum) throws RemoteException, InvalidSessionException {
		if (this.sessionID != null) {
			if (validateSessionID(this.sessionID) && loggedIn) {

				Account temp = accounts.stream().filter(account -> accountnum == (account.getAccountNum())).findAny()
						.orElse(null);

				if (temp != null) {

					return "Current balance for account " + accountnum + " = " + temp.getBalance();
				} else {
					return "Invalid account number. Account does not exist.";
				}
			} else {
				throw new InvalidSessionException("Expired/No sessionID. Please Log in.");
			}
		}
		throw new InvalidSessionException("Expired/No sessionID. Please Log in.");
	}

	/**
	 * Run the server
	 */
	public static void main(String args[]) {
		try {
			// First reset our Security manager
			System.setSecurityManager(new RMISecurityManager());
			System.out.println("Security manager set");

			int port = Integer.parseInt(args[0]);
			System.out.println("Port selected = " + port);

			Registry registry = LocateRegistry.createRegistry(port);

			// Create an instance of the local object
			Bank bank = new Bank(port);
			System.out.println("Bank server created");

			registry.rebind("Bank", bank);

			System.out.println("Name rebind completed");
			System.out.println("Server ready for requests!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}