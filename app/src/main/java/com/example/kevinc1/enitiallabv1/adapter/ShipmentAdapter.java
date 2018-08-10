package com.example.kevinc1.enitiallabv1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.example.kevinc1.enitiallabv1.R;
import com.example.kevinc1.enitiallabv1.model.Shipment;

public class ShipmentAdapter extends RecyclerView.Adapter<ShipmentAdapter.ShipmentViewHolder> {

    private List<Shipment> shipments;
    private int rowLayout;
    private Context context;


    public ShipmentAdapter(List<Shipment> shipments, int rowLayout, Context context) {
        this.shipments = shipments;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ShipmentAdapter.ShipmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ShipmentViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ShipmentViewHolder holder, final int position) {
        holder.positionNumber.setText("Question number : " + String.valueOf(position + 1));
        holder.id.setText("ID :" + shipments.get(position).getId());
        holder.custpo.setText("Customer PO: " + shipments.get(position).getCustpo());
        holder.item.setText("Item: " + shipments.get(position).getItem());
        holder.tracking.setText("Tracking: " + shipments.get(position).getTracking());
        holder.shipName.setText("Ship to Name: " + shipments.get(position).getShipName());
    }

    @Override
    public int getItemCount() {
        return shipments.size();
    }

    public static class ShipmentViewHolder extends RecyclerView.ViewHolder {
        TextView positionNumber;
        TextView id;
        TextView custpo;
        TextView item;
        TextView tracking;
        TextView shipName;

        public ShipmentViewHolder(View v) {
            super(v);
            positionNumber = (TextView) v.findViewById(R.id.positionNumber);
            id = (TextView) v.findViewById(R.id.id);
            custpo = (TextView) v.findViewById(R.id.custpo);
            item = (TextView) v.findViewById(R.id.item);
            tracking = (TextView) v.findViewById(R.id.tracking);
            shipName = (TextView) v.findViewById(R.id.ship_name);
        }
    }
}

