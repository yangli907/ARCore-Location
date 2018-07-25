package com.google.ar.core.examples.java.helloar;

import com.google.ar.core.examples.java.helloar.model.LocationObject;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class DataProvider {

    public List<LocationObject> getLocationData() {
        return ImmutableList.of(
                new LocationObject().setLat(37.66f).setLon(-122.54f).setName("Stanford").setRate(5).createLocationObject(),
                new LocationObject().setLat(37.62f).setLon(-122.66f).setName("Pier 39").setRate(3).createLocationObject(),
                new LocationObject().setLat(37.49f).setLon(-122.46f).setName("Golden Bridge").setRate(4).createLocationObject(),
                new LocationObject().setLat(37.75f).setLon(-122.36f).setName("Viator SF").setRate(5).createLocationObject(),
                new LocationObject().setLat(37.79f).setLon(-122.56f).setName("Napa Valley").setRate(5).createLocationObject()
        );
    }
}
