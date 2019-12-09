package com.example.booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView texto, txtlogin;
    private EditText txtemail, txtpassword;
    private Button btnregistro, btnlogin;
    private ProgressDialog progressDialog;


    //declaramos un objeto de tipo firebaseAuth

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );



        texto = findViewById( R.id.loginTv1 );
        Typeface font = Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        texto.setTypeface( font );



        txtlogin= findViewById( R.id.txtlogin );
        Typeface fuente= Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        txtlogin.setTypeface( fuente );
        txtlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( LoginActivity.this,RegistroActivity.class );
                startActivity( i );
            }
        } );

        //inicializamos el objeto de tipo firebaseauth

        firebaseAuth = FirebaseAuth.getInstance();

        btnregistro = findViewById( R.id.btnregistro );
        Typeface font1= Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        btnregistro.setTypeface( font1 );

        btnlogin = findViewById( R.id.btnenviar );
        Typeface f= Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        btnlogin.setTypeface( f );


        btnregistro.setOnClickListener( this );
        btnlogin.setOnClickListener( this );

        txtemail = findViewById( R.id.edtcorreo );
        Typeface fuente1= Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        txtemail.setTypeface( fuente1 );


        txtpassword = findViewById( R.id.edtcontraseña );
        Typeface fuent1= Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        txtpassword.setTypeface( fuent1 );

        progressDialog = new ProgressDialog( this );


    }

    private void registarUsuario() {
        String email = txtemail.getText().toString().trim();
        String password = txtpassword.getText().toString().trim();
        //  String confirmacion = txtconfirmacion.getText().toString().trim();

        //verificacion de los campos no esten vacios

        if (TextUtils.isEmpty( email )) {
            Toast.makeText( this,"debe ingresar un correo",Toast.LENGTH_SHORT ).show();
            return;
        }
        if (TextUtils.isEmpty( password )) {
            Toast.makeText( this,"debe ingresar una contraseña",Toast.LENGTH_SHORT ).show();
            return;
        }
        progressDialog.setMessage( "Realizando registro en linea..." );
        progressDialog.show();

        //crear un nuevo usuario

        firebaseAuth.createUserWithEmailAndPassword( email,password )
                .addOnCompleteListener( this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText( LoginActivity.this,"el email ha sido registrado con exito"+txtemail.getText(),Toast.LENGTH_SHORT ).show();
                            Intent i = new Intent( LoginActivity.this,MainActivity.class );
                            startActivity( i );
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {// Por una posible coliscion
                                Toast.makeText( LoginActivity.this,"el usuario ya existe",Toast.LENGTH_SHORT ).show();

                            } else {
                                Toast.makeText( LoginActivity.this,"No se pudo registar el Usuario",Toast.LENGTH_SHORT ).show();
                            }

                        }
                        progressDialog.dismiss();
                    }
                } );

    }

    private void enviarUsuario() {
        String email = txtemail.getText().toString().trim();
        String password = txtpassword.getText().toString().trim();
        //  String confirmacion = txtconfirmacion.getText().toString().trim();

        //verificacion de los campos no esten vacios

        if (TextUtils.isEmpty( email )) {
            Toast.makeText( this,"debe ingresar un correo",Toast.LENGTH_SHORT ).show();
            return;
        }
        if (TextUtils.isEmpty( password )) {
            Toast.makeText( this,"debe ingresar una contraseña",Toast.LENGTH_SHORT ).show();
            return;
        }
        progressDialog.setMessage( "Realizando registro en linea..." );
        progressDialog.show();

        //enviar un nuevo usuario

        firebaseAuth.signInWithEmailAndPassword( email,password )
                .addOnCompleteListener( this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText( LoginActivity.this,"Bienvenido ha ingresado correctamente"+txtemail.getText(),Toast.LENGTH_SHORT ).show();
                            Intent i = new Intent( LoginActivity.this,MainActivity.class );
                            startActivity( i );
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {// Por una posible coliscion
                                Toast.makeText( LoginActivity.this,"el usuario ya existe",Toast.LENGTH_SHORT ).show();

                            } else {
                                Toast.makeText( LoginActivity.this,"No se pudo registar el Usuario",Toast.LENGTH_SHORT ).show();
                            }

                        }
                        progressDialog.dismiss();
                    }
                } );
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnregistro:
                //invocamos al metodo
                registarUsuario();
                break;
            case R.id.btnenviar:
                enviarUsuario();
        }


    }

}