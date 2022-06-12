package com.example.mp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String WINNER = "com.example.mp2.extra.WINNER" ;
    public static final String WINBY = "com.example.mp2.extra.WINBY";
    public static final String COLOR = "com.example.mp2.extra.COLOR";
    public static final int NEWGAME = 1;
    Button t1Btn;
    Button t2Btn;
    private int t1Score, t2Score;
    TextView score1, score2, team1, team2 = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        t1Score = t2Score = 0;
        setContentView(R.layout.activity_main);
        t1Btn = (Button) findViewById(R.id.team_1_button);
        t2Btn = (Button) findViewById(R.id.team_2_button);
        score1 = (TextView) findViewById(R.id.team_1_score);
        score2 = (TextView) findViewById(R.id.team_2_score);
        team1 = (TextView) findViewById(R.id.team_1_name);
        team2 = (TextView) findViewById(R.id.team_2_name);

        if (savedInstanceState != null) {
          t1Score = savedInstanceState.getInt("score_1");
          t2Score = savedInstanceState.getInt("score_2");
          score1.setText(String.valueOf(t1Score));
          score2.setText(String.valueOf(t2Score));
        }

    }

    public void addPointT2(View view) {
        Log.d(LOG_TAG, "Button T2 clicked!");
        t2Score++;
        score2.setText(String.valueOf(t2Score));
        if(t2Score == 5) {
            Intent intent = new Intent(this, Winner.class);
            String winner = team2.getText().toString();
            int winBy = t2Score -t1Score;
            int color = score2.getCurrentTextColor();
            intent.putExtra(WINNER, winner);
            intent.putExtra(WINBY, winBy);
            intent.putExtra(COLOR, color);
            startActivityForResult(intent, NEWGAME);
        }
    }

    public void addPointT1(View view) {
        Log.d(LOG_TAG, "Button T1 clicked!");
        t1Score++;
        score1.setText(String.valueOf(t1Score));
        if(t1Score == 5) {
            Intent intent = new Intent(this, Winner.class);
            String winner = team1.getText().toString();
            int winBy = t1Score -t2Score;
            int color = score1.getCurrentTextColor();
            intent.putExtra(WINNER, winner);
            intent.putExtra(WINBY, winBy);
            intent.putExtra(COLOR, color);
            startActivityForResult(intent, NEWGAME);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score_1", t1Score);
        outState.putInt("score_2",t2Score);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply.
        if (requestCode == NEWGAME) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
               t1Score = 0;
               t2Score = 0;
               score1.setText(String.valueOf(t1Score));
               score2.setText(String.valueOf(t2Score));
            }
        }
    }
}