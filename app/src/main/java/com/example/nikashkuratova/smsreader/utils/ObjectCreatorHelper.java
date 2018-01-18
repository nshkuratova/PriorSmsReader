package com.example.nikashkuratova.smsreader.utils;

import com.example.nikashkuratova.smsreader.pojo.SmsCategory;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.ALL_SMS_CATEGORY;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.ALL_SMS_SEARCH_STR;
import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.NO_CATEGORY;

public final class ObjectCreatorHelper {
    public static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    public static double retrieveSum(String smsText){
        String sum_regex_pattern = "Oplata.\\d+[.]\\d\\d.BYN";
        double sum = 0;

        Pattern pattern = Pattern.compile(sum_regex_pattern);
        Matcher m = pattern.matcher(smsText);

        if (m.find()) {
            String str = m.group().replaceAll("[^\\d.]", "");
            sum = Double.valueOf(str);
        }
        return sum;
    }

    public static ArrayList<SmsCategory> createDefaultCategories(){
        ArrayList<SmsCategory> smsCategory= new ArrayList<SmsCategory>();
        smsCategory.add(new SmsCategory(ALL_SMS_CATEGORY, ALL_SMS_SEARCH_STR));
        smsCategory.add(new SmsCategory(NO_CATEGORY, ALL_SMS_SEARCH_STR));

        return smsCategory;
    }
}
