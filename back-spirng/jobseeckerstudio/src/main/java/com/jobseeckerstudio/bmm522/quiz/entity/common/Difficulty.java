package com.jobseeckerstudio.bmm522.quiz.entity.common;

public enum Difficulty {

    EASY("easy"), HARD("hard");

    private final String difficulty;

    Difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String get() {
        return difficulty;
    }
}