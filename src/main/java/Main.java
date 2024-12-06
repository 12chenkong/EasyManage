import com.chan.account.AccountManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountManager accountManager=new AccountManager();
        for(int i=0;i<2;i++){
            accountManager.addAccount();
        }

        accountManager.showAllAccounts();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your accout name : ");
        String name=scanner.nextLine();
        System.out.println("enter your password: ");
        String pas=scanner.nextLine();
        accountManager.accoundByNameAndPassword(name,pas);
    }
}
