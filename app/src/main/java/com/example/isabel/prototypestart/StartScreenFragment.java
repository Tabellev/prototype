package com.example.isabel.prototypestart;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class StartScreenFragment extends android.support.v4.app.Fragment {


    private boolean hasBeenVisible = false;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        return (RelativeLayout)inflater.inflate(R.layout.fragment_start_screen, container, false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            hasBeenVisible = true;
        }else{
           if(hasBeenVisible){
               ((MainActivity)getActivity()).setRunStartTime(System.currentTimeMillis());
               Log.d("startScreene",((MainActivity)getActivity()).getRunStartTime() + "");
           }
        }
    }

    public StartScreenFragment(){}

    // TODO: remove Context parameter
    public static StartScreenFragment newInstance(int index) {
        StartScreenFragment f = new StartScreenFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }
}