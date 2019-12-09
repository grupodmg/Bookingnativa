package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TipolugarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tipolugar );
        ImageView myImageView = findViewById( R.id.imageView6);
        myImageView.setImageResource( R.drawable.monasterio );

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );


        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMain();
            }
        } );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
    }

    private void backToMain() {
        Intent intent = new Intent( this,MainActivity.class );
        startActivity( intent );
    }

}


