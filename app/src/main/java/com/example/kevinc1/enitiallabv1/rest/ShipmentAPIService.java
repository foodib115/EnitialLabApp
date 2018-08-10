package com.example.kevinc1.enitiallabv1.rest;

import com.example.kevinc1.enitiallabv1.model.Shipment;
import com.example.kevinc1.enitiallabv1.model.ShipmentList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShipmentAPIService {

    @GET("/~homesi17/Enitiallab/task_manager/v1/shipments")
    Call<List<Shipment>> fetchShipments(@Query("shipname") String shipName);

}
