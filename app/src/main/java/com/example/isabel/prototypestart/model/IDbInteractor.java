package com.example.isabel.prototypestart.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Isabel on 09.03.2015.
 */

/**
 * Created by Isabel on 09.03.2015.
 */
public interface IDbInteractor {

    // TODO: Change to HashMap<Integer, Question> when things are working
    List<Question> getQuestions();
    // TODO: Change as appropriate to match HashMap...(if necessary)
    Question getQuestionFromId(int id);

    // new methods
    HashMap<Integer, Question> getMockQuestions();

    Question getQuestion(int key);

    Session getMockSession();

    TestResult getTestResult();
}
