package com.example.booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    private EditText txtnombre, txtcorreo, txtcontraseña, txtconfirmacion;
    private TextView registro,register;
    private Button btnlogin;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registro );

        register= findViewById( R.id.registerActivityLogin);
        Typeface font = Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        register.setTypeface( font );


        registro = findViewById( R.id.registro );

        Typeface fuente = Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        registro.setTypeface( fuente );

        txtnombre = findViewById( R.id.txtnombre );
        Typeface fo = Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        txtnombre.setTypeface( fo );

        txtcorreo = findViewById( R.id.txtcorreo );
        Typeface fo1 = Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        txtcorreo.setTypeface( fo1 );

        txtcontraseña = findViewById( R.id.txtpassword );
        Typeface ft = Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        txtcontraseña.setTypeface( ft );

        txtconfirmacion = findViewById( R.id.txtconfirmacion );
        Typeface f = Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        txtconfirmacion.setTypeface( f );

        btnlogin = findViewById( R.id.btnlogin );
        Typeface fuent = Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        btnlogin.setTypeface( fuent );
        btnlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        } );

    }

    private void login() {
            if (TextUtils.isEmpty( txtcorreo.getText().toString().trim() ) || TextUtils.isEmpty( txtcontraseña.getText().toString().trim() )) {
                txtcorreo.setError( "No ha ingresado el correo" );
                txtcontraseña.setError( "No ha ingresado la contraseña" );
            } else if (!emailValidator( txtcontraseña.getText().toString() )) {
                txtcorreo.setError( "ingrese un correo valido" );
            } else {
                Intent i = new Intent( RegistroActivity.this,MainActivity.class );
                startActivity( i );
                Toast.makeText( this,"ingreso correcto",Toast.LENGTH_SHORT ).show();
            }
        }


    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-0]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile( EMAIL_PATTERN );
        matcher = pattern.matcher( email );
        return matcher.matches();

    }
}














