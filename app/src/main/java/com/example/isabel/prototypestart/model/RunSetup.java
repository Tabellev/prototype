package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 16.03.2015.
 *
 * A run holding information from the configuration file
 */
public class RunSetup {
    private int mRunID;
    private String mOperatorID;
    private String mScenario;
    private int mNumberOfQuestions;
    private long mRunTimeLimit;
    private QuestionSet[] mQuestionSet;

    public RunSetup(int runID, String operatorID, String scenario, long runTimeLimit,
                    QuestionSet[] questionSet) {
        this.mRunID = runID;
        this.mOperatorID = operatorID;
        this.mScenario = scenario;
        this.mRunTimeLimit = runTimeLimit;
        this.mQuestionSet = questionSet;
        setNumberOfQuestions();
    }

    private void setNumberOfQuestions() {
        this.mNumberOfQuestions = mQuestionSet.length;
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

    public int getmNumberOfQuestions() {
        return mNumberOfQuestions;
    }

    public long getmRunTimeLimit() {
        return mRunTimeLimit;
    }

    public QuestionSet[] getmQuestionSet() {
        return mQuestionSet;
    }
}
