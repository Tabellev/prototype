package com.example.isabel.prototypestart.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Isabel on 09.03.2015.
 */
public class DbInteractorTest implements IDbInteractor {
    private Context mContext;
    //
    private JsonManager mockDataProvider = new JsonManager(mContext);
    // Collection to hold Question objects from Questions file
    private HashMap<Integer, MockQuestion> mockQuestions;
    List<Question> questionList = new ArrayList<Question>();


    public DbInteractorTest(Context context){
        this.mContext = context;
        mockQuestions = new HashMap<>();
        this.mockQuestions = mockDataProvider.getQuestionMap();

        for (int i = 0; i < mockQuestions.size(); i++) {
            System.out.println(mockQuestions.values().toString() + " : " +
                    mockQuestions.keySet().toString());
        }
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
