package com.example.nikashkuratova.smsreader.utils;

import com.example.nikashkuratova.smsreader.pojo.SmsMessage;

import java.util.List;

public final class CalculationsHelper {

    public static double calculateSumForCategory(List<SmsMessage> messages) {
        double sum = 0;

        for (SmsMessage sms : messages) {
            sum += sms.getSum();
        }
        return sum;
    }

}
