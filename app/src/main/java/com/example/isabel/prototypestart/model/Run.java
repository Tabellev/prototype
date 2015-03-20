package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 20.03.2015.
 */
public class Run {
    private int mRunID;
    private String mOperatorID;
    private String mScenario;
    private long mRunTimeLimit;

    public Run(int runID, String operatorID, String scenario, long runTimeLimit) {
        this.mRunID = runID;
        this.mOperatorID = operatorID;
        this.mScenario = scenario;
        this.mRunTimeLimit = runTimeLimit;
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
}
