package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 16.03.2015.
 *
 * A run holding information from the configuration file
 */
public class RunSetup extends Run {
    private int mNumberOfQuestions;
    private QuestionSet[] mQuestionSet;

    public RunSetup(int runID, String operatorID, String scenario, long runTimeLimit,
                    QuestionSet[] questionSet) {
        super(runID, operatorID, scenario, runTimeLimit);
        this.mQuestionSet = questionSet;
        setNumberOfQuestions();
    }

    private void setNumberOfQuestions() {
        this.mNumberOfQuestions = mQuestionSet.length;
    }

    public int getmNumberOfQuestions() {
        return mNumberOfQuestions;
    }

    public QuestionSet[] getmQuestionSet() {
        return mQuestionSet;
    }
}
