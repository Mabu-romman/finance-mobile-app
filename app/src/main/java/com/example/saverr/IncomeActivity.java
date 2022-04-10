package com.example.saverr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class IncomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //References to buttons and other controls
    private Button backButton;
    private Button enterButton;
    private EditText amountET;
    private Spinner type;

    private Spinner monthSpinner;
    private Spinner yearSpinner;

    private String spinnerMonthValue;
    private String spinnerYearValue;
    private String spinnerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        backButton = (Button) findViewById(R.id.incomeBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        enterButton = (Button) findViewById(R.id.incomeEnter);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //FinanceModel financeModel = new FinanceModel(1, dateET.getText().toString(), spinnerValue, Double.parseDouble(amountET.getText().toString()));

                FinanceModel financeModel;
                try {
                    financeModel = new FinanceModel(1, Double.parseDouble(amountET.getText().toString()), spinnerValue, spinnerMonthValue, spinnerYearValue);
                    Toast.makeText(IncomeActivity.this, financeModel.toString(), Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(IncomeActivity.this, "Error: Fill In All Fields", Toast.LENGTH_LONG).show();
                    financeModel = new FinanceModel(-1, 0, "error", "error", "error");
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(IncomeActivity.this);

                boolean success = dataBaseHelper.addOne(financeModel);

               // Toast.makeText(IncomeActivity.this, "Success = " + success, Toast.LENGTH_SHORT).show();
            }
        });

        amountET = (EditText) findViewById(R.id.incomeAmountET);

        type = (Spinner) findViewById(R.id.incomeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.incomeType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
        type.setOnItemSelectedListener(this);

        monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.incomeMonth, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter2);
        monthSpinner.setOnItemSelectedListener(this);

        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.incomeYear,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter3);
        yearSpinner.setOnItemSelectedListener(this);



    }

    public void openMainActivity() {
        Intent backIntent = new Intent(this, MainActivity.class);
        startActivity(backIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Spinner spinner = (Spinner) adapterView;

        if(spinner.getId() == R.id.incomeSpinner)
        {
            String incomeTypeString = adapterView.getItemAtPosition(i).toString();
            spinnerValue = incomeTypeString;
        }
        else if(spinner.getId() == R.id.monthSpinner)
        {
            String incomeMonthString = adapterView.getItemAtPosition(i).toString();
            spinnerMonthValue = incomeMonthString;
            //Toast.makeText(this, "month selected", Toast.LENGTH_SHORT).show();
        }
        else if (spinner.getId() == R.id.yearSpinner)
        {
            String incomeYearString = adapterView.getItemAtPosition(i).toString();
            spinnerYearValue = incomeYearString;
           // Toast.makeText(this, "Year Selected", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}