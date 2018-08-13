package com.example.kevinc1.enitiallabv1.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.example.kevinc1.enitiallabv1.R;
import com.example.kevinc1.enitiallabv1.activity.ProfileActivity;
import com.example.kevinc1.enitiallabv1.model.Customer;


public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private static final String TAG = "CustomerAdapter";
    private List<Customer> customers;
    private int rowLayout;
    private Context context;


    public CustomerAdapter(List<Customer> customers, int rowLayout, Context context) {
        this.customers = customers;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public CustomerAdapter.CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CustomerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(CustomerViewHolder holder, final int position) {
        holder.positionNumber.setText("Customer number : " + String.valueOf(position + 1));
        holder.custnum.setText("Customer:" + customers.get(position).getCustnum());
        holder.orders.setText("Orders: " + customers.get(position).getOrders());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + customers.get(position).getCustnum());

                //Toast.makeText(customers.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("customer", customers.get(position).getCustnum());
                intent.putExtra("orders", customers.get(position).getOrders().toString());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView positionNumber;
        TextView custnum;
        TextView orders;
        LinearLayout parentLayout;

        public CustomerViewHolder(View v) {
            super(v);
            positionNumber = (TextView) v.findViewById(R.id.positionNumber);
            custnum = (TextView) v.findViewById(R.id.custnum);
            orders = (TextView) v.findViewById(R.id.orders);
            parentLayout = v.findViewById(R.id.parento);

        }
    }
}