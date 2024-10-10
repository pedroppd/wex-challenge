package com.testewex.demo;

import lombok.Getter;

@Getter
public class User {
    private String date;
    private String pageId;
    private String customerId;

    public User(String date, String pageId, String customerId) {
        this.date = date;
        this.customerId = customerId;
        this.pageId = pageId;
    }

    public String getId() {
        return String.join(this.date, this.customerId);
    }
}
