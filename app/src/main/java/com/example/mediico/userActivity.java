package com.example.mediico;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class userActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        Button login = (Button) findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(userActivity.this,medicine_show .class));
            }
        });
    }
}
