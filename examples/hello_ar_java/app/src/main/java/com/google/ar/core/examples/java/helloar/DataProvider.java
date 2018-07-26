package com.google.ar.core.examples.java.helloar;

import android.os.AsyncTask;
import android.widget.Toast;

import com.google.ar.core.examples.java.helloar.model.LocationObject;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataProvider {

    private static final List<String> PRODUCTS = ImmutableList.of("37902P1");
    List<LocationObject> itineraries = new ArrayList<>();
    OkHttpClient client = new OkHttpClient();

    public List<LocationObject> getItineraries() {
        return ImmutableList.of(
                new LocationObject().setLat(37.66f).setLon(-122.54f).setName("Stanford").setRate(5).createLocationObject(),
                new LocationObject().setLat(37.62f).setLon(-122.66f).setName("Pier 39").setRate(3).createLocationObject(),
                new LocationObject().setLat(37.49f).setLon(-122.46f).setName("Golden Bridge").setRate(4).createLocationObject(),
                new LocationObject().setLat(37.75f).setLon(-122.36f).setName("Viator SF").setRate(5).createLocationObject(),
                new LocationObject().setLat(37.79f).setLon(-122.56f).setName("Napa Valley").setRate(5).createLocationObject()
        );
    }
}
