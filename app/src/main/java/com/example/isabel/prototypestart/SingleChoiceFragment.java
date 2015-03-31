package com.example.isabel.prototypestart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.isabel.prototypestart.model.*;

public class SingleChoiceFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private Button btnOption1;
    private Button btnOption2;
    private Button dontKnow;
    private TextView swipe;
    private TextView questionText;
    private Boolean option1isClicked = false;
    private Boolean option2isClicked = false;
    private Boolean dontKnowIsClicked = false;
    private int questionID;
    private Question question;
    private static final String QUESTIONID = "questionID";
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

        // The comments below is just examples of how to interact with the data structures
        //((MainActivity)getActivity()).getDBInteractor().getTestResult().getRunResults()[0].getRunID();
        //((MainActivity)getActivity()).getDBInteractor().getTestResult().getRunResults()[0].addAnsweredQuestion(questionToAdd);

        View view = (RelativeLayout)inflater.inflate(R.layout.fragment_single_choice, container, false);
        btnOption1 = (Button)view.findViewById(R.id.btnAnswerOption1);
        btnOption1.setOnClickListener(this);
        btnOption2 = (Button) view.findViewById(R.id.btnAnswerOption2);
        btnOption2.setOnClickListener(this);
        dontKnow = (Button) view.findViewById(R.id.singleDontKnow);
        dontKnow.setOnClickListener(this);
        swipe = (TextView) view.findViewById(R.id.singleChoiceContinue);
        questionText = (TextView) view.findViewById(R.id.singleAnswerQuestion);
        questionText.setText(question.getQuestionText());

        // Must find a more dynamic way of doing this-------------------------------
        btnOption1.setText(question.getResponseOptions()[0]);
        btnOption2.setText(question.getResponseOptions()[1]);
        //-----------------------------------------------------------------------

        return  view;
    }

    public SingleChoiceFragment(){}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAnswerOption1:
                if(!option1isClicked){
                    btnOption1.setBackgroundColor(Color.rgb(7, 147, 194));
                    btnOption2.setEnabled(false);
                    btnOption2.setBackgroundColor(Color.argb(50,160,200,220));
                    dontKnow.setEnabled(false);
                    dontKnow.setBackgroundColor(Color.argb(50,160,200,220));
                    swipe.setVisibility(View.VISIBLE);
                    option1isClicked = true;
                }else{
                    btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                    btnOption2.setEnabled(true);
                    btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                    dontKnow.setEnabled(true);
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    option1isClicked = false;
                }
                break;
            case R.id.btnAnswerOption2:
                if(!option2isClicked){
                    btnOption2.setBackgroundColor(Color.rgb(7, 147, 194));
                    btnOption1.setEnabled(false);
                    btnOption1.setBackgroundColor(Color.argb(50,160,200,220));
                    dontKnow.setEnabled(false);
                    dontKnow.setBackgroundColor(Color.argb(50,160,200,220));
                    swipe.setVisibility(View.VISIBLE);
                    option2isClicked = true;
                }else{
                    btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                    btnOption1.setEnabled(true);
                    btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                    dontKnow.setEnabled(true);
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    option2isClicked = false;
                }
                break;
            case R.id.singleDontKnow:
                if(!dontKnowIsClicked){
                    dontKnow.setBackgroundColor(Color.rgb(7, 147, 194));
                    btnOption1.setEnabled(false);
                    btnOption1.setBackgroundColor(Color.argb(50,160,200,220));
                    btnOption2.setEnabled(false);
                    btnOption2.setBackgroundColor(Color.argb(50,160,200,220));
                    swipe.setVisibility(View.VISIBLE);
                    dontKnowIsClicked = true;
                }else{
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    btnOption1.setEnabled(true);
                    btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                    btnOption2.setEnabled(true);
                    btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                    dontKnowIsClicked = false;
                }
                break;
        }
    }
}
