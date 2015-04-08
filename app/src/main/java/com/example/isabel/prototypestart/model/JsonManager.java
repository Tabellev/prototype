package com.example.isabel.prototypestart.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.isabel.prototypestart.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by oyvind on 27.03.2015.
 */
public class JsonManager {

    private Context context;
    // Placeholders for actual data from JSON Configuration file
    // TODO: rename mMockSession to mSession
    private Session mMockSession;
    private HashMap<Integer, Question> questionMap;
    private final int QUESTIONS_FILE = 1;
    private final int CONFIGURATION_FILE = 2;
    private final String OUTPUT_DIRECOTRY_NAME = "Testresult";
    private final String OUTPUT_FILE_NAME = "testresult.json";
    private Gson gson;

    public JsonManager(Context context) {
        this.context = context;
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .setPrettyPrinting()
                .create();

        questionMap = new HashMap<>();

        this.mMockSession = createSession();
        createQuestions();

        //exportTestResultsToJson(); // called for testing export of testresults to JSON
    }

    public HashMap<Integer, QuestionSetup> importDataFromQuestionsFile() {
        // Populate a HashMap with QuestionSetups
        return null;
    }

    // The actual Questions will be fetched when needed based on QuestionSetup-id
    public Session buildSessionFromJson() {
        // Instantiate a Session object with data from Configuration and Questions file
        return null;
    }


