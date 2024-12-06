package com.chan.account;

import com.chan.account.JDBC.JdbcUtils;
import com.chan.account.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountManager {
   static List<Account>accounts=new ArrayList<>();
//    methods
  static Scanner scanner=new Scanner(System.in);
   public static void addAccount(){
        System.out.println("Enter account Name: ");
        String accountName=scanner.nextLine();
        System.out.println("Enter your name: ");
        String name=scanner.nextLine();
        System.out.println("Enter your email: ");
        String email=scanner.nextLine();
        System.out.println("Enter your password: ");
        String password=scanner.nextLine();
        System.out.println("Enter your balance: ");
        double balances=scanner.nextDouble();
       scanner.nextLine();
        Account account=new Account(accountName,name,email,password,balances);
//        encrypt password before adding to list
      account.setPaswwordHahed(account.BcryptPassword());
        accounts.add(account);
    }
    public static void addAccountsToDB(){
       JdbcUtils.insertAccounts(accounts);
    }

    public static void accoundByNameAndPassword(String accountName,String password){
       boolean is_ture=false;
        Account accountFound=null;
       for(Account account:accounts){
           if(account.getAccountName().equalsIgnoreCase(accountName) && account.verifyAccount(password,account.getPaswwordHahed())){
             is_ture=true;
             accountFound=account;
             break;
           }
       }
       if(is_ture){
           System.out.println("Verified!!!");
           System.out.println(accountFound);

       }else System.out.println("Account not found!!!!");

    }

    public  static void showAllAccounts(){
        System.out.println("All accounts: ");
        for (Account account :accounts){
            System.out.println(account);
        }
    }
}
