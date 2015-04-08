package com.example.isabel.prototypestart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.isabel.prototypestart.model.DbInteractorTest;
import com.example.isabel.prototypestart.model.IDbInteractor;
import com.example.isabel.prototypestart.model.Question;
import com.example.isabel.prototypestart.model.QuestionSetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends android.support.v4.app.FragmentActivity {

    private PagerAdapter mPagerAdapter;
    private IDbInteractor mDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = new DbInteractorTest(getApplicationContext());

        super.setContentView(R.layout.activity_main);

        this.initialisePaging();
    }

    private void initialisePaging() {
        // Old before testing with HashMap
        //this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), getDBInteractor().getQuestions());

        // Testing with HashMap for Questions--------------------------------------------------------------------------------
        this.mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), getDBInteractor().getMockQuestions());
        //-----------------------------------------------------------------------------------------------------------------

        final ControlledViewPager pager = (ControlledViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);

    }

    // This is called from the fragments
    public IDbInteractor getDBInteractor() { return mDataManager; }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        private List<Question> questions; // Needs to be HashMap<Integer, Question>, get from mDataManager
        /*
        public PagerAdapter(FragmentManager fm, List<Question> questions) {
            super(fm);
            this.questions = questions;
        }*/

        // Testing with HashMap and new constructor------------------------------------------------------------------------
        private HashMap<Integer, Question> mapOfQuestions;

        public PagerAdapter(FragmentManager fm, HashMap<Integer, Question> mapOfQuestions) {
            super(fm);
            questions = new ArrayList<Question>();
            this.mapOfQuestions = mapOfQuestions;
            // create an ArrayList<Question> with the question order from RunSetup in Session!!!!!!!!!!!!!!!!!!!!!!!!!!
            // The below must be a method
            // TODO: extract to method to eliminate using hard-coded getRunsToSetup()[*] - create a HashMap<runID, ArrayList<Question> for the Runs
            QuestionSetup[] temp = getDBInteractor().getMockSession().getRunsToSetup()[0].getQuestionSetup();
            for (int i = 0; i < temp.length; i++) {
                questions.add(i, mapOfQuestions.get(temp[i].getQuestionID()));
            }
        }
        //--------------------------------------------------------------------------------------------------------------

        @Override
        public Fragment getItem(int position) {
            // Use numberOfRuns from Session
            Question q = questions.get(position); //questions is only for the 1st run
            Fragment f;

            switch (q.getType()) {
                case SingleChoice:
                    f = SingleChoiceFragment.newInstance(q.getID());// testing with q.getID() instead of position
                    break;
                case MultipleChoice:
                    f = MultipleChoiceFragment.newInstance(q.getID());
                    break;
                case Numerical:
                    f = NumericalFragment.newInstance(q.getID());
                    break;
                default:
                    f = StartScreenFragment.newInstance(position);
            }

            return f;
        }



        @Override
        public int getCount() {
            return this.questions.size();
        }

    }

    @Override
    public void onBackPressed(){}
}

