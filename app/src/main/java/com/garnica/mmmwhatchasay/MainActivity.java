package com.garnica.mmmwhatchasay;

import android.app.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;


public class MainActivity extends Activity {

    MediaPlayer media;
    boolean firstClick = true; // only for filthy version
    boolean filthyMode = false; // keeps track what mode is currently on.

    int images[] = {R.drawable.filthy_background1, R.drawable.filthy_background2,
            R.drawable.filthy_background3, R.drawable.filthy_background4,
            R.drawable.filthy_background5, R.drawable.filthy_background6,
            R.drawable.filthy_background7, R.drawable.filthy_background8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }


    public void Switch(View view) {
        if (media != null) media.stop();

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.mylayout);
        Button filthyButton = (Button) findViewById(R.id.filthyButtonView);
        ImageView gun = (ImageView) findViewById(R.id.gunImageView);

        if (filthyMode == false) {
            layout.setBackgroundResource(R.drawable.filthy_background1);
            filthyButton.setVisibility(View.VISIBLE);
            gun.setVisibility(View.GONE);
            filthyMode = true;
        } else {
            layout.setBackgroundResource(0);
            gun.setVisibility(View.VISIBLE);
            filthyButton.setVisibility(View.GONE);
            filthyMode = false;
        }
    }

    public void PlayRegular(View view) {
        if (media != null) media.stop();

        ImageView gun = (ImageView) findViewById(R.id.gunImageView);

        AnimationSet as = new AnimationSet(true);
        TranslateAnimation animation2 = new TranslateAnimation(0f, -30, 0f, 0f);
        as.addAnimation(animation2);
        gun.startAnimation(as);

        media = MediaPlayer.create(this, R.raw.regular_sound);
        media.start();


    }

    public void PlayFilthy(View view) {

        if (firstClick) {
            media = MediaPlayer.create(this, R.raw.filthy_sound);
            media.start();
            firstClick = false;
        } else {
            Random rand = new Random();
            int n = rand.nextInt(8);

            ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.mylayout);
            layout.setBackgroundResource(images[n]);

            media = MediaPlayer.create(this, R.raw.filthy_sound);
            media.start();
        }
    }


}
