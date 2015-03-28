package com.example.isabel.prototypestart.model;

import java.util.HashMap;

/**
 * Created by oyvind on 27.03.2015.
 */
public class JsonManager {

    // Placeholders for actual data from JSON Configuration file
    private Session mDummySession;
    private HashMap<Integer, Question> questionMap;
    private String[][] mockAlternatives;

    public JsonManager() {
        // Create simulation data to work with the UI
        mockAlternatives = new String[20][];
        // Q1 - Numerical
        mockAlternatives[0][0] = "";
        // Q2
        mockAlternatives[1][0] = "Running";
        mockAlternatives[1][2] = "Stopped";
        // Q3 - Numerical
        mockAlternatives[2][0] = "";
        // Q4
        mockAlternatives[3][0] = "Running";
        mockAlternatives[3][1] = "Tripped";
        // Q5
        mockAlternatives[4][0] = "P104";
        mockAlternatives[4][1] = "P105";
        // Q6 - Numerical
        mockAlternatives[5][0] = "";
        // Q7
        mockAlternatives[6][0] = "Running";
        mockAlternatives[6][1] = "Stopped";
        // Q8 - Numerical
        mockAlternatives[7][0] = "";
        // Q9 - Numerical
        mockAlternatives[8][0] = "";
        // Q10 - Numerical
        mockAlternatives[9][0] = "";
        // Q11
        mockAlternatives[10][0] = "YES";
        mockAlternatives[10][1] = "NO";
        // Q12
        mockAlternatives[11][0] = "YES";
        mockAlternatives[11][1] = "NO";
        // Q13
        mockAlternatives[12][0] = " YES";
        mockAlternatives[12][1] = "NO";
        // Q14 - Numerical
        mockAlternatives[13][0] = "";
        // Q15
        mockAlternatives[14][0] = "YES";
        mockAlternatives[14][1] = "NO";
        // Q16
        mockAlternatives[15][0] = "YES";
        mockAlternatives[15][1] = "NO";
        // Q17 - Numerical
        mockAlternatives[16][0] = "";
        // Q18
        mockAlternatives[17][0] = "SG-1";
        mockAlternatives[17][1] = "SG-2";
        mockAlternatives[17][2] = "SG-3";
        // Q19
        mockAlternatives[18][0] = "YES";
        mockAlternatives[18][1] = "NO";
        // Q20
        mockAlternatives[19][0] = "YES";
        mockAlternatives[19][1] = "NO";



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
        configRuns[1] = new RunSetup(7001, "SS", "c1", 250000, getConfigQuestionsForOneRun(20, 200));
        configRuns[2] = new RunSetup(7002, "SS", "c3", 280000, getConfigQuestionsForOneRun(20, 300));

        Session currentSession = new Session("Micro-Tasks", 5000, 800, configRuns);

        return currentSession;
    }

    // Placeholder method for providing mock-data while implementing the View(UI)
    private QuestionSet[] getConfigQuestionsForOneRun(int numberOfQuestions, int startId) {
        QuestionSet[] configQuestionsReturned = new QuestionSet[numberOfQuestions];
        int id = startId - 1;
        for(int i = 0; i < numberOfQuestions; i++) {
            configQuestionsReturned[i] = new QuestionSet(startId++, 30000);
        }

        // Returns array of QuestionSets with amount given when creating a RunSetup
        return configQuestionsReturned;
    }

    // Placeholder method to provide Question objects for populating the fragments
    public HashMap<Integer, Question> getQuestionMap() {
        return this.questionMap;
    }

    private void createQuestions() {
        // Create all the questions
        // Take real questions with real alternatives from IFE material(20 x 3)
        // Create all the Questions --> add to this.questionMap
        MockQuestion q = new MockQuestion(100, "spørsmålet", QuestionType.MultipleChoice,
                                        mockAlternatives[0]);
    }
}
