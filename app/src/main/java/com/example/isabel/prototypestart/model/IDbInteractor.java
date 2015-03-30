package com.example.isabel.prototypestart.model;

import java.util.List;

/**
 * Created by Isabel on 09.03.2015.
 */

/**
 * Created by Isabel on 09.03.2015.
 */
public interface IDbInteractor {

    List<Question> getQuestions();
    Question getQuestionFromId(int id);
}
