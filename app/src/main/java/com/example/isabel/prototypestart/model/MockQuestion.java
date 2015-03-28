package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 28.03.2015.
 */
public class MockQuestion extends Question {
    private String[] mResponseOptions;

    public MockQuestion(int id, String questionText, QuestionType type, String[] options) {
        super(id, questionText, type);
        this.mResponseOptions = options;
    }

    public String[] getmResponseOptions() {
        return mResponseOptions;
    }
}
