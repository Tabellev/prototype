package com.example.isabel.prototypestart;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

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
    private ControlledViewPager pager;
    public long runStartTime;
    public long runStopTime;
    public long runTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = new DbInteractorTest(getApplicationContext());

        super.setContentView(R.layout.activity_main);
        pager = (ControlledViewPager)super.findViewById(R.id.viewpager);
        WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);

        // Don't need this, only used to understand what happens when swiping and loading fragments
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                /*Log.d("onPageScrolled", String.valueOf(position) + ", " +
                String.valueOf(positionOffset) + ", " +
                String.valueOf(positionOffsetPixels));*/
            }

            @Override
            public void onPageSelected(int position) {
                //Log.d("onPageSelected():", String.valueOf(position));
                //startTime = System.currentTimeMillis();

                // FUNKER IKKE! får ikke tak i setHasBeenVisible() i RunFinishedFragment...
               /* if (mPagerAdapter.getItem(pager.getCurrentItem()) instanceof RunFinishedFragment) {
                    mPagerAdapter.mSessionFragments.get(position).setHasBeenVisible(false);

                }*/
                if (position == mPagerAdapter.mSessionFragments.size()) {
                    Log.d("onPageSelected()", String.valueOf(position));
                    getDBInteractor().createOutputJsonFile();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Log.d("..ScrollStateChanged()", String.valueOf(state));
            }
        });

        this.initialisePaging();
    }

    private void initialisePaging() {
        this.mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(), getDBInteractor().getMockQuestions());
        pager.setAdapter(this.mPagerAdapter);
    }


    // This is called from the fragments
    public IDbInteractor getDBInteractor() { return mDataManager; }

    // called when new run should start
    /*public void resetPager() {
        pager.setCurrentItem(0);
    }*/

    public class PagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> mSessionFragments;
        private HashMap<Integer, ArrayList<Question>> mRunsWithQuestions;
        private Fragment[] mStaticFragments;
        private int[] mRunIDs;
        private HashMap<Integer, Question> mapOfQuestions;


        public PagerAdapter(FragmentManager fm, HashMap<Integer, Question> mapOfQuestions) {
            super(fm);

            this.mapOfQuestions = mapOfQuestions;
            this.mRunsWithQuestions = buildRunsWithQuestions();
            // Create the before and in-between-run Fragments
            setStaticFragments();
            // get runIDs from the Session object
            this.mRunIDs = getDBInteractor().getRunIDs();
            // Create a list of all fragments for one test session
            populateSessionFragment();
        }


        private void setStaticFragments() {
            mStaticFragments = new Fragment[3];
            mStaticFragments[0] = new StartScreenFragment();
            mStaticFragments[1] = new RunFinishedFragment();
            mStaticFragments[2] = new SwipeToStartNewRunFragment();
        }


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


        private void populateSessionFragment() {
            mSessionFragments = new ArrayList<>();
            int numberOfRuns = mRunsWithQuestions.size() - 1;
            mSessionFragments.add(0, mStaticFragments[0]);
            for (int i = 0; i < mRunsWithQuestions.size(); i++) {
                ArrayList<Question> tempQuestions = mRunsWithQuestions.get(mRunIDs[i]);
                for (int j = 0; j < tempQuestions.size(); j++) {
                    int questionID = tempQuestions.get(j).getID();
                    switch (tempQuestions.get(j).getType()) {
                        case SingleChoice:
                            mSessionFragments.add(SingleChoiceFragment.newInstance(mRunIDs[i], questionID));
                            break;
                        case MultipleChoice:
                            mSessionFragments.add(MultipleChoiceFragment.newInstance(mRunIDs[i], questionID));
                            break;
                        case Numerical:
                            mSessionFragments.add(NumericalFragment.newInstance(mRunIDs[i], questionID));
                            break;
                    }
                }
                int size = mSessionFragments.size();

                if (i == numberOfRuns) {
                    mSessionFragments.add(size, mStaticFragments[1]);
                } else {
                    mSessionFragments.add(size, mStaticFragments[1]);
                    mSessionFragments.add(++size, mStaticFragments[2]);
                }
            }
        }

        @Override
        public Fragment getItem(int position) {

            /*if (position == mSessionFragments.size() - 1) {
                // Generate output file
                getDBInteractor().createOutputJsonFile();
            }*/
            if (position == mSessionFragments.size()) {
                //getDBInteractor().createOutputJsonFile();//her lages output før siste spm er satt!!!
                return new EndScreenFragment();
            } else {
                return mSessionFragments.get(position);
            }

        }

        /*@Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }*/

        @Override
        public int getCount() {
            return mSessionFragments.size() + 1;
        }
    }

    public ControlledViewPager getPager() {
        return pager;
    }

    @Override
    public void onBackPressed(){}

    public long getRunStartTime() {
        return runStartTime;
    }

    public long getRunStopTime() {
        return runStopTime;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunStartTime(long runStartTime) {
        this.runStartTime = runStartTime;
    }

    public void setRunStopTime(long runStopTime) {
        this.runStopTime = runStopTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }
}

