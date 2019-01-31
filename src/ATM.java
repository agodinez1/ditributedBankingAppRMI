import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ATM {

	public static void main(String args[]) throws Exception {
		try {
			// get server address
			String serverAddress = args[0];

			// get port
			int port = Integer.parseInt(args[1]);

			// get operation
			String operation = args[2];

			// Looking up the registry for the remote object
			BankInterface bank = (BankInterface) Naming.lookup("//" + serverAddress + ":" + port + "/Bank");

			if (operation.equals("login")) {

				System.out.println("Logging in...");

				String username = args[3];
				String password = args[4];

				System.out.println(bank.login(username, password));
			}
			if (operation.equals("withdraw")) {
				int account = Integer.parseInt(args[3]);
				int amount = Integer.parseInt(args[4]);

				System.out.println(bank.withdraw(account, amount));
			}
			if (operation.equals("deposit")) {
				int account = Integer.parseInt(args[3]);
				int amount = Integer.parseInt(args[4]);

				System.out.println(bank.deposit(account, amount));
			}
			if (operation.equals("inquiry")) {
				int accountnum = Integer.parseInt(args[3]);
				
				System.out.println(bank.inquiry(accountnum));
			}
			if (operation.equals("statement")) {
				SimpleDateFormat dateformat = new SimpleDateFormat("dd/M/yyyy");
				
				int accountNum = Integer.parseInt(args[3]);
				Date fromDate = dateformat.parse(args[4]);
				Date toDate = dateformat.parse(args[5]);
				
				System.out.println(bank.getStatement(accountNum, fromDate, toDate));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}