package com.example.isabel.prototypestart.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by oyvind on 16.03.2015.
 *
 * A run holding results from test
 */
public class RunResult extends Run {
    @SerializedName("startTime")
    private Date mStartTime;
    @SerializedName("stopTime")
    private Date mStopTime;
    @SerializedName("runTimeUsed")
    private long mRunTimeUsed;
    @SerializedName("numberOfQuestions")
    private int mNumberOfQuestions;
    @SerializedName("answeredQuestions")
    // initialize with numberOfQuestions from RunSetup (1)
    private AnsweredQuestion[] mAnsweredQuestions;

    // Add parameter int numberOfQuestions (2)
    public RunResult(int runID, String operatorID, String scenario, long runTimeLimit,
                      int numberOfQuestions) {
        super(runID, operatorID, scenario, runTimeLimit);

        this.mNumberOfQuestions = numberOfQuestions;
        // Initialize mAnsweredQuestions here (3)
        this.mAnsweredQuestions = new AnsweredQuestion[numberOfQuestions];
    }

    /*public long getRunTimeUsed() {
        return mRunTimeUsed;
    }

    // Set time when user navigates to next question
    public void setRunTimeUsed(long timeUsed) { this.mRunTimeUsed = timeUsed; }

    public AnsweredQuestion[] getAnsweredQuestions() {
        return mAnsweredQuestions;
    }*/

    public void addAnsweredQuestion(AnsweredQuestion question) {

        for(int i = 0; i < mAnsweredQuestions.length; i++) {
            if (mAnsweredQuestions[i] == null) {
                mAnsweredQuestions[i] = question;
                break;
            }
        }
        // Add time used for added question to mRunTimeUsed
        this.mRunTimeUsed += question.getTimeUsed();
    }
}
