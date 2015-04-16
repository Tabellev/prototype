package com.example.isabel.prototypestart;

import android.app.Activity;
import android.app.Notification;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.InputType;
import android.util.Log;
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
        String questionT = checkTextLength(question.getQuestionText());
        questionText.setText(questionT);
        input.setInputType(InputType.TYPE_NULL);


        return view;
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
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
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
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.btnZero:
                    input.setText(input.getText() + "0");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnOne:
                    input.setText(input.getText() + "1");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnTwo:
                    input.setText(input.getText() + "2");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnThree:
                    input.setText(input.getText() + "3");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnFour:
                    input.setText(input.getText() + "4");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnFive:
                    input.setText(input.getText() + "5");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnSix:
                    input.setText(input.getText() + "6");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnSeven:
                    input.setText(input.getText() + "7");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnEight:
                    input.setText(input.getText() + "8");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnNine:
                    input.setText(input.getText() + "9");
                    swipe.setVisibility(View.VISIBLE);
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
                case R.id.btnNumericalDontKnow:
                    v.setBackgroundColor(Color.rgb(7, 147, 194));
                    input.setText("");
                    break;
                case R.id.btnPoint:
                    if (!isPoint(input.getText().toString())) {
                        input.setText(input.getText() + ".");
                        swipe.setVisibility(View.VISIBLE);
                        v.setBackgroundColor(Color.rgb(160, 200, 220));
                        dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    } else {
                        v.setBackgroundColor(Color.rgb(160, 200, 220));
                        dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    }
                    break;
                case R.id.backspace:
                    input.setText(eraseCharacter(input.getText().toString()));
                    v.setBackgroundColor(Color.rgb(160, 200, 220));
                    dontKnow.setBackgroundColor(Color.rgb(160, 200, 220));
                    break;
            }
        }

        return false;
    }
        /**
         * Called when the Fragment is no longer resumed.  This is generally
         * tied to {@link android.app.Activity#onPause() Activity.onPause} of the containing
         * Activity's lifecycle.
         */
        @Override
        public void onPause () {
            Log.d("IN ON_PAUSE():", "Fragment got paused.");
            super.onPause();
        }

        /**
         * Called when the Fragment is no longer resumed.  This is generally
         * tied to {@link android.app.Activity#onPause() Activity.onPause} of the containing
         * Activity's lifecycle.
         */
        @Override
        public void onStop () {
            Log.d("IN ON_STOP():", "Fragment got stopped.");
            String[] answerFromInput = new String[]{input.getText().toString()};
            answeredQuestion.setGivenAnswer(answerFromInput);

            super.onStop();
        }


    @Override
    public boolean onLongClick(View v) {
        input.setText("");
        return true;
    }
}
