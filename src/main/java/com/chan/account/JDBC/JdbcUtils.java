package com.chan.account.JDBC;

import com.chan.account.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

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
}
