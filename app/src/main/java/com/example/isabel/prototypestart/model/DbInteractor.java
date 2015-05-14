package com.example.isabel.prototypestart.model;

import android.content.Context;

import java.util.HashMap;

public class DbInteractor implements IDbInteractor {
    // The context is needed if we intend to use resources (context.getResources()...)
    private Context mContext;
    // Will provide data from JSON and export data to JSON
    private JsonManager dataProvider;
    // Collection to hold Question objects from Questions file
    private HashMap<Integer, Question> questions;
    // session holds information from simulated Configuration file
    private Session session;
    // Will be a data-buffer for input during the test session
    private  TestResult mTestResult;
    // Key: runSetup ID , Values: HashMap(Key: questionSetup ID , Values: QuestionSetup objects from "Configuaration file")
    // Used in the Fragments to access a Question's time limit
    private HashMap<Integer, HashMap<Integer, QuestionSetup>> runSetupQuestions;

    private int[] mRunIDs;

    private TestStatistics testStatistics;

    public DbInteractor(Context context){
        this.mContext = context;
        dataProvider = new JsonManager(mContext);
        questions = new HashMap<>();
        runSetupQuestions = new HashMap<>();
        this.questions = dataProvider.getQuestionMap();
        this.session = dataProvider.getSession();

        this.testStatistics = new TestStatistics();

        // populates mRunIDs
        setRunIDs();

        // instantiate mTestResult so it is ready to receive input data during the test session
        // we will work directly with this when saving input and time used
        initializeTestResult();

        // The data used to access a Question's time limit when populating an AnsweredQuestion object in the Fragments
        createAccessibleQuestionSetupList();

    }

    // Creates the initial TestResult object with information from Session
    private void initializeTestResult() {
        RunSetup[] runs = session.getRunsToSetup();
        int length = runs.length;

        mTestResult = new TestResult(session.getExperimentName(), session.getSessionID(),
                session.getCrewID(), session.getNumberOfRuns());

        for (int i = 0; i < length; i++) {
            int id = session.getRunsToSetup()[i].getRunID();

            String operatorID = session.getRunsToSetup()[i].getOperatorID();
            String scenario = session.getRunsToSetup()[i].getScenario();
            long timeLimit = session.getRunsToSetup()[i].getRunTimeLimit();
            int numOfQuestions = session.getRunsToSetup()[i].getNumberOfQuestions();

            mTestResult.addRunResult(new RunResult(id, operatorID, scenario, timeLimit, numOfQuestions), i);
        }
    }


    private void createAccessibleQuestionSetupList() {
        int lengthRuns = session.getRunsToSetup().length;

        for (int i = 0; i < lengthRuns; i++) {
            HashMap<Integer, QuestionSetup> temp = new HashMap<>();
            int lengthQuestions = session.getRunsToSetup()[i].getQuestionSetup().length;

            for (int j = 0; j < lengthQuestions; j++) {
                int questionId = session.getRunsToSetup()[i].getQuestionSetup()[j].getQuestionID();
                temp.put(questionId, session.getRunsToSetup()[i].getQuestionSetup()[j]);
            }
            runSetupQuestions.put(session.getRunsToSetup()[i].getRunID(), temp);
        }
    }


   // Method to provide the questions from Questions file
    @Override
    public HashMap<Integer, Question> getQuestions() {
        return questions;
    }

    // Method to provide one question
    @Override
    public Question getQuestion(int key) { return questions.get(key); }

    // Provides a Session object containing all information needed to construct a test session
    @Override
    public Session getSession() {
        return session;
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
        int length = this.session.getRunsToSetup().length;
        mRunIDs = new int[length];
        for (int i = 0; i < mRunIDs.length; i++) {
            mRunIDs[i] = this.session.getRunsToSetup()[i].getRunID();
        }
    }

    public int[] getRunIDs() {
        return this.mRunIDs;
    }

    public void createOutputJsonFile() {
        this.dataProvider.exportTestResultsToJson(mTestResult);
    }

    @Override
    public TestStatistics getTestStatistics() {
        return testStatistics;
    }
}
