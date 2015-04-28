package com.example.isabel.prototypestart;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class RunFinishedFragment extends android.support.v4.app.Fragment {

   private boolean hasBeenVisible = false;
    private long runTime;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        return (RelativeLayout)inflater.inflate(R.layout.fragment_run_finished, container, false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            hasBeenVisible = true;
            //----------------------------------------------
            ((MainActivity)getActivity()).setRunStopTime(System.currentTimeMillis());
            Log.d("startTime",((MainActivity)getActivity()).getRunStartTime() + "");
            Log.d("stopTime",((MainActivity)getActivity()).getRunStopTime() + "");
            ((MainActivity)getActivity()).setRunTime((((MainActivity)getActivity()).getRunStopTime() - ((MainActivity)getActivity()).getRunStartTime())/1000);
            Log.d("Run finished", ((MainActivity)getActivity()).getRunTime() + "");
            //--------------------------------------------------
        }else{
            if(hasBeenVisible){
                /*((MainActivity)getActivity()).setRunStopTime(System.currentTimeMillis());
                Log.d("startTime",((MainActivity)getActivity()).getRunStartTime() + "");
                Log.d("stopTime",((MainActivity)getActivity()).getRunStopTime() + "");
                ((MainActivity)getActivity()).setRunTime((((MainActivity)getActivity()).getRunStopTime() - ((MainActivity)getActivity()).getRunStartTime())/1000);
                Log.d("Run finished", ((MainActivity)getActivity()).getRunTime() + "");*/
            }
        }
    }

    public RunFinishedFragment(){}

    public static RunFinishedFragment newInstance(int index) {
        RunFinishedFragment f = new RunFinishedFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public void setHasBeenVisible(boolean value) {
        this.hasBeenVisible = value;
    }
    // add button that resets pageradapters counter

}
