package com.example.nikashkuratova.smsreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        Button btn = (Button) this.findViewById(R.id.submit);
        final EditText category_name = (EditText) findViewById(R.id.enterName);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("enterName", category_name.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
