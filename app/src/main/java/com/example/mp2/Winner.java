package com.example.mp2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

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


    public void shareText(View view) {
        Intent intent = getIntent();

        String txt = "My team: " + intent.getStringExtra(MainActivity.WINNER) +
                ", they won by " + String.valueOf(intent.getIntExtra(MainActivity.WINBY, 0));
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_text_with)
                .setText(txt)
                .startChooser();
    }

    public void findArena(View view) {
        String loc = "League of Legends Arena Near Me";
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("Winner", "Can't handle this intent!");
        }
    }
    public void callFriend(View view) {
        Intent intent = new Intent( Intent.ACTION_DIAL);
        startActivity(intent);
    }
}