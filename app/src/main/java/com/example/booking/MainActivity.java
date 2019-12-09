package com.example.booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private FrameLayout frameLayout;

    TextView texto;
    Toolbar toolbar;
    public TabLayout tabLayout;
    public ViewPager viewPager;
    NavigationView navigationView;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        hideToolBar();
        showToolBar();

        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setDisplayShowTitleEnabled( false );


        drawerLayout = findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this,drawerLayout,toolbar,R.string.open,R.string.close );
        drawerLayout.addDrawerListener( toggle );
        toggle.syncState();

        navigationView = findViewById( R.id.navigation_view );
        navigationView.setNavigationItemSelectedListener( this );
        navigationView.setItemIconTintList( null );


    }

    private void showToolBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }

    }

    private void hideToolBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.escanear:
                Intent i = new Intent( MainActivity.this,LectorqrActivity.class );
                startActivity( i );
                break;

            case R.id.reconcimiento:
                Intent intent1 = new Intent( MainActivity.this,VozActivity.class );
                startActivity( intent1 );
                break;

            case R.id.mapa:
                Intent intent= new Intent( MainActivity.this,MapaPrincipalActivity.class );
                startActivity( intent );
                break;

            case R.id.pagos:
                Intent intent2 = new Intent( MainActivity.this,CheckoutActivity.class );
                startActivity( intent2 );
                break;
            case R.id.sesion:
                Intent s = new Intent( MainActivity.this,LoginActivity.class );
                startActivity( s );
                break;

            case R.id.reservas:
                Intent r = new Intent( MainActivity.this,PerfilActivity.class );
                startActivity( r );
                break;

            case R.id.ofertas:
                Toast.makeText( this,"ofertas",Toast.LENGTH_SHORT ).show();

                break;


            case R.id.listas:
                Toast.makeText( this,"listas",Toast.LENGTH_SHORT ).show();
                break;

                case R.id.Alquiler:
                Toast.makeText( this,"Alquiler de coches",Toast.LENGTH_SHORT ).show();
                break;

        }

        drawerLayout = findViewById( R.id.drawer_layout );
        drawerLayout.closeDrawer( GravityCompat.START );
        return true;
    }

    //Nos crea el menú de opciones en la Action Bar y aparecen los tres puntos
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu,menu );
        menuIconColor( menu,R.color.colorLight );
        return super.onCreateOptionsMenu( menu );
    }

    //Los Iconos les podemos poner el color a través de este metodo
    public void menuIconColor(Menu menu,int color) {
        for (int i = 0; i < menu.size(); i++) {
            Drawable drawable = menu.getItem( i ).getIcon();
            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter( getResources().getColor( color ),PorterDuff.Mode.SRC_ATOP );
            }
        }
    }

    //Para saber que item ha sido seleccionado
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
                Intent comenzar = new Intent( MainActivity.this,LoginActivity.class );
                startActivity( comenzar );
                break;
            default:
                //Error Desconocido
        }
        return super.onOptionsItemSelected( item );
    }

    //Se ejecuta cuando se pulsa el boton Navigate Up
    @Override
    public boolean onSupportNavigateUp() {
        Log.d( "TAG","homeButton - onSupportNavigateUp" );
        return true;
    }

    /*
   setDisplayHomeAsUpEnabled(true) -> Este método nos muestra el icono y además que se pueda presionar.
   Pero para implementar funcionalidad hay 3 opcione
   1.- Añadir en el manifest android: parentActivityName
   2.- Implementar setNavigationOnclikcListener
   3.- @Override public boolean onSupportNavigateUp() {}
    */
    public void showHomeUpIcon() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        }
    }

    public void hideHomeUpIcon() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled( false );
        }
    }

    //Para poner el color y el Icono de Home
    private void setUpHomeUpIconAndColor(int drawable,int color) {
        if (getSupportActionBar() != null) {
            final Drawable upArrow = getResources().getDrawable( drawable );
            upArrow.setColorFilter( getResources().getColor( color ),PorterDuff.Mode.SRC_ATOP );
            getSupportActionBar().setHomeAsUpIndicator( upArrow );
        }
    }

    private void customTitleToolBar() {
        if (getSupportActionBar() != null) {
            //Ocultamos el titulo por defecto de la Toolbar
            TextView textView = toolbar.findViewById( R.id.toolbar );
            textView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d( "TAG","Title Toolbar" );
                }
            } );
        }
    }
}



