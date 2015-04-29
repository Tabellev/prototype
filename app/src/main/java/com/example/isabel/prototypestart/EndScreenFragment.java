package com.example.isabel.prototypestart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.isabel.prototypestart.model.TestResult;

import org.w3c.dom.Text;

/**
 * Created by oyvind on 28.04.2015.
 */
public class EndScreenFragment extends Fragment {
    private TestResult mTestResult;
    private TextView txtTestName, txtTestSessionId, txtTestCrewId, txtTestNumberOfRuns;

    public EndScreenFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_end_screen, container, false);

        txtTestName = (TextView)view.findViewById(R.id.txtOutputTestResultName);
        txtTestSessionId = (TextView)view.findViewById(R.id.txtOutputTestResultSessionId);
        txtTestCrewId = (TextView)view.findViewById(R.id.txtOutputTestResultCrewId);
        txtTestNumberOfRuns = (TextView)view.findViewById(R.id.txtOutputTestResultNumberOfRuns);

        populateWithTestResults();

        return view;
    }

    private void populateWithTestResults() {
        //TODO: get TestResult from DbInteractorTest
        mTestResult = ((MainActivity)getActivity()).getDBInteractor().getTestResult();

        txtTestName.setText(mTestResult.getExperimentName());
        txtTestSessionId.setText(String.valueOf(mTestResult.getSessionID()));
        txtTestCrewId.setText(String.valueOf(mTestResult.getCrewID()));
        txtTestNumberOfRuns.setText(String.valueOf(mTestResult.getNumberOfRuns()));
    }
}
