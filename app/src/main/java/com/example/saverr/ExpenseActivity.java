package com.example.saverr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ExpenseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button backButton2;
    private Button enterButton2;
    private EditText amountET2;
    private Spinner type2;

    private Spinner monthSpinner2;
    private Spinner yearSpinner2;

    private String spinnerMonthValue2;
    private String spinnerYearValue2;
    private String spinnerValue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        backButton2 = (Button) findViewById(R.id.expenseBackButton);
        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        enterButton2 = (Button) findViewById(R.id.enterButton2);
        enterButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ExpenseModel expenseModel;
                try {
                    expenseModel = new ExpenseModel(1, Double.parseDouble(amountET2.getText().toString()), spinnerValue2, spinnerMonthValue2, spinnerYearValue2);
                    Toast.makeText(ExpenseActivity.this, expenseModel.toString(), Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(ExpenseActivity.this, "Error: Fill In All Fields", Toast.LENGTH_LONG).show();
                    expenseModel = new ExpenseModel(-1, 0, "error", "error", "error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(ExpenseActivity.this);

                boolean success = dataBaseHelper.addOne(expenseModel);

               // Toast.makeText(ExpenseActivity.this, "Success = " + success, Toast.LENGTH_SHORT).show();

            }
        });

        amountET2 = (EditText) findViewById(R.id.amountET2);

        type2 = (Spinner) findViewById(R.id.type2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.expenseType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type2.setAdapter(adapter);
        type2.setOnItemSelectedListener(this);

        monthSpinner2 = (Spinner) findViewById(R.id.monthSpinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.incomeMonth, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner2.setAdapter(adapter2);
        monthSpinner2.setOnItemSelectedListener(this);

        yearSpinner2 = (Spinner) findViewById(R.id.yearSpinner2);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.incomeYear,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner2.setAdapter(adapter3);
        yearSpinner2.setOnItemSelectedListener(this);

    }

    public void openMainActivity() {
        Intent backIntent = new Intent(this, MainActivity.class);
        startActivity(backIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;

        if(spinner.getId() == R.id.type2)
        {
            String incomeTypeString = adapterView.getItemAtPosition(i).toString();
            spinnerValue2 = incomeTypeString;
        }
        else if(spinner.getId() == R.id.monthSpinner2)
        {
            String incomeMonthString = adapterView.getItemAtPosition(i).toString();
            spinnerMonthValue2 = incomeMonthString;
            //Toast.makeText(this, "month selected", Toast.LENGTH_SHORT).show();
        }
        else if (spinner.getId() == R.id.yearSpinner2)
        {
            String incomeYearString = adapterView.getItemAtPosition(i).toString();
            spinnerYearValue2 = incomeYearString;
            // Toast.makeText(this, "Year Selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}