package com.chan.account;

import com.chan.account.JDBC.JdbcUtils;
import com.chan.account.model.Expense;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseManager {
    List<Expense>expenseList=new ArrayList<>();
    public static void addExpense(Expense expense){
        JdbcUtils.addExpenseOperation(expense);
    }

    public static void showExpenseSummary(){
        JdbcUtils.summaryExpense();
    }
    public static void DeleteExpense(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter category you want to remove: ");
        String category=scanner.nextLine().toLowerCase();
        JdbcUtils.deleteExpense(category);
    }
}
