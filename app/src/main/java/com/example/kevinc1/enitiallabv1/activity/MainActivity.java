package com.example.kevinc1.enitiallabv1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
//import android.widget.SearchView;
import android.util.Log;
import android.widget.Toast;

import com.example.kevinc1.enitiallabv1.R;
import com.example.kevinc1.enitiallabv1.adapter.CustomerAdapter;
import com.example.kevinc1.enitiallabv1.adapter.ShipmentAdapter;
import com.example.kevinc1.enitiallabv1.model.Customer;
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
    //ShipmentAdapter adapter;
    CustomerAdapter adapter;
    List<Customer> customers = new ArrayList<>();
    List<Shipment> shipments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //searchView=(SearchView) findViewById(R.id.action_search);
        //searchView.setQueryHint("Search View");

        apiService = RestClient.getClient().create(ShipmentAPIService.class);

        recyclerView = (RecyclerView) findViewById(R.id.ShipmentListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CustomerAdapter(customers, R.layout.customer_item, getApplicationContext());
        recyclerView.setAdapter(adapter);
        /****
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                customers.clear();
                fetchCustomers();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                customers.clear();
                //fetchShipmentList(newText);
                return false;
            }
        });
        ******/
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.options_menu, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                customers.clear();
                fetchCustomers();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return true;
    }

    private void fetchShipmentList(String query) {
        Call<List<Shipment>> call = apiService.fetchShipments(query);
        call.enqueue(new Callback<List<Shipment>>() {
            @Override
            public void onResponse(Call<List<Shipment>> call, Response<List<Shipment>> response) {
                Log.d(TAG, "Total number of shipments fetched : " + response.body().size());

                shipments.addAll(response.body());
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Shipment>> call, Throwable t) {
                Log.e(TAG, "Got error : " + t.getLocalizedMessage());
            }
        });
    }

    private void fetchCustomers() {
        Call<List<Customer>> call = apiService.fetchCustomers();
        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                Log.d(TAG, "Total number of customers fetched : " + response.body().size());

                customers.addAll(response.body());
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Log.e(TAG, "Got error : " + t.getLocalizedMessage());
            }
        });
    }


}
