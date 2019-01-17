package com.csedu.mediico.my_project.activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.csedu.mediico.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button mybutton = (Button) findViewById(R.id.userbutton);
        mybutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, admin_general.class));
            }
        });

        Button deliveryman = (Button) findViewById(R.id.deliverybutton);
        deliveryman.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DeliverymanActivity.class));
            }
        });


    }



}
