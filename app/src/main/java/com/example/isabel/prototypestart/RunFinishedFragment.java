package com.example.isabel.prototypestart;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class RunFinishedFragment extends android.support.v4.app.Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        return (RelativeLayout)inflater.inflate(R.layout.fragment_run_finished, container, false);
    }

    public RunFinishedFragment(){}

    public static RunFinishedFragment newInstance(int index) {
        RunFinishedFragment f = new RunFinishedFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    // add button that resets pageradapters counter

}
