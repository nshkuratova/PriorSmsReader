package com.example.nikashkuratova.smsreader.utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nikashkuratova.smsreader.R;
import com.example.nikashkuratova.smsreader.adaptor.SmsAdapter;
import com.example.nikashkuratova.smsreader.pojo.SmsMessage;

import java.util.ArrayList;
import java.util.List;

public class SmsAsyncLoader extends AsyncTask<Void, Void, List<SmsMessage>> {
    //todo potential memory leak: thread holds link to Activity
    private Activity activity;
    private RecyclerView recyclerView;

    public SmsAsyncLoader(Activity pActivity) {
        activity = pActivity;
    }

    @Override
    protected List<SmsMessage> doInBackground(Void... voids) {
        String WHERE_CONDITION = "address = \"Priorbank\"";
        Cursor cursor = activity.getContentResolver().query(Uri.parse("content://sms/inbox"), new String[]{"body"}, WHERE_CONDITION, null, null);
        List<SmsMessage> smsArray;
        smsArray = new ArrayList<>();

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                    msgData += cursor.getString(idx);
                    smsArray.add(new SmsMessage(msgData));
                }
            } while (cursor.moveToNext());
        }
        return smsArray;
    }

    //todo make this helper class independent from target UI: create listener which retrieve list of SmsMessages and implement this this listener in Activity
    @Override
    protected void onPostExecute(List<SmsMessage> strings) {

        recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        SmsAdapter adapter = new SmsAdapter(strings);
        recyclerView.setAdapter(adapter);
    }
}
