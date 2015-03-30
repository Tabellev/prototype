package com.example.isabel.prototypestart.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Isabel on 09.03.2015.
 */
public class DbInteractorTest implements IDbInteractor {
    private Context mContext;
    private JsonManager mockDataProvider;
    // Collection to hold Question objects from Questions file
    private HashMap<Integer, Question> mockQuestions;
    private Session mockSession;
    List<Question> questionList = new ArrayList<Question>();


    public DbInteractorTest(Context context){
        this.mContext = context;
        mockDataProvider = new JsonManager(mContext);
        mockQuestions = new HashMap<>();
        this.mockQuestions = mockDataProvider.getQuestionMap();
        this.mockSession = mockDataProvider.getMockSession();

        for (int i = 100; i < mockQuestions.size(); i++) {
            Log.d("Questions-content",String.valueOf(mockQuestions.get(i).getID()) + " : " +
                    mockQuestions.get(i).getQuestionText());
        }
        questionList.add(new Question(1, "Hva heter du?", QuestionType.SingleChoice, new String[]{"YES", "NO"},
                                            new String[]{"NO"}));
        questionList.add(new Question(2,"Hvor gammel er du?", QuestionType.MultipleChoice, new String[]{"Two", "Three", "Four", "Five"},
                                            new String[]{"Three"}));
        questionList.add(new Question(3, "Hva er 5/2?", QuestionType.Numerical, new String[]{}, new String[]{"2.5"}));
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
