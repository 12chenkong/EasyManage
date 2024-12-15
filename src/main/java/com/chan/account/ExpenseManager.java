package com.chan.account;

import com.chan.account.JDBC.JdbcUtils;
import com.chan.account.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    List<Expense>expenseList=new ArrayList<>();
    public static void addExpense(Expense expense){
        JdbcUtils.addExpenseOperation(expense);
    }
}
