package com.example.isabel.prototypestart.model;

/**
 * Created by Isabel on 09.03.2015.
 */
public class Question {

    private int id;
    private QuestionType mType;

    public String getBodyText() {
        return bodyText;
    }

    private String bodyText;

    public int getId() {
        return id;
    }

    public QuestionType getmType() {
        return mType;
    }

    public Question(int id, String bodyText, QuestionType type){
        this.id = id;
        this.bodyText = bodyText;
        this.mType = type;
    }
}
