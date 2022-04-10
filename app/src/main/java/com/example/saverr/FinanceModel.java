package com.example.saverr;

public class FinanceModel {

    private int transactionNum;

    private double incomeAmount;
    private String incomeType;
    private String incomeMonth;
    private String incomeYear;

    //constructors


    public FinanceModel(int transactionNum, double incomeAmount, String incomeType, String incomeMonth, String incomeYear) {
        this.transactionNum = transactionNum;
        this.incomeAmount = incomeAmount;
        this.incomeType = incomeType;
        this.incomeMonth = incomeMonth;
        this.incomeYear = incomeYear;
    }

    public FinanceModel() {
    }

    //toString (For Printing)

    @Override
    public String toString() {
        return "Income Amount: $" + incomeAmount + '\n' +
                "Income Type: " + incomeType + '\n' +
                "Income Month: " + incomeMonth + '\n' +
                "Income Year: " + incomeYear;
    }


    //Getters and Setters


    public int getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(int transactionNum) {
        this.transactionNum = transactionNum;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public String getIncomeMonth() {
        return incomeMonth;
    }

    public void setIncomeMonth(String incomeMonth) {
        this.incomeMonth = incomeMonth;
    }

    public String getIncomeYear() {
        return incomeYear;
    }

    public void setIncomeYear(String incomeYear) {
        this.incomeYear = incomeYear;
    }
}
