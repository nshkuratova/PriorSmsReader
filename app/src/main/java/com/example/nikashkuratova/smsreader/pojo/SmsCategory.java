package com.example.nikashkuratova.smsreader.pojo;

public class SmsCategory {
    private String categoryName;
    private String searchString;

    public SmsCategory() {
        this.categoryName = "Все sms";
        this.searchString = "";
    }

    public SmsCategory(String pNname, String pSearchString) {
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
}
