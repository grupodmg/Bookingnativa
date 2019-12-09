package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView recyclerViewhotel;
    private RecyclerAdaptador adpatadorhotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recycler );



        recyclerViewhotel= findViewById( R.id.reciclerhotel );
        recyclerViewhotel.setLayoutManager( new LinearLayoutManager(this));

        adpatadorhotel= new RecyclerAdaptador( obtenerhotel() );
        recyclerViewhotel.setAdapter( adpatadorhotel );
    }
    public List<HotelModelo>obtenerhotel(){
        List<HotelModelo>hotel= new ArrayList<>();
        hotel.add( new HotelModelo( "Hotel Camino Real","Comodo y confortable",R.drawable.caminoreal ) );
        hotel.add( new HotelModelo( "Hotel la Herreria","Comodo y confortable",R.drawable.herreria ) );
        hotel.add( new HotelModelo( "Hotel Toledo","Comodo y confortable",R.drawable.toledo ) );
        hotel.add( new HotelModelo( "Hotel San Geronimo","Comodo y confortable",R.drawable.santodo ) );
        hotel.add( new HotelModelo( "Hotel San Martin","Comodo y confortable",R.drawable.hotelsanmartin ) );

        return hotel;

    }
}
