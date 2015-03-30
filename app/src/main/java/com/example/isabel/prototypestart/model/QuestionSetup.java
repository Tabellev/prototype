package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 16.03.2015.
 *
 * Contains information from the Configuration file
 */
public class QuestionSetup {
    private int mQuestionID;
    private long mTimeLimit;

    public QuestionSetup(int questionID, long timeLimit) {
        this.mQuestionID = questionID;
        this.mTimeLimit = timeLimit;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public long getTimeLimit() {
        return mTimeLimit;
    }
}
