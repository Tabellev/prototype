package com.example.isabel.prototypestart.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by oyvind on 16.03.2015.
 *
 * A run holding results from test
 */
public class RunResult extends Run {
    @SerializedName("startTime")
    private long mStartTime; // Must be a Date, not long
    @SerializedName("stopTime")
    private long mStopTime; // Must be a Date, not long
    @SerializedName("runTimeUsed")
    private long mRunTimeUsed;
    @SerializedName("numberOfQuestions")
    private int mNumberOfQuestions;
    @SerializedName("answeredQuestions")
    private AnsweredQuestion[] mAnsweredQuestions; // initialize with numberOfQuestions from RunSetup (1)

    // Add parameter int numberOfQuestions (2)
    public RunResult(int runID, String operatorID, String scenario, long runTimeLimit,
                     long runTimeUsed, int numberOfQuestions /*AnsweredQuestion[] answeredQuestions*/) {
        super(runID, operatorID, scenario, runTimeLimit);
        this.mRunTimeUsed = runTimeUsed;
        this.mNumberOfQuestions = numberOfQuestions;
        // Initialize mAnsweredQuestions here (3)
        this.mAnsweredQuestions = new AnsweredQuestion[numberOfQuestions];
        //this.mAnsweredQuestions = answeredQuestions;
    }

    private void setStartTime() {
        this.mStartTime = 0;
        // Convert to Date with timezone!!!!!!!!!!
    }
    public void setStopTime() {
        this.mStopTime = 0;
        // Convert to Date with timezone!!!!!!!!!
    }
    public long getRunTimeUsed() {
        return mRunTimeUsed;
    }

    // Set time when user navigates to next question
    public void setRunTimeUsed(long timeUsed) { this.mRunTimeUsed = timeUsed; }

    public AnsweredQuestion[] getAnsweredQuestions() {
        return mAnsweredQuestions;
    }

    public void addAnsweredQuestion(AnsweredQuestion question) {
        this.mAnsweredQuestions = appendQuestion(this.mAnsweredQuestions, question);
    }

    // If this fails, build AnsweredQuestion[] in DBInteractorTest
    private AnsweredQuestion[] appendQuestion(AnsweredQuestion[] answeredQuestions, AnsweredQuestion newQuestion) {
        ArrayList<AnsweredQuestion> temp = new ArrayList<>(Arrays.asList(answeredQuestions));
        temp.add(newQuestion);
        return (AnsweredQuestion[]) temp.toArray();
    }
}
