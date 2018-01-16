package com.example.nikashkuratova.smsreader.pojo;

import android.util.Log;

import java.text.DateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsMessage {

    private static final String SUM_REGEX_PATTERN = "Oplata.\\d+[.]\\d\\d.BYN";

    private String smsText;
    private double sum;
    private DateFormat date;

    public SmsMessage(String smsText) {
        this.smsText = smsText;
        Pattern pattern = Pattern.compile(SUM_REGEX_PATTERN);
        Matcher m = pattern.matcher(smsText);

        if (m.find()) {
            String str = m.group().replaceAll("[^\\d.]", "");
            sum = Double.valueOf(str);
        }
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }
}
