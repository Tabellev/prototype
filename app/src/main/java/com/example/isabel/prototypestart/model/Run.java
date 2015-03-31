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

    public int getRunID() {
        return mRunID;
    }

    public String getOperatorID() {
        return mOperatorID;
    }

    public String getScenario() {
        return mScenario;
    }

    public long getRunTimeLimit() {
        return mRunTimeLimit;
    }
}
