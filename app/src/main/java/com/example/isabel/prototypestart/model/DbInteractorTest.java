package com.example.isabel.prototypestart.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Isabel on 09.03.2015.
 */
public class DbInteractorTest implements IDbInteractor {

    List<Question> questionList = new ArrayList<Question>();



    public DbInteractorTest(){
        questionList.add(new Question(1, "Hva heter du?", QuestionType.SingleChoice));
        questionList.add(new Question(2,"Hvor gammel er du?", QuestionType.MultipleChoice));
        questionList.add(new Question(3, "Hva er 5/2?", QuestionType.Numerical));
    }
    @Override
    public List<Question> getQuestions() {
        return questionList;
    }

    @Override
    public Question getQuestionFromId(int id) {
        return questionList.get(id);
    }
}
