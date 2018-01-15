package com.example.nikashkuratova.smsreader.listener;

import com.example.nikashkuratova.smsreader.pojo.SmsMessage;

import java.util.List;

public interface OnAsyncTaskCompleted {
    void onTaskCompeted(List<SmsMessage> messages);
}
