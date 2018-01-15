package com.example.nikashkuratova.smsreader.pojo;

public class SmsCategory {
    private String categoryName;

    public SmsCategory() {
        this.categoryName = "Все sms";
    }
    public SmsCategory(String pNname) {
        this.categoryName = pNname;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
