package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 16.03.2015.
 *
 * This class may be obsolete if we use information directly from configuration file
 *
 * It represents the Configuration file
 */
public class Session {
    private String mExperimentName;
    private int mSessionID;
    private int mCrewID;
    private int mNumberOfRuns;
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

    public String getmExperimentName() {
        return mExperimentName;
    }

    public int getmSessionID() {
        return mSessionID;
    }

    public int getmCrewID() {
        return mCrewID;
    }

    public int getmNumberOfRuns() {
        return mNumberOfRuns;
    }

    public RunSetup[] getmRunsToSetup() {
        return mRunsToSetup;
    }
}
