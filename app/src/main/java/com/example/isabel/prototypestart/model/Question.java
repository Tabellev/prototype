package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 28.03.2015.
 */
public class Question {
    private int mID;
    private QuestionType mType;
    private String mQuestionText;

    private String[] mResponseOptions;
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
