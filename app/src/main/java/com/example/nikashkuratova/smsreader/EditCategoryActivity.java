package com.example.nikashkuratova.smsreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.CAT_ID;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.CAT_NAME;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.SEARCH_STRING;

public class EditCategoryActivity extends AppCompatActivity {

    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        Button editBtn = (Button) this.findViewById(R.id.submitEdit);
        final EditText editCatName = (EditText) this.findViewById(R.id.editName);
        final EditText editSearchStr = (EditText) this.findViewById(R.id.editSearchString);

        Intent intent = getIntent();
        final int catId = intent.getIntExtra(CAT_ID, -1);
        String catName = intent.getStringExtra(CAT_NAME);
        String searchStr = intent.getStringExtra(SEARCH_STRING);

        editCatName.setText(catName);
        editSearchStr.setText(searchStr);


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(CAT_ID, catId);
                intent.putExtra(CAT_NAME, editCatName.getText().toString());
                intent.putExtra(SEARCH_STRING, editSearchStr.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
