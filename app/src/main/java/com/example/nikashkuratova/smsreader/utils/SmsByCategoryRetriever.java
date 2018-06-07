package com.example.nikashkuratova.smsreader.utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.example.nikashkuratova.smsreader.pojo.SmsMessage;

import java.util.ArrayList;

public final class SmsByCategoryRetriever {

    public static final String WHERE_CONDITION = "address = \"Priorbank\"";
    public static final String[] PROJECTION = new String[]{"body"};
    public static final String SMS_URI = "content://sms/inbox";

    public static ArrayList<SmsMessage> retrieveSms(SmsCategory smsCategory, Activity activity) {
        String searchWord = "";
        String searchPositiveCriteria = " and body like";
        String searchNegativeCriteria = " and body not like ";

        StringBuilder stringBuilder = new StringBuilder();
        if (!smsCategory.getSearchString().equals("")) {
            stringBuilder.append(searchNegativeCriteria);
            stringBuilder.append("'%" + smsCategory.getSearchString() + "%'");
            searchWord = stringBuilder.toString();
        }

        Cursor cursor = activity.getContentResolver().query(Uri.parse(SMS_URI), PROJECTION, WHERE_CONDITION + searchWord, null, null);
        ArrayList<SmsMessage> smsArray;
        smsArray = new ArrayList<>();

        if (cursor.moveToFirst())

        { // must check the result to prevent exception
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

}
