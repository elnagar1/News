package com.example.news.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.news.R;

public class SpaleshActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalesh);

       ImageView imageView = findViewById(R.id.SpaleshImage);
        final Context context = this;

       /* Animation animation = new AlphaAnimation(0,1);
        animation.setDuration(50);
        animation.setRepeatCount(10);
        animation.setRepeatMode(Animation.REVERSE);
        imageView.startAnimation(animation);
   */

     /*
        final MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.dice_sound);
        mediaPlayer.start();
     */

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(context,HomeActivity.class);
            context.startActivity(intent);
            finish();
        }, 3000);


        }

}
