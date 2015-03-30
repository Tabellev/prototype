package com.example.isabel.prototypestart.model;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by oyvind on 27.03.2015.
 */
public class JsonManager {

    private Context context;
    // Placeholders for actual data from JSON Configuration file
    private Session mMockSession;
    private HashMap<Integer, MockQuestion> questionMap;

    public JsonManager(Context context) {
        this.context = context;
        questionMap = new HashMap<>();

        this.mMockSession = createSession();
        createQuestions();
    }

    public HashMap<Integer, QuestionSet> importDataFromQuestionsFile() {
        // Populate a HashMap with QuestionSets
        return null;
    }

    // The actual Questions will be fetched when needed based on QuestionSet-id
    public Session buildSessionFromJson() {
        // Instantiate a Session object with data from Configuration and Questions file
        return null;
    }


    public void exportTestResultsToJson(TestResult testResult) {
        // Create Output file in tablet's local file system
    }


    public Session getMockSession() {
        return mMockSession;
    }

    // Placeholder method for providing mock-data while implementing the View(UI)
    public Session createSession() {
        // Create RunSetup objects, hard-coded 3 runs
        RunSetup[] configRuns = new RunSetup[3];
        configRuns[0] = new RunSetup(7000, "SS", "c2", 300000, getConfigQuestionsForOneRun(3, 100));
        configRuns[1] = new RunSetup(7001, "SS", "c1", 250000, getConfigQuestionsForOneRun(3, 103));
        configRuns[2] = new RunSetup(7002, "SS", "c3", 280000, getConfigQuestionsForOneRun(3, 106));

        Session currentSession = new Session("Micro-Tasks", 5000, 800, configRuns);

        return currentSession;
    }

    // Placeholder method for providing mock-data while implementing the View(UI)
    private QuestionSet[] getConfigQuestionsForOneRun(int numberOfQuestions, int startId) {
        QuestionSet[] configQuestionsReturned = new QuestionSet[numberOfQuestions];
        int id = startId - 1;
        for(int i = 0; i < numberOfQuestions; i++) {
            configQuestionsReturned[i] = new QuestionSet(++id, 30000);
        }

        // Returns array of QuestionSets with amount given when creating a RunSetup
        return configQuestionsReturned;
    }

    // Placeholder method to provide Question objects for populating the fragments
    public HashMap<Integer, MockQuestion> getQuestionMap() {
        return this.questionMap;
    }

    //---------------------------------------------------------------------------------------------
    private void createQuestions() {
        // Create all the questions
        // Take real questions with real alternatives from IFE material(20 x 3)
        // Create all the Questions --> add to this.questionMap
        HashMap<Integer, MockQuestion> returnedMockQuestions = new HashMap<>();
        //int startId = 99;

        // Run 1
        questionMap.put(100, new MockQuestion(100, "How many condensate pumps are running on turbine 31?",
                QuestionType.Numerical, new String[]{}, new String[]{"3"}));
        questionMap.put(101, new MockQuestion(101, "On Turbine 31, what is the status of pump P204?",
                QuestionType.SingleChoice, new String[]{"Running", "Stopped"}, new String[]{"Stopped"}));
        questionMap.put(102, new MockQuestion(102, "Which SG atmospheric valves are in manual control?",
                QuestionType.MultipleChoice, new String[]{"SG-1", "SG-2", "SG-3"}, new String[]{"SG-2", "SG-3"}));

        // Run 2
        questionMap.put(103, new MockQuestion(103, "How many condensate pumps are running on turbine 31?",
                QuestionType.Numerical, new String[]{}, new String[]{"3"}));
        questionMap.put(104, new MockQuestion(104, "On Turbine 31, what is the status of pump P204?",
                QuestionType.SingleChoice, new String[]{"Running", "Stopped"}, new String[]{"Stopped"}));
        questionMap.put(105, new MockQuestion(105, "Which SG atmospheric valves are in manual control?",
                QuestionType.MultipleChoice, new String[]{"SG-1", "SG-2", "SG-3"}, new String[]{"SG-2", "SG-3"}));

        // Run 3
        questionMap.put(106, new MockQuestion(106, "How many condensate pumps are running on turbine 31?",
                QuestionType.Numerical, new String[]{}, new String[]{"3"}));
        questionMap.put(107, new MockQuestion(107, "On Turbine 31, what is the status of pump P204?",
                QuestionType.SingleChoice, new String[]{"Running", "Stopped"}, new String[]{"Stopped"}));
        questionMap.put(108, new MockQuestion(108, "Which SG atmospheric valves are in manual control?",
                QuestionType.MultipleChoice, new String[]{"SG-1", "SG-2", "SG-3"}, new String[]{"SG-2", "SG-3"}));

//        String[] questions = context.getResources().getStringArray(R.array.mock_questions);
//        // TODO: correctAnswers needs to be multidimensional to hold multiple correct answers
//        String[] correctAnswers = context.getResources().getStringArray(R.array.mock_correct_answers);
//        String[] questionTypes = context.getResources().getStringArray(R.array.mock_question_types);
//
//
//        /*MockQuestion q = new MockQuestion(100, "spørsmålet", QuestionType.MultipleChoice,
//                                        mMockAlternatives[0]);*/
//        // int id, String questionText, QuestionType type, String[] options
//        for (int i = 0; i <= questions.length; i++) {
//            MockQuestion q = new MockQuestion(++startId, questions[i], QuestionType.valueOf(questionTypes[i]),
//                                                mMockAlternatives[0], correctAnswers[i]);


            //questionMap.put(q.getID(), q);

    }
    //---------------------------------------------------------------------------------------------
}
