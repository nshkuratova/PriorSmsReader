package com.example.nikashkuratova.smsreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.nikashkuratova.smsreader.adaptor.CategoryAdapter;
import com.example.nikashkuratova.smsreader.listener.RecyclerViewClickListener;
import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.example.nikashkuratova.smsreader.utils.SharedPrefHelper;

import java.util.ArrayList;

import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.ALL_SMS_CATEGORY;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.ALL_SMS_SEARCH_STR;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.ARRAY_SIZE;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.CAT_ID;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.CAT_NAME;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.NO_CATEGORY;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.SEARCH_STRING;

public class CategoryListActivity extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback, RecyclerViewClickListener {

    public static final int ADD_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_ACTIVITY_REQUEST_CODE = 2;
    private boolean editMode = false;

    private RecyclerView recyclerView;
    private ArrayList<SmsCategory> smsCategory;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        smsCategory = new ArrayList<SmsCategory>();
        smsCategory = SharedPrefHelper.readFromSharedPref(this);

        setContentView(R.layout.activity_category_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(CategoryListActivity.this, AddCategoryActivity.class), ADD_ACTIVITY_REQUEST_CODE);
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
    protected void onStop() {
        super.onStop();
        SharedPrefHelper.saveToSharedPref(smsCategory, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPrefHelper.saveToSharedPref(smsCategory, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADD_ACTIVITY_REQUEST_CODE: {
                if (resultCode == RESULT_OK) {
                    smsCategory.add(new SmsCategory(data.getStringExtra(CAT_NAME), data.getStringExtra(SEARCH_STRING)));
                    categoryAdapter.notifyDataSetChanged();
                    SharedPrefHelper.saveToSharedPref(smsCategory, this);
                }
            }
            break;
            case EDIT_ACTIVITY_REQUEST_CODE: {
                if (resultCode == RESULT_OK) {
                    int id = data.getIntExtra(CAT_ID, -1);
                    for (SmsCategory cat : smsCategory) {
                        if (cat.getCatId() == id) {
                            cat.setCategoryName(data.getStringExtra(CAT_NAME));
                            cat.setSearchString(data.getStringExtra(SEARCH_STRING));
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
        String catName = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(view.getId())).getText().toString();
        Intent intent = new Intent(this, SmsDataActivity.class);
        String search = "";
        int i = 0;
        if (catName.equals(ALL_SMS_CATEGORY)) {
            intent.putExtra(String.valueOf(i), ALL_SMS_SEARCH_STR);
            intent.putExtra(ARRAY_SIZE, 1);
        } else if (catName.equals(NO_CATEGORY)) {
            for (SmsCategory cat : smsCategory) {
                if (!cat.getSearchString().isEmpty() || cat.getSearchString() != "") {
                    intent.putExtra(String.valueOf(i), cat.getSearchString());
                    i++;
                }
                intent.putExtra(ARRAY_SIZE, i);
            }
        } else {
            for (SmsCategory cat : smsCategory) {
                if (catName.equals(cat.getCategoryName())) {
                    search = cat.getSearchString();
                }
            }
            intent.putExtra(String.valueOf(i), search);
            intent.putExtra(ARRAY_SIZE, 1);
        }
        startActivity(intent);
    }

}
