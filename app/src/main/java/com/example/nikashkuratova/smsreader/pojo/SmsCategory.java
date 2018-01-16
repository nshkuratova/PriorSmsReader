package com.example.nikashkuratova.smsreader.pojo;

import java.util.concurrent.atomic.AtomicInteger;

public class SmsCategory {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
    private int catId;
    private String categoryName;
    private String searchString;

    public SmsCategory() {
        catId = ID_GENERATOR.getAndIncrement();
        this.categoryName = "Все sms";
        this.searchString = "";
    }

    public SmsCategory(String pNname, String pSearchString) {
        catId = ID_GENERATOR.getAndIncrement();
        this.categoryName = pNname;
        this.searchString = pSearchString;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }
}
