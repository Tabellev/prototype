package com.example.isabel.prototypestart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.isabel.prototypestart.model.*;

import java.util.HashMap;

public class SingleChoiceFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private Button btnOption1;
    private Button btnOption2;
    private Button btnOption3;
    private Button btnOption4;
    private Button btnOption5;
    private Button dontKnow;
    private TextView swipe;
    private TextView questionText;
    private Boolean option1isClicked = false;
    private Boolean option2isClicked = false;
    private Boolean dontKnowIsClicked = false;
    private int questionID;
    private Question question;
    private static final String QUESTIONID = "questionID";
    private TableRow buttonsRow;
    private LinearLayout buttonLayout;

    private HashMap<Integer, HashMap<Integer, QuestionSetup>> questionConfigurationData;
    private AnsweredQuestion answeredQuestion;

    public static SingleChoiceFragment newInstance(int questionID) {
        SingleChoiceFragment fragmentSingleChoice = new SingleChoiceFragment();
        Bundle args = new Bundle();
        args.putInt(QUESTIONID, questionID);

        fragmentSingleChoice.setArguments(args);
        return fragmentSingleChoice;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        questionID = getArguments().getInt(QUESTIONID);
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
        int runId = 7000; // this ID must come from the current run which the current Question belongs to
        HashMap<Integer, QuestionSetup> run1QuestionSetups = questionConfigurationData.get(runId);
        // argument to AnsweredQuestion constructor
        long timeLimit = run1QuestionSetups.get(questionID).getTimeLimit();

        // instantiate answeredQuestion
        answeredQuestion = new AnsweredQuestion(questionID, timeLimit, correctAnswer);

        //-------------------------------------------------------------------------------------------------------


        // The comments below is just examples of how to interact with the data structures
        //((MainActivity)getActivity()).getDBInteractor().getTestResult().getRunResults()[0].getRunID();
        //((MainActivity)getActivity()).getDBInteractor().getTestResult().getRunResults()[0].addAnsweredQuestion(questionToAdd);

        View view = (RelativeLayout)inflater.inflate(R.layout.fragment_single_choice, container, false);
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
        buttonsRow = new TableRow(getActivity().getApplicationContext());
        buttonLayout = (LinearLayout)view.findViewById(R.id.buttonLayoutSingle);
        questionText = (TextView) view.findViewById(R.id.singleAnswerQuestion);
        questionText.setText(question.getQuestionText());

       /* // Must find a more dynamic way of doing this-------------------------------
        btnOption1.setText(question.getResponseOptions()[0]);
        btnOption2.setText(question.getResponseOptions()[1]);
        //-----------------------------------------------------------------------*/

        buttonSetup();
        return  view;
    }

    public void buttonSetup(){
        final float scale = getResources().getDisplayMetrics().density;
        LinearLayout.LayoutParams tableParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        tableParams.topMargin = 550;

        TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        int rightMargin = (int) (50 * scale + 0.5f);
        buttonParams.rightMargin = rightMargin;


        /*int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 220, getResources().getDisplayMetrics());*/


        // Convert the dps to pixels, based on density scale
        int height = (int) (200 * scale + 0.5f);
        int width = (int) (220 * scale + 0.5f);
        //float size = (1 * scale + 0.5f);

        buttonParams.width = width;
        buttonParams.height = height;

        btnOption1.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption1.setTextColor(Color.rgb(255,255,255));
        //btnOption1.setLayoutParams(new TableRow.LayoutParams(220, 200));
        btnOption1.setLayoutParams(buttonParams);
        btnOption1.setId(R.id.btnSingleOption1Id);
        //btnOption1.setTextSize(size);

        btnOption2.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption2.setTextColor(Color.rgb(255,255,255));
        //btnOption2.setLayoutParams(new TableRow.LayoutParams(220, 200));
        btnOption2.setLayoutParams(buttonParams);
        btnOption2.setId(R.id.btnSingleOption2Id);

        btnOption3.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption3.setTextColor(Color.rgb(255,255,255));
        //btnOption3.setLayoutParams(new TableRow.LayoutParams(220, 200));
        btnOption3.setLayoutParams(buttonParams);
        btnOption3.setId(R.id.btnSingleOption3Id);

        btnOption4.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption4.setTextColor(Color.rgb(255,255,255));
        //btnOption4.setLayoutParams(new TableRow.LayoutParams(220, 200));
        btnOption4.setLayoutParams(buttonParams);
        btnOption4.setId(R.id.btnSingleOption4Id);

        btnOption5.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption5.setTextColor(Color.rgb(255,255,255));
        //btnOption5.setLayoutParams(new TableRow.LayoutParams(220, 200));
        btnOption5.setLayoutParams(buttonParams);
        btnOption5.setId(R.id.btnSingleOption5Id);

        switch (question.getResponseOptions().length) {
            case 2:
                rightMargin = (int) (150 * scale + 0.5f);
                buttonParams.rightMargin = rightMargin;
                tableParams.leftMargin = 650;
                buttonsRow.addView(btnOption1);
                btnOption1.setText(question.getResponseOptions()[0]);

                buttonsRow.addView(btnOption2);
                btnOption2.setText(question.getResponseOptions()[1]);

                break;
            case 3:
                tableParams.leftMargin = 500;
                buttonsRow.addView(btnOption1);
                btnOption1.setText(question.getResponseOptions()[0]);

                buttonsRow.addView(btnOption2);
                btnOption2.setText(question.getResponseOptions()[1]);

                buttonsRow.addView(btnOption3);
                btnOption3.setText(question.getResponseOptions()[2]);
                break;
            case 4:
                tableParams.leftMargin = 225;
                buttonsRow.addView(btnOption1);
                btnOption1.setText(question.getResponseOptions()[0]);

                buttonsRow.addView(btnOption2);
                btnOption2.setText(question.getResponseOptions()[1]);

                buttonsRow.addView(btnOption3);
                btnOption3.setText(question.getResponseOptions()[2]);

                buttonsRow.addView(btnOption4);
                btnOption4.setText(question.getResponseOptions()[3]);
                break;
            case 5:
                rightMargin = (int) (30 * scale + 0.5f);
                buttonParams.rightMargin = rightMargin;
                tableParams.leftMargin = 25;
                buttonsRow.addView(btnOption1);
                btnOption1.setText(question.getResponseOptions()[0]);

                buttonsRow.addView(btnOption2);
                btnOption2.setText(question.getResponseOptions()[1]);

                buttonsRow.addView(btnOption3);
                btnOption3.setText(question.getResponseOptions()[2]);

                buttonsRow.addView(btnOption4);
                btnOption4.setText(question.getResponseOptions()[3]);

                buttonsRow.addView(btnOption5);
                btnOption5.setText(question.getResponseOptions()[4]);
                break;
        }

        buttonsRow.setLayoutParams(tableParams);
        buttonLayout.addView(buttonsRow);

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
}
