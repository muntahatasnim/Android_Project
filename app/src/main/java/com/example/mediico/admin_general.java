package com.example.mediico;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class admin_general extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_general);

        Button general_button = (Button) findViewById(R.id.general_user);
        general_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(admin_general.this,userActivity.class));
            }
        });

        Button admin_button = (Button) findViewById(R.id.admin);
        admin_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(admin_general.this,admin_medicine_reg.class));
            }
        });


    }
}
