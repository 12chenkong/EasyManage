package com.chan.account.JDBC;

import com.chan.account.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class JdbcUtils {
    static Connection  connect=JdbcConnection.Connection();
    public static void insertAccounts(List<Account> accounts){
        String query="INSERT INTO accounts(account_name,username,email,password_hashed,balance) " +
                "VALUES(?,?,?,?,?)";

        try{
           PreparedStatement preparedStatement=connect.prepareStatement(query);
           for(Account account:accounts){
               preparedStatement.setString(1,account.getAccountName());
               preparedStatement.setString(2,account.getUserName());
               preparedStatement.setString(3,account.getEmail());
               preparedStatement.setString(4,account.getPaswwordHahed());
               preparedStatement.setDouble(5,account.getBalance());
               int rowEffected=preparedStatement.executeUpdate();
               if(rowEffected>0)
                   System.out.println("inserted successfully!");
           }
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    public static void fetchAllAccounts(){
        final String query="SELECT* FROM accounts";
        try {
            Statement stm=connect.createStatement();
            ResultSet rs=stm.executeQuery(query);
            while (rs.next()){
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

    public static boolean DeleteAccountByname(String accoutName){
        final String query="DELETE  FROM accounts WHERE account_name= ?";
        boolean status=false;
        try {
            PreparedStatement psm=connect.prepareStatement(query);
            psm.setString(1,accoutName);
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


}
