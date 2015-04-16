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
import com.example.isabel.prototypestart.model.AnsweredQuestion;
import com.example.isabel.prototypestart.model.Question;
import com.example.isabel.prototypestart.model.QuestionSetup;

import java.util.HashMap;

public class MultipleChoiceFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private Boolean option1isClicked = false;
    private Boolean option2isClicked = false;
    private Boolean option3isClicked = false;
    private Boolean option4isClicked = false;
    private Boolean option5isClicked = false;
    private Button btnOption1;
    private Button btnOption2;
    private Button btnOption3;
    private Button btnOption4;
    private Button btnOption5;
    private Button dontKnow;
    private TextView swipe;
    private LinearLayout buttonLayout;

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
        answeredQuestion = new AnsweredQuestion(questionID, timeLimit, correctAnswer);

        //-------------------------------------------------------------------------------------------------------

        View view = inflater.inflate(R.layout.fragment_multiple_choice, container, false);
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
        dontKnow = (Button) view.findViewById(R.id.multipleDontKnow);
        dontKnow.setOnClickListener(this);
        swipe = (TextView) view.findViewById(R.id.multipleChoiceContinue);
        questionText = (TextView) view.findViewById(R.id.multipleAnswerQuestion);
        String questionT = checkTextLength(question.getQuestionText());
        questionText.setText(questionT);
        buttonLayout = (LinearLayout)view.findViewById(R.id.buttonLayoutMultiple);

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


    public MultipleChoiceFragment(){}

    public static MultipleChoiceFragment newInstance(int questionID/*index*/) {
        MultipleChoiceFragment f = new MultipleChoiceFragment();
        Bundle args = new Bundle();
        //args.putInt("index", index);
        args.putInt(QUESTIONID, questionID);
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
                    swipe.setVisibility(View.VISIBLE);
                    option1isClicked = true;
                }else{
                    btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                    option1isClicked = false;
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                }
                break;
            case R.id.btnOption2Id:
                if(!option2isClicked){
                    btnOption2.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    swipe.setVisibility(View.VISIBLE);
                    option2isClicked = true;
                }else{
                    btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                    option2isClicked = false;
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                }
                break;
            case R.id.btnOption3Id:
                if(!option3isClicked){
                    btnOption3.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    swipe.setVisibility(View.VISIBLE);
                    option3isClicked = true;
                }else{
                    btnOption3.setBackgroundColor(Color.rgb(160,200,220));
                    option3isClicked = false;
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                }
                break;
            case R.id.btnOption4Id:
                if(!option4isClicked){
                    btnOption4.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    swipe.setVisibility(View.VISIBLE);
                    option4isClicked = true;
                }else{
                    btnOption4.setBackgroundColor(Color.rgb(160,200,220));
                    option4isClicked = false;
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                }
                break;
            case R.id.btnOption5Id:
                if(!option5isClicked){
                    btnOption5.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    swipe.setVisibility(View.VISIBLE);
                    option5isClicked = true;
                }else{
                    btnOption5.setBackgroundColor(Color.rgb(160,200,220));
                    option5isClicked = false;
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                }
                break;
            case R.id.multipleDontKnow:
                dontKnow.setBackgroundColor(Color.rgb(7, 147, 194));
                swipe.setVisibility(View.VISIBLE);
                btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                btnOption3.setBackgroundColor(Color.rgb(160,200,220));
                btnOption4.setBackgroundColor(Color.rgb(160,200,220));
                btnOption5.setBackgroundColor(Color.rgb(160,200,220));
                option1isClicked = false;
                option2isClicked = false;
                option3isClicked = false;
                option4isClicked = false;
                break;
        }
    }
}
