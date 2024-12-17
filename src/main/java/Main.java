import com.chan.account.AccountManager;
import com.chan.account.ExpenseManager;
import com.chan.account.JDBC.JdbcUtils;
import com.chan.account.chatbot.SimpleChatBot;
import com.chan.account.model.Expense;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
        Scanner scanner=new Scanner(System.in);
        boolean is_true=true;
        while (is_true){
            System.out.println("1.add account");
            System.out.println("2.show all accounts");
            System.out.println("3.Find account by username");
            System.out.println("4.Delete account by name");
            System.out.println("5.Add expense");
            System.out.println("6.Ask chatbot ");
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
                    AccountManager.deleteAccountByName(name);
                    break;
                case 5:
                    System.out.println("Enter amount: ");
                    double cost=scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter category: ");
                    String category=scanner.nextLine();

                    // Creating an object of expense
                    Expense expense=new Expense(null,100,category);
                    ExpenseManager.addExpense(expense);
                    System.out.println(expense);
                    break;
                case 6:
                    SimpleChatBot.questions();
                    SimpleChatBot.chatBotResponse();
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
