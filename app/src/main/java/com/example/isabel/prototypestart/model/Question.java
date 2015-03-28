package com.example.isabel.prototypestart.model;

/**
 * Created by Isabel on 09.03.2015.
 *
 * Contains information from the Questions file
 */
public class Question {

    private int mID;
    private QuestionType mType;
    private String mQuestionText;
    //private String[] mResponseOptions;

    public Question(int id, String questionText, QuestionType type){
        this.mID = id;
        this.mQuestionText = questionText;
        this.mType = type;
    }

    /*public String[] getResponseOptions() {
        return mResponseOptions;
    }*/

    public String getQuestionText() {
        return mQuestionText;
    }

    public int getID() {
        return mID;
    }

    public QuestionType getType() {
        return mType;
    }

}
