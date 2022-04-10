package com.example.saverr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button incomeButton;
    private Button expenseButton;
    private Button viewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        incomeButton = (Button) findViewById(R.id.incomeButton);
        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openIncomeActivity();
            }
        });

        expenseButton = (Button) findViewById(R.id.expenseButton);
        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExpenseActivity();
            }
        });

        viewButton = (Button) findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewActivity();
            }
        });
    }

    public void openIncomeActivity() {
        Intent incomeIntent = new Intent(this, IncomeActivity.class);
        startActivity(incomeIntent);
    }

    public void openExpenseActivity() {
        Intent expenseIntent = new Intent(this, ExpenseActivity.class);
        startActivity(expenseIntent);
    }

    public void openViewActivity() {
        Intent viewIntent = new Intent(this, ViewActivity.class);
        startActivity(viewIntent);
    }
}