package com.example.isabel.prototypestart;

import android.app.Activity;
import android.app.Notification;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.isabel.prototypestart.model.AnsweredQuestion;
import com.example.isabel.prototypestart.model.Question;
import com.example.isabel.prototypestart.model.QuestionSetup;

import java.util.HashMap;

public class NumericalFragment extends android.support.v4.app.Fragment implements View.OnTouchListener, View.OnLongClickListener{

    private Boolean dontKnowIsClicked = false;
    private EditText input;
    private TextView swipe;
    private  Button btn0;
    private  Button btn1;
    private  Button btn2;
    private  Button btn3;
    private  Button btn4;
    private  Button btn5;
    private  Button btn6;
    private  Button btn7;
    private  Button btn8;
    private  Button btn9;
    private  Button btnPoint;
    private Button dontKnow;
    private ImageButton btnBackspace;
    private TextView questionText;
    // New---------------------------------------------
    private int questionID;
    private Question question;
    private static final String QUESTIONID = "questionID";

    private HashMap<Integer, HashMap<Integer, QuestionSetup>> questionConfigurationData;
    private AnsweredQuestion answeredQuestion;
    // --------------------------------------------------------

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        // New ---------------------------------------------------------------------------------------
        questionID = getArguments().getInt(QUESTIONID);

        question = ((MainActivity)getActivity()).getDBInteractor().getMockQuestions().get(questionID);
        // argument to AnsweredQuestion constructor
        String[] correctAnswer = question.getCorrectAnswer();
                //------------------------------------------------------------------------------------------------

        // Experimental code ------------------------------------------------------------------------------------
        // Get information about the current Question(time limit, etc.)
        questionConfigurationData = ((MainActivity)getActivity()).getDBInteractor().getRunSetupQuestions();
        int runId = 7000; // this ID must come from the current run which the current Question belongs to
        HashMap<Integer, QuestionSetup> run1QuestionSetups = questionConfigurationData.get(runId);
        // argument to AnsweredQuestion constructor
        long timeLimit = run1QuestionSetups.get(questionID).getTimeLimit();

        // instantiate answeredQuestion
        // TODO: mTimeUsed, mSkippedQuestion, mGivenAnswer og mAnswerWasCorrect must be set when user navigates to next question
        answeredQuestion = new AnsweredQuestion(questionID, timeLimit, correctAnswer);

        //-------------------------------------------------------------------------------------------------------



        View view = (RelativeLayout)inflater.inflate(R.layout.fragment_numerical, container, false);
        btn0 = (Button)view.findViewById(R.id.btnZero);
        btn0.setOnTouchListener(this);
        btn1 = (Button) view.findViewById(R.id.btnOne);
        btn1.setOnTouchListener(this);
        btn2 = (Button) view.findViewById(R.id.btnTwo);
        btn2.setOnTouchListener(this);
        btn3 = (Button) view.findViewById(R.id.btnThree);
        btn3.setOnTouchListener(this);
        btn4 = (Button) view.findViewById(R.id.btnFour);
        btn4.setOnTouchListener(this);
        btn5 = (Button) view.findViewById(R.id.btnFive);
        btn5.setOnTouchListener(this);
        btn6 = (Button) view.findViewById(R.id.btnSix);
        btn6.setOnTouchListener(this);
        btn7 = (Button) view.findViewById(R.id.btnSeven);
        btn7.setOnTouchListener(this);
        btn8 = (Button) view.findViewById(R.id.btnEight);
        btn8.setOnTouchListener(this);
        btn9 = (Button) view.findViewById(R.id.btnNine);
        btn9.setOnTouchListener(this);
        btnPoint = (Button) view.findViewById(R.id.btnPoint);
        btnPoint.setOnTouchListener(this);
        btnBackspace = (ImageButton) view.findViewById(R.id.backspace);
        btnBackspace.setOnTouchListener(this);
        btnBackspace.setOnLongClickListener(this);
        dontKnow = (Button) view.findViewById(R.id.btnNumericalDontKnow);
        dontKnow.setOnTouchListener(this);
        input = (EditText) view.findViewById(R.id.input);
        swipe = (TextView) view.findViewById(R.id.numericalContinue);
        questionText = (TextView) view.findViewById(R.id.numericalQuestion);
        questionText.setText(question.getQuestionText());

