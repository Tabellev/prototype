package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 16.03.2015.
 *
 * A run holding results from test
 */
public class RunResult extends Run {
    private long mRunTimeUsed;
    private AnsweredQuestion[] mAnsweredQuestions;

    public RunResult(int runID, String operatorID, String scenario, long runTimeLimit,
                     long runTimeUsed, AnsweredQuestion[] answeredQuestions) {
        super(runID, operatorID, scenario, runTimeLimit);
        this.mRunTimeUsed = runTimeUsed;
        this.mAnsweredQuestions = answeredQuestions;
    }

    public long getmRunTimeUsed() {
        return mRunTimeUsed;
    }

    public AnsweredQuestion[] getmAnsweredQuestions() {
        return mAnsweredQuestions;
    }
}
