package com.example.booking;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapaFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener {

    private static final LatLng PERTH = new LatLng( -31.952854,115.857342 );
    private static final LatLng SYDNEY = new LatLng( -33.87365,151.20689 );
    private static final LatLng BRISBANE = new LatLng( -27.47093,153.0235 );


    // private Marker mPerth;
    //   private Marker mSydney;
    //  private Marker mBrisbane;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MapaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapaFragment newInstance(String param1,String param2) {
        MapaFragment fragment = new MapaFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1,param1 );
        args.putString( ARG_PARAM2,param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
        Button buscar;
        Button buscar2;

        View v = inflater.inflate( R.layout.fragment_mapa,container,false );
        buscar = v.findViewById( R.id.buscarbarra );
        buscar2 = v.findViewById( R.id.buscarboton );

        buscar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );

        buscar2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarLineas();
            }
        } );


        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction( uri );
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    +" must implement OnFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    GoogleMap map;
    Boolean actualPosition = true;
    JSONObject jso;
    Double longitudOrigen, latitudOrigen;

    @Override
    public void onMapReady(GoogleMap googleMap) {


        map = googleMap;

        if (ActivityCompat.checkSelfPermission( getActivity(),Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale( getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION )) {

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions( getActivity(),
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        1 );


            }
            if (ActivityCompat.shouldShowRequestPermissionRationale( getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION )) {

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions( getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1 );


            }


            return;
        }


        map.setOnMyLocationButtonClickListener( this );
        map.setOnMyLocationClickListener( this );
        map.getUiSettings().setZoomControlsEnabled( true );
        map.getUiSettings().setMyLocationButtonEnabled( true );
        map.setMyLocationEnabled( true );
        map.getUiSettings().setScrollGesturesEnabled( true );
        map.setOnMyLocationChangeListener( new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

                //2.942043!4d-75.2522789

                if (actualPosition) {
                    latitudOrigen = location.getLatitude();
                    longitudOrigen = location.getLongitude();
                    actualPosition = false;

                    LatLng miPosicion = new LatLng( latitudOrigen,longitudOrigen );

                    map.addMarker( new MarkerOptions().position( miPosicion ).title( "posicion actual" ) );


               //     LatLng popayan = new LatLng( 2.441924,-76.606339 );
                //    map.addMarker( new MarkerOptions().position( popayan ).draggable( true ).title( "popayan" ).snippet( "la ciudad blanca" ).icon( BitmapDescriptorFactory.fromResource( R.drawable.ic_cartagena ) ) );


                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target( new LatLng( latitudOrigen,longitudOrigen ) )      // Sets the center of the map to Mountain View
                            .zoom( 17 )
                            .bearing( 90 )// Sets the zoom
                            .build();                   // Creates a CameraPosition from the builder

                    map.animateCamera( CameraUpdateFactory.newCameraPosition( cameraPosition ) );

                    String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+latitudOrigen+","+longitudOrigen+"&destination=2.9435667,-75.2458577";

                    RequestQueue queue = Volley.newRequestQueue( getActivity() );
                    StringRequest stringRequest = new StringRequest( Request.Method.GET,url,new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {
                                jso = new JSONObject( response );
                                trazarRuta( jso );
                                Log.i( "jsonRuta: ",""+response );

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    } );

                    queue.add( stringRequest );
                }
            }
        } );


    }

    private void trazarRuta(JSONObject jso) {

        JSONArray jRoutes;
        JSONArray jLegs;
        JSONArray jSteps;

        try {
            jRoutes = jso.getJSONArray( "routes" );
            for (int i = 0; i < jRoutes.length(); i++) {

                jLegs = ((JSONObject) (jRoutes.get( i ))).getJSONArray( "legs" );

                for (int j = 0; j < jLegs.length(); j++) {

                    jSteps = ((JSONObject) jLegs.get( j )).getJSONArray( "steps" );

                    for (int k = 0; k < jSteps.length(); k++) {


                        String polyline = ""+((JSONObject) ((JSONObject) jSteps.get( k )).get( "polyline" )).get( "points" );
                        Log.i( "end",""+polyline );
                        List<LatLng> list = PolyUtil.decode( polyline );
                        map.addPolyline( new PolylineOptions().addAll( list ).color( Color.GRAY ).width( 5 ) );


                    }


                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText( getContext(),"ubicacion actual",Toast.LENGTH_SHORT ).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText( getContext(),"Current location:\n"+location,Toast.LENGTH_LONG ).show();

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void mostrarLineas() {
        //Dibujo con Lineas

        PolylineOptions lineas = new PolylineOptions()
                .add( new LatLng( 45.0,-12.0 ) )
                .add( new LatLng( 45.0,5.0 ) )
                .add( new LatLng( 34.5,5.0 ) )
                .add( new LatLng( 34.5,-12.0 ) )
                .add( new LatLng( 45.0,-12.0 ) );

        lineas.width( 8 );
        lineas.color( Color.RED );

        map.addPolyline( lineas );
    }

}
