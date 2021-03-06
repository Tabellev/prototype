package com.example.isabel.prototypestart;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.isabel.prototypestart.model.AnsweredQuestion;
import com.example.isabel.prototypestart.model.Question;
import com.example.isabel.prototypestart.model.QuestionSetup;

import java.util.ArrayList;
import java.util.HashMap;

public class MultipleChoiceFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private Boolean option1isClicked = false;
    private Boolean option2isClicked = false;
    private Boolean option3isClicked = false;
    private Boolean option4isClicked = false;
    private Boolean option5isClicked = false;
    private Boolean dontKnowIsClicked = false;

    private Boolean[] optionStatus = new Boolean[] {false, false, false, false, false};
    private Button[] optionButtons = new Button[5];

    private Button btnOption1;
    private Button btnOption2;
    private Button btnOption3;
    private Button btnOption4;
    private Button btnOption5;
    private Button dontKnow;
    private TextView swipe;
    private LinearLayout buttonLayout;
    private TextView questionText;
    private long startTime;
    private long stopTime;
    private long questionTime;

    private int questionID;
    private int runID;
    private Question question;
    private static final String QUESTIONID = "questionID";
    private static final String RUN_ID = "runID";
    ControlledViewPager pager;

    private HashMap<Integer, HashMap<Integer, QuestionSetup>> questionConfigurationData;
    private AnsweredQuestion answeredQuestion;
    private ArrayList<String> mGivenAnswer = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        questionID = getArguments().getInt(QUESTIONID);
        runID = getArguments().getInt(RUN_ID);

        question = ((MainActivity)getActivity()).getDBInteractor().getQuestions().get(questionID);
        // argument to AnsweredQuestion constructor
        String[] correctAnswer = question.getCorrectAnswer();

        // Get information about the current Question(time limit, etc.)
        questionConfigurationData = ((MainActivity)getActivity()).getDBInteractor().getRunSetupQuestions();

        HashMap<Integer, QuestionSetup> runQuestionSetups = questionConfigurationData.get(runID);

        // argument to AnsweredQuestion constructor
        long timeLimit = runQuestionSetups.get(questionID).getTimeLimit();

        // instantiate answeredQuestion
        answeredQuestion = new AnsweredQuestion(questionID, timeLimit, correctAnswer);


        View view = inflater.inflate(R.layout.fragment_multiple_choice, container, false);
        btnOption1 = new Button(getActivity().getApplicationContext());
        btnOption1.setOnClickListener(this);
        optionButtons[0] = btnOption1;
        btnOption2 = new Button(getActivity().getApplicationContext());
        btnOption2.setOnClickListener(this);
        optionButtons[1] = btnOption2;
        btnOption3 = new Button(getActivity().getApplicationContext());
        btnOption3.setOnClickListener(this);
        optionButtons[2] = btnOption3;
        btnOption4 = new Button(getActivity().getApplicationContext());
        btnOption4.setOnClickListener(this);
        optionButtons[3] = btnOption4;
        btnOption5 = new Button(getActivity().getApplicationContext());
        btnOption5.setOnClickListener(this);
        optionButtons[4] = btnOption5;
        dontKnow = (Button) view.findViewById(R.id.multipleDontKnow);
        dontKnow.setOnClickListener(this);
        swipe = (TextView) view.findViewById(R.id.multipleChoiceContinue);
        questionText = (TextView) view.findViewById(R.id.multipleAnswerQuestion);
        String questionT = checkTextLength(question.getQuestionText());
        questionText.setText(questionT);
        buttonLayout = (LinearLayout)view.findViewById(R.id.buttonLayoutMultiple);
        pager = ((MainActivity) getActivity()).getPager();

        buttonSetup();

        return view;
    }

    public void buttonSetup(){
        final float scale = getResources().getDisplayMetrics().density;
        RelativeLayout.LayoutParams tableParamsM = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        tableParamsM.topMargin = 550;

        LinearLayout.LayoutParams buttonParamsM = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        int rightMargin = (int) (50 * scale + 0.5f);
        buttonParamsM.rightMargin = rightMargin;

        // Convert the dps to pixels, based on density scale
        int height = (int) (200 * scale + 0.5f);
        int width = (int) (220 * scale + 0.5f);

        buttonParamsM.width = width;
        buttonParamsM.height = height;

        btnOption1.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption1.setTextColor(Color.rgb(255,255,255));
        btnOption1.setLayoutParams(buttonParamsM);
        btnOption1.setId(R.id.btnOption1Id);
        btnOption1.setTextAppearance(getActivity().getApplicationContext(), R.style.buttonTextSize);

        btnOption2.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption2.setTextColor(Color.rgb(255,255,255));
        btnOption2.setLayoutParams(buttonParamsM);
        btnOption2.setId(R.id.btnOption2Id);
        btnOption2.setTextAppearance(getActivity().getApplicationContext(), R.style.buttonTextSize);

        btnOption3.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption3.setTextColor(Color.rgb(255,255,255));
        btnOption3.setLayoutParams(buttonParamsM);
        btnOption3.setId(R.id.btnOption3Id);
        btnOption3.setTextAppearance(getActivity().getApplicationContext(), R.style.buttonTextSize);

        btnOption4.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption4.setTextColor(Color.rgb(255,255,255));
        btnOption4.setLayoutParams(buttonParamsM);
        btnOption4.setId(R.id.btnOption4Id);
        btnOption4.setTextAppearance(getActivity().getApplicationContext(), R.style.buttonTextSize);

        btnOption5.setBackgroundColor(Color.rgb(160, 200, 220));
        btnOption5.setTextColor(Color.rgb(255,255,255));
        btnOption5.setLayoutParams(buttonParamsM);
        btnOption5.setId(R.id.btnOption5Id);
        btnOption5.setTextAppearance(getActivity().getApplicationContext(), R.style.buttonTextSize);

        switch (question.getResponseOptions().length) {
            case 3:
                tableParamsM.leftMargin = 500;
                buttonLayout.addView(btnOption1);
                btnOption1.setText(question.getResponseOptions()[0]);

                buttonLayout.addView(btnOption2);
                btnOption2.setText(question.getResponseOptions()[1]);

                buttonLayout.addView(btnOption3);
                btnOption3.setText(question.getResponseOptions()[2]);
                break;
            case 4:
                tableParamsM.leftMargin = 225;
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
                buttonParamsM.rightMargin = rightMargin;
                tableParamsM.leftMargin = 25;
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
        buttonLayout.setLayoutParams(tableParamsM);
    }

    public String checkTextLength(String qText){
        if(qText.length() > 47) {

            String firstPart = "";
            String lastPart = "";
            String[] removedSpace = qText.split(" ");

            for (int i = 0; i < removedSpace.length/2; i++) {
                firstPart += (removedSpace[i].concat(" "));
            }

            for (int i = removedSpace.length/2; i < removedSpace.length; i++) {
                lastPart += (removedSpace[i].concat(" "));
            }

            lastPart = System.getProperty("line.separator").concat(lastPart);
            qText = firstPart.concat(lastPart);
        }
        return qText;
    }


    public MultipleChoiceFragment(){}

    public static MultipleChoiceFragment newInstance(int runID, int questionID) {
        MultipleChoiceFragment f = new MultipleChoiceFragment();
        Bundle args = new Bundle();
        args.putInt(QUESTIONID, questionID);
        args.putInt(RUN_ID, runID);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnOption1Id:
                if(!option1isClicked){
                    btnOption1.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    dontKnowIsClicked = false;
                    swipe.setVisibility(View.VISIBLE);
                    pager.setPagingEnabled(true);
                    option1isClicked = true;
                }else{
                    btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                    option1isClicked = false;
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    if(!option2isClicked && !option3isClicked && !option4isClicked && !option5isClicked && !dontKnowIsClicked){
                        pager.setPagingEnabled(false);
                    }
                }
                // set chosen flag based on if the option is clicked
                if (option1isClicked) {
                    optionStatus[0] = true;
                } else {
                    optionStatus[0] = false;
                }
                break;
            case R.id.btnOption2Id:
                if(!option2isClicked){
                    btnOption2.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    dontKnowIsClicked = false;
                    swipe.setVisibility(View.VISIBLE);
                    pager.setPagingEnabled(true);
                    option2isClicked = true;
                }else{
                    btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                    option2isClicked = false;
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    if(!option1isClicked && !option3isClicked && !option4isClicked && !option5isClicked && !dontKnowIsClicked){
                        pager.setPagingEnabled(false);
                    }
                }
                // set chosen flag based on if the option is clicked
                if (option2isClicked) {
                    optionStatus[1] = true;
                } else {
                    optionStatus[1] = false;
                }
                break;
            case R.id.btnOption3Id:
                if(!option3isClicked){
                    btnOption3.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    dontKnowIsClicked = false;
                    swipe.setVisibility(View.VISIBLE);
                    pager.setPagingEnabled(true);
                    option3isClicked = true;
                }else{
                    btnOption3.setBackgroundColor(Color.rgb(160,200,220));
                    option3isClicked = false;
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    if(!option1isClicked && !option2isClicked && !option4isClicked && !option5isClicked && !dontKnowIsClicked){
                        pager.setPagingEnabled(false);
                    }
                }
                // set chosen flag based on if the option is clicked
                if (option3isClicked) {
                    optionStatus[2] = true;
                } else {
                    optionStatus[2] = false;
                }
                break;
            case R.id.btnOption4Id:
                if(!option4isClicked){
                    btnOption4.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    dontKnowIsClicked = false;
                    swipe.setVisibility(View.VISIBLE);
                    pager.setPagingEnabled(true);
                    option4isClicked = true;
                }else{
                    btnOption4.setBackgroundColor(Color.rgb(160,200,220));
                    option4isClicked = false;
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    if(!option1isClicked && !option2isClicked && !option3isClicked && !option5isClicked && !dontKnowIsClicked){
                        pager.setPagingEnabled(false);
                    }
                }
                // set chosen flag based on if the option is clicked
                if (option4isClicked) {
                    optionStatus[3] = true;
                } else {
                    optionStatus[3] = false;
                }
                break;
            case R.id.btnOption5Id:
                if(!option5isClicked){
                    btnOption5.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    dontKnowIsClicked = false;
                    swipe.setVisibility(View.VISIBLE);
                    pager.setPagingEnabled(true);
                    option5isClicked = true;
                }else{
                    btnOption5.setBackgroundColor(Color.rgb(160,200,220));
                    option5isClicked = false;
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    if(!option1isClicked && !option2isClicked && !option3isClicked && !option4isClicked && !dontKnowIsClicked){
                        pager.setPagingEnabled(false);
                    }
                }
                // set chosen flag based on if the option is clicked
                if (option5isClicked) {
                    optionStatus[4] = true;
                } else {
                    optionStatus[4] = false;
                }
                break;
            case R.id.multipleDontKnow:
                dontKnow.setBackgroundColor(Color.rgb(7, 147, 194));
                swipe.setVisibility(View.VISIBLE);
                btnOption1.setBackgroundColor(Color.rgb(160, 200, 220));
                btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                btnOption3.setBackgroundColor(Color.rgb(160,200,220));
                btnOption4.setBackgroundColor(Color.rgb(160,200,220));
                btnOption5.setBackgroundColor(Color.rgb(160,200,220));
                option1isClicked = false;
                option2isClicked = false;
                option3isClicked = false;
                option4isClicked = false;
                dontKnowIsClicked = true;
                pager.setPagingEnabled(true);
                // remove answer
                mGivenAnswer.clear();
                // remove chosen flags
                for (int i = 0; i < optionStatus.length; i++) {
                    optionStatus[i] = false;
                }

                answeredQuestion.setSkippedQuestion(true);
                break;
        }
    }

    private void setGivenAnswer() {
        // add answers to mGivenAnswer if matching flag is true
        // TODO: maybe move this to ...visibleToUser() before adding answeredQuestion to TestResult?
        for (int i = 0; i < optionStatus.length; i++) {
            if (optionStatus[i]) {
                //Log.d("Status:", "optionStatus[" + i + "]" + optionStatus[i].toString());
                mGivenAnswer.add(optionButtons[i].getText().toString());
            } else {
                //Log.d("Status:", "optionStatus[" + i + "]" + optionStatus[i].toString());
            }
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
            pager.setPagingEnabled(false);
        }else{
            stopTime = System.currentTimeMillis();
            questionTime = getQuestionTime(startTime,stopTime);
            if(answeredQuestion != null){
                answeredQuestion.setTimeUsed(getQuestionTime(startTime, stopTime));

                // populate mGivenAnswer
                setGivenAnswer();

                answeredQuestion.setGivenAnswer(mGivenAnswer.toArray(new String[mGivenAnswer.size()]));

                ((MainActivity)getActivity()).getDBInteractor().getTestStatistics().setSessionTimeUsed((int) answeredQuestion.getTimeUsed());
                ((MainActivity)getActivity()).getDBInteractor().getTestStatistics().setNumberOfCorrectAnswers(answeredQuestion.answerWasCorrect() ? 1 : 0);
                ((MainActivity)getActivity()).getDBInteractor().getTestStatistics().setNumberOfWrongAnswers(answeredQuestion.answerWasCorrect() ? 0 : 1);
                ((MainActivity)getActivity()).getDBInteractor().getTestStatistics().setNumberOfSkippedAnswers(answeredQuestion.skippedQuestion() ? 1 : 0);
                ((MainActivity)getActivity()).getDBInteractor().getTestResult().getRunResult(runID).addAnsweredQuestion(answeredQuestion);
            }
        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
