package com.example.isabel.prototypestart.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by oyvind on 20.03.2015.
 */
public class Run {
    @SerializedName("runID")
    private int mRunID;
    @SerializedName("operatorID")
    private String mOperatorID;
    @SerializedName("scenario")
    private String mScenario;
    @SerializedName("runTimeLimit")
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
