package com.example.getwheels1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Car> list;

    public MyAdapter(Context context, ArrayList<Car> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          Car car = list.get(position);
          holder.carName.setText(car.getCar_Name());
        holder.carmodel.setText(car.getCar_model());
        holder.manuYear.setText(car.getManu_year());
        holder.regNo.setText(car.getReg_no());
        holder.adddetails.setText(car.getAdd_details());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView carName,adddetails,carmodel,manuYear,regNo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            carName = itemView.findViewById(R.id.carname1);
            carmodel = itemView.findViewById(R.id.carmodel1);
            manuYear = itemView.findViewById(R.id.manuyear1);
            regNo = itemView.findViewById(R.id.regyear1);
            adddetails = itemView.findViewById(R.id.addidetails1);
        }

    }

}

