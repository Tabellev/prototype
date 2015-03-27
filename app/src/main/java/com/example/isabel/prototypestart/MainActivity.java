package com.example.isabel.prototypestart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;

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
        mDataManager = new DbInteractorTest();

        super.setContentView(R.layout.activity_main);

        this.initialisePaging();
    }

    private void initialisePaging() {

        this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), getDBInteractor().getQuestions());
        //
        final ControlledViewPager pager = (ControlledViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);

    }

    public IDbInteractor getDBInteractor() { return mDataManager; }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        private List<Question> questions;

        public PagerAdapter(FragmentManager fm, List<Question> questions) {
            super(fm);
            this.questions = questions;
        }

        @Override
        public Fragment getItem(int position) {
            int qid = position;
            Question q = questions.get(qid);
            Fragment f;
            switch ( q.getType()){
                case SingleChoice:
                    f =  SingleChoiceFragment.newInstance(qid);
                    break;
                case MultipleChoice:
                    f = MultipleChoiceFragment.newInstance(qid);
                    break;
                case Numerical:
                    f =  NumericalFragment.newInstance(qid);
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

