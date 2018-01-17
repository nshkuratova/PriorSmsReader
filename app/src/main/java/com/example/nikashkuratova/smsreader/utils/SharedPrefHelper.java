package com.example.nikashkuratova.smsreader.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.example.nikashkuratova.smsreader.pojo.SmsCategory.ALL_SMS_CATEGORY;
import static com.example.nikashkuratova.smsreader.pojo.SmsCategory.NO_CATEGORY;

public final class SharedPrefHelper {
    //todo remove unecessary fields
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;

    public static void saveToSharedPref(ArrayList<SmsCategory> list, Activity activity) {
        sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        String categoriesListtoJson = new Gson().toJson(list);
        editor.putString("CategoriesList", categoriesListtoJson);
        // todo use apply instead commit
        editor.commit();
    }

    public static ArrayList<SmsCategory> readFromSharedPref(Activity activity) {
        ArrayList<SmsCategory> smsCategory = new ArrayList<SmsCategory>();
        sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        // todo not usedc editor
        editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = sharedPref.getString("CategoriesList", "");
        if (json.isEmpty()) {
            smsCategory.add(new SmsCategory(ALL_SMS_CATEGORY));
            smsCategory.add(new SmsCategory(NO_CATEGORY));
        } else {
            smsCategory = gson.fromJson(json, new TypeToken<ArrayList<SmsCategory>>() {
            }.getType());
        }
        return smsCategory;
    }
}
