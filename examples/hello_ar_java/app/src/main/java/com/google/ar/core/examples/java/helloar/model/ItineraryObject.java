package com.google.ar.core.examples.java.helloar.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ItineraryObject implements Serializable {
    private String productCode;
    private String productName;
    private List<LocationObject> itineraryItems;

    public ItineraryObject() {
    }

    public ItineraryObject(String productCode, String productName, List<LocationObject> itineraryItems) {
        this.productCode = productCode;
        this.productName = productName;
        this.itineraryItems = itineraryItems;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public List<LocationObject> getItineraryItems() {
        return itineraryItems;
    }

    public ItineraryObject setProductCode(String productCode) {
        this.productCode = productCode;
        return this;
    }

    public ItineraryObject setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public ItineraryObject setItineraryItems(List<LocationObject> itineraryItems) {
        this.itineraryItems = itineraryItems;
        return this;
    }

    public ItineraryObject createItineraryObject() {
        return new ItineraryObject(productCode, productName, itineraryItems);
    }
}
