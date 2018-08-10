package com.example.kevinc1.enitiallabv1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Shipment implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("custpo")
    @Expose
    private String custpo;
    @SerializedName("item")
    @Expose
    private String item;
    @SerializedName("tracking")
    @Expose
    private String tracking;
    @SerializedName("ship_name")
    @Expose
    private String shipName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustpo() {
        return custpo;
    }

    public void setCustpo(String custpo) {
        this.custpo = custpo;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

}
