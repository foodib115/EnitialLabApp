package com.example.kevinc1.enitiallabv1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.util.Log;
import android.widget.Toast;

import com.example.kevinc1.enitiallabv1.R;
import com.example.kevinc1.enitiallabv1.adapter.ShipmentAdapter;
import com.example.kevinc1.enitiallabv1.model.Shipment;
import com.example.kevinc1.enitiallabv1.model.ShipmentList;
import com.example.kevinc1.enitiallabv1.rest.RestClient;
import com.example.kevinc1.enitiallabv1.rest.ShipmentAPIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    ShipmentAPIService apiService;
    SearchView searchView;
    RecyclerView recyclerView;
    ShipmentAdapter adapter;
    List<Shipment> shipments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView=(SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search View");

        apiService = RestClient.getClient().create(ShipmentAPIService.class);

        recyclerView = (RecyclerView) findViewById(R.id.ShipmentListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ShipmentAdapter(shipments, R.layout.shipment_item, getApplicationContext());
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                shipments.clear();
                fetchShipmentList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                shipments.clear();
                fetchShipmentList(newText);
                return false;
            }
        });





    }

    private void fetchShipmentList(String query) {
        Call<List<Shipment>> call = apiService.fetchShipments(query);
        call.enqueue(new Callback<List<Shipment>>() {
            @Override
            public void onResponse(Call<List<Shipment>> call, Response<List<Shipment>> response) {
                Log.d(TAG, "Total number of questions fetched : " + response.body().size());

                shipments.addAll(response.body());
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Shipment>> call, Throwable t) {
                Log.e(TAG, "Got error : " + t.getLocalizedMessage());
            }
        });
    }


}
