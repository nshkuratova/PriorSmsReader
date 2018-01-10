package com.example.nikashkuratova.smsreader.Pojo;

public class SmsCategory {
    private String categoryName;

    public SmsCategory() {
        this.categoryName = "Без категории";
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
