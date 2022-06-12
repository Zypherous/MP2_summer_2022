package com.example.mp2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Winner extends AppCompatActivity {
    private static final String LOG_TAG = Winner.class.getSimpleName();

    public static final String WINNER = "com.example.mp2.extra.WINNER";
    public static final String WINBY = "com.example.mp2.extra.WINBY";

    TextView winnerTeam, winByNum;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        // Initialize view variables.
        winnerTeam = findViewById(R.id.winner);
        winByNum = findViewById(R.id.win_by);

        Intent intent = getIntent();
        String winner = intent.getStringExtra(MainActivity.WINNER);
        int winBy = intent.getIntExtra(MainActivity.WINBY, 0);
        int color = intent.getIntExtra(MainActivity.COLOR, 0);

        winnerTeam.setText(winner);
        winnerTeam.setTextColor(color);
        winByNum.setText(String.valueOf(winBy));
        winByNum.setTextColor(color);
    }

    public void newGame(View view) {
        Intent replyIntent = new Intent();
        setResult(RESULT_OK, replyIntent);
        Log.d(LOG_TAG, "End WinnerActivity");
        finish();
    }

}