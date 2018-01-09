package com.example.nikashkuratova.smsreader;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SmsAsyncLoader extends AsyncTask<Void, Void, ArrayList<String>> {
    private Activity activity;
    ArrayList<String> smsArray;
    ListView listView;

    public SmsAsyncLoader(Activity pActivity) {
        activity = pActivity;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        String WHERE_CONDITION = "address = \"Priorbank\"";
        Cursor cursor = activity.getContentResolver().query(Uri.parse("content://sms/inbox"), new String[]{"body"}, WHERE_CONDITION, null, null);
        smsArray = new ArrayList<>();


        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                    msgData += cursor.getString(idx);
                    smsArray.add(msgData);
                }
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
        return smsArray;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {

        listView = (ListView) activity.findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_list_item_1, smsArray);
        listView.setAdapter(adapter);

    }
}
