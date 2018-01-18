package com.example.nikashkuratova.smsreader.utils;

import java.text.DecimalFormat;

public final class FormatterUtils {

    public static String formatSum(double sum){
        DecimalFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(sum);
    }
}
