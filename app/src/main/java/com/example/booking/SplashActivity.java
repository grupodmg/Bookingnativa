package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DELAY = 4000;


    ProgressBar progressBar;
    TextView texto,txt1;
    ImageView img1;

    Handler handler;
    int progress = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );

        txt1= findViewById( R.id.txt2 );
        Typeface fuente= Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        txt1.setTypeface(fuente);

        texto = findViewById( R.id.txt1 );
        Typeface font = Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        texto.setTypeface( font );

     //   img1 = findViewById( R.id.Img1 );

        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent().setClass(
                        SplashActivity.this,AccesoActivity.class );
                startActivity( i );
                //desaparecer();
                // dilatacion();
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule( task,SPLASH_SCREEN_DELAY );


        progressBar = findViewById( R.id.progress_btn );

        handler = new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                if (progressBar.getProgress()<100){
                    progressBar.setProgress( progress );
                    progress++;
                    handler.postDelayed( this,100 );
                    txt1.setText( "Cargando las mejores ofertas" );
                }else txt1.setText( "finalizado" );

            }
        },60 );

    }



    public void desaparecer() {
        Animation animation = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.animation );
        img1.startAnimation( animation );

    }



}




