package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 29.04.2015.
 */
public class TestStatistics {
    // Sum of all runTimeUsed
    private int sessionTimeUsed;
    private int numberOfCorrectAnswers;
    private int numberOfWrongAnswers;
    private int numberOfSkippedAnswers;

    public TestStatistics() {}

    public int getSessionTimeUsed() {
        return sessionTimeUsed;
    }

    public void setSessionTimeUsed(int sessionTimeUsed) {
        this.sessionTimeUsed += sessionTimeUsed;
    }

    public int getNumberOfCorrectAnswers() {
        return numberOfCorrectAnswers;
    }

    public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers) {
        this.numberOfCorrectAnswers += numberOfCorrectAnswers;
    }

    public int getNumberOfWrongAnswers() {
        return numberOfWrongAnswers;
    }

    public void setNumberOfWrongAnswers(int numberOfWrongAnswers) {
        this.numberOfWrongAnswers += numberOfWrongAnswers;
    }

    public int getNumberOfSkippedAnswers() {
        return numberOfSkippedAnswers;
    }

    public void setNumberOfSkippedAnswers(int numberOfSkippedAnswers) {
        this.numberOfSkippedAnswers += numberOfSkippedAnswers;
    }
}
