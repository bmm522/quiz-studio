package com.jobseeckerstudio.bmm522.user.entity.common;

public enum CategoryName {

    JAVA("java");

    private final String category;

    CategoryName(String category) {this.category = category;}

    public String get(){
        return category;
    }
}
