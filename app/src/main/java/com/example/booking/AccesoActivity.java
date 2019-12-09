package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class AccesoActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Button btnbuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_acceso );



        tabLayout = findViewById( R.id.tablelayout );
        viewPager = findViewById( R.id.ViewPager );

        ViewPagerAdapter adapter = new ViewPagerAdapter( getSupportFragmentManager() );
        adapter.add( new BlankFragment(),"Alojamiento" );
        adapter.add( new BlankFragment(),"Coches" );

        viewPager.setAdapter( adapter );
        tabLayout.setupWithViewPager( viewPager );

        BlankFragment fragment= new BlankFragment();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();


    }

    public boolean onCreateOptionMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d( "TAG","homeButton - onOptionsItemSelected" );
                break;
            case R.id.opcion1:
                Toast.makeText( this,"Busquedad",Toast.LENGTH_SHORT ).show();
                break;
            case R.id.opcion2:
                Toast.makeText( this,"Idioma",Toast.LENGTH_SHORT ).show();
                break;
            case R.id.opcion3:
                Toast.makeText( this,"Moneda",Toast.LENGTH_SHORT ).show();
                break;
            case R.id.opcion4:
                Intent comenzar = new Intent( AccesoActivity.this,LoginActivity.class );
                startActivity( comenzar );
                break;
            default:
                //Error Desconocido
        }
        return super.onOptionsItemSelected( item );
    }

}


