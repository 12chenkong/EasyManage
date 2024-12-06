package com.chan.account.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {

    public static Connection Connection(){
        String url="jdbc:mysql://localhost:3306/easymanage";
        String user="root";
        String password="1234567";
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(url,user,password);

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;

    }
}
