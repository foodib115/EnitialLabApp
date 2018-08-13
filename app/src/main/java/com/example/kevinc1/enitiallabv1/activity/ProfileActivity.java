package com.example.kevinc1.enitiallabv1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevinc1.enitiallabv1.R;

public class ProfileActivity extends AppCompatActivity{

    private static final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("customer") && getIntent().hasExtra("orders")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String customer = getIntent().getStringExtra("customer");
            String orders = getIntent().getStringExtra("orders");

            setProfile(customer, orders);
        }
    }

    private void setProfile(String customer, String orders){
        Log.d(TAG, "setImage: setting the customer and orders to widgets.");

        TextView custnum = findViewById(R.id.customer);
        custnum.setText(customer);

        TextView order = findViewById(R.id.orders1);
        order.setText(orders);

    }

}
