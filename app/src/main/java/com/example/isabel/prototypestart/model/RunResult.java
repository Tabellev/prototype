package com.example.isabel.prototypestart.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by oyvind on 16.03.2015.
 *
 * A run holding results from test
 */
public class RunResult extends Run {
    @SerializedName("startTime")
    private Date mStartTime; // Must be a Date, not long
    @SerializedName("stopTime")
    private Date mStopTime; // Must be a Date, not long
    @SerializedName("runTimeUsed")
    private long mRunTimeUsed;
    @SerializedName("numberOfQuestions")
    private int mNumberOfQuestions;
    @SerializedName("answeredQuestions")
    private AnsweredQuestion[] mAnsweredQuestions; // initialize with numberOfQuestions from RunSetup (1)

    // Add parameter int numberOfQuestions (2)
    public RunResult(int runID, String operatorID, String scenario, long runTimeLimit,
                     /*long runTimeUsed,*/ int numberOfQuestions /*AnsweredQuestion[] answeredQuestions*/) {
        super(runID, operatorID, scenario, runTimeLimit);
        //this.mRunTimeUsed = runTimeUsed;
        this.mNumberOfQuestions = numberOfQuestions;
        // Initialize mAnsweredQuestions here (3)
        this.mAnsweredQuestions = new AnsweredQuestion[numberOfQuestions];
        //this.mAnsweredQuestions = answeredQuestions;

        // These must be called when a run starts and stops in the flow of fragments

        //setStartTime();
        //setStopTime();

    }

    // This method will not be used in the prototype
    // TODO: remove this method!!!
    public void setTheAnsweredQuestions(AnsweredQuestion[] aq) {
        this.mAnsweredQuestions = aq;
    }

    public void setStartTime() {
        Date now = new Date();
        this.mStartTime = now;
    }

    public void setStopTime() {
        Date now = new Date();
        this.mStopTime = now;
    }

    public long getRunTimeUsed() {
        return mRunTimeUsed;
    }

    // Set time when user navigates to next question
    public void setRunTimeUsed(long timeUsed) { this.mRunTimeUsed = timeUsed; }

    public AnsweredQuestion[] getAnsweredQuestions() {
        return mAnsweredQuestions;
    }

    //TODO: modify as described in SingleChoiceFragment
    public void addAnsweredQuestion(AnsweredQuestion question/*, int index*/) {

        for(int i = 0; i < mAnsweredQuestions.length; i++) {
            if (mAnsweredQuestions[i] == null) {
                mAnsweredQuestions[i] = question;
                break;
            }
        }

        //Log.d("AnsweredQuestions!:", Arrays.deepToString(mAnsweredQuestions));
        /*for(int i = 0; i < mAnsweredQuestions.length; i++) {
            if (mAnsweredQuestions[i] != null) {
                Log.d("AnsweredQuestion:", "AQ(" + i + ")" + mAnsweredQuestions[i].getQuestionID() +
                        "GivAnsw: " + mAnsweredQuestions[i].getGivenAnswer()[0]);
            } else {
                Log.d("AnsweredQuestion:", "AQ(" + i + ") is null");
            }
        }*/
        //this.mAnsweredQuestions = appendQuestion(this.mAnsweredQuestions, question);
    }

    // If this fails, build AnsweredQuestion[] in DBInteractorTest
    /*private AnsweredQuestion[] appendQuestion(AnsweredQuestion[] answeredQuestions, AnsweredQuestion newQuestion) {
        ArrayList<AnsweredQuestion> temp = new ArrayList<>(Arrays.asList(answeredQuestions));
        temp.add(newQuestion);
        return (AnsweredQuestion[]) temp.toArray();
    }*/
}
