package com.google.ar.core.examples.java.helloar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.ar.core.examples.java.helloar.helpers.NetworkHelper;
import com.google.ar.core.examples.java.helloar.model.ItineraryObject;
import com.google.ar.core.examples.java.helloar.model.LocationObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ItineraryLookupActivity extends AppCompatActivity {

    private static final String TAG = "Itinerary Lookup" ;

    Map<String, ItineraryObject> map = new HashMap<>();

    private String selectedProductCode;

    ArrayAdapter<String> spinnerAdapter;

    ArrayAdapter<String> listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itinerary_lookup);

        final Spinner spinner = findViewById(R.id.productList);
        spinnerAdapter = new ArrayAdapter<>(this, R.layout.menu_item_black_text);
        spinnerAdapter.setDropDownViewResource(R.layout.menu_item_black_text);
        spinner.setAdapter(spinnerAdapter);

        final ListView itineraryItemList = findViewById(R.id.itinerary_info);
        listViewAdapter = new ArrayAdapter<>(this, R.layout.menu_item_black_text);
        itineraryItemList.setAdapter(listViewAdapter);

        populateTestDataSet();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String productCode = (String) spinner.getItemAtPosition(i);
                ItineraryObject itineraryObject = map.get(productCode);
                listViewAdapter.clear();
                for (LocationObject location : itineraryObject.getItineraryItems()) {
                    listViewAdapter.add(location.getName());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        loadContent();

        ImageButton toggleButton = findViewById(R.id.submit_lookup);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner = findViewById(R.id.productList);
                Intent intent = new Intent(getBaseContext(), HelloArActivity.class);
                intent.putExtra("PRODUCT_CODE", spinner.getSelectedItem().toString());
                intent.putExtra("ITINERARY", map.get(spinner.getSelectedItem().toString()));
                startActivity(intent);
            }
        });
    }

    private void populateTestDataSet() {
        map.put("1337P1",
                new ItineraryObject()
                        .setProductCode("1337P1")
                        .setProductName("SF day tour")
                        .setItineraryItems(new DataProvider().getItineraries("1337P1")));
        map.put("1337P2",
                new ItineraryObject()
                        .setProductCode("1337P2")
                        .setProductName("Stanford half-day tour")
                        .setItineraryItems(new DataProvider().getItineraries("1337P2")));
        map.put("1337P3",
                new ItineraryObject()
                        .setProductCode("1337P3")
                        .setProductName("Yosemite National Park")
                        .setItineraryItems(new DataProvider().getItineraries("1337P3")));
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
    }

    private void loadContent() {
        getHttpResponse();
    }

    public Object getHttpResponse() {
        return new RetrieveFeedTask().execute("https://304101.common.zelda.oxf1.viatorsystems.com/products/37902P1/itinerary");
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, Map<String, ItineraryObject>> {

        protected Map<String, ItineraryObject> doInBackground(String... urls) {

            try {
                OkHttpClient httpClient = NetworkHelper.getUnsafeOkHttpClient();
                String url = urls[0];
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = httpClient.newCall(request).execute();

                String jsonData = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray itineraryItemNodes = jsonObject.getJSONArray("itineraryItems");
                String productCode = jsonObject.getString("productCode");

                List<LocationObject> items = new ArrayList<>();
                for (int i = 0; i < itineraryItemNodes.length(); i++) {
                    JSONObject itineraryItemNode = itineraryItemNodes.getJSONObject(i);
                    JSONObject poiLocation = itineraryItemNode.getJSONObject("poiLocation");
                    String poiName = poiLocation.getString("name");
                    double lat = poiLocation.getJSONObject("centre").getDouble("latitude");
                    double lon = poiLocation.getJSONObject("centre").getDouble("longitude");

                    LocationObject itineraryLocation = new LocationObject().setName(poiName).setLat((float)lat).setLon((float)lon).setRate(4).createLocationObject();
                    items.add(itineraryLocation);
                }

                map.put(productCode, new ItineraryObject().setProductCode(productCode).setItineraryItems(items).createItineraryObject());

                return map;

            } catch (Exception e) {

                return null;
            }
        }

        protected void onPostExecute(Map<String, ItineraryObject> itineraries) {
            super.onPostExecute(itineraries);

            for (Map.Entry<String, ItineraryObject> itinerary : itineraries.entrySet()) {
                spinnerAdapter.add(itinerary.getKey());
            }


//            TextView itineraryDetailView = (TextView)findViewById(R.id.itinerary_info);
//            itineraryDetailView.setText("");
//            for (LocationObject location : itinerary.getValue().getItineraryItems()) {
//                itineraryDetailView.setText(itineraryDetailView.getText() + location.getName() + "\n");
//            }
        }
    }
}


