package com.example.scorekeeper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TEAM_A_SCORE_KEY = "teamAScore";
    private static final String TEAM_B_SCORE_KEY = "teamBScore";
    private static final String TEAM_A_FOUL_KEY = "teamAFoul";
    private static final String TEAM_B_FOUL_KEY = "teamBFoul";

    private TextView teamAScoreTv;
    private TextView teamBScoreTv;
    private TextView teamAFoulScoreTv;
    private TextView teamBFoulScoreTv;
    private int teamAScoreInt;
    private int teamBScoreInt;
    private int teamAFoulScoreInt;
    private int teamBFoulScoreInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetValues();
        teamAScoreTv = (TextView) findViewById(R.id.team_a_score_tv);
        teamBScoreTv = (TextView) findViewById(R.id.team_b_score_tv);
        teamAFoulScoreTv = (TextView) findViewById(R.id.team_a_foul_score_tv);
        teamBFoulScoreTv = (TextView) findViewById(R.id.team_b_foul_score_tv);
        Button teamAGoalButton = (Button) findViewById(R.id.team_a_goal_button);
        Button teamBGoalButton = (Button) findViewById(R.id.team_b_goal_button);
        Button teamAFoulButton = (Button) findViewById(R.id.team_a_foul_button);
        Button teamBFoulButton = (Button) findViewById(R.id.team_b_foul_button);
        Button resetButton = (Button) findViewById(R.id.rest_button);

        //restore point if activity was destroyed
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(TEAM_A_SCORE_KEY))
                teamAScoreInt = savedInstanceState.getInt(TEAM_A_SCORE_KEY);
            if (savedInstanceState.containsKey(TEAM_B_SCORE_KEY))
                teamBScoreInt = savedInstanceState.getInt(TEAM_B_SCORE_KEY);
            if (savedInstanceState.containsKey(TEAM_A_FOUL_KEY))
                teamAFoulScoreInt = savedInstanceState.getInt(TEAM_A_FOUL_KEY);
            if (savedInstanceState.containsKey(TEAM_B_FOUL_KEY))
                teamBFoulScoreInt = savedInstanceState.getInt(TEAM_B_FOUL_KEY);

            updateResultOnScreen();
        }

        teamAGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamAScoreInt++;
                updateResultOnScreen();
            }
        });
        teamBGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamBScoreInt++;
                updateResultOnScreen();
            }
        });
        teamAFoulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamAFoulScoreInt++;
                updateResultOnScreen();
            }
        });
        teamBFoulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamBFoulScoreInt++;
                updateResultOnScreen();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetValues();
                updateResultOnScreen();
            }
        });
        updateResultOnScreen();
    }

    //reset score value for two teams
    private void resetValues() {
        teamAScoreInt = 0;
        teamBScoreInt = 0;
        teamAFoulScoreInt = 0;
        teamBFoulScoreInt = 0;
    }

    //update data on TextView
    private void updateResultOnScreen() {
        teamAScoreTv.setText(Integer.toString(teamAScoreInt));
        teamBScoreTv.setText(Integer.toString(teamBScoreInt));

        if (teamAFoulScoreInt > 1)
            teamAFoulScoreTv.setText(teamAFoulScoreInt + " fouls");
        else
            teamAFoulScoreTv.setText(teamAFoulScoreInt + " foul");

        if (teamBFoulScoreInt > 1)
            teamBFoulScoreTv.setText(teamBFoulScoreInt + " fouls");
        else
            teamBFoulScoreTv.setText(teamBFoulScoreInt + " foul");
    }

    //save point on onSaveInstanceState before destroy the activity like rotation device
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TEAM_A_SCORE_KEY, teamAScoreInt);
        outState.putInt(TEAM_B_SCORE_KEY, teamBScoreInt);
        outState.putInt(TEAM_A_FOUL_KEY, teamAFoulScoreInt);
        outState.putInt(TEAM_B_FOUL_KEY, teamBFoulScoreInt);
    }
}