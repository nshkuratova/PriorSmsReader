package com.example.nikashkuratova.smsreader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.nikashkuratova.smsreader.adaptor.CategoryAdapter;
import com.example.nikashkuratova.smsreader.listener.RecyclerViewClickListener;
import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback, RecyclerViewClickListener {

    private RecyclerView recyclerView;
    private List<SmsCategory> smsCategory;
    private CategoryAdapter adapter;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                smsCategory.add(new SmsCategory(data.getStringExtra("categoryName"), data.getStringExtra("searchString")));
                adapter.notifyDataSetChanged();
                String categoriesListtoJson = new Gson().toJson(smsCategory);
                editor.putString("CategoriesList", categoriesListtoJson);
                editor.commit();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        smsCategory = new ArrayList<SmsCategory>();

        Gson gson = new Gson();
        String json = sharedPref.getString("CategoriesList", "");
        if (json.isEmpty()) {
            smsCategory.add(new SmsCategory());
        } else {
            smsCategory = gson.fromJson(json, new TypeToken<ArrayList<SmsCategory>>() {
            }.getType());
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddCategoryActivity.class), 1);
            }
        });

        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new CategoryAdapter(smsCategory, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String categoriesListtoJson = new Gson().toJson(smsCategory);
        editor.putString("CategoriesList", categoriesListtoJson);
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                break;
            case R.id.edit_categories:
//todo make edit button visible
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void recyclerViewListClicked(View view, int position) {
        if (position == 0) {
            Intent intent = new Intent(this, SmsDataActivity.class);
            startActivity(intent);
        }

    }
}


