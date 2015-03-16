package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 16.03.2015.
 */
public class QuestionSet {
    private int mQuestionID;
    private long mTimeLimit;

    public QuestionSet(int questionID, long timeLimit) {
        this.mQuestionID = questionID;
        this.mTimeLimit = timeLimit;
    }

    public int getmQuestionID() {
        return mQuestionID;
    }

    public long getmTimeLimit() {
        return mTimeLimit;
    }
}
