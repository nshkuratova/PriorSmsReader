package com.example.nikashkuratova.smsreader.utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.nikashkuratova.smsreader.listener.OnAsyncTaskCompleted;
import com.example.nikashkuratova.smsreader.pojo.SmsMessage;

import java.util.ArrayList;
import java.util.List;

public class SmsAsyncLoader extends AsyncTask<Void, Void, List<SmsMessage>> {
    //todo potential memory leak: thread holds link to Activity
    private Activity activity;
    private OnAsyncTaskCompleted listener;

    public SmsAsyncLoader(Activity pActivity, OnAsyncTaskCompleted pListener) {
        activity = pActivity;
        listener = pListener;
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

    @Override
    protected void onPostExecute(List<SmsMessage> strings) {
        listener.onTaskCompeted(strings);

    }
}
