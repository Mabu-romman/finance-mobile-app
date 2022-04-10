package com.example.saverr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String INCOME_TABLE = "INCOME_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_INCOME_AMOUNT = "INCOME_AMOUNT";
    public static final String COLUMN_INCOME_TYPE = "INCOME_TYPE";
    public static final String COLUMN_INCOME_MONTH = "INCOME_MONTH";
    public static final String COLUMN_INCOME_YEAR = "INCOME_YEAR";

    public static final String EXPENSE_TABLE = "EXPENSE_TABLE";
    public static final String COLUMN_ID2 = "ID";
    public static final String COLUMN_EXPENSE_AMOUNT = "EXPENSE_AMOUNT";
    public static final String COLUMN_EXPENSE_TYPE = "EXPENSE_TYPE";
    public static final String COLUMN_EXPENSE_MONTH = "EXPENSE_MONTH";
    public static final String COLUMN_EXPENSE_YEAR = "EXPENSE_YEAR";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "budget.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableStatement = "CREATE TABLE " + INCOME_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_INCOME_AMOUNT + " DOUBLE, " + COLUMN_INCOME_TYPE + " TEXT, " + COLUMN_INCOME_MONTH + " TEXT, " + COLUMN_INCOME_YEAR + " TEXT)";

        sqLiteDatabase.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String createTableStatement2 = "CREATE TABLE " + EXPENSE_TABLE + "(" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EXPENSE_AMOUNT + " DOUBLE, " + COLUMN_EXPENSE_TYPE + " TEXT, " + COLUMN_EXPENSE_MONTH + " TEXT, " + COLUMN_EXPENSE_YEAR + " TEXT)";

        sqLiteDatabase.execSQL(createTableStatement2);

    }

    public boolean addOne (FinanceModel financeModel){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_INCOME_AMOUNT, financeModel.getIncomeAmount());
        cv.put(COLUMN_INCOME_TYPE, financeModel.getIncomeType());
        cv.put(COLUMN_INCOME_MONTH, financeModel.getIncomeMonth());
        cv.put(COLUMN_INCOME_YEAR, financeModel.getIncomeYear());

        long insert = sqLiteDatabase.insert(INCOME_TABLE, null, cv);
        if (insert==-1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean addOne (ExpenseModel expenseModel){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EXPENSE_AMOUNT, expenseModel.getExpenseAmount());
        cv.put(COLUMN_EXPENSE_TYPE, expenseModel.getExpenseType());
        cv.put(COLUMN_EXPENSE_MONTH, expenseModel.getExpenseMonth());
        cv.put(COLUMN_EXPENSE_YEAR, expenseModel.getExpenseYear());

        long insert = sqLiteDatabase.insert(EXPENSE_TABLE, null, cv);
        if (insert==-1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteOne (FinanceModel financeModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "DELETE FROM " + INCOME_TABLE + " WHERE " + COLUMN_ID + " = " + financeModel.getTransactionNum();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteOne (ExpenseModel expenseModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "DELETE FROM " + EXPENSE_TABLE + " WHERE " + COLUMN_ID2 + " = " + expenseModel.getTransactionNum();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public List<FinanceModel> getAll(){

        List<FinanceModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + INCOME_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through the cursor (result set) and create new customer objects. Put them into the return list.

            do{
                int transactionID =  cursor.getInt(0);
                Double incomeAmount = cursor.getDouble(1);
                String incomeType = cursor.getString(2);
                String incomeMonth = cursor.getString(3);
                String incomeYear = cursor.getString(4);

                FinanceModel newFinanceModel = new FinanceModel(transactionID, incomeAmount, incomeType, incomeMonth, incomeYear);
                returnList.add(newFinanceModel);

            } while(cursor.moveToNext());
        } else {

            //do not add anything to the list.

        }

        // close both the cursor and the db when done.
        cursor.close();
        sqLiteDatabase.close();

        return returnList;
    }

    public List<ExpenseModel> getAllExpense(){

        List<ExpenseModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + EXPENSE_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through the cursor (result set) and create new customer objects. Put them into the return list.

            do{
                int transactionID =  cursor.getInt(0);
                Double expenseAmount = cursor.getDouble(1);
                String expenseType = cursor.getString(2);
                String expenseMonth = cursor.getString(3);
                String expenseYear = cursor.getString(4);

                ExpenseModel newExpenseModel = new ExpenseModel(transactionID, expenseAmount, expenseType, expenseMonth, expenseYear);
                returnList.add(newExpenseModel);

            } while(cursor.moveToNext());
        } else {

            //do not add anything to the list.

        }

        // close both the cursor and the db when done.
        cursor.close();
        sqLiteDatabase.close();

        return returnList;
    }

    public List<FinanceModel> getIncomeMonth(String enteredIncomeMonth) {

        List<FinanceModel> returnList = new ArrayList<>();

        /*if(enteredIncomeMonth.equals("All")){

            String queryString = "SELECT * FROM " + INCOME_TABLE + " WHERE " + COLUMN_INCOME_MONTH + " = " + "\"" + enteredIncomeMonth + "\"";

        }
         */

        String queryString = "SELECT * FROM " + INCOME_TABLE + " WHERE " + COLUMN_INCOME_MONTH + " = " + "\"" + enteredIncomeMonth + "\"";

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through the cursor (result set) and create new customer objects. Put them into the return list.

            do{
                int transactionID =  cursor.getInt(0);
                Double incomeAmount = cursor.getDouble(1);
                String incomeType = cursor.getString(2);
                String incomeMonth = cursor.getString(3);
                String incomeYear = cursor.getString(4);

                FinanceModel newFinanceModel = new FinanceModel(transactionID, incomeAmount, incomeType, incomeMonth, incomeYear);
                returnList.add(newFinanceModel);

            } while(cursor.moveToNext());
        } else {

            //do not add anything to the list.

        }

        // close both the cursor and the db when done.
        cursor.close();
        sqLiteDatabase.close();

        return returnList;
    }

    public List<FinanceModel> getIncomeFilter(String enteredIncomeMonth, String enteredIncomeYear, String enteredIncomeType) {

        List<FinanceModel> returnList = new ArrayList<>();

        String queryString;


        if (!"All".equals(enteredIncomeMonth) && enteredIncomeYear.equals("All") && enteredIncomeType.equals("All")) {

            //Month = !, Year = All, Type = All
            queryString = "SELECT * FROM " + INCOME_TABLE + " WHERE " + COLUMN_INCOME_MONTH + " = " + "\"" + enteredIncomeMonth + "\"";

        } else if (!"All".equals(enteredIncomeMonth) && !"All".equals(enteredIncomeYear) && enteredIncomeType.equals("All")){

            //Month = !, Year = !, Type = All
            queryString = "SELECT * FROM " + INCOME_TABLE + " WHERE " + COLUMN_INCOME_MONTH + " = " + "\"" + enteredIncomeMonth + "\"" + " AND " +  COLUMN_INCOME_YEAR + " = " + "\"" + enteredIncomeYear + "\"";

        } else if (!"All".equals(enteredIncomeMonth) && !"All".equals(enteredIncomeYear) && !"All".equals(enteredIncomeType)){

            //Month = !, Year = !, Type = !
            queryString = "SELECT * FROM " + INCOME_TABLE + " WHERE " + COLUMN_INCOME_MONTH + " = " + "\"" + enteredIncomeMonth + "\"" + " AND " +  COLUMN_INCOME_YEAR + " = " + "\"" + enteredIncomeYear + "\"" + " AND " + COLUMN_INCOME_TYPE + " = " + "\"" + enteredIncomeType + "\"";

        } else if (enteredIncomeMonth.equals("All") && !"All".equals(enteredIncomeYear) && !"All".equals(enteredIncomeType)){

            //Month = All, Year = !, Type = !
            queryString = "SELECT * FROM " + INCOME_TABLE + " WHERE " + COLUMN_INCOME_YEAR + " = " + "\"" + enteredIncomeYear + "\"" + " AND " + COLUMN_INCOME_TYPE + " = " + "\"" + enteredIncomeType + "\"";

        } else if (!"All".equals(enteredIncomeMonth) && enteredIncomeYear.equals("All") && !"All".equals(enteredIncomeType)) {

            //Month = !, Year = All, Type = !
            queryString = "SELECT * FROM " + INCOME_TABLE + " WHERE " + COLUMN_INCOME_MONTH + " = " + "\"" + enteredIncomeMonth + "\"" + " AND " + COLUMN_INCOME_TYPE + " = " + "\"" + enteredIncomeType + "\"";

        } else if (enteredIncomeMonth.equals("All") && !"All".equals(enteredIncomeYear) && enteredIncomeType.equals("All")) {

            //Month = All, Year = !, Type = All
            queryString = "SELECT * FROM " + INCOME_TABLE + " WHERE " + COLUMN_INCOME_YEAR + " = " + "\"" + enteredIncomeYear + "\"";

        } else if (enteredIncomeMonth.equals("All") && enteredIncomeYear.equals("All") && !"All".equals(enteredIncomeType)){

            //Month = All, Year = All, Type = 1
            queryString = "SELECT * FROM " + INCOME_TABLE + " WHERE " + COLUMN_INCOME_TYPE + " = " + "\"" + enteredIncomeType + "\"";

        } else {

            //Month = All, Year = All, Type = All
            queryString = "SELECT * FROM " + INCOME_TABLE;
        }

       // String queryString = "SELECT * FROM " + INCOME_TABLE + " WHERE " + COLUMN_INCOME_MONTH + " = " + "\"" + enteredIncomeYear + "\"";

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through the cursor (result set) and create new customer objects. Put them into the return list.

            do{
                int transactionID =  cursor.getInt(0);
                Double incomeAmount = cursor.getDouble(1);
                String incomeType = cursor.getString(2);
                String incomeMonth = cursor.getString(3);
                String incomeYear = cursor.getString(4);

                FinanceModel newFinanceModel = new FinanceModel(transactionID, incomeAmount, incomeType, incomeMonth, incomeYear);
                returnList.add(newFinanceModel);

            } while(cursor.moveToNext());
        } else {

            //do not add anything to the list.

        }

        // close both the cursor and the db when done.
        cursor.close();
        sqLiteDatabase.close();

        return returnList;
    }

    public List<ExpenseModel> getExpenseFilter(String enteredExpenseMonth, String enteredExpenseYear, String enteredExpenseType) {

        List<ExpenseModel> returnList = new ArrayList<>();

        String queryString;


        if (!"All".equals(enteredExpenseMonth) && enteredExpenseYear.equals("All") && enteredExpenseType.equals("All")) {

            //Month = !, Year = All, Type = All
            queryString = "SELECT * FROM " + EXPENSE_TABLE + " WHERE " + COLUMN_EXPENSE_MONTH + " = " + "\"" + enteredExpenseMonth + "\"";

        } else if (!"All".equals(enteredExpenseMonth) && !"All".equals(enteredExpenseYear) && enteredExpenseType.equals("All")){

            //Month = !, Year = !, Type = All
            queryString = "SELECT * FROM " + EXPENSE_TABLE + " WHERE " + COLUMN_EXPENSE_MONTH + " = " + "\"" + enteredExpenseMonth + "\"" + " AND " +  COLUMN_EXPENSE_YEAR + " = " + "\"" + enteredExpenseYear + "\"";

        } else if (!"All".equals(enteredExpenseMonth) && !"All".equals(enteredExpenseYear) && !"All".equals(enteredExpenseType)){

            //Month = !, Year = !, Type = !
            queryString = "SELECT * FROM " + EXPENSE_TABLE + " WHERE " + COLUMN_EXPENSE_MONTH + " = " + "\"" + enteredExpenseMonth + "\"" + " AND " +  COLUMN_EXPENSE_YEAR + " = " + "\"" + enteredExpenseYear + "\"" + " AND " + COLUMN_EXPENSE_TYPE + " = " + "\"" + enteredExpenseType + "\"";

        } else if (enteredExpenseMonth.equals("All") && !"All".equals(enteredExpenseYear) && !"All".equals(enteredExpenseType)){

            //Month = All, Year = !, Type = !
            queryString = "SELECT * FROM " + EXPENSE_TABLE + " WHERE " + COLUMN_EXPENSE_YEAR + " = " + "\"" + enteredExpenseYear + "\"" + " AND " + COLUMN_EXPENSE_TYPE + " = " + "\"" + enteredExpenseType + "\"";

        } else if (!"All".equals(enteredExpenseMonth) && enteredExpenseYear.equals("All") && !"All".equals(enteredExpenseType)) {

            //Month = !, Year = All, Type = !
            queryString = "SELECT * FROM " + EXPENSE_TABLE + " WHERE " + COLUMN_EXPENSE_MONTH + " = " + "\"" + enteredExpenseMonth + "\"" + " AND " + COLUMN_EXPENSE_TYPE + " = " + "\"" + enteredExpenseType + "\"";

        } else if (enteredExpenseMonth.equals("All") && !"All".equals(enteredExpenseYear) && enteredExpenseType.equals("All")) {

            //Month = All, Year = !, Type = All
            queryString = "SELECT * FROM " + EXPENSE_TABLE + " WHERE " + COLUMN_EXPENSE_YEAR + " = " + "\"" + enteredExpenseYear + "\"";

        } else if (enteredExpenseMonth.equals("All") && enteredExpenseYear.equals("All") && !"All".equals(enteredExpenseType)){

            //Month = All, Year = All, Type = 1
            queryString = "SELECT * FROM " + EXPENSE_TABLE + " WHERE " + COLUMN_EXPENSE_TYPE + " = " + "\"" + enteredExpenseType + "\"";

        } else {

            //Month = All, Year = All, Type = All
            queryString = "SELECT * FROM " + EXPENSE_TABLE;
        }

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through the cursor (result set) and create new customer objects. Put them into the return list.

            do{
                int transactionID =  cursor.getInt(0);
                Double expenseAmount = cursor.getDouble(1);
                String expenseType = cursor.getString(2);
                String expenseMonth = cursor.getString(3);
                String expenseYear = cursor.getString(4);

                ExpenseModel newExpenseModel = new ExpenseModel(transactionID, expenseAmount, expenseType, expenseMonth, expenseYear);
                returnList.add(newExpenseModel);

            } while(cursor.moveToNext());
        } else {

            //do not add anything to the list.

        }

        // close both the cursor and the db when done.
        cursor.close();
        sqLiteDatabase.close();

        return returnList;
    }


}
