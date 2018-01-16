package com.example.nikashkuratova.smsreader.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public final class SharedPrefHelper {
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;

    public static void saveToSharedPref(ArrayList<SmsCategory> list, Activity activity){
        sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        String categoriesListtoJson = new Gson().toJson(list);
        editor.putString("CategoriesList", categoriesListtoJson);
        editor.commit();
    }

    public static ArrayList<SmsCategory> readFromSharedPref(){
        ArrayList<SmsCategory> smsCategory = new ArrayList<SmsCategory>();

        Gson gson = new Gson();
        String json = sharedPref.getString("CategoriesList", "");
        if (json.isEmpty()) {
            smsCategory.add(new SmsCategory());
        } else {
            smsCategory = gson.fromJson(json, new TypeToken<ArrayList<SmsCategory>>() {
            }.getType());
        }
        return smsCategory;
    }
}
