package com.example.saverr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class ViewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button backButton;
    private Button viewButton;
    private Button viewButton2;

    private Spinner monthView;
    private Spinner yearView;
    private Spinner incomeTypeView;
    private Spinner expenseTypeView;

    private String monthViewValue;
    private String yearViewValue;
    private String incomeTypeViewValue;
    private String expenseTypeViewValue;

    private ListView viewList;

    private Boolean isIncomeListed;

    ArrayAdapter financeArray;
    ArrayAdapter expenseArray;

    ArrayAdapter incomeFilterArray;
    ArrayAdapter expenseFilterArray;

    DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        viewList = findViewById(R.id.viewList);

        dataBaseHelper = new DataBaseHelper(ViewActivity.this);

        ShowAllIncomeOnLV(dataBaseHelper);


        backButton = (Button) findViewById(R.id.viewBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        viewButton = (Button) findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(ViewActivity.this);

                ShowIncome(dataBaseHelper);

            }
        });

        viewButton2 = (Button) findViewById(R.id.viewButton2);
        viewButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(ViewActivity.this);

               // ShowAllExpenseOnLV(dataBaseHelper);

                ShowExpense(dataBaseHelper);

            }
        });

        viewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(isIncomeListed == true) {
                    FinanceModel clickedIncome = (FinanceModel) adapterView.getItemAtPosition(i);
                    dataBaseHelper.deleteOne(clickedIncome);
                    ShowIncome(dataBaseHelper);
                    Toast.makeText(ViewActivity.this, "Deleted " + clickedIncome.toString(), Toast.LENGTH_LONG).show();
                } else{
                    ExpenseModel clickedExpense = (ExpenseModel) adapterView.getItemAtPosition(i);
                    dataBaseHelper.deleteOne(clickedExpense);
                    ShowExpense(dataBaseHelper);
                    Toast.makeText(ViewActivity.this, "Deleted " + clickedExpense.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        monthView = (Spinner) findViewById(R.id.monthView);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.monthView, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthView.setAdapter(adapter);
        monthView.setOnItemSelectedListener(this);

        yearView = (Spinner) findViewById(R.id.yearView);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.yearView, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearView.setAdapter(adapter2);
        yearView.setOnItemSelectedListener(this);

        incomeTypeView = (Spinner) findViewById(R.id.incomeTypeView);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.incomeTypeView, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        incomeTypeView.setAdapter(adapter3);
        incomeTypeView.setOnItemSelectedListener(this);

        expenseTypeView = (Spinner) findViewById(R.id.expenseTypeView);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.expenseTypeView, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expenseTypeView.setAdapter(adapter4);
        expenseTypeView.setOnItemSelectedListener(this);

    }

    private void ShowAllIncomeOnLV(DataBaseHelper dataBaseHelper2) {
        financeArray = new ArrayAdapter<FinanceModel>(ViewActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getAll());
        viewList.setAdapter(financeArray);
        isIncomeListed = true;
    }

    private void ShowAllExpenseOnLV(DataBaseHelper dataBaseHelper2) {
        expenseArray = new ArrayAdapter<ExpenseModel>(ViewActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getAllExpense());
        viewList.setAdapter(expenseArray);
        isIncomeListed = false;
    }

    private void ShowIncome(DataBaseHelper dataBaseHelper2) {
        incomeFilterArray = new ArrayAdapter<FinanceModel>(ViewActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getIncomeFilter(monthViewValue, yearViewValue, incomeTypeViewValue));
        viewList.setAdapter(incomeFilterArray);
        isIncomeListed = true;

    }

    private void ShowExpense(DataBaseHelper dataBaseHelper2) {
        expenseFilterArray = new ArrayAdapter<ExpenseModel>(ViewActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getExpenseFilter(monthViewValue, yearViewValue, expenseTypeViewValue));
        viewList.setAdapter(expenseFilterArray);
        isIncomeListed = false;

    }

    public void openMainActivity() {
        Intent backIntent = new Intent(this, MainActivity.class);
        startActivity(backIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Spinner spinner = (Spinner) adapterView;

        if(spinner.getId() == R.id.monthView)
        {
            String monthView = adapterView.getItemAtPosition(i).toString();
            monthViewValue = monthView;
        }
        else if(spinner.getId() == R.id.yearView)
        {
            String yearView = adapterView.getItemAtPosition(i).toString();
            yearViewValue = yearView;
        }
        else if (spinner.getId() == R.id.incomeTypeView)
        {
            String incomeTypeView = adapterView.getItemAtPosition(i).toString();
            incomeTypeViewValue = incomeTypeView;
        }
        else if (spinner.getId() == R.id.expenseTypeView)
        {
            String expenseTypeView = adapterView.getItemAtPosition(i).toString();
            expenseTypeViewValue = expenseTypeView;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}