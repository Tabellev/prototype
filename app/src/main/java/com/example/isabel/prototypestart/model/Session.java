package com.example.isabel.prototypestart.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by oyvind on 16.03.2015.
 *
 * This class may be obsolete if we use information directly from configuration file
 *
 * It represents the Configuration file
 */
public class Session {
    @SerializedName("experimentName")
    private String mExperimentName;
    @SerializedName("sessionID")
    private int mSessionID;
    @SerializedName("crewID")
    private int mCrewID;
    private int mNumberOfRuns;
    @SerializedName("runs")
    private RunSetup[] mRunsToSetup;


    public Session(String experimentName, int sessionID, int crewID, RunSetup[] runsToSetup) {
        this.mExperimentName = experimentName;
        this.mSessionID = sessionID;
        this.mCrewID = crewID;
        this.mRunsToSetup = runsToSetup;
        setNumberOfRuns();
    }

    private void setNumberOfRuns() {
        this.mNumberOfRuns = mRunsToSetup.length;
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

    public int getNumberOfRuns() { return mNumberOfRuns; }

    public RunSetup[] getRunsToSetup() {
        return mRunsToSetup;
    }
}
