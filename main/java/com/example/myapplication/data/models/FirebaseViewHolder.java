package com.example.myapplication.data.models;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;


/*
take the datiles from fireBase and show it in the view(screen android)
 */
public class FirebaseViewHolder extends RecyclerView.ViewHolder
{
    public TextView packageId, ownerAddress;

    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);

        packageId = itemView.findViewById(R.id.PackageId);
        ownerAddress = itemView.findViewById(R.id.ownerAddress);

    }
}


