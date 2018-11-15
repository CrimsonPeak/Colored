package tronya.reaction;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button startGame, startGame2, howTo;
    MediaPlayer bgMusic;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ad View
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5215375185046126~8241804694");

        AdView mAdView = (AdView) findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //---

        //background Music
        bgMusic = MediaPlayer.create(MainActivity.this, R.raw.bg2);
        bgMusic.setLooping(true);
        bgMusic.start();

        // starts te endless mode of the game
        startGame = (Button) findViewById(R.id.button);
        startGame.setOnClickListener(this);

        // starts the 17 sek mode of the game
        startGame2 = (Button) findViewById(R.id.button2);
        startGame2.setOnClickListener(this);

        // button to show how to play the game
        howTo = (Button) findViewById(R.id.button5);
        howTo.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()) {

            case R.id.button:
                //stops backgroundMusic
                bgMusic.release();
                //---
                finish();
                Intent intent = new Intent(MainActivity.this, EndlessMode.class);
                startActivity(intent);
                break;

            case R.id.button2:
                //stops backgroundMusic
                bgMusic.release();
                //---
                finish();
                Intent intent2 = new Intent(MainActivity.this, SeventeenMode.class);
                startActivity(intent2);
                break;

            case R.id.button5:
                dialog = new Dialog(MainActivity.this);
                // make background of Dialog transparent
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                // set dialog message
                dialog.setContentView(R.layout.custom_dialog);
                // show it
                dialog.show();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first
        bgMusic.release();
    }


}
