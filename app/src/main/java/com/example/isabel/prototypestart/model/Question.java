package com.example.isabel.prototypestart.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by oyvind on 28.03.2015.
 */
public class Question {
    @SerializedName("questionID")
    private int mID;
    @SerializedName("questionType")
    private QuestionType mType;
    @SerializedName("questionText")
    private String mQuestionText;
    @SerializedName("responseOptions")
    private String[] mResponseOptions;
    @SerializedName("correctAnswer")
    private String[] mCorrectAnswer;

    public Question(int id, String questionText, QuestionType type, String[] options,
                    String[] correctAnswer) {
        //super(id, questionText, type);
        this.mID = id;
        this.mQuestionText = questionText;
        this.mType = type;
        this.mResponseOptions = options;
        this.mCorrectAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public int getID() {
        return mID;
    }

    public QuestionType getType() {
        return mType;
    }

    public String[] getResponseOptions() {
        return mResponseOptions;
    }

    public String[] getCorrectAnswer() {
        return mCorrectAnswer;
    }
}
