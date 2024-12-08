import com.chan.account.AccountManager;
import com.chan.account.JDBC.JdbcUtils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        boolean is_true=true;
        while (is_true){
            System.out.println("1.add account");
            System.out.println("2.show all accounts");
            System.out.println("3.Find account by username");
            System.out.println("4.Delete account by name");
            System.out.println("-------------------------");
            System.out.println("Type choice here: ");
            int choice= scanner.nextInt();
            switch (choice){
                case 1:
                    AccountManager.addAccount();
                    AccountManager.addAccountsToDB();
                    break;
                case 2:
                    AccountManager.showAllAccounts();
                    break;
                case 3:
                    System.out.println("Enter your username: ");
                    scanner.nextLine();
                    String username=scanner.nextLine();
                    AccountManager.findAccoundByName(username);
                    break;
                case 4:
                    System.out.println("Enter account name to delete: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.println("In case 4");
                    AccountManager.deleteAccountByName(name);
                    break;
                default:
                    System.out.println("Invalid input !!!");
            }
            System.out.println("Continue 1/0");
            int stutus=scanner.nextInt();
            scanner.nextLine();
            if(stutus==0)
                is_true=false;

        }


    }
}
