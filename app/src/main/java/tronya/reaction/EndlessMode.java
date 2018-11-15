package tronya.reaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by LAL on 27.07.2016.
 */
public class EndlessMode extends AppCompatActivity implements View.OnClickListener {

    Button rightBottom, rightTop, leftBottom, leftTop;
    ImageView image;
    TextView score;
    int exScore;
    int Score;

    boolean noDelay = false;

    Random rand = new Random();
    String str = "img_" + rand.nextInt(3);

    //Game Over ---
    CountDownTimer cdt = new CountDownTimer(1000, 1000) {

        public void onTick(long millisUntilFinished) {
            //Während dem CountDown
        }

        public void onFinish() {
            if(noDelay==false) {
                wrongSound.start();
                finish();
                Intent intent = new Intent(EndlessMode.this, GameOver.class);
                startActivity(intent);
            }
        }
    };
    // -------------

    MediaPlayer rightSound;
    MediaPlayer wrongSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endless);

        // Dialog ---

        //----------

        changeImage();

        rightBottom = (Button) findViewById(R.id.rb);
        rightBottom.setOnClickListener(this);

        rightTop = (Button) findViewById(R.id.rt);
        rightTop.setOnClickListener(this);

        leftBottom = (Button) findViewById(R.id.lb);
        leftBottom.setOnClickListener(this);

        leftTop = (Button) findViewById(R.id.lt);
        leftTop.setOnClickListener(this);

        score = (TextView) findViewById(R.id.textView);

        // Sound effects by clicking a a color
        rightSound = MediaPlayer.create(EndlessMode.this, R.raw.right);
        wrongSound = MediaPlayer.create(EndlessMode.this, R.raw.wrong);
    }

    //Changes the actual image with another one
    public void changeImage(){
        rand = new Random();
        str = "img_" + rand.nextInt(4);
        image = new ImageView(this);
        image = (ImageView) findViewById(R.id.imageView);
        image.setImageDrawable(getResources().getDrawable(getResourceID(str , "drawable", getApplicationContext())));
    }

    //finishes the game if not the right button was pressed (without any delay)
    public void gameOverNoDelay(){
        //plays the wrong sound for once
        wrongSound.start();

        // set noDelay variable to true, because then the cdt finish() will not react
        noDelay = true;

        finish();
        Intent intent = new Intent(EndlessMode.this, GameOver.class);
        startActivity(intent);
    }

    //Animation for disappearance and appearance of image
    public void imageScale(){
        Animation imageScale = AnimationUtils.loadAnimation(this, R.anim.image_anim);
        image.startAnimation(imageScale);

        Score = exScore++;
        score.setText(String.valueOf(Score));

        //setting Score preferences
        SharedPreferences prefs = this.getSharedPreferences("EndlessMode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("endless", Score);
        editor.commit();

        // plays the right sound for once
        rightSound.start();
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.rb:
                if (str.equals("img_" + 0)){
                    // This will change the image with a random one
                    imageScale();
                    changeImage();
                    cdt.cancel();
                    cdt.start();
                }
                else{gameOverNoDelay();}
                break;

            case R.id.rt:
                if (str.equals("img_" + 1)){
                    // This will change the image with a random one
                    imageScale();
                    changeImage();
                    cdt.cancel();
                    cdt.start();
                }
                else{gameOverNoDelay();}
                break;

            case R.id.lb:
                if (str.equals("img_" + 2)){
                    // This will change the image with a random one
                    imageScale();
                    changeImage();
                    cdt.cancel();
                    cdt.start();
                }
                else{gameOverNoDelay();}
                break;

            case R.id.lt:
                if (str.equals("img_" + 3)){
                    // This will change the image with a random one
                    imageScale();
                    changeImage();
                    cdt.cancel();
                    cdt.start();
                }
                else{gameOverNoDelay();}
                break;
        }
    }

    // Random image change
    protected final static int getResourceID(final String resName, final String resType, final Context ctx)
    {
        final int ResourceID =
                ctx.getResources().getIdentifier(resName, resType,
                        ctx.getApplicationInfo().packageName);
        if (ResourceID == 0)
        {
            throw new IllegalArgumentException
                    (
                            "No resource string found with name " + resName
                    );
        }
        else
        {
            return ResourceID;
        }
    }

}