        return view;
    }

    public NumericalFragment(){}

    public static NumericalFragment newInstance(int questionID/*index*/) {
        NumericalFragment f = new NumericalFragment();
        Bundle args = new Bundle();
        //args.putInt("index", index);
        args.putInt(QUESTIONID, questionID);
        f.setArguments(args);
        return f;
    }

    public String eraseCharacter(String str) {
        if (str.length() > 0) {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    public boolean isPoint(String str){

        if(str.contains(".")){
            return true;
        }else{
            return false;
        }
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            switch (v.getId()) {
                case R.id.btnZero:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
                case R.id.btnOne:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
                case R.id.btnTwo:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
                case R.id.btnThree:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
                case R.id.btnFour:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
                case R.id.btnFive:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
                case R.id.btnSix:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
                case R.id.btnSeven:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
                case R.id.btnEight:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
                case R.id.btnNine:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
                case R.id.btnPoint:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
                case R.id.backspace:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
                case R.id.btnNumericalDontKnow:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    break;
            }
        } else if(event.getAction() == MotionEvent.ACTION_UP){
            switch (v.getId()) {
                case R.id.btnZero:
                    input.setText(input.getText() + "0");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnOne:
                    input.setText(input.getText() + "1");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnTwo:
                    input.setText(input.getText() + "2");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnThree:
                    input.setText(input.getText() + "3");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnFour:
                    input.setText(input.getText() + "4");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnFive:
                    input.setText(input.getText() + "5");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnSix:
                    input.setText(input.getText() + "6");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnSeven:
                    input.setText(input.getText() + "7");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnEight:
                    input.setText(input.getText() + "8");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnNine:
                    input.setText(input.getText() + "9");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnPoint:
                    if (!isPoint(input.getText().toString())) {
                        input.setText(input.getText() + ".");
                        swipe.setVisibility(View.VISIBLE);
                        v.setBackgroundColor(Color.rgb(160, 200, 220));
                    } else {
                        v.setBackgroundColor(Color.rgb(160, 200, 220));
                    }
                    break;
                case R.id.backspace:
                    input.setText(eraseCharacter(input.getText().toString()));
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
            }
        }

        return false;
    }

    @Override
    public boolean onLongClick(View v) {
        input.setText("");
        return true;
    }

   /* @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnZero:
                input.setText(input.getText() + "0");
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnOne:
                input.setText(input.getText() + "1");
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnTwo:
                input.setText(input.getText() + "2");
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnThree:
                input.setText(input.getText() + "3");
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnFour:
                input.setText(input.getText() + "4");
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnFive:
                input.setText(input.getText() + "5");
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnSix:
                input.setText(input.getText() + "6");
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnSeven:
                input.setText(input.getText() + "7");
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnEight:
                input.setText(input.getText() + "8");
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnNine:
                input.setText(input.getText() + "9");
                swipe.setVisibility(View.VISIBLE);
                break;
            case R.id.btnPoint:
                if(!isPoint(input.getText().toString())){
                    input.setText(input.getText() + ".");
                    swipe.setVisibility(View.VISIBLE);
                }else{
                    return;
                }
                break;
            case R.id.backspace:
                input.setText(eraseCharacter(input.getText().toString()));
                break;
            case R.id.btnNumericalDontKnow:
                if(!dontKnowIsClicked){
                    dontKnow.setBackgroundColor(Color.rgb(7, 147, 194));
                    btn0.setEnabled(false);
                    btn0.setBackgroundColor(Color.argb(50,160, 200, 220));
                    btn1.setEnabled(false);
                    btn1.setBackgroundColor(Color.argb(50,160, 200, 220));
                    btn2.setEnabled(false);
                    btn2.setBackgroundColor(Color.argb(50,160, 200, 220));
                    btn3.setEnabled(false);
                    btn3.setBackgroundColor(Color.argb(50,160, 200, 220));
                    btn4.setEnabled(false);
                    btn4.setBackgroundColor(Color.argb(50,160, 200, 220));
                    btn5.setEnabled(false);
                    btn5.setBackgroundColor(Color.argb(50,160, 200, 220));
                    btn6.setEnabled(false);
                    btn6.setBackgroundColor(Color.argb(50,160, 200, 220));
                    btn7.setEnabled(false);
                    btn7.setBackgroundColor(Color.argb(50,160, 200, 220));
                    btn8.setEnabled(false);
                    btn8.setBackgroundColor(Color.argb(50,160, 200, 220));
                    btn9.setEnabled(false);
                    btn9.setBackgroundColor(Color.argb(50,160, 200, 220));
                    btnPoint.setEnabled(false);
                    btnPoint.setBackgroundColor(Color.argb(50,160, 200, 220));
                    btnBackspace.setEnabled(false);
                    btnBackspace.setBackgroundColor(Color.argb(50,160, 200, 220));
                    input.setText("");
                    swipe.setVisibility(View.VISIBLE);
                    dontKnowIsClicked = true;
                }else{
                    dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    btn0.setEnabled(true);
                    btn0.setBackgroundColor(Color.rgb(160, 200, 220));
                    btn1.setEnabled(true);
                    btn1.setBackgroundColor(Color.rgb(160, 200, 220));
                    btn2.setEnabled(true);
                    btn2.setBackgroundColor(Color.rgb(160, 200, 220));
                    btn3.setEnabled(true);
                    btn3.setBackgroundColor(Color.rgb(160, 200, 220));
                    btn4.setEnabled(true);
                    btn4.setBackgroundColor(Color.rgb(160, 200, 220));
                    btn5.setEnabled(true);
                    btn5.setBackgroundColor(Color.rgb(160, 200, 220));
                    btn6.setEnabled(true);
                    btn6.setBackgroundColor(Color.rgb(160, 200, 220));
                    btn7.setEnabled(true);
                    btn7.setBackgroundColor(Color.rgb(160, 200, 220));
                    btn8.setEnabled(true);
                    btn8.setBackgroundColor(Color.rgb(160, 200, 220));
                    btn9.setEnabled(true);
                    btn9.setBackgroundColor(Color.rgb(160, 200, 220));
                    btnPoint.setEnabled(true);
                    btnPoint.setBackgroundColor(Color.rgb(160, 200, 220));
                    btnBackspace.setEnabled(true);
                    btnBackspace.setBackgroundColor(Color.rgb(160, 200, 220));
                    dontKnowIsClicked = false;
                }
                break;
        }
    }*/
}
