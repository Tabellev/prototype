package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 16.03.2015.
 *
 * A run holding results from test
 */
public class RunResult {
    private int mRunID;
    private String mOperatorID;
    private String mScenario;
    private long mRunTimeLimit;
    private long mRunTimeUsed;
    private AnsweredQuestion[] mAnsweredQuestions;

    public RunResult(int runID, String operatorID, String scenario, long runTimeLimit,
                     long runTimeUsed, AnsweredQuestion[] answeredQuestions) {

        this.mRunID = runID;
        this.mOperatorID = operatorID;
        this.mScenario = scenario;
        this.mRunTimeLimit = runTimeLimit;
        this.mRunTimeUsed = runTimeUsed;
        this.mAnsweredQuestions = answeredQuestions;
    }

    public int getmRunID() {
        return mRunID;
    }

    public String getmOperatorID() {
        return mOperatorID;
    }

    public String getmScenario() {
        return mScenario;
    }

    public long getmRunTimeLimit() {
        return mRunTimeLimit;
    }

    public long getmRunTimeUsed() {
        return mRunTimeUsed;
    }

    public AnsweredQuestion[] getmAnsweredQuestions() {
        return mAnsweredQuestions;
    }
}
