package com.example.mediico;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import static android.support.constraint.Constraints.TAG;

public class SearchResultsActivity extends Activity {

    Medicine medicine;
    int price;

    TextView medName, pharmName, quantity, phoneNo, Cost, cartMedName, cartQuantity, cartPrice;
    int cnt = 0;
    TextView showCnt;

    int totalPrice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        phoneNo = findViewById(R.id.phoneValue);
        medName = findViewById(R.id.brandNameValue);
        pharmName = findViewById(R.id.pharmacyNameValue);
        quantity = findViewById(R.id.quantityValue);
        Cost = findViewById(R.id.priceValue);
        final Button checkoutButton = findViewById(R.id.checkout);
        cartMedName = findViewById(R.id.CartBrandNameValue);
        cartQuantity = findViewById(R.id.cart_quantity);
        cartPrice = findViewById(R.id.cart_price);
        String queryName = getIntent().getStringExtra("showKey");
        System.out.println(queryName);
        Query query = FirebaseDatabase.getInstance().getReference().child("Medicine").orderByChild("brandName").equalTo(queryName);
        DatabaseReference dataOfMed = FirebaseDatabase.getInstance().getReference().child("Medicine");
        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    medicine = childSnapshot.getValue(Medicine.class);
                    medName.setText(medicine.getBrandName());
                    cartMedName.setText(medicine.getBrandName());
                    pharmName.setText(medicine.getAvailableIn());
                    quantity.setText(String.valueOf(String.valueOf(medicine.getStock())));
                    Cost.setText(String.valueOf(medicine.getPrice()));
                    price = medicine.getPrice();
                    Query queryPharma = FirebaseDatabase.getInstance().getReference().child("Pharmacy").orderByChild("Pharmacy Name").equalTo(medicine.getAvailableIn());
                    queryPharma.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot childSnapshot1: dataSnapshot.getChildren()) {
                                {
                                    Pharmacy pharmacy = childSnapshot1.getValue(Pharmacy.class);
                                    phoneNo.setText(String.valueOf(pharmacy.getPhoneNo()));
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d(TAG, databaseError.getMessage());
                        }
                    });


                    //dataOfUser.child("User Id").setValue(userId);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        });
        showCnt = findViewById(R.id.count);
        final TextView mycart = findViewById(R.id.your_Cart);
        mycart.setVisibility(View.GONE);
        final Button cartButton = (Button) findViewById(R.id.cart);
        final Button plusButton = findViewById(R.id.plus);
        plusButton.setVisibility(View.GONE);
        checkoutButton.setVisibility(View.GONE);
        final Button minusButton = findViewById(R.id.minus);
        minusButton.setVisibility(View.GONE);
        cartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cartButton.setVisibility(View.GONE);
                minusButton.setVisibility(View.VISIBLE);
                checkoutButton.setVisibility(View.VISIBLE);
                plusButton.setVisibility(View.VISIBLE);


            }
        });
        plusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cnt++;
                totalPrice = price * cnt;
                cartQuantity.setText(String.valueOf(cnt));
                showCnt.setText(String.valueOf(cnt));


            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cnt--;
                totalPrice = price * cnt;
                cartQuantity.setText(String.valueOf(cnt));
                if (cnt < 0) {
                    Toast.makeText(getApplicationContext(), "Cart Item can't be less then zero(0).", Toast.LENGTH_SHORT).show();
                    cnt = 0;
                    showCnt.setText(String.valueOf(cnt));
                }
                showCnt.setText(String.valueOf(cnt));
            }
        });

        cartPrice.setVisibility(View.GONE);
        cartMedName.setVisibility(View.GONE);
        cartQuantity.setVisibility(View.GONE);
        final TextView printProduct = findViewById(R.id.printBrandName);
        printProduct.setVisibility(View.GONE);
        final TextView printAmount = findViewById(R.id.printQuantity);
        printAmount.setVisibility(View.GONE);
        final TextView print_price = findViewById(R.id.printPrice);
        print_price.setVisibility(View.GONE);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkoutButton.setVisibility(View.GONE);
                mycart.setVisibility(View.VISIBLE);
                cartPrice.setVisibility(View.VISIBLE);
                plusButton.setVisibility(View.GONE);
                minusButton.setVisibility(View.GONE);
                showCnt.setVisibility(View.GONE);
                cartMedName.setVisibility(View.VISIBLE);
                print_price.setVisibility(View.VISIBLE);
                printAmount.setVisibility(View.VISIBLE);
                printProduct.setVisibility(View.VISIBLE);
                cartQuantity.setVisibility(View.VISIBLE);
                cartPrice.setText(String.valueOf(totalPrice));
            }
        });


    }


}