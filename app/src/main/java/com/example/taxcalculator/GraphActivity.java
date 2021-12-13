package com.example.taxcalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.slider.Slider;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {
    TextView[] textViews = new TextView[8];
    Slider slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        textViews[0]= findViewById(R.id.text1);
        textViews[1]= findViewById(R.id.text2);
        textViews[2]= findViewById(R.id.text3);
        textViews[3]= findViewById(R.id.text4);
        textViews[4]= findViewById(R.id.text5);
        textViews[5]= findViewById(R.id.text6);
        textViews[6]= findViewById(R.id.text7);
        textViews[7]= findViewById(R.id.text8);

        slider = findViewById(R.id.slider);

        double income =getIntent().getDoubleExtra("value",50000);

        double federalTax = (income*10.006)/100;
        double provincialTax = (income*4.012)/100;
        double totalIncomeTax = provincialTax+federalTax;

        double eiDeduction = (income*1.58)/100;
        double cppDeduction = (income*6)/100;
        double netIncome =income-eiDeduction-cppDeduction-totalIncomeTax;

        double averageTexRate = (totalIncomeTax*100)/netIncome;
        double rrspRefund = (income*5.012)/100;

        textViews[0].setText("Federal Tax: $"+federalTax);
        textViews[1].setText("Provincial Tax: $"+provincialTax);
        textViews[2].setText("Total Income Tax: $"+totalIncomeTax);
        textViews[3].setText("EI Deduction: $"+eiDeduction);
        textViews[4].setText("CPP Deduction: $"+cppDeduction);
        textViews[5].setText("Net Income: $"+netIncome);


        DecimalFormat f = new DecimalFormat("##.00");
        textViews[6].setText("Avg Tax Rate: $"+f.format(averageTexRate)+"%");
        textViews[7].setText("RRPS Fund: $"+rrspRefund);

        PieChart pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> list = new ArrayList<>();

        list.add(new PieEntry((float) netIncome,"Net Income"));
        list.add(new PieEntry((float) federalTax,"Federal Tax"));
        list.add(new PieEntry((float) provincialTax,"Provincial Tax"));
        list.add(new PieEntry((float) (cppDeduction+eiDeduction),"Deductions"));


        PieDataSet pieDataSet = new PieDataSet(list,".");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(13.0f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        //pieChart.setCenterText("ooo");
        //pieChart.getDescription().setEnabled(false);
        pieChart.animate();

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                DecimalFormat f = new DecimalFormat("##.00");
                textViews[7].setText("RRPS Fund: $"+f.format(rrspRefund*value));
            }
        });
    }
}