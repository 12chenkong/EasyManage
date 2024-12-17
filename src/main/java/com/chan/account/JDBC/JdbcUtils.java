package com.chan.account.JDBC;

import com.chan.account.model.Account;
import com.chan.account.model.Expense;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class JdbcUtils {
    static Connection  connect=JdbcConnection.Connection();
    public static void insertAccounts(List<Account> accounts){
        String query="INSERT INTO accounts(account_name,username,email,password_hashed,balance,acocunt_id) " +
                "VALUES(?,?,?,?,?,?)";

        try{
           PreparedStatement preparedStatement=connect.prepareStatement(query);
           for(Account account:accounts){
               preparedStatement.setString(1,account.getAccountName());
               preparedStatement.setString(2,account.getUserName());
               preparedStatement.setString(3,account.getEmail());
               preparedStatement.setString(4,account.getPaswwordHahed());
               preparedStatement.setDouble(5,account.getBalance());
               preparedStatement.setInt(6,account.getAccount_id());
               int rowEffected=preparedStatement.executeUpdate();
               if(rowEffected>0)
                   System.out.println("inserted successfully!");
           }
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    public static void fetchAllAccounts(){
        final String query="SELECT *FROM accounts";
        try {
            Statement stm=connect.createStatement();
            ResultSet rs=stm.executeQuery(query);
            while (rs.next()){
                System.out.println("Account Id: "+rs.getString("acocunt_id"));
                System.out.println("Account name: "+rs.getString("account_name"));
                System.out.println("Username: "+rs.getString("username"));
                System.out.println("Email: "+rs.getString("email"));
                System.out.println("Balance: "+rs.getString("balance")+"$");
                System.out.println("----------------------------");

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Account fetchByName(String username){
        final String query="SELECT * FROM accounts WHERE username= ? ";
        Account accountReturn=new Account();
        try {
           PreparedStatement ptm= connect.prepareStatement(query);
           ptm.setString(1,username);
           ResultSet rs=ptm.executeQuery();
               while (rs.next()){
                   accountReturn.setAccountName(rs.getString("account_name"));
                   accountReturn.setUserName(rs.getString("username"));
                   accountReturn.setBalance(rs.getDouble("balance"));
                   accountReturn.setPaswwordHahed(rs.getString("password_hashed"));
                   accountReturn.setEmail(rs.getString("email"));
               }
        }catch (Exception e){
            e.printStackTrace();
        }
        return accountReturn;

    }

    public static boolean DeleteAccountByname(String accountName){
        final String query="DELETE  FROM accounts WHERE account_name= ?";
        boolean status=false;
        try {
            PreparedStatement psm=connect.prepareStatement(query);
            psm.setString(1,accountName);
            int rowEffected=psm.executeUpdate();
            if(rowEffected>0)
                status=true;
            else
                status =false;

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return status;

    }

    public static void addExpenseOperation(Expense expense){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter account ID to play: ");
        String account_id=scanner.nextLine();

        final String expenseSQL="INSERT INTO expense (date, category, amount, time, account_id) " +
                "VALUES (?,?,?,?,?) ";
        final  String updateBalanceSQL="UPDATE accounts SET balance=balance- ? WHERE acocunt_id=? ";
        final String checkBalanceSQL = "SELECT balance FROM accounts WHERE acocunt_id = ?";

        try {
            JdbcConnection.Connection().setAutoCommit(false);

            //        Insert a expense record to database
            try(   PreparedStatement checkBalancepsm= JdbcConnection.Connection().prepareStatement(checkBalanceSQL);
                   PreparedStatement psm= JdbcConnection.Connection().prepareStatement(expenseSQL);
                   PreparedStatement updateBalance=JdbcConnection.Connection().prepareStatement(updateBalanceSQL)

            ) {
//            Check if balance is sufficient
                checkBalancepsm.setString(1,account_id);
                ResultSet rs=checkBalancepsm.executeQuery();
                if(rs.next()){
                    Double currentBalance=rs.getDouble("balance");
                    System.out.println(currentBalance);
                    if(expense.getAmount()>= currentBalance){
                        System.out.println("Insufficient balance for the expense. ");
                        throw new SQLException("Insufficient balance for the expense.");
                    }
                    psm.setString(1,expense.getDate());
                    psm.setString(2,expense.getCategory());
                    psm.setDouble(3,expense.getAmount());
                    psm.setString(4,expense.getTime());
                    psm.setString(5,account_id);
                    psm.executeUpdate();
// deduct money from the account
                    updateBalance.setDouble(1,expense.getAmount());
                    updateBalance.setString(2,account_id);
                    updateBalance.executeUpdate();

                }else {
                    System.out.println("Account with ID "+ account_id+ " does not exit");
                    throw new SQLException("Account with ID "+ account_id+" does not exit");
                }
                JdbcConnection.Connection().commit();
                System.out.println("Transaction committed successfully!");

            }
        }
        catch (Exception e){
            if(JdbcConnection.Connection()!=null){
               try {
                   JdbcConnection.Connection().rollback();
                   System.err.println("Transaction rolled back due to an error: " + e.getMessage());

               }catch (SQLException rollBackEx){
                   rollBackEx.getMessage();
               }

            }

        }finally {
            if (JdbcConnection.Connection()!=null){
                try {
                    JdbcConnection.Connection().setAutoCommit(true);
                    JdbcConnection.Connection().close();
                }catch (Exception ex){
                    System.err.println("Error closing connection: " + ex.getMessage());
                }

            }
        }

    }
    public  static void summaryExpense(){
        final String sql= """
                SELECT  account_name,expense.amount,expense.date,category,time FROM accounts
                INNER  JOIN  expense ON accounts.acocunt_id = expense.account_id;
                """;

        try {
          PreparedStatement pst= JdbcConnection.Connection().prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            System.out.println("Account name\tAmount\tDate\t\tCategory\tTime");
            while (rs.next()){
                double amount=rs.getDouble("amount");
                String date =rs.getString("date");
                String category=rs.getString("category");
                String time=rs.getString("time");
                String accountName=rs.getString("account_name");


                System.out.printf("%s\t\t\t%.2f$\t%s\t%s\t%s\t",accountName,amount,date,category,time);
                System.out.println();


            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }



    }

    public static void deleteExpense(String category){
        String delete= """
                DELETE FROM expense WHERE category=?;
                """;
        try {
            PreparedStatement ptm=JdbcConnection.Connection().prepareStatement(delete);
            ptm.setString(1,category);
           int row= ptm.executeUpdate();
           if(row==0)
               System.out.println("No Expense to delete!!");

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }




}
