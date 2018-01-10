package com.example.nikashkuratova.smsreader.Pojo;
// todo: all package names should be in lower case. and give high level packages more meaningful names describing their purpose

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
