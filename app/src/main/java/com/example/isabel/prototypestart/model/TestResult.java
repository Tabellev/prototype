package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 16.03.2015.
 *
 * This class may be obsolete if storage to DB between questions goes fast.
 * It may be necessary if we store data to an instance of this class between questions,
 * and write to DB when a run is finished.
 *
 * Alias: Output file
 */
public class TestResult {
    private String mExperimentName;
    private int mSessionID;
    private int mCrewID;
    private int mNumberOfRuns;
    private RunResult[] mRunResults;

    public TestResult(String experimentName, int sessionID, int crewID, RunResult[] runResults) {

        this.mExperimentName = experimentName;
        this.mSessionID = sessionID;
        this.mCrewID = crewID;
        this.mRunResults = runResults;
        setNumberOfRuns();
    }

    private void setNumberOfRuns() {
        this.mNumberOfRuns = mRunResults.length;
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

    public RunResult[] getmRunResults() {
        return mRunResults;
    }
}
