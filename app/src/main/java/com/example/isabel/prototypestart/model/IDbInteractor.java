package com.example.isabel.prototypestart.model;

import java.util.HashMap;

public interface IDbInteractor {


    HashMap<Integer, Question> getQuestions();

    Question getQuestion(int key);

    Session getSession();

    TestResult getTestResult();

    HashMap<Integer, HashMap<Integer, QuestionSetup>> getRunSetupQuestions();

    int[] getRunIDs();

    public void createOutputJsonFile();

    TestStatistics getTestStatistics();
}
