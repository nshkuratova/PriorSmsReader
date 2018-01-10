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

//todo wrong package por util class
public class SmsAsyncLoader extends AsyncTask<Void, Void, List<SmsMessage>> {
    //todo potential memory leak: thread holds link to Activity
    private Activity activity;
    //todo: unnecessary field (this link can be local). fields save state, and extra efforts always needed to have state correct during application life (this is design principe)
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


        //todo replace with wile(cursor.next())
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                    msgData += cursor.getString(idx);
                    smsArray.add(new SmsMessage(msgData));
                }
            } while (cursor.moveToNext());
        } else {
            //todo remove empty block
            // empty box, no SMS
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

        SmsAdapter adapter = new SmsAdapter(smsArray);
        recyclerView.setAdapter(adapter);
    }
}
