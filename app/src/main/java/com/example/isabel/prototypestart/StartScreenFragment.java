package com.example.isabel.prototypestart;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StartScreenFragment extends android.support.v4.app.Fragment {

    private boolean hasBeenVisible = false;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        return inflater.inflate(R.layout.fragment_start_screen, container, false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            hasBeenVisible = true;
        }else{
           if(hasBeenVisible){
               ((MainActivity)getActivity()).setRunStartTime(System.currentTimeMillis());
           }
        }
    }

    public StartScreenFragment(){}

    public static StartScreenFragment newInstance(int index) {
        StartScreenFragment f = new StartScreenFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }
}