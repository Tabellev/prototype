package com.example.isabel.prototypestart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.isabel.prototypestart.model.DbInteractorTest;
import com.example.isabel.prototypestart.model.IDbInteractor;
import com.example.isabel.prototypestart.model.Question;

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

        this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), getDBInteractor().getQuestions());
        //
        final ControlledViewPager pager = (ControlledViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);

    }

    // This is called from the fragments
    public IDbInteractor getDBInteractor() { return mDataManager; }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        private List<Question> questions; // Needs to be HashMap<Integer, Question>, get from mDataManager

        public PagerAdapter(FragmentManager fm, List<Question> questions) {
            super(fm);
            this.questions = questions;
        }

        @Override
        public Fragment getItem(int position) {

            Question q = questions.get(position);
            Fragment f;
            switch ( q.getType()){
                case SingleChoice:
                    f =  SingleChoiceFragment.newInstance(position);
                    break;
                case MultipleChoice:
                    f = MultipleChoiceFragment.newInstance(position);
                    break;
                case Numerical:
                    f =  NumericalFragment.newInstance(position);
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

