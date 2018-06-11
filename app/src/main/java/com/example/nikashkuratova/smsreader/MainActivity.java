package com.example.nikashkuratova.smsreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.example.nikashkuratova.smsreader.utils.SharedPrefHelper;

import java.util.ArrayList;

import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.ARRAY_SIZE;


public class MainActivity extends AppCompatActivity {

    private ArrayList<SmsCategory> smsCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        smsCategory = new ArrayList<SmsCategory>();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CardView allCategoryCard = (CardView) findViewById(R.id.categories_card);
        allCategoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoryListActivity.class);
                startActivity(intent);
            }
        });

        CardView reportsCard = (CardView) findViewById(R.id.reports_card);
        reportsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                //Intent intent = new Intent(MainActivity.this, PieChartActivity.class);
                startActivity(intent);
            }
        });


        CardView noCategory = (CardView) findViewById(R.id.no_category_card);
        noCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SmsDataActivity.class);
                int i = 0;
                for (SmsCategory cat : smsCategory) {
                    if (!cat.getSearchString().isEmpty() || cat.getSearchString() != "") {
                        intent.putExtra(String.valueOf(i), cat.getSearchString());
                        i++;
                    }
                    intent.putExtra(ARRAY_SIZE, i);
                }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        smsCategory = SharedPrefHelper.readFromSharedPref(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}




