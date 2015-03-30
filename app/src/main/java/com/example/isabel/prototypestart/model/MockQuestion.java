package com.example.isabel.prototypestart.model;

/**
 * Created by oyvind on 28.03.2015.
 */
public class MockQuestion extends Question {
    private String[] mResponseOptions;
    //private String[] mCorrectAnswer;
    private String[] mCorrectAnswer;

    public MockQuestion(int id, String questionText, QuestionType type, String[] options,
                        String[] correctAnswer) {
        super(id, questionText, type);
        this.mResponseOptions = options;
        this.mCorrectAnswer = correctAnswer;
    }

    public String[] getResponseOptions() {
        return mResponseOptions;
    }

    public String[] getCorrectAnswer() {
        return mCorrectAnswer;
    }
}
