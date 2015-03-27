package com.example.isabel.prototypestart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.MotionEvent;
import android.view.View;

import com.example.isabel.prototypestart.model.DbInteractorTest;
import com.example.isabel.prototypestart.model.IDbInteractor;
import com.example.isabel.prototypestart.model.Question;

import java.util.List;

public class MainActivity extends android.support.v4.app.FragmentActivity {

    private PagerAdapter mPagerAdapter;
    private int oldX;
    private int deltaX = 0;
    private IDbInteractor mDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = new DbInteractorTest();

        super.setContentView(R.layout.activity_main);

        this.initialisePaging();
    }

    private void initialisePaging() {

       /* List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, StartScreenFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, SingleChoiceQuestionFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, MultipleChoiceFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, NumericalInputFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, NumericalResetFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, RunFinishedFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, SwipeToStartNewRunFragment.class.getName()));*/
        this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), getDBInteractor().getQuestions());
        //
        ControlledViewPager pager = (ControlledViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);
        pager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
                    int newX = (int) event.getX();
                    deltaX = oldX - newX;
                    oldX = newX;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    oldX = 0;
                }
                return deltaX < 0;
            }
        });
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

