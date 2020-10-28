package com.example.myapplication.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.data.models.FirebaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.data.models.ParcelFireBaseViewModel;
import com.example.myapplication.data.models.ParcelViewModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//The activity in which the deliver is registered for the package

public class deliverActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DataSetFire> arrayList;
    private FirebaseRecyclerAdapter<DataSetFire, FirebaseViewHolder> adapter;
    private FirebaseRecyclerOptions<DataSetFire> option;
    private DatabaseReference databaseReference;
    private ParcelFireBaseViewModel viewModel;
    private ParcelViewModel parcelViewModel;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);
        recyclerView = findViewById(R.id.recyclerViewHistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<DataSetFire>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Parcels");
        databaseReference.keepSynced(true);
        option = new FirebaseRecyclerOptions.Builder<DataSetFire>().setQuery(databaseReference, DataSetFire.class).build();
        //room
        parcelViewModel = ViewModelProviders.of(this).get(ParcelViewModel.class);

        adapter = new FirebaseRecyclerAdapter<DataSetFire, FirebaseViewHolder>(option) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull final DataSetFire model) {

                holder.packageId.setText(model.getPackageId());
                holder.ownerAddress.setText(model.getOwnerAddress());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Dialogue opened after the deliver chose a package to enter his details
                        final Dialog dialog = new Dialog(deliverActivity.this);
                        dialog.setContentView(R.layout.daeliver_dialog);
                        dialog.setTitle("Chosh");

                        EditText dialogtext = dialog.findViewById(R.id.editText12);
                        dialogtext.setText(model.getPackageId());

                        //If the deliver regrets, the dialogue closes and returns to the list
                        Button xButton = (Button) dialog.findViewById(R.id.xButton);
                        xButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();

                            }
                        });

                        //After the deliver enters his details and registers
                        Button dialogButton = (Button) dialog.findViewById(R.id.buttonNisayon);
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                EditText dialogtext = dialog.findViewById(R.id.editText12);
                                String parcelID = dialogtext.getText().toString();
                                EditText dialogDeliverName = dialog.findViewById(R.id.editTextDeliverName);
                                EditText dialogDeliverPhone = dialog.findViewById(R.id.editTextDeliverPhone);

                                //Delete from Firebase
                                DatabaseReference remove = FirebaseDatabase.getInstance().getReference("Parcels").child(parcelID);
                                remove.removeValue();

                                //Update the deliver information in the room
                                String deliver = dialogDeliverName.getText() + " " + dialogDeliverPhone.getText();
                                parcelViewModel.updatePackage(deliver, parcelID);
                                String statusD = "Someone_offered_to_pick_her_up";
                                parcelViewModel.updateStatus(statusD, parcelID);

                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                    }
                });
            }


            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new FirebaseViewHolder(LayoutInflater.from(deliverActivity.this).inflate(R.layout.row, viewGroup, false));
            }
        };


        recyclerView.setAdapter(adapter);

    }
}