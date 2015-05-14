package com.example.isabel.prototypestart.model;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by oyvind on 16.03.2015.
 *
 * Alias: Output file
 */
public class TestResult {
    @SerializedName("startTime")
    private Date mStartTime;
    @SerializedName("stopTime")
    private Date mStopTime;
    @SerializedName("experimentName")
    private String mExperimentName;
    @SerializedName("sessionID")
    private int mSessionID;
    @SerializedName("crewID")
    private int mCrewID;
    @SerializedName("numberOfRuns")
    private int mNumberOfRuns;
    @SerializedName("runs")
    private RunResult[] mRunResults;

    public TestResult(String experimentName, int sessionID, int crewID, int numberOfRuns) {
        this.mExperimentName = experimentName;
        this.mSessionID = sessionID;
        this.mCrewID = crewID;
        this.mNumberOfRuns = numberOfRuns;
        this.mRunResults = new RunResult[numberOfRuns];
        this.mStartTime = new Date();
    }

    public void setStopTime(Date stopTime){
        this.mStopTime = stopTime;
    }
    public String getExperimentName() {
        return mExperimentName;
    }

    public int getSessionID() {
        return mSessionID;
    }

    public int getCrewID() {
        return mCrewID;
    }

    public int getNumberOfRuns() {
        return mNumberOfRuns;
    }

    // Testing to reach the right RunResult when adding an AnsweredQuestion
    public RunResult getRunResult(int runID) {
        for(int i = 0; i < mRunResults.length; i++) {

            if (runID == mRunResults[i].getRunID()) {

                return mRunResults[i];
            } else {

                continue;
            }
        }
        return null;
    }

    public void addRunResult(RunResult newRunResult, int index) {

        this.mRunResults[index] = newRunResult;
    }
}
