package com.example.nikashkuratova.smsreader.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.example.nikashkuratova.smsreader.utils.ObjectCreatorHelper.createDefaultCategories;

public final class SharedPrefHelper {

    public static final String CATEGORIES_LIST_KEY ="CategoriesList";

    public static void saveToSharedPref(ArrayList<SmsCategory> list, Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String categoriesListtoJson = new Gson().toJson(list);
        editor.putString(CATEGORIES_LIST_KEY, categoriesListtoJson);
        editor.apply();
    }

    public static ArrayList<SmsCategory> readFromSharedPref(Activity activity) {
        ArrayList<SmsCategory> smsCategory = new ArrayList<SmsCategory>();
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(CATEGORIES_LIST_KEY, "");
        if (json.isEmpty()) {
            smsCategory = createDefaultCategories();
        } else {
            smsCategory = gson.fromJson(json, new TypeToken<ArrayList<SmsCategory>>() {
            }.getType());
        }
        return smsCategory;
    }
}
