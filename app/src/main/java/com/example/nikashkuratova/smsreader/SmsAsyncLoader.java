package com.example.nikashkuratova.smsreader;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nikashkuratova.smsreader.Adaptor.SmsAdapter;
import com.example.nikashkuratova.smsreader.Pojo.SmsMessage;

import java.util.ArrayList;
import java.util.List;

public class SmsAsyncLoader extends AsyncTask<Void, Void, List<SmsMessage>> {
    private Activity activity;
    private List<SmsMessage> smsArray;
    private RecyclerView recyclerView;

    public SmsAsyncLoader(Activity pActivity) {
        activity = pActivity;
    }

    @Override
    protected List<SmsMessage> doInBackground(Void... voids) {
        String WHERE_CONDITION = "address = \"Priorbank\"";
        Cursor cursor = activity.getContentResolver().query(Uri.parse("content://sms/inbox"), new String[]{"body"}, WHERE_CONDITION, null, null);
        smsArray = new ArrayList<>();


        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                    msgData += cursor.getString(idx);
                    smsArray.add(new SmsMessage(msgData));
                }
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
        return smsArray;
    }

    @Override
    protected void onPostExecute(List<SmsMessage> strings) {

        recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        SmsAdapter adapter = new SmsAdapter(smsArray);
        recyclerView.setAdapter(adapter);
    }
}
