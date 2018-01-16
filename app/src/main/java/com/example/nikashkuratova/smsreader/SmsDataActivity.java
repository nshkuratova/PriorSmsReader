package com.example.nikashkuratova.smsreader;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.nikashkuratova.smsreader.adaptor.SmsAdapter;
import com.example.nikashkuratova.smsreader.listener.OnAsyncTaskCompleted;
import com.example.nikashkuratova.smsreader.pojo.SmsMessage;
import com.example.nikashkuratova.smsreader.utils.PermissionCheckHelper;
import com.example.nikashkuratova.smsreader.utils.SmsAsyncLoader;

import java.text.DecimalFormat;
import java.util.List;

public class SmsDataActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 0;
    private OnAsyncTaskCompleted listener;
    String searchStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_data);
    }


    @Override
    protected void onStart() {
        super.onStart();
        searchStr = getIntent().getStringExtra("searchStr");
        if (PermissionCheckHelper.checkSmsPermissionGranted(this)) {
            showSMS(searchStr);
        }
    }


    private void showSMS(String searchStr) {
        listener = new OnAsyncTaskCompleted() {
            @Override
            public void onTaskCompeted(List<SmsMessage> messages) {
                RecyclerView recyclerView;
                recyclerView = (RecyclerView) SmsDataActivity.this.findViewById(R.id.recyclerview_sms);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SmsDataActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);

                SmsAdapter adapter = new SmsAdapter(messages);
                recyclerView.setAdapter(adapter);
                double sum = 0;

                for (SmsMessage sms : messages) {
                    sum += sms.getSum();
                }

                DecimalFormat formatter = new DecimalFormat("#0.00");

                TextView sumView = (TextView) findViewById(R.id.all_sms_sum);
                sumView.setText(String.valueOf(formatter.format(sum)) + " BYN");

            }
        };
        new SmsAsyncLoader(this, listener).execute(searchStr);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showSMS(searchStr);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    //todo nice screen to explain to turn on the permission
                    finishAndRemoveTask();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}