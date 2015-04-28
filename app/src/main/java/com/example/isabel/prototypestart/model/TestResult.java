package com.example.isabel.prototypestart.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

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
    /*private long mStartTime;
    private long mStopTime;*/
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
        /*this.mStartTime = 0;
        this.mStopTime = 0;*/
        this.mRunResults = new RunResult[numberOfRuns];
    }

    /*public TestResult(long startTime, long stopTime, String experimentName, int sessionID,
                      int crewID, RunResult[] runResults) {

        this.mStartTime = startTime;
        this.mStopTime = stopTime;
        this.mExperimentName = experimentName;
        this.mSessionID = sessionID;
        this.mCrewID = crewID;
        this.mRunResults = runResults;
        // Will get numberOfRuns from Session object
        //setNumberOfRuns();
    }*/

    /*public long getStartTime() {
        return mStartTime;
    }*/

    /*public void setStartTime(long startTime) { this.mStartTime = startTime; }*/

    /*public long getStopTime() {
        return mStopTime;
    }*/

    /*public void setStopTime(long stopTime) { this.mStopTime = stopTime; }*/

    /*private void setNumberOfRuns() {
        this.mNumberOfRuns = mRunResults.length;
    }*/
// This method will not be used in the prototype
    //TODO: remove this method!!!!!!
    public void setTheRunResults(RunResult[] rr) {
        this.mRunResults = rr;
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

    // Testing to reach the right RunResult when adding an AnsweredQuestion
    public RunResult getRunResult(int runID) {
        Log.d("Input-runID:", String.valueOf(runID));
        for(int i = 0; i < mRunResults.length; i++) {
            Log.d("ID:", String.valueOf(mRunResults[i].getRunID()));
            // Må gjøres annerledes!!! går i else fordi første element i mRunResults[] er 7000 og neste Run har 7001
            // lage switch på runID hvis vi ikke får til en bedre løsning...
            if (runID == mRunResults[i].getRunID()) {

                //Log.d("In getRunResult():", String.valueOf(mRunResults[i].getRunID()));

                return mRunResults[i];
            } else {

                //Log.d("In getRunResult():", "Returned NULL!!!");
                continue;
                //return null;
            }
        }
        //Log.d("In getRunResult():", "Returned NULL After Loop!!!");
        return null;
    }

    public void addRunResult(RunResult newRunResult, int index) {
        //this.mRunResults = appendRunResult(this.mRunResults, newRunResult);
        int length = this.mRunResults.length;
        this.mRunResults[index] = newRunResult;

    }

    // If this fails, build RunResult[] in DBInteractorTest
    /*private RunResult[] appendRunResult(RunResult[] runResults, RunResult newRunResult) {
        ArrayList<RunResult> temp = new ArrayList<>(Arrays.asList(runResults));
        temp.add(newRunResult);
        return (RunResult[]) temp.toArray();
    }*/
}
