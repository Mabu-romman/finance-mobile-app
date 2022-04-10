package com.example.saverr;

public class ExpenseModel {

    private int transactionNum;

    private double expenseAmount;
    private String expenseType;
    private String expenseMonth;
    private String expenseYear;

    //Constructors

    public ExpenseModel(int transactionNum, double expenseAmount, String expenseType, String expenseMonth, String expenseYear) {
        this.transactionNum = transactionNum;
        this.expenseAmount = expenseAmount;
        this.expenseType = expenseType;
        this.expenseMonth = expenseMonth;
        this.expenseYear = expenseYear;
    }

    public ExpenseModel() {
    }

    //toString

    @Override
    public String toString() {
        return "Expense Amount: $" + expenseAmount + '\n' +
                "Expense Type: " + expenseType + '\n' +
                "Expense Month: " + expenseMonth + '\n' +
                "Expense Year: " + expenseYear;
    }

    //Getters and Setters


    public int getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(int transactionNum) {
        this.transactionNum = transactionNum;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseMonth() {
        return expenseMonth;
    }

    public void setExpenseMonth(String expenseMonth) {
        this.expenseMonth = expenseMonth;
    }

    public String getExpenseYear() {
        return expenseYear;
    }

    public void setExpenseYear(String expenseYear) {
        this.expenseYear = expenseYear;
    }
}
