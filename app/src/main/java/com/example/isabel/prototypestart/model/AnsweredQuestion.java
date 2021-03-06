package com.example.isabel.prototypestart.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/* Will represent a question in the Output file
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

    public long getTimeUsed() {
        return mTimeUsed;
    }

    public boolean skippedQuestion() {
        return mSkippedQuestion;
    }

    public boolean answerWasCorrect() {
        return mAnswerWasCorrect;
    }

    public void setTimeUsed(long mTimeUsed) {
        this.mTimeUsed = mTimeUsed;
    }

    public void setSkippedQuestion(boolean mSkippedQuestion) {
        this.mSkippedQuestion = mSkippedQuestion;
    }

    public void setGivenAnswer(String[] givenAnswer) {

        for (int i = 0; i < givenAnswer.length; i++) {
            if (givenAnswer[i] == null) {
                this.mSkippedQuestion = true;
            }
            else if (givenAnswer.length != 0) {
                this.mGivenAnswer = givenAnswer;

                Arrays.sort(mCorrectAnswer);
                Arrays.sort(mGivenAnswer);

                this.mSkippedQuestion = false;
                this.mAnswerWasCorrect = Arrays.equals(mCorrectAnswer, mGivenAnswer);
            } else {
                this.mAnswerWasCorrect = false;
            }
        }
    }
}
