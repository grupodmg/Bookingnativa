package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {

    Button btnllamar;
   String sNum;
   EditText numTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_perfil );



        btnllamar= findViewById( R.id.btnllamar );
        Typeface fuente= Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        btnllamar.setTypeface(fuente);


    }

    public void btncall(View v){
        Intent i= new Intent( Intent.ACTION_CALL );
        sNum= numTxt.getText().toString();
        if (sNum.trim().isEmpty()){
            i.setData( Uri.parse( "tel:7788994455" ) );
        }
        else {
            i.setData( Uri.parse( "tel"+sNum ) );
        }
        if (ActivityCompat.checkSelfPermission( this,Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText( this,"ingrese un numero valido para llamar",Toast.LENGTH_SHORT ).show();
            requestPermission();
        }else {
            startActivity( i );
        }

    }
    private void requestPermission(){
        ActivityCompat.requestPermissions( this,new String[]{Manifest.permission.CALL_PHONE},1 );
    }
}
