package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 16.03.2015.
 *
 * Will represent a question in the Output file
 */
public class AnsweredQuestion {
    private int mQuestionID;  // From configuration file?
    private long mTimeLimit; // From configuration file
    private long mTimeUsed;
    private boolean mSkippedQuestion;
    private String[] mGivenAnswer; // Maybe given answer must be String for all questionTypes?
    private String mCorrectAnswer; // Same as above?
    private boolean mAnswerWasCorrect;

    public AnsweredQuestion(int questionID, long timeLimit, long timeUsed, boolean skipped,
                            String[] givenAnswer, String correctAnswer, boolean answerWasCorrect) {
        this.mQuestionID = questionID;
        this.mTimeLimit = timeLimit;
        this.mTimeUsed = timeUsed;
        this.mSkippedQuestion = skipped;
        this.mGivenAnswer = givenAnswer;
        this.mCorrectAnswer = correctAnswer;
        this.mAnswerWasCorrect = answerWasCorrect;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public long getTimeLimit() {
        return mTimeLimit;
    }

    public long getTimeUsed() {
        return mTimeUsed;
    }

    public boolean isSkippedQuestion() {
        return mSkippedQuestion;
    }

    public String[] getGivenAnswer() {
        return mGivenAnswer;
    }

    public String getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public boolean isAnswerWasCorrect() {
        return mAnswerWasCorrect;
    }
}
