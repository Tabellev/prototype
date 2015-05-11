package com.example.isabel.prototypestart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.isabel.prototypestart.model.TestResult;
import com.example.isabel.prototypestart.model.TestStatistics;

import org.w3c.dom.Text;

/**
 * Created by oyvind on 28.04.2015.
 */
public class EndScreenFragment extends Fragment {
    private TestResult mTestResult;
    private TextView txtTestName, txtTestSessionId, txtTestCrewId, txtTestNumberOfRuns, txtTestTimeUsed,
                     txtTestCorrectAnswers, txtTestWrongAnswers, txtTestSkippedAnswers;
    private TestStatistics mTestStatistics;

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
        txtTestTimeUsed = (TextView)view.findViewById(R.id.txtOutputTestResultOverallTimeUsed);
        txtTestCorrectAnswers = (TextView)view.findViewById(R.id.txtOutputTestResultCorrectAnswers);
        txtTestWrongAnswers = (TextView)view.findViewById(R.id.txtOutputTestResultWrongAnswers);
        txtTestSkippedAnswers = (TextView)view.findViewById(R.id.txtOutputTestResultSkippedAnswers);

        populateWithTestResults();

        return view;
    }

    private void populateWithTestResults() {
        //TODO: get TestResult from DbInteractor
        mTestResult = ((MainActivity)getActivity()).getDBInteractor().getTestResult();

        mTestStatistics = ((MainActivity)getActivity()).getDBInteractor().getTestStatistics();

        txtTestName.setText(mTestResult.getExperimentName());
        txtTestSessionId.setText(String.valueOf(mTestResult.getSessionID()));
        txtTestCrewId.setText(String.valueOf(mTestResult.getCrewID()));
        txtTestNumberOfRuns.setText(String.valueOf(mTestResult.getNumberOfRuns()));

        txtTestTimeUsed.setText(String.valueOf(mTestStatistics.getSessionTimeUsed()) + " Seconds");
        txtTestCorrectAnswers.setText(String.valueOf(mTestStatistics.getNumberOfCorrectAnswers()));
        txtTestWrongAnswers.setText(String.valueOf(mTestStatistics.getNumberOfWrongAnswers()));
        txtTestSkippedAnswers.setText(String.valueOf(mTestStatistics.getNumberOfSkippedAnswers()));
    }
}
