package com.example.nikashkuratova.smsreader.utils;

import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.example.nikashkuratova.smsreader.pojo.SmsMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class CalculationsHelper {

    public static double calculateSumForMessages(List<SmsMessage> messages) {
        double sum = 0;

        for (SmsMessage sms : messages) {
            sum += sms.getSum();
        }
        return sum;
    }

    public static HashMap<SmsCategory, Double> calculateSumForAllCategories(ArrayList<SmsCategory> smsCategories){
        HashMap<SmsCategory, Double> sumForCategory = new HashMap<>();


        return sumForCategory;
    }

}
