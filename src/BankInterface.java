import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface BankInterface extends Remote {

    public String login(String username, String password) throws RemoteException, InvalidLogin;

    public String deposit(int accountnum, int amountD) throws RemoteException, InvalidSessionException;

    public String withdraw(int accountnum, int amount) throws RemoteException, InvalidSessionException;

    public String inquiry(int accountnum) throws RemoteException, InvalidSessionException;

    public String getStatement(int accountNum, Date from, Date to) throws RemoteException, InvalidSessionException;

}