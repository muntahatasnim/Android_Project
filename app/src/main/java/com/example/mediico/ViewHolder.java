package com.example.mediico;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.LayoutInflater.from;

class medicineListRecyclerViewAdapter extends RecyclerView.Adapter<medicineListRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "Recycler View";
    private ArrayList<String > medicineName;
    private ArrayList<String >medicineKey;
    private Context context;

    public medicineListRecyclerViewAdapter(Context context, ArrayList<String> medicineName, ArrayList<String> medicineKey) {
        this.medicineName = medicineName;
        this.medicineKey = medicineKey;
        this.context = context;
    }
    @NonNull
    @Override
    public medicineListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = from(viewGroup.getContext()).inflate(R.layout.layout_listofmedicine,viewGroup,false);
        medicineListRecyclerViewAdapter.ViewHolder viewHolder = new medicineListRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull medicineListRecyclerViewAdapter.ViewHolder viewHolder, final int i) {

        Log.d(TAG, "onBindViewHolder: " + i);

        viewHolder.medicineNameList.setText(medicineName.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchResultsActivity.class);
                intent.putExtra("showKey",medicineName.get(i));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return medicineName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView medicineNameList;
        LinearLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medicineNameList = itemView.findViewById(R.id.medicine_name);
            parentLayout = itemView.findViewById(R.id.medicine_element_layout);
        }
    }
}