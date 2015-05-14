package com.example.isabel.prototypestart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SwipeToStartNewRunFragment extends android.support.v4.app.Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        return inflater.inflate(R.layout.fragment_swipe_to_start_new_run, container, false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            ((MainActivity)getActivity()).setRunStartTime(System.currentTimeMillis());
        }
    }

    public SwipeToStartNewRunFragment(){}

    public static SwipeToStartNewRunFragment newInstance(int index) {
        SwipeToStartNewRunFragment f = new SwipeToStartNewRunFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }
}
