package com.example.nikashkuratova.smsreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.CAT_NAME;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.SEARCH_STRING;

public class AddCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        Button btn = (Button) this.findViewById(R.id.submitAdd);
        final EditText categoryName = (EditText) findViewById(R.id.enterName);
        final EditText searchString = (EditText) findViewById(R.id.enterSearchString);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(CAT_NAME, categoryName.getText().toString());
                intent.putExtra(SEARCH_STRING, searchString.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
