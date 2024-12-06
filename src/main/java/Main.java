import com.chan.account.AccountManager;
import com.chan.account.JDBC.JdbcUtils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountManager accountManager=new AccountManager();


        for(int i=0;i<1;i++){
            accountManager.addAccount();
        }
        accountManager.showAllAccounts();
        accountManager.addAccountsToDB();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your accout name : ");
        String name=scanner.nextLine();
        System.out.println("enter your password: ");
        String pas=scanner.nextLine();
        accountManager.accoundByNameAndPassword(name,pas);

    }
}
