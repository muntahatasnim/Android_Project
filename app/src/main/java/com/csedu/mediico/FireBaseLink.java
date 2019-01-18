package com.csedu.mediico;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.csedu.mediico.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_SHORT;


public class FireBaseLink extends Activity {

    EditText editTextBName, editTextGName, editTextAvailIn, editTextStock;
    Button buttonMed;
    DatabaseReference databaseMedicine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_link);
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(this);
        databaseMedicine=FirebaseDatabase.getInstance().getReference("Medicine");
        editTextBName = (EditText) findViewById(R.id.bname);
        editTextGName = (EditText) findViewById(R.id.gname);
        editTextAvailIn = (EditText) findViewById(R.id.availin);
        editTextStock = (EditText) findViewById(R.id.stock);
        buttonMed = (Button) findViewById(R.id.add_medicine);
        buttonMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddMedicine();

            }
        });

    }
    public boolean isInteger( String input ) { //Pass in string
        try { //Try to make the input into an integer
            Integer.parseInt( input );
            return true; //Return true if it works
        }
        catch( Exception e ) {
            return false; //If it doesn't work return false
        }
    }

    private void AddMedicine() {
        int stock;
        String brandName = editTextBName.getText().toString();

        String genericName = editTextGName.getText().toString();
        //System.out.println(brandName);
        String pharmacyName = editTextAvailIn.getText().toString();
        //System.out.println(pharmacyName);
        if (isInteger(editTextStock.getText().toString())) {
            stock = Integer.parseInt(editTextStock.getText().toString());
        }
        else{
            stock = 0;
        }
        //System.out.println(stock);
        if (!TextUtils.isEmpty(brandName) &&!TextUtils.isEmpty(genericName) &&!TextUtils.isEmpty(pharmacyName) && stock!=0) {
            String id = databaseMedicine.push().getKey();
            Medicine medicine = new Medicine(id, brandName, genericName, pharmacyName, stock);
            assert id != null;
            databaseMedicine.child(id).setValue(medicine,new
                    DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, @NonNull DatabaseReference
                                databaseReference) {
                            //Problem with saving the data
                            System.out.println("Value was set ");

                        }
                    });
        }

        else {
            Toast. makeText(this, "You Should Enter All Your Informations", LENGTH_SHORT).show();
        }

    }
}