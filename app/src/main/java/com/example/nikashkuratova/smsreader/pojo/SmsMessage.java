package com.example.nikashkuratova.smsreader.pojo;

import java.text.DateFormat;

import static com.example.nikashkuratova.smsreader.utils.ObjectCreatorHelper.retrieveSum;

public class SmsMessage {

    private String smsText;
    private double sum;
    private DateFormat date;

    public SmsMessage(String smsText) {
        this.smsText = smsText;
        this.sum = retrieveSum(smsText);
    }

    public double getSum() {
        return sum;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }
}
