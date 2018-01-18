package com.example.nikashkuratova.smsreader.pojo;

import static com.example.nikashkuratova.smsreader.utils.ObjectCreatorHelper.ID_GENERATOR;

public class SmsCategory {

    private int catId;
    private String categoryName;
    private String searchString;

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
