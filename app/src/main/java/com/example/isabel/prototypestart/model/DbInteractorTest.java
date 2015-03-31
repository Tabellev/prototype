package com.example.isabel.prototypestart.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Isabel on 09.03.2015.
 *
 * This class is the central provider of data for the application
 *
 * TODO: Rename everything containing mock[....] when things are working as expected
 */
public class DbInteractorTest implements IDbInteractor {
    // The context is needed if we intend to use resources (context.getResources()...)
    private Context mContext;
    // Will provide data from JSON and export data to JSON
    private JsonManager mockDataProvider;
    // Collection to hold Question objects from Questions file
    private HashMap<Integer, Question> mockQuestions;
    // mockSession holds information from simulated Configuration file
    private Session mockSession;
    // Will be a data-buffer for input during the test session
    private  TestResult mTestResult;
    // Deprecated!
    List<Question> questionList = new ArrayList<Question>();


    public DbInteractorTest(Context context){
        this.mContext = context;
        mockDataProvider = new JsonManager(mContext);
        mockQuestions = new HashMap<>();
        this.mockQuestions = mockDataProvider.getQuestionMap();
        this.mockSession = mockDataProvider.getMockSession();

        // instantiate mTestResult so it is ready to receive input data during the test session
        // we will work directly with this when saving input and time used
        mTestResult = new TestResult(mockSession.getExperimentName(), mockSession.getSessionID(),
                                    mockSession.getCrewID(), mockSession.getNumberOfRuns());


        /*information from questionList must be switched out with information from mockQuestions
        constructed from the information in mockSession*/
        questionList.add(new Question(1, "Hva heter du?", QuestionType.SingleChoice, new String[]{"YES", "NO"},
                                            new String[]{"NO"}));
        questionList.add(new Question(2,"Hvor gammel er du?", QuestionType.MultipleChoice, new String[]{"Two", "Three", "Four", "Five"},
                                            new String[]{"Three"}));
        questionList.add(new Question(3, "Hva er 5/2?", QuestionType.Numerical, new String[]{}, new String[]{"2.5"}));
    }

    // TODO: Change to HashMap<Integer, Question> when things are working
    @Override
    public List<Question> getQuestions() {
        return questionList;
    }

    // TODO: Change as appropriate to match HashMap...(if necessary)
    @Override
    public Question getQuestionFromId(int id) {
        return questionList.get(id);
    }

    // Temporary method to provide the questions from Questions file
    @Override
    public HashMap<Integer, Question> getMockQuestions() {
        return mockQuestions;
    }

    // Temporary method to provide one question
    @Override
    public Question getQuestion(int key) { return mockQuestions.get(key); }

    // Provides a Session object containing all information needed to construct a test session
    @Override
    public Session getMockSession() {
        return mockSession;
    }

    @Override
    public TestResult getTestResult() {
        return mTestResult;
    }
}
