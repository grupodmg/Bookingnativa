package com.example.booking;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2Fragment extends Fragment {


  Button btnbuscar;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        final View view =inflater.inflate( R.layout.fragment_fragment2,container,false );


        btnbuscar= view.findViewById( R.id.btnlogin);

        btnbuscar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(),MainActivity.class );
                startActivity( intent );
            }
        } );

        return view;
    }

}
