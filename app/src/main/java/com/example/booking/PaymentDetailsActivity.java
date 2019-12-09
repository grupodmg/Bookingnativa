package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetailsActivity extends AppCompatActivity {

    TextView txtid,txtAmount,txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_payment_details );

        txtid = findViewById( R.id.txtid );
        txtAmount= findViewById( R.id.txtamount);
        txtStatus= findViewById( R.id.txtstatus);


        // intent

        Intent intent= getIntent();

        try {
            JSONObject jsonObject = new JSONObject( intent.getStringExtra( "paymentDetails" ) );
            showDetails(jsonObject.getJSONObject( "response" ),intent.getStringExtra( "PaymentAmount" ));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showDetails(JSONObject response,String paymentAmount) {
        try {
            txtid.setText( response.getString( "id" ) );
            txtStatus.setText( response.getString( "state" ) );
            txtAmount.setText("$"+paymentAmount );

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
