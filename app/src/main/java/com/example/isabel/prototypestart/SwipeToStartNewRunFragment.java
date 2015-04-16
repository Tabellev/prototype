package com.example.isabel.prototypestart;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class SwipeToStartNewRunFragment extends android.support.v4.app.Fragment {
    private boolean hasBeenVisible;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        return (RelativeLayout)inflater.inflate(R.layout.fragment_swipe_to_start_new_run, container, false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            hasBeenVisible = true;
        }else{
            if(hasBeenVisible){
                ((MainActivity)getActivity()).setRunStartTime(System.currentTimeMillis());
                Log.d("startScreene", ((MainActivity) getActivity()).getRunStartTime() + "");
            }
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
