package com.example.nikashkuratova.smsreader.Pojo;

public class SmsMessage {
    private String smsText;

    public SmsMessage(String smsText) {
        this.smsText = smsText;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }
}
