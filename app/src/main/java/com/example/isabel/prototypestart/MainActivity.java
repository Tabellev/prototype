package com.example.isabel.prototypestart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.isabel.prototypestart.model.DbInteractorTest;
import com.example.isabel.prototypestart.model.IDbInteractor;
import com.example.isabel.prototypestart.model.Question;
import com.example.isabel.prototypestart.model.QuestionSetup;
import com.example.isabel.prototypestart.model.QuestionType;
import com.example.isabel.prototypestart.model.Session;

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

        private Session mSession; // Testing
        private ArrayList<Fragment> mSessionFragments; // Testing
        private HashMap<Integer, ArrayList<Question>> mRunsWithQuestions; // Testing
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

            this.mSessionFragments = createSessionFlow(); // Testing
            this.mRunsWithQuestions = buildRunsWithQuestions(); // Testing


            getQuestionSetupsForOneRun();
        }
        //--------------------------------------------------------------------------------------------------------------

        // Populates local list of Questions(
        private void getQuestionSetupsForOneRun() {
            QuestionSetup[] temp = getDBInteractor().getMockSession().getRunsToSetup()[0].getQuestionSetup();

            for (int i = 0; i < temp.length; i++) {
                // Add Questions to local List
                questions.add(i, mapOfQuestions.get(temp[i].getQuestionID()));
            }
        }

        /*
        Notes: Maybe create an ArrayList with ID's for Runs and Questions
               and give an ID to the fragments that aren't questions?
               OR
               Create a list of all fragments to be used during the session before starting.
               Each fragment must be in it's right position when getCount() and getItem() controls
               the swiping flow.
         */

        // Testing to isolate runs with containing questions
        private HashMap<Integer, ArrayList<Question>> buildRunsWithQuestions() {
            HashMap<Integer, ArrayList<Question>> temp = new HashMap<>();
            Session session = getDBInteractor().getMockSession();

            for (int i = 0; i < session.getRunsToSetup().length; i++) {
                ArrayList<Question> tmp = new ArrayList<>();
                for (int j = 0; j < session.getRunsToSetup()[i].getQuestionSetup().length; j++) {
                    tmp.add(mapOfQuestions.get(session.getRunsToSetup()[i].getQuestionSetup()[j].getQuestionID()));
                }
                temp.put(session.getRunsToSetup()[i].getRunID(), tmp);
            }
            return temp;
        }

        // Testing method to create a list of all fragments needed for a session
        private ArrayList<Fragment> createSessionFlow() {
            ArrayList<Fragment> fragments = new ArrayList<>();
            fragments.add(StartScreenFragment.newInstance(0));
            fragments.add(SwipeToStartNewRunFragment.newInstance(1));
            fragments.add(RunFinishedFragment.newInstance(2));



            return fragments;
        }

        @Override
        public Fragment getItem(int position) {

            return mSessionFragments.get(position);
            // Use numberOfRuns from Session
            /*Question q = questions.get(position); //questions is only for the 1st run
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

            return f;*/
        }



        @Override
        public int getCount() {
            //return this.questions.size();
            return mSessionFragments.size();
        }

    }

    @Override
    public void onBackPressed(){}
}

