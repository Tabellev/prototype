package com.example.isabel.prototypestart.model;

import android.content.Context;

import com.example.isabel.prototypestart.R;

import java.util.HashMap;

/**
 * Created by oyvind on 27.03.2015.
 */
public class JsonManager {

    private Context context;
    // Placeholders for actual data from JSON Configuration file
    private Session mDummySession;
    private HashMap<Integer, MockQuestion> questionMap;
    private String[][] mMockAlternatives;

    public JsonManager(Context context) {
        this.context = context;
        // TODO: create HashMap<Integer, String[]> alternativesForSingleAndMultipleChoice
        // Unable to use Array with empty string for Numerical questions.
        // 1. convert mMockAlternatives to HashMap<Integer, String[]>, find key from QuestionSets
        // 2. may need to use HarshMap<Integer, QuestionSet> and the same for
        // 3. may need to move this operation after the Session creation here in constructor?
        mMockAlternatives = new String[20][];
        // Q1 - Numerical
        mMockAlternatives[0][0] = "";
        // Q2
        mMockAlternatives[1][0] = "Running";
        mMockAlternatives[1][2] = "Stopped";
        // Q3 - Numerical
        mMockAlternatives[2][0] = "";
        // Q4
        mMockAlternatives[3][0] = "Running";
        mMockAlternatives[3][1] = "Tripped";
        // Q5
        mMockAlternatives[4][0] = "P104";
        mMockAlternatives[4][1] = "P105";
        // Q6 - Numerical
        mMockAlternatives[5][0] = "";
        // Q7
        mMockAlternatives[6][0] = "Running";
        mMockAlternatives[6][1] = "Stopped";
        // Q8 - Numerical
        mMockAlternatives[7][0] = "";
        // Q9 - Numerical
        mMockAlternatives[8][0] = "";
        // Q10 - Numerical
        mMockAlternatives[9][0] = "";
        // Q11
        mMockAlternatives[10][0] = "YES";
        mMockAlternatives[10][1] = "NO";
        // Q12
        mMockAlternatives[11][0] = "YES";
        mMockAlternatives[11][1] = "NO";
        // Q13
        mMockAlternatives[12][0] = " YES";
        mMockAlternatives[12][1] = "NO";
        // Q14 - Numerical
        mMockAlternatives[13][0] = "";
        // Q15
        mMockAlternatives[14][0] = "YES";
        mMockAlternatives[14][1] = "NO";
        // Q16
        mMockAlternatives[15][0] = "YES";
        mMockAlternatives[15][1] = "NO";
        // Q17 - Numerical
        mMockAlternatives[16][0] = "";
        // Q18
        mMockAlternatives[17][0] = "SG-1";
        mMockAlternatives[17][1] = "SG-2";
        mMockAlternatives[17][2] = "SG-3";
        // Q19
        mMockAlternatives[18][0] = "YES";
        mMockAlternatives[18][1] = "NO";
        // Q20
        mMockAlternatives[19][0] = "YES";
        mMockAlternatives[19][1] = "NO";



        this.mDummySession = getSession();
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



    // Placeholder method for providing mock-data while implementing the View(UI)
    public Session getSession() {
        // Create RunSetup objects, hard-coded 3 runs
        RunSetup[] configRuns = new RunSetup[3];
        configRuns[0] = new RunSetup(7000, "SS", "c2", 300000, getConfigQuestionsForOneRun(20, 100));
        configRuns[1] = new RunSetup(7001, "SS", "c1", 250000, getConfigQuestionsForOneRun(20, 120));
        configRuns[2] = new RunSetup(7002, "SS", "c3", 280000, getConfigQuestionsForOneRun(20, 140));

        Session currentSession = new Session("Micro-Tasks", 5000, 800, configRuns);

        return currentSession;
    }

    // Placeholder method for providing mock-data while implementing the View(UI)
    private QuestionSet[] getConfigQuestionsForOneRun(int numberOfQuestions, int startId) {
        QuestionSet[] configQuestionsReturned = new QuestionSet[numberOfQuestions];
        int id = startId - 1;
        for(int i = 0; i < numberOfQuestions; i++) {
            configQuestionsReturned[i] = new QuestionSet(++startId, 30000);
        }

        // Returns array of QuestionSets with amount given when creating a RunSetup
        return configQuestionsReturned;
    }

    // Placeholder method to provide Question objects for populating the fragments
    public HashMap<Integer, MockQuestion> getQuestionMap() {
        return this.questionMap;
    }

    private void createQuestions() {
        // Create all the questions
        // Take real questions with real alternatives from IFE material(20 x 3)
        // Create all the Questions --> add to this.questionMap
        HashMap<Integer, MockQuestion> returnedMockQuestions = new HashMap<>();
        int startId = 99;

        String[] questions = context.getResources().getStringArray(R.array.mock_questions);
        // TODO: correctAnswers needs to be multidimensional to hold multiple correct answers
        String[] correctAnswers = context.getResources().getStringArray(R.array.mock_correct_answers);
        String[] questionTypes = context.getResources().getStringArray(R.array.mock_question_types);


        /*MockQuestion q = new MockQuestion(100, "spørsmålet", QuestionType.MultipleChoice,
                                        mMockAlternatives[0]);*/
        // int id, String questionText, QuestionType type, String[] options
        for (int i = 0; i <= questions.length; i++) {
            MockQuestion q = new MockQuestion(++startId, questions[i], QuestionType.valueOf(questionTypes[i]),
                                                mMockAlternatives[0], correctAnswers[i]);

            // Add the mock-question to the hashmap
            questionMap.put(q.getID(), q);
        }
    }
}