    public void exportTestResultsToJson(TestResult testResult) {
        // Create Output file in tablet's local file system
        // TODO: remove the dummy code. It is only for testing file writing functionality
        /*AnsweredQuestion[] answeredQuestions = new AnsweredQuestion[] {
                new AnsweredQuestion(100, 20000, new String[] {"A", "B"}),
                new AnsweredQuestion(101, 25000, new String[] {"C"}),
                new AnsweredQuestion(102, 30000, new String[] {"42"})
        };
        answeredQuestions[0].setGivenAnswer(new String[] {"C", "B"});
        answeredQuestions[1].setGivenAnswer(new String[] {"C"});
        answeredQuestions[2].setGivenAnswer(new String[] {"42"});

        AnsweredQuestion[] answeredQuestions2 = new AnsweredQuestion[] {
                new AnsweredQuestion(103, 20000, new String[] {"C", "D"}),
                new AnsweredQuestion(104, 25000, new String[] {"A"}),
                new AnsweredQuestion(105, 30000, new String[] {"42"})
        };
        answeredQuestions2[0].setGivenAnswer(new String[] {"D", "C"});
        answeredQuestions2[1].setGivenAnswer(new String[] {"A"});
        answeredQuestions2[2].setGivenAnswer(new String[] {"43"});

        RunResult[] runResults = new RunResult[] {
                new RunResult(1000, "SS", "c1", 75000, 0, 3),
                new RunResult(1001, "SB", "c2", 75000, 0, 3)
        };

        // setTheAnsweredQuestions() is not to be used in prototype
        runResults[0].setTheAnsweredQuestions(answeredQuestions);
        runResults[1].setTheAnsweredQuestions(answeredQuestions2);

        TestResult testResult = new TestResult("MicroTasks", 800, 5000, 2);
        // setTheRunResults() is not to be used in prototype
        testResult.setTheRunResults(runResults);*/

        String json = gson.toJson(testResult);

        // Write output file to device's external storage
        //Log.e("SD-card", String.valueOf(isExternalStorageWritable()));
        if (isExternalStorageWritable()) {
            File dir = getOutputStorageDir(context, OUTPUT_DIRECOTRY_NAME);
            File file = new File(dir, OUTPUT_FILE_NAME);
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream out = new FileOutputStream(file);
                out.write(json.getBytes());
                out.flush();
                out.close();
            } catch (Exception e) {
                Log.e("Exception", e.toString());
            }
        }

    }

    // Check if external storage is available for read and write
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    // Creates directory to hold output file
    public File getOutputStorageDir(Context context, String albumName) {
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS), albumName);
        if (!file.mkdirs()) {
            Log.e("Create Directory", "Directory not created");
        }
        return file;
    }

    // TODO: modify to read from external storage
    private String readJsonFile(int rawResource) {
        // Convert this to read from local storage on tablet
        InputStream inStream;
        switch (rawResource) {
            case CONFIGURATION_FILE:
                inStream = context.getResources().openRawResource(R.raw.configurationfile);
                break;
            case QUESTIONS_FILE:
                inStream = context.getResources().openRawResource(R.raw.questionsfile);
                break;
            default:
                inStream = null;
        }

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
            int n;
            while((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                inStream.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();
        return jsonString;
    }





    public Session getMockSession() {
        return mMockSession;
    }

    // Placeholder method for providing mock-data while implementing the View(UI)
    public Session createSession() {
        // Read from R.raw.configurationfile
        String jsonString = readJsonFile(CONFIGURATION_FILE);
        Session session = gson.fromJson(jsonString, Session.class);

        return session;
    }

    // Placeholder method for providing mock-data while implementing the View(UI)
    private QuestionSetup[] getConfigQuestionsForOneRun(int numberOfQuestions, int startId) {
        QuestionSetup[] configQuestionsReturned = new QuestionSetup[numberOfQuestions];
        int id = startId - 1;
        for(int i = 0; i < numberOfQuestions; i++) {
            configQuestionsReturned[i] = new QuestionSetup(++id, 30000);
        }

        // Returns array of QuestionSets with amount given when creating a RunSetup
        return configQuestionsReturned;
    }

    // Placeholder method to provide Question objects for populating the fragments
    public HashMap<Integer, Question> getQuestionMap() {
        return this.questionMap;
    }

    //---------------------------------------------------------------------------------------------
    private void createQuestions() {
        // Create all the questions
        // Take real questions with real alternatives from IFE material(20 x 3)
        // Create all the Questions --> add to this.questionMap
        HashMap<Integer, Question> returnedMockQuestions = new HashMap<>();

        // Reading from R.raw.questionsfile.json
        String jsonString = readJsonFile(QUESTIONS_FILE);
        //Gson
        /*Type type = new TypeToken<Question[]>(){}.getType();
        Question[] questions = gson.fromJson(jsonString, type);*/


        Type type = new TypeToken<ArrayList<Question>>(){}.getType();
        ArrayList<Question> questions = gson.fromJson(jsonString, type);

        // Populate the HashMap of Question objects
        for (int i = 0; i < questions.size(); i++) {
            questionMap.put(questions.get(i).getID(), questions.get(i));
        }
    }

    // class to handle serializing and deserializing Date
    private class DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

        private final DateFormat dateFormat;

        private DateTypeAdapter() {
            dateFormat = new SimpleDateFormat("yyyy.MM.dd'T'HH:mm:ssZ", Locale.getDefault());
            dateFormat.setTimeZone(TimeZone.getDefault());
        }
        /**
         * Gson invokes this call-back method during deserialization when it encounters a field of the
         * specified type.
         * <p>In the implementation of this call-back method, you should consider invoking
         * {@link com.google.gson.JsonDeserializationContext#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type)} method to create objects
         * for any non-trivial field of the returned object. However, you should never invoke it on the
         * the same type passing {@code json} since that will cause an infinite loop (Gson will call your
         * call-back method again).
         *
         * @param json    The Json data being deserialized
         * @param typeOfT The type of the Object to deserialize to
         * @param context
         * @return a deserialized object of the specified type typeOfT which is a subclass of {@code T}
         * @throws com.google.gson.JsonParseException if json is not in the expected format of {@code typeofT}
         */
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return dateFormat.parse(json.getAsString());
            } catch (ParseException e) {
                throw new JsonParseException(e);
            }
        }

        /**
         * Gson invokes this call-back method during serialization when it encounters a field of the
         * specified type.
         * <p/>
         * <p>In the implementation of this call-back method, you should consider invoking
         * {@link com.google.gson.JsonSerializationContext#serialize(Object, java.lang.reflect.Type)} method to create JsonElements for any
         * non-trivial field of the {@code src} object. However, you should never invoke it on the
         * {@code src} object itself since that will cause an infinite loop (Gson will call your
         * call-back method again).</p>
         *
         * @param src       the object that needs to be converted to Json.
         * @param typeOfSrc the actual type (fully genericized version) of the source object.
         * @param context
         * @return a JsonElement corresponding to the specified object.
         */
        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(dateFormat.format(src));
        }
    }
}
