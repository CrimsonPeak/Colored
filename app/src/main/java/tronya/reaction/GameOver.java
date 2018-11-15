package tronya.reaction;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by LAL on 27.07.2016.
 */
public class GameOver extends AppCompatActivity {

    Button restart, menu;
    TextView score, high;

    //getting preferences
    SharedPreferences prefs, prefsHigh;
    int endlessScore;
    int highScore;

    MediaPlayer bgMusic;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);

        //Ad View
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5215375185046126~8241804694");

        AdView mAdView = (AdView) findViewById(R.id.ad_view2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //---

        //background Music
        bgMusic = MediaPlayer.create(GameOver.this, R.raw.bg2);
        bgMusic.setLooping(true);
        bgMusic.start();
        //---

        // the restart button, which closes the activity and opens endless.class
        restart = (Button) findViewById(R.id.button3);
        restart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                bgMusic.release();
                finish();
                Intent intent = new Intent(GameOver.this, EndlessMode.class);
                startActivity(intent);
            }
        });

        // Menu Button opens up MainAvtivity.class
        menu = (Button) findViewById(R.id.button4);
        menu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                bgMusic.release();
                finish();
                Intent intent = new Intent(GameOver.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // TextViews to display the score and highscore
        score = (TextView) findViewById(R.id.textView5);
        high = (TextView) findViewById(R.id.textView6);

        // Shared Preferences which gets the score results from the endless.class
        prefs = this.getSharedPreferences("EndlessMode", Context.MODE_PRIVATE);
        endlessScore = prefs.getInt("endless", 0); //0 is the default value
        score.setText(String.valueOf(endlessScore));

        prefsHigh = this.getSharedPreferences("EndlessMode", Context.MODE_PRIVATE);
        highScore = prefsHigh.getInt("endlessHigh", 0);
        if (highScore > endlessScore) {
            high.setText(String.valueOf(highScore));
        }
        else {
            highScore = endlessScore;
            high.setText(String.valueOf(highScore));
            prefsHigh.edit().putInt("endlessHigh", highScore).apply();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first
        bgMusic.release(); // close the music after leaving the app
    }

}
