package com.example.booking;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    View view1;
    EditText edthabitacion;
    TextView textViewBirthday;
    TextView registro, hoteles;
    Spinner spinner;
    Button btnbuscar;


    String textBirthday = "  /  /    ";

    private int bDay, bMonth, bYear;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        final View view1 = inflater.inflate( R.layout.fragment_blank,container,false );

        // hoteles = view1.findViewById( R.id.hoteles );
        // Typeface f= Typeface.createFromAsset(getAssets(),"EAGLESE.ttf" );
        //  hoteles.setTypeface( f );


        //registro = view1.findViewById( R.id.registro );
        //   Typeface font = Typeface.createFromAsset( getAssets(  ),"EAGLESE.ttf" );
        //  registro.setTypeface( font );


        edthabitacion = view1.findViewById( R.id.txthabitacion );


        spinner = view1.findViewById( R.id.spinner );
        final ArrayList<String> hoteles = new ArrayList<>();

        hoteles.add( "Hotel San Martin" );
        hoteles.add( "Hotel toledo" );
        hoteles.add( "Hotel Monasterio" );
        hoteles.add( "Hotel Camino Real" );
        hoteles.add( " Hotel Alcayata" );
        hoteles.add( "Hotel San Geronimo" );

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(view1.getContext(),R.layout.support_simple_spinner_dropdown_item,hoteles );
        spinner.setAdapter( adapter );

        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,View view,int position,long id) {
                Toast.makeText( view.getContext(),"ha seleccionado..."+hoteles.get(position),Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );


        //textViewBirthday.setText( textBirthday );
        textViewBirthday = view1.findViewById( R.id.calendar );
        textViewBirthday.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                bDay = calendar.get( Calendar.DAY_OF_MONTH );
                bMonth = calendar.get( Calendar.MONTH );
                bYear = calendar.get( Calendar.YEAR );

                DatePickerDialog datePickerDialog = new DatePickerDialog( getContext(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view,int year,int month,int dayOfMonth) {
                      textViewBirthday.setText( dayOfMonth+"/"+(month+1)+"/"+year );

                    }
                },bYear,bMonth,bDay );
                datePickerDialog.show();


            }
        } );


        btnbuscar = view1.findViewById( R.id.btnbuscar );
        btnbuscar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getActivity(),"Enviando informacion...",Toast.LENGTH_SHORT ).show();
                //  Bundle bundle = new Bundle();

                // Inicializas el Intent
                Intent intent = new Intent( v.getContext(),MainActivity.class );

                // Información del EditText
                /*  EditText editText = (EditText) findViewById(R.id.editText);*/
                // String nombre = edtnombre.getText().toString();
                String habitacion = edthabitacion.getText().toString();


              /*  // Agregas la información del EditText al Bundle
                bundle.putString("textFromActivityA", texto);
                // Agregas el Bundle al Intent e inicias ActivityB
                intent.putExtras(bundle);*/
                startActivity( intent );

            }
        } );
        return view1;
    }


}
