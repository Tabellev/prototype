package com.example.isabel.prototypestart.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by oyvind on 16.03.2015.
 *
 * Will represent a question in the Output file
 */
public class AnsweredQuestion {
    @SerializedName("questionID")
    private int mQuestionID;
    @SerializedName("timeLimit")
    private long mTimeLimit;
    @SerializedName("timeUsed")
    private long mTimeUsed;
    @SerializedName("skippedQuestion")
    private boolean mSkippedQuestion;
    @SerializedName("givenAnswer")
    private String[] mGivenAnswer;
    @SerializedName("correctAnswer")
    private String[] mCorrectAnswer;
    @SerializedName("answerWasCorrect")
    private boolean mAnswerWasCorrect;


    // We may need this constructor when initializing a private instance in the Fragments
    public AnsweredQuestion(int questionID, long timeLimit, String[] correctAnswer) {
        this.mQuestionID = questionID;
        this.mTimeLimit = timeLimit;
        this.mCorrectAnswer = correctAnswer;
        // mSkippedQuestion is set false when an answer is given
        this.mSkippedQuestion = true;
    }

    /*public AnsweredQuestion(int questionID, long timeLimit, long timeUsed, boolean skipped,
                            String[] givenAnswer, String[] correctAnswer, boolean answerWasCorrect) {
        this.mQuestionID = questionID;
        this.mTimeLimit = timeLimit;
        this.mTimeUsed = timeUsed;
        this.mSkippedQuestion = skipped;
        this.mGivenAnswer = givenAnswer;
        this.mCorrectAnswer = correctAnswer;
        this.mAnswerWasCorrect = answerWasCorrect;
    }*/

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

    public String[] getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public boolean isAnswerWasCorrect() {
        return mAnswerWasCorrect;
    }

    public void setTimeLimit(long mTimeLimit) {
        this.mTimeLimit = mTimeLimit;
    }

    public void setTimeUsed(long mTimeUsed) {
        this.mTimeUsed = mTimeUsed;
    }

    public void setSkippedQuestion(boolean mSkippedQuestion) {
        this.mSkippedQuestion = mSkippedQuestion;
    }

    public void setGivenAnswer(String[] givenAnswer) {
        this.mGivenAnswer = givenAnswer;

        Arrays.sort(mCorrectAnswer);
        Arrays.sort(mGivenAnswer);

        this.mSkippedQuestion = false;
        this.mAnswerWasCorrect = Arrays.equals(mCorrectAnswer, mGivenAnswer);
    }

    /*private void setAnswerWasCorrect(boolean answerWasCorrect) {

        this.mAnswerWasCorrect = answerWasCorrect;
    }*/
}
