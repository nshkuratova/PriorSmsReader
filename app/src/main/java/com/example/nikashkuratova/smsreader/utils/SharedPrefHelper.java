package com.example.nikashkuratova.smsreader.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.ALL_SMS_CATEGORY;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.NO_CATEGORY;


public final class SharedPrefHelper {

    public static void saveToSharedPref(ArrayList<SmsCategory> list, Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String categoriesListtoJson = new Gson().toJson(list);
        editor.putString("CategoriesList", categoriesListtoJson);
        editor.apply();
    }

    public static ArrayList<SmsCategory> readFromSharedPref(Activity activity) {
        ArrayList<SmsCategory> smsCategory = new ArrayList<SmsCategory>();
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
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
