package com.example.isabel.prototypestart;


import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.isabel.prototypestart.model.*;

import java.util.HashMap;
import java.util.Locale;


public class SingleChoiceFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private Button btnOption1;
    private Button btnOption2;
    private Button btnOption3;
    private Button btnOption4;
    private Button btnOption5;
    private Button dontKnow;
    private TextView swipe;
    private TextView questionText;
    private int questionID;
    private int runID;
    private Question question;
    private static final String QUESTIONID = "questionID";
    private static final String RUN_ID = "runID";
    private LinearLayout buttonLayout;
    private long startTime;
    private long stopTime;
    private long questionTime;
    private long runTime;

    private HashMap<Integer, HashMap<Integer, QuestionSetup>> questionConfigurationData;
    private AnsweredQuestion answeredQuestion;

    // Maybe add parameter RunID?
    public static SingleChoiceFragment newInstance(int runID, int questionID) {
        SingleChoiceFragment fragmentSingleChoice = new SingleChoiceFragment();
        // Maybe add RUNID to the bundle?
        Bundle args = new Bundle();
        args.putInt(QUESTIONID, questionID);
        args.putInt(RUN_ID, runID);
        fragmentSingleChoice.setArguments(args);
        return fragmentSingleChoice;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        questionID = getArguments().getInt(QUESTIONID);
        runID = getArguments().getInt(RUN_ID);
        // This is where the Fragment gets hold of the question
        // Old before test with HashMap
        //question = ((MainActivity)getActivity()).getDBInteractor().getQuestionFromId(questionID);

        // Test with HashMap for Questions
        question = ((MainActivity)getActivity()).getDBInteractor().getMockQuestions().get(questionID);
        // argument to AnsweredQuestion constructor
        String[] correctAnswer = question.getCorrectAnswer();
        //------------------------------------------------------------------------------------------------

        // Experimental code ------------------------------------------------------------------------------------
        // Get information about the current Question(time limit, etc.)
        questionConfigurationData = ((MainActivity)getActivity()).getDBInteractor().getRunSetupQuestions();

        // TODO: separate the Runs with the 'runFinished'-Fragments
        // runId must be the actual runId to match the once used in PagerAdapter....!!!!!
        //int runId = 7000; // this ID must come from the current run which the current Question belongs to
        HashMap<Integer, QuestionSetup> run1QuestionSetups = questionConfigurationData.get(runID);
        // argument to AnsweredQuestion constructor
        long timeLimit = run1QuestionSetups.get(questionID).getTimeLimit();

        // instantiate answeredQuestion
        answeredQuestion = new AnsweredQuestion(questionID, timeLimit, correctAnswer);

        //-------------------------------------------------------------------------------------------------------


        // The comments below is just examples of how to interact with the data structures
        //((MainActivity)getActivity()).getDBInteractor().getTestResult().getRunResults()[0].getRunID();
        //((MainActivity)getActivity()).getDBInteractor().getTestResult().getRunResults()[0].addAnsweredQuestion(questionToAdd);
        View view =  inflater.inflate(R.layout.fragment_single_choice, container, false);
        btnOption1 = new Button(getActivity().getApplicationContext());
        btnOption1.setOnClickListener(this);
        btnOption2 = new Button(getActivity().getApplicationContext());
        btnOption2.setOnClickListener(this);
        btnOption3 = new Button(getActivity().getApplicationContext());
        btnOption3.setOnClickListener(this);
        btnOption4 = new Button(getActivity().getApplicationContext());
        btnOption4.setOnClickListener(this);
        btnOption5 = new Button(getActivity().getApplicationContext());
        btnOption5.setOnClickListener(this);
        dontKnow = (Button) view.findViewById(R.id.singleDontKnow);
        dontKnow.setOnClickListener(this);
        swipe = (TextView) view.findViewById(R.id.singleChoiceContinue);
        buttonLayout = (LinearLayout)view.findViewById(R.id.buttonLayoutSingle);
        questionText = (TextView) view.findViewById(R.id.singleAnswerQuestion);
        String questionT = checkTextLength(question.getQuestionText());
        questionText.setText(questionT);
        //questionText.setText(question.getQuestionText() + question.getQuestionText().length());

        buttonSetup();
        return  view;
    }

    public void buttonSetup(){
        final float scale = getResources().getDisplayMetrics().density;
        RelativeLayout.LayoutParams tableParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        tableParams.topMargin = 550;

       LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        int rightMargin = (int) (50 * scale + 0.5f);
        buttonParams.rightMargin = rightMargin;

        // Convert the dps to pixels, based on density scale
        int height = (int) (200 * scale + 0.5f);
        int width = (int) (220 * scale + 0.5f);

        buttonParams.width = width;
        buttonParams.height =  height;

        btnOption1.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption1.setTextColor(Color.rgb(255,255,255));
        btnOption1.setLayoutParams(buttonParams);
        btnOption1.setId(R.id.btnSingleOption1Id);
        btnOption1.setTextAppearance(getActivity().getApplicationContext(), R.style.buttonTextSize);

        btnOption2.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption2.setTextColor(Color.rgb(255, 255, 255));
        btnOption2.setLayoutParams(buttonParams);
        btnOption2.setId(R.id.btnSingleOption2Id);
        btnOption2.setTextAppearance(getActivity().getApplicationContext(), R.style.buttonTextSize);

        btnOption3.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption3.setTextColor(Color.rgb(255, 255, 255));
        btnOption3.setLayoutParams(buttonParams);
        btnOption3.setId(R.id.btnSingleOption3Id);
        btnOption3.setTextAppearance(getActivity().getApplicationContext(), R.style.buttonTextSize);

        btnOption4.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption4.setTextColor(Color.rgb(255, 255, 255));
        btnOption4.setLayoutParams(buttonParams);
        btnOption4.setId(R.id.btnSingleOption4Id);
        btnOption4.setTextAppearance(getActivity().getApplicationContext(), R.style.buttonTextSize);

        btnOption5.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption5.setTextColor(Color.rgb(255, 255, 255));
        btnOption5.setLayoutParams(buttonParams);
        btnOption5.setId(R.id.btnSingleOption5Id);
        btnOption5.setTextAppearance(getActivity().getApplicationContext(), R.style.buttonTextSize);

        switch (question.getResponseOptions().length) {
            case 2:
                rightMargin = (int) (150 * scale + 0.5f);
                buttonParams.rightMargin = rightMargin;
                tableParams.leftMargin = 650;
                buttonLayout.addView(btnOption1);
                btnOption1.setText(question.getResponseOptions()[0]);

                buttonLayout.addView(btnOption2);
                btnOption2.setText(question.getResponseOptions()[1]);

                break;
            case 3:
                tableParams.leftMargin = 500;
                buttonLayout.addView(btnOption1);
                btnOption1.setText(question.getResponseOptions()[0]);

                buttonLayout.addView(btnOption2);
                btnOption2.setText(question.getResponseOptions()[1]);

                buttonLayout.addView(btnOption3);
                btnOption3.setText(question.getResponseOptions()[2]);
                break;
            case 4:
                tableParams.leftMargin = 225;
                buttonLayout.addView(btnOption1);
                btnOption1.setText(question.getResponseOptions()[0]);

                buttonLayout.addView(btnOption2);
                btnOption2.setText(question.getResponseOptions()[1]);

                buttonLayout.addView(btnOption3);
                btnOption3.setText(question.getResponseOptions()[2]);

                buttonLayout.addView(btnOption4);
                btnOption4.setText(question.getResponseOptions()[3]);
                break;
            case 5:
                rightMargin = (int) (30 * scale + 0.5f);
                buttonParams.rightMargin = rightMargin;
                tableParams.leftMargin = 60;
                buttonLayout.addView(btnOption1);
                btnOption1.setText(question.getResponseOptions()[0]);

                buttonLayout.addView(btnOption2);
                btnOption2.setText(question.getResponseOptions()[1]);

                buttonLayout.addView(btnOption3);
                btnOption3.setText(question.getResponseOptions()[2]);

                buttonLayout.addView(btnOption4);
                btnOption4.setText(question.getResponseOptions()[3]);

                buttonLayout.addView(btnOption5);
                btnOption5.setText(question.getResponseOptions()[4]);
                break;
        }
        buttonLayout.setLayoutParams(tableParams);
    }

    public String checkTextLength(String qText){
            if(qText.length() > 54){
       /* Paint p = questionText.getPaint();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int width = displaymetrics.widthPixels;

        android.util.Log.d("JADA",p.measureText(qText) + " " + width);

        if( p.measureText(qText) > questionText.getWidth()){*/
                String firstPart;
                String lastPart;
                if(qText.length() % 2 == 0){
                    firstPart = qText.substring(0,(qText.length()/2)-1);
                    lastPart = qText.substring((qText.length()/2));
                }else{
                    firstPart = qText.substring(0,(qText.length())/2);
                    lastPart = qText.substring((qText.length()/2));
                }

                String[] removedSpace = firstPart.split("\\s");
                String newString = "";
                for(int i = 0; i<removedSpace.length; i++){
                   if(i == removedSpace.length-1){
                       newString += removedSpace[i];
                   }else{
                       newString += (removedSpace[i].concat(" "));
                   }

                }
                lastPart = System.getProperty("line.separator").concat(lastPart);
                qText = newString.concat(lastPart);
            }
        return qText;
    }

    public SingleChoiceFragment(){}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSingleOption1Id:
                btnOption1.setBackgroundColor(Color.rgb(7, 147, 194));
                btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                btnOption3.setBackgroundColor(Color.rgb(160,200,220));
                btnOption4.setBackgroundColor(Color.rgb(160,200,220));
                btnOption5.setBackgroundColor(Color.rgb(160,200,220));
                dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnSingleOption2Id:
                btnOption2.setBackgroundColor(Color.rgb(7, 147, 194));
                btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                btnOption3.setBackgroundColor(Color.rgb(160,200,220));
                btnOption4.setBackgroundColor(Color.rgb(160,200,220));
                btnOption5.setBackgroundColor(Color.rgb(160,200,220));
                dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnSingleOption3Id:
                btnOption3.setBackgroundColor(Color.rgb(7, 147, 194));
                btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                btnOption4.setBackgroundColor(Color.rgb(160,200,220));
                btnOption5.setBackgroundColor(Color.rgb(160,200,220));
                dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnSingleOption4Id:
                btnOption4.setBackgroundColor(Color.rgb(7, 147, 194));
                btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                btnOption3.setBackgroundColor(Color.rgb(160,200,220));
                btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                btnOption5.setBackgroundColor(Color.rgb(160,200,220));
                dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnSingleOption5Id:
                btnOption5.setBackgroundColor(Color.rgb(7, 147, 194));
                btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                btnOption3.setBackgroundColor(Color.rgb(160,200,220));
                btnOption4.setBackgroundColor(Color.rgb(160,200,220));
                btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.singleDontKnow:
                dontKnow.setBackgroundColor(Color.rgb(7, 147, 194));
                btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                btnOption3.setBackgroundColor(Color.rgb(160,200,220));
                btnOption4.setBackgroundColor(Color.rgb(160,200,220));
                btnOption5.setBackgroundColor(Color.rgb(160,200,220));
                swipe.setVisibility(View.VISIBLE);
                break;
        }
    }

    public long getQuestionTime(long startTime, long stopTime){
        questionTime = stopTime - startTime;

        questionTime = questionTime/1000;
        return questionTime;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            startTime = System.currentTimeMillis();
        }else{
            stopTime = System.currentTimeMillis();
            questionTime = getQuestionTime(startTime,stopTime);
            if(answeredQuestion != null){
                answeredQuestion.setTimeUsed(getQuestionTime(startTime, stopTime));
                Log.d("AnsweredQuestionTime", answeredQuestion.getTimeUsed() + " answered");
            }
            Log.d("Time", questionTime + " Single");
        }
    }

    @Override
    public void onPause() {
        //Log.d("IN ON_PAUSE():", "SingelChoiceFragment got paused.");
        super.onPause();
    }

    @Override
    public void onResume() {
        //Log.d("IN ON_Resume():", "SingelChoiceFragment got resumed.");

        super.onResume();
    }

    @Override
    public void onStop() {
        //Log.d("IN ON_STOP():", "SingelChoiceFragment got stopped.");
        super.onStop();
    }

    @Override
    public void onDestroy() {
       //Log.d("IN ON_DESTROY():", "SingelChoiceFragment got destroyed.");

        super.onDestroy();
    }
}
