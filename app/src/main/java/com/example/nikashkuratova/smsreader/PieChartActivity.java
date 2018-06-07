package com.example.nikashkuratova.smsreader;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nikashkuratova.smsreader.listener.OnAsyncTaskCompleted;
import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.example.nikashkuratova.smsreader.pojo.SmsMessage;
import com.example.nikashkuratova.smsreader.utils.CalculationsHelper;
import com.example.nikashkuratova.smsreader.utils.SharedPrefHelper;
import com.example.nikashkuratova.smsreader.utils.SmsAsyncLoader;
import com.example.nikashkuratova.smsreader.utils.SmsByCategoryRetriever;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PieChartActivity extends AppCompatActivity {
    private OnAsyncTaskCompleted listener;
    private ArrayList<SmsCategory> categories;
    private ArrayList<SmsMessage> messages;
    private HashMap<SmsCategory, Double> sumByCategory;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        pieChart = (PieChart) findViewById(R.id.chart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);
        pieChart.setRotationAngle(0);

        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(12f);

        setData();
    }

    private void setData() {
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        categories = SharedPrefHelper.readFromSharedPref(this);

        for (SmsCategory cat : categories) {
            messages = SmsByCategoryRetriever.retrieveSms(cat, this);
            sumByCategory.put(cat,CalculationsHelper.calculateSumForMessages(messages));
        }


        for (Map.Entry<SmsCategory, Double> entry : sumByCategory.entrySet()) {
            SmsCategory sms = entry.getKey();
            Double sum = entry.getValue();
            entries.add(new PieEntry(sum.floatValue(), sms.getCategoryName()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Categories Results");

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.invalidate();
    }
}
