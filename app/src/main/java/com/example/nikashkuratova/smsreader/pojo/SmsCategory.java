package com.example.nikashkuratova.smsreader.pojo;

import java.util.concurrent.atomic.AtomicInteger;

import static com.example.nikashkuratova.smsreader.utils.UtilsHelper.ALL_SMS_SEARCH_STR;

public class SmsCategory {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    private int catId;
    private String categoryName;
    private String searchString;

    public SmsCategory(String categoryName) {
        catId = ID_GENERATOR.getAndIncrement();
        this.categoryName = categoryName;
        this.searchString = ALL_SMS_SEARCH_STR;
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

}
