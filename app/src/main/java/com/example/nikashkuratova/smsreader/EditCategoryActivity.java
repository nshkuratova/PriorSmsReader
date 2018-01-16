package com.example.nikashkuratova.smsreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        Button editBtn = (Button) this.findViewById(R.id.submitEdit);
        final EditText editCatName = (EditText) this.findViewById(R.id.editName);
        final EditText editSearchStr = (EditText) this.findViewById(R.id.editSearchString);

        Intent intent = getIntent();
        final int catId = intent.getIntExtra("catId", -1);
        String catName = intent.getStringExtra("catName");
        String searchStr = intent.getStringExtra("searchStr");

        editCatName.setText(catName);
        editSearchStr.setText(searchStr);


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("id", catId);
                intent.putExtra("categoryName", editCatName.getText().toString());
                intent.putExtra("searchString", editSearchStr.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
