package com.example.booking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booking.config.Config;
import com.google.android.gms.wallet.PaymentsClient;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    public static final int PAYPAL_REQUEST_CODE = 7171;


    private static PayPalConfiguration config = new PayPalConfiguration().
            environment( PayPalConfiguration.ENVIRONMENT_SANDBOX ).
            clientId( Config.PAYPAL_CLIENT_ID );


    Button btnpago;
    EditText edtAmoun;
    TextView txtTv2;

    String amount = "";

    @Override
    protected void onDestroy() {
        stopService( new Intent( CheckoutActivity.this,PayPalService.class ) );
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_checkout );


        //inicializar el servicio de paypal

        Intent intent = new Intent( CheckoutActivity.this,PayPalService.class );
        intent.putExtra( PayPalService.EXTRA_PAYPAL_CONFIGURATION,config );
        startService( intent );

        TextView txtTv2= findViewById( R.id.loginTv2 );
        Typeface fue= Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        txtTv2.setTypeface(fue);

        edtAmoun = findViewById( R.id.edtamount );
        Typeface fuente= Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        edtAmoun.setTypeface(fuente);


        btnpago = findViewById( R.id.paypal );
        Typeface fuen= Typeface.createFromAsset( getAssets(),"EAGLESE.ttf" );
        btnpago.setTypeface(fuen);

        btnpago.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPayment();
            }

            private void processPayment() {
                amount = edtAmoun.getText().toString();
                PayPalPayment payPalPayment = new PayPalPayment( new BigDecimal( String.valueOf( amount ) ),"USD",
                        "Donacion voluntaria Booking",PayPalPayment.PAYMENT_INTENT_SALE );
                Intent intent = new Intent( CheckoutActivity.this,PaymentActivity.class );
                intent.putExtra( PayPalService.EXTRA_PAYPAL_CONFIGURATION,config );
                intent.putExtra( PaymentActivity.EXTRA_PAYMENT,payPalPayment );
                startActivityForResult( intent,PAYPAL_REQUEST_CODE );
            }
        } );
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult( requestCode,resultCode,data );
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (requestCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra( PaymentActivity.EXTRA_RESULT_CONFIRMATION );
                if (confirmation != null) {
                    try {
                        String paymentDetails = confirmation.toJSONObject().toString( 4 );
                        startActivity( new Intent( CheckoutActivity.this,PaymentDetailsActivity.class )
                                .putExtra( "paymentDetails",paymentDetails )
                                .putExtra( "PaymentAmount",amount )

                        );

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
            else if (requestCode== Activity.RESULT_CANCELED)
                Toast.makeText( this,"Cancelado",Toast.LENGTH_SHORT ).show();
        }
        else if (resultCode==PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText( this,"Invalido",Toast.LENGTH_SHORT ).show();

    }
}


