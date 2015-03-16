package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 16.03.2015.
 *
 * This class may be obsolete if we use information directly from configuration file
 *
 * Alias: Configuration file
 */
public class ConfigurationData {
    private String mExperimentName;
    private int mSessionID;
    private int mCrewID;
    private String mOperatorID;
    private int mNumberOfRuns;
    private RunSetup[] mRunsToSetup;

    public ConfigurationData(String experimentName, int sessionID, int crewID, String operatorID,
                             RunSetup[] runsToSetup) {
        this.mExperimentName = experimentName;
        this.mSessionID = sessionID;
        this.mCrewID = crewID;
        this.mOperatorID = operatorID;
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

    public String getmOperatorID() {
        return mOperatorID;
    }

    public int getmNumberOfRuns() {
        return mNumberOfRuns;
    }

    public RunSetup[] getmRunsToSetup() {
        return mRunsToSetup;
    }
}
