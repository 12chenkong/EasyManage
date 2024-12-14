package com.chan.account.model;

import org.mindrot.jbcrypt.BCrypt;

public class Account {
    private int account_id;
    private String accountName;
    private String userName;
    private String email;
    private String paswwordHahed;
    private double balance=0;

    public Account(){}
    public Account(String accountName, String userName, String email, String paswwordHahed, double balance,int account_id) {
        this.accountName = accountName;
        this.userName = userName;
        this.email = email;
        this.paswwordHahed = paswwordHahed;
        this.balance = balance;
        this.account_id=account_id;
    }



    void topUpBalance(double balance){
        this.balance+=balance;
    }

   public String BcryptPassword(){
        String hashsedPassword= BCrypt.hashpw(this.paswwordHahed,BCrypt.gensalt());
        System.out.println("hashed password: "+hashsedPassword);
        return  hashsedPassword;
    }

    public boolean verifyAccount(String password,String paswwordHahed){
        Boolean isMatch=BCrypt.checkpw(password,paswwordHahed);
        return isMatch;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUserName() {
        return userName;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaswwordHahed() {
        return paswwordHahed;
    }

    public void setPaswwordHahed(String paswwordHahed) {
        this.paswwordHahed = paswwordHahed;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + accountName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", paswwordHahed='" + paswwordHahed + '\'' +
                ", balance=" + balance +
                '}';
    }
}
