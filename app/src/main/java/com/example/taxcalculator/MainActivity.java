package com.example.taxcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText annualEt, rrspConEt, capitalEt, ineligibleEt, eligibleEt;
    Button calculateBtn;
    int selectedIndex = 0;
    String[] years = {"2021", "2022"};
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences("test", Context.MODE_PRIVATE);

        calculateBtn = findViewById(R.id.btn);
        annualEt = findViewById(R.id.annual);
        rrspConEt = findViewById(R.id.other);
        capitalEt = findViewById(R.id.capital);
        eligibleEt = findViewById(R.id.eligible);
        ineligibleEt = findViewById(R.id.ineligible);

        loadData();

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tax tax = new Tax(Double.parseDouble(annualEt.getText().toString()), Integer.parseInt(years[selectedIndex]));
                tax.setCapital(getValue(capitalEt.getText().toString()));
                tax.setOther(getValue(rrspConEt.getText().toString()));
                tax.setEligibleDividends(getValue(eligibleEt.getText().toString()));
                tax.setInEligibleDividends(getValue(ineligibleEt.getText().toString()));
                tax.calculateTax();
                saveData();
                startActivity(new Intent(getApplicationContext(), GraphActivity.class).
                        putExtra("value", Double.parseDouble(annualEt.getText().toString())));
            }
        });

    }

    private void saveData() {

        try {
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("income", annualEt.getText().toString());
            editor.putString("rrsp", rrspConEt.getText().toString());
            editor.apply();
        } catch (Exception e) {

        }

    }

    private void loadData() {
        try {
            String income = sharedpreferences.getString("income", "");//"No name defined" is the default value.
            String rrsp = sharedpreferences.getString("rrsp", "");

            annualEt.setText(income);
            rrspConEt.setText(rrsp);
        } catch (Exception e) {

        }

    }

    private double getValue(String input) {
        return input.isEmpty() ? 0 : Double.parseDouble(input);
    }


}