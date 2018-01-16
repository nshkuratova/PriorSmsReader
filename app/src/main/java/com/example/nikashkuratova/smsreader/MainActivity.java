package com.example.nikashkuratova.smsreader;

import android.content.Intent;
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

import com.example.nikashkuratova.smsreader.adaptor.CategoryAdapter;
import com.example.nikashkuratova.smsreader.listener.RecyclerViewClickListener;
import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.example.nikashkuratova.smsreader.utils.SharedPrefHelper;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback, RecyclerViewClickListener {

    public static final int ADD_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_ACTIVITY_REQUEST_CODE = 2;
    private boolean editMode = false;

    private RecyclerView recyclerView;
    private ArrayList<SmsCategory> smsCategory;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADD_ACTIVITY_REQUEST_CODE: {
                if (resultCode == RESULT_OK) {
                    smsCategory.add(new SmsCategory(data.getStringExtra("categoryName"), data.getStringExtra("searchString")));
                    categoryAdapter.notifyDataSetChanged();
                    SharedPrefHelper.saveToSharedPref(smsCategory, this);
                }
            }
            break;
            case EDIT_ACTIVITY_REQUEST_CODE: {
                if (resultCode == RESULT_OK) {
                    int id = data.getIntExtra("id", -1);
                    for (SmsCategory cat : smsCategory) {
                        if (cat.getCatId() == id) {
                            cat.setCategoryName(data.getStringExtra("categoryName"));
                            cat.setSearchString(data.getStringExtra("searchString"));
                        }
                    }
                    categoryAdapter.notifyDataSetChanged();
                    SharedPrefHelper.saveToSharedPref(smsCategory, this);
                }
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        smsCategory = new ArrayList<SmsCategory>();
        smsCategory = SharedPrefHelper.readFromSharedPref(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddCategoryActivity.class), ADD_ACTIVITY_REQUEST_CODE);
            }
        });

        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        categoryAdapter = new CategoryAdapter(smsCategory, this, this);
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPrefHelper.saveToSharedPref(smsCategory, this);
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
                if (!editMode) {
                    categoryAdapter.enableEditAndRemoveOption(true);
                    editMode = true;
                } else {
                    categoryAdapter.enableEditAndRemoveOption(false);
                    editMode = false;
                }
                categoryAdapter.notifyDataSetChanged();
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


