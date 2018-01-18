package com.example.nikashkuratova.smsreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.nikashkuratova.smsreader.adaptor.SmsAdapter;
import com.example.nikashkuratova.smsreader.listener.OnAsyncTaskCompleted;
import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.example.nikashkuratova.smsreader.pojo.SmsMessage;
import com.example.nikashkuratova.smsreader.utils.CalculationsHelper;
import com.example.nikashkuratova.smsreader.utils.FormatterUtils;
import com.example.nikashkuratova.smsreader.utils.SharedPrefHelper;
import com.example.nikashkuratova.smsreader.utils.SmsAsyncLoader;
import com.github.mikephil.charting.charts.HorizontalBarChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.ALL_SMS_CATEGORY;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.ALL_SMS_SEARCH_STR;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.ARRAY_SIZE;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.NO_CATEGORY;

public class ChartsActivity extends AppCompatActivity {
    private OnAsyncTaskCompleted listener;
    HashMap<SmsCategory, Double> sumByCategory;
    ArrayList<SmsCategory> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        HorizontalBarChart chart = (HorizontalBarChart) findViewById(R.id.chart);
        categories = SharedPrefHelper.readFromSharedPref(this);
        sumByCategory = new HashMap<>();
        for (SmsCategory cat: categories){
            getSmsByCategory(cat, cat.getSearchString());
        }
    }


    private void getSmsByCategory(final SmsCategory category, String searchStrList) {
        listener = new OnAsyncTaskCompleted() {
            @Override
            public void onTaskCompeted(List<SmsMessage> messages) {
                sumByCategory.put(category, CalculationsHelper.calculateSumForMessages(messages));
            }
        };
        new SmsAsyncLoader(this, listener).execute(searchStrList);
    }
}
