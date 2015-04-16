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
 * TODO: We may be forced to build the AnsweredQuestion objects tied to the Fragments here, and save input to this object with the ViewPager.OnPageChangeListeners methods
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
    // Key: runSetup ID , Values: HashMap(Key: questionSetup ID , Values: QuestionSetup objects from "Configuaration file")
    // Used in the Fragments to access a Question's time limit
    private HashMap<Integer, HashMap<Integer, QuestionSetup>> runSetupQuestions;

    private int[] mRunIDs;
    // Deprecated!
    List<Question> questionList = new ArrayList<Question>();


    public DbInteractorTest(Context context){
        this.mContext = context;
        mockDataProvider = new JsonManager(mContext);
        mockQuestions = new HashMap<>();
        runSetupQuestions = new HashMap<Integer, HashMap<Integer, QuestionSetup>>();
        this.mockQuestions = mockDataProvider.getQuestionMap();
        this.mockSession = mockDataProvider.getMockSession();

        // populates mRunIDs
        setRunIDs();

        // instantiate mTestResult so it is ready to receive input data during the test session
        // we will work directly with this when saving input and time used

        initializeTestResult();

        // The data used to access a Question's time limit when populating an AnsweredQuestion object in the Fragments
        createAccessibleQuestionSetupList();


        // Old-----------------------------------------------------------------------------------------------
        /*information from questionList must be switched out with information from mockQuestions
        constructed from the information in mockSession*/
        questionList.add(new Question(1, "Hva heter du?", QuestionType.SingleChoice, new String[]{"YES", "NO"},
                                            new String[]{"NO"}));
        questionList.add(new Question(2,"Hvor gammel er du?", QuestionType.MultipleChoice, new String[]{"Two", "Three", "Four", "Five"},
                                            new String[]{"Three"}));
        questionList.add(new Question(3, "Hva er 5/2?", QuestionType.Numerical, new String[]{}, new String[]{"2.5"}));
        //-------------------------------------------------------------------------------------------------------------------
    }

    // Creates the initial TestResult object with information from Session
    private void initializeTestResult() {
        RunSetup[] runs = mockSession.getRunsToSetup();
        int length = runs.length;

        mTestResult = new TestResult(mockSession.getExperimentName(), mockSession.getSessionID(),
                mockSession.getCrewID(), mockSession.getNumberOfRuns());

        /*int runID, String operatorID, String scenario, long runTimeLimit, int numberOfQuestions */
        for (int i = 0; i < length; i++) {
            int id = mockSession.getRunsToSetup()[i].getRunID();
            String operatorID = mockSession.getRunsToSetup()[i].getOperatorID();
            String scenario = mockSession.getRunsToSetup()[i].getScenario();
            long timeLimit = mockSession.getRunsToSetup()[i].getRunTimeLimit();
            int numOfQuestions = mockSession.getRunsToSetup()[i].getNumberOfQuestions();
            mTestResult.addRunResult(new RunResult(id, operatorID, scenario, timeLimit, numOfQuestions), i);
        }
    }


    private void createAccessibleQuestionSetupList() {
        int lengthRuns = mockSession.getRunsToSetup().length;

        for (int i = 0; i < lengthRuns; i++) {
            HashMap<Integer, QuestionSetup> temp = new HashMap<>();
            int runId = mockSession.getRunsToSetup()[i].getRunID();
            int lengthQuestions = mockSession.getRunsToSetup()[i].getQuestionSetup().length;

            for (int j = 0; j < lengthQuestions; j++) {
                int questionId = mockSession.getRunsToSetup()[i].getQuestionSetup()[j].getQuestionID();
                temp.put(questionId, mockSession.getRunsToSetup()[i].getQuestionSetup()[j]);
            }
            runSetupQuestions.put(mockSession.getRunsToSetup()[i].getRunID(), temp);
        }
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

    @Override
    public HashMap<Integer, HashMap<Integer, QuestionSetup>> getRunSetupQuestions() {
        return runSetupQuestions;
    }

    private void setRunIDs() {
        int length = this.mockSession.getRunsToSetup().length;
        mRunIDs = new int[length];
        for (int i = 0; i < mRunIDs.length; i++) {
            mRunIDs[i] = this.mockSession.getRunsToSetup()[i].getRunID();
        }
    }

    public int[] getRunIDs() {
        return this.mRunIDs;
    }
}
