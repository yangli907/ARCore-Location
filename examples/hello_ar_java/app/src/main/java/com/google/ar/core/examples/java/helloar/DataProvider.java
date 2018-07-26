package com.google.ar.core.examples.java.helloar;

import com.google.ar.core.examples.java.helloar.model.LocationObject;
import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProvider {

    private static final Map<String, List<LocationObject>> ALL_PRODUCTS = new HashMap<>();

    static {
        ALL_PRODUCTS.put(
                "1337P1", ImmutableList.of(
                        new LocationObject().setLat(37.8034f).setLon(-122.4491f).setName("Palace of Fine Arts").setRate(5).createLocationObject(),
                        new LocationObject().setLat(37.7866f).setLon(-122.4011f).setName("SOMA Museum").setRate(3).createLocationObject(),
                        new LocationObject().setLat(37.8075f).setLon(-122.4729f).setName("Golden Gate Bridge").setRate(4).createLocationObject(),
                        new LocationObject().setLat(37.782587f).setLon(-122.398175f).setName("Viator SF").setRate(5).createLocationObject(),
                        new LocationObject().setLat(37.808522f).setLon(-122.4095f).setName("Pier 39").setRate(5).createLocationObject()
                )
        );
        ALL_PRODUCTS.put(
                "1337P2", ImmutableList.of(
                        new LocationObject().setLat(37.426683f).setLon(-122.170524f).setName("Stanford Church").setRate(5).createLocationObject(),
                        new LocationObject().setLat(37.434067f).setLon(-122.161124f).setName("Stanford Stadium").setRate(3).createLocationObject(),
                        new LocationObject().setLat(37.441992f).setLon(-122.171453f).setName("Shopping Center").setRate(4).createLocationObject(),
                        new LocationObject().setLat(37.427441f).setLon(-122.167037f).setName("Hoover Tower").setRate(5).createLocationObject()
                )
        );
        ALL_PRODUCTS.put(
                "1337P3", ImmutableList.of(
                        new LocationObject().setLat(37.744f).setLon(-119.585342f).setName("Yosemite Visitor Center").setRate(5).createLocationObject(),
                        new LocationObject().setLat(37.771829f).setLon(-119.580939f).setName("Upper Fall").setRate(3).createLocationObject(),
                        new LocationObject().setLat(37.745581f).setLon(-119.533212f).setName("Half Dome").setRate(4).createLocationObject()
                )
        );
    }

    public List<LocationObject> getItineraries(String productCode) {
        return ALL_PRODUCTS.get(productCode);
    }
}
