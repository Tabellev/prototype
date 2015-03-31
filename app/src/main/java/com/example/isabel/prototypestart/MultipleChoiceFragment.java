package com.example.isabel.prototypestart;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.isabel.prototypestart.MainActivity;
import com.example.isabel.prototypestart.R;
import com.example.isabel.prototypestart.model.AnsweredQuestion;
import com.example.isabel.prototypestart.model.Question;

public class MultipleChoiceFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private Boolean option1isClicked = false;
    private Boolean option2isClicked = false;
    private Boolean option3isClicked = false;
    private Boolean option4isClicked = false;
    private Boolean dontKnowIsClicked = false;
    private Button btnOption1;
    private Button btnOption2;
    private Button btnOption3;
    private Button btnOption4;
    private Button dontKnow;
    private TextView swipe;

    private TextView questionText;
    // New---------------------------------------------
    private int questionID;
    private Question question;
    private static final String QUESTIONID = "questionID";
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
        //------------------------------------------------------------------------------------------------
        View view = (RelativeLayout)inflater.inflate(R.layout.fragment_multiple_choice, container, false);
        btnOption1 = (Button)view.findViewById(R.id.btnMulAnsOne);
        btnOption1.setOnClickListener(this);
        btnOption2 = (Button) view.findViewById(R.id.btnMulAnsTwo);
        btnOption2.setOnClickListener(this);
        btnOption3 = (Button) view.findViewById(R.id.btnMulAnsThree);
        btnOption3.setOnClickListener(this);
        btnOption4 = (Button) view.findViewById(R.id.btnMulAnsFour);
        btnOption4.setOnClickListener(this);
        dontKnow = (Button) view.findViewById(R.id.multipleDontKnow);
        dontKnow.setOnClickListener(this);
        swipe = (TextView) view.findViewById(R.id.multipleChoiceContinue);
        questionText = (TextView) view.findViewById(R.id.multipleAnswerQuestion);
        questionText.setText(question.getQuestionText());

        // Must find a more dynamic way of doing this-------------------------------
        btnOption1.setText(question.getResponseOptions()[0]);
        btnOption2.setText(question.getResponseOptions()[1]);
        btnOption3.setText(question.getResponseOptions()[2]);
        btnOption4.setText("Skulibuli");
        //----------------------------------------------------------------------------

        return view;
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
            case R.id.btnMulAnsOne:
                if(!option1isClicked){
                    btnOption1.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setEnabled(false);
                    dontKnow.setBackgroundColor(Color.argb(50,160,200,220));
                    swipe.setVisibility(View.VISIBLE);
                    option1isClicked = true;
                }else{
                    btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                    option1isClicked = false;
                    if(!option1isClicked && !option2isClicked && !option3isClicked && !option4isClicked){
                        dontKnow.setEnabled(true);
                        dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    }
                }
                break;
            case R.id.btnMulAnsTwo:
                if(!option2isClicked){
                    btnOption2.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setEnabled(false);
                    dontKnow.setBackgroundColor(Color.argb(50,160,200,220));
                    swipe.setVisibility(View.VISIBLE);
                    option2isClicked = true;
                }else{
                    btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                    option2isClicked = false;
                    if(!option1isClicked && !option2isClicked && !option3isClicked && !option4isClicked){
                        dontKnow.setEnabled(true);
                        dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    }
                }
                break;
            case R.id.btnMulAnsThree:
                if(!option3isClicked){
                    btnOption3.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setEnabled(false);
                    dontKnow.setBackgroundColor(Color.argb(50,160,200,220));
                    swipe.setVisibility(View.VISIBLE);
                    option3isClicked = true;
                }else{
                    btnOption3.setBackgroundColor(Color.rgb(160,200,220));
                    option3isClicked = false;
                    if(!option1isClicked && !option2isClicked && !option3isClicked && !option4isClicked){
                        dontKnow.setEnabled(true);
                        dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    }
                }
                break;
            case R.id.btnMulAnsFour:
                if(!option4isClicked){
                    btnOption4.setBackgroundColor(Color.rgb(7, 147, 194));
                    dontKnow.setEnabled(false);
                    dontKnow.setBackgroundColor(Color.argb(50,160,200,220));
                    swipe.setVisibility(View.VISIBLE);
                    option4isClicked = true;
                }else{
                    btnOption4.setBackgroundColor(Color.rgb(160,200,220));
                    option4isClicked = false;
                    if(!option1isClicked && !option2isClicked && !option3isClicked && !option4isClicked){
                        dontKnow.setEnabled(true);
                        dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    }
                }
                break;
            case R.id.multipleDontKnow:
                if(!dontKnowIsClicked){
                    dontKnow.setBackgroundColor(Color.rgb(7, 147, 194));
                    swipe.setVisibility(View.VISIBLE);
                    btnOption1.setEnabled(false);
                    btnOption1.setBackgroundColor(Color.argb(50,160,200,220));
                    btnOption2.setEnabled(false);
                    btnOption2.setBackgroundColor(Color.argb(50,160,200,220));
                    btnOption3.setEnabled(false);
                    btnOption3.setBackgroundColor(Color.argb(50,160,200,220));
                    btnOption4.setEnabled(false);
                    btnOption4.setBackgroundColor(Color.argb(50,160,200,220));
                    dontKnowIsClicked = true;
                }else{
                    dontKnow.setBackgroundColor(Color.rgb(160,200,220));
                    btnOption1.setEnabled(true);
                    btnOption1.setBackgroundColor(Color.rgb(160,200,220));
                    btnOption2.setEnabled(true);
                    btnOption2.setBackgroundColor(Color.rgb(160,200,220));
                    btnOption3.setEnabled(true);
                    btnOption3.setBackgroundColor(Color.rgb(160,200,220));
                    btnOption4.setEnabled(true);
                    btnOption4.setBackgroundColor(Color.rgb(160,200,220));
                    dontKnowIsClicked = false;
                }
                break;
        }
    }
}
