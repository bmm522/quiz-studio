package com.jobseeckerstudio.user.quiz.entity.common;

public enum CategoryName {

    JAVA("java");

    private final String category;

    CategoryName(String category) {this.category = category;}

    public String get(){
        return category;
    }
}
