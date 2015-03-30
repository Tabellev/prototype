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
    private long mStartTime;
    private long mStopTime;
    private String mExperimentName;
    private int mSessionID;
    private int mCrewID;
    private int mNumberOfRuns;
    private RunResult[] mRunResults;

    public TestResult(long startTime, long stopTime, String experimentName, int sessionID,
                      int crewID, RunResult[] runResults) {

        this.mStartTime = startTime;
        this.mStopTime = stopTime;
        this.mExperimentName = experimentName;
        this.mSessionID = sessionID;
        this.mCrewID = crewID;
        this.mRunResults = runResults;
        setNumberOfRuns();
    }

    public long getStartTime() {
        return mStartTime;
    }

    public long getStopTime() {
        return mStopTime;
    }

    private void setNumberOfRuns() {
        this.mNumberOfRuns = mRunResults.length;
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

    public RunResult[] getRunResults() {
        return mRunResults;
    }
}
