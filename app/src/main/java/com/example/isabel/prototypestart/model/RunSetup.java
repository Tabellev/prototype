package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 16.03.2015.
 *
 * A run holding information from the configuration file
 */
public class RunSetup extends Run {
    private int mNumberOfQuestions;
    private QuestionSetup[] mQuestionSetup;

    public RunSetup(int runID, String operatorID, String scenario, long runTimeLimit,
                    QuestionSetup[] questionSetup) {
        super(runID, operatorID, scenario, runTimeLimit);
        this.mQuestionSetup = questionSetup;
        setNumberOfQuestions();
    }

    private void setNumberOfQuestions() {
        this.mNumberOfQuestions = mQuestionSetup.length;
    }

    public int getNumberOfQuestions() {
        return mNumberOfQuestions;
    }

    public QuestionSetup[] getQuestionSetup() {
        return mQuestionSetup;
    }
}
