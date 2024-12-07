package com.chan.account;

import com.chan.account.JDBC.JdbcUtils;
import com.chan.account.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public static void findAccoundByName(String username){
    Account account=JdbcUtils.fetchByName(username);
      if(account.getAccountName()==null)
          System.out.println("Account not found!!!");
      else {
          System.out.println("Here is Your account: ");
          System.out.println(account);
      }

    }

    public  static void showAllAccounts(){
        System.out.println("All accounts: ");
        for (Account account :accounts){
            System.out.println(account);
        }
    }

    public static void deleteAccountByName(String AccountName){
       boolean status=JdbcUtils.DeleteAccountByname(AccountName);
       if(status){
           System.out.println("Delete successfully.");
       }else System.out.println("not found info to delete");
    }
}
