package com.example.nikashkuratova.smsreader.utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.nikashkuratova.smsreader.listener.OnAsyncTaskCompleted;
import com.example.nikashkuratova.smsreader.pojo.SmsMessage;

import java.util.ArrayList;
import java.util.List;

public class SmsAsyncLoader extends AsyncTask<String, Void, List<SmsMessage>> {

    public final String WHERE_CONDITION = "address = \"Priorbank\"";
    public final String[] PROJECTION = new String[]{"body"};
    public final String SMS_URI = "content://sms/inbox";

    private Activity activity;
    private OnAsyncTaskCompleted listener;


    public SmsAsyncLoader(Activity pActivity, OnAsyncTaskCompleted pListener) {
        activity = pActivity;
        listener = pListener;
    }

    @Override
    protected List<SmsMessage> doInBackground(String... params) {
        String searchWord = "";
        String searchPositiveCriteria = " and body like";
        String searchNegativeCriteria = " and body not like ";

        //todo fix bug
        if (params.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < params.length; i++) {
                if (!params[i].equals("")) {
                    stringBuilder.append(searchNegativeCriteria);
                    stringBuilder.append("'%" + params[i] + "%'");
                }
            }
            if (stringBuilder.length() >= 2) {
                stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "");
            }
            searchWord = stringBuilder.toString();
        } else if (params.length == 1 & !params[0].equals("")) {
            searchWord = searchPositiveCriteria + "'%" +  params[0].toString() + "%'";
        }

        Cursor cursor = activity.getContentResolver().query(Uri.parse(SMS_URI), PROJECTION, WHERE_CONDITION + searchWord, null, null);
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
        activity = null;
    }

    //todo add method nullify activity called on activity destroy
}
