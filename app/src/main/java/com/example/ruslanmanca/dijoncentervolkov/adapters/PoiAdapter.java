package com.example.ruslanmanca.dijoncentervolkov.adapters;

import com.example.ruslanmanca.dijoncentervolkov.models.Location;
import com.example.ruslanmanca.dijoncentervolkov.models.Poi;
import com.example.ruslanmanca.dijoncentervolkov.models.Position;
import com.example.ruslanmanca.dijoncentervolkov.services.PoisGetTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class PoiAdapter {

    private String apiUrl;

    public PoiAdapter(String apiUrl){
        this.apiUrl = apiUrl;
    }

    public ArrayList<Poi> GetAll(){
        //String to place our result in
        ArrayList<Poi> result = new ArrayList<>();
        //Instantiate new instance of our class
        PoisGetTask getRequest = new PoisGetTask();
        //Perform the doInBackground method, passing in our url
        try {
            String jsonStr = getRequest.execute(apiUrl).get();
            if (jsonStr != null) {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("pois");

                for (int i = 0; i < contacts.length(); i++) {

                    JSONObject c = contacts.getJSONObject(i);
                    String id = c.getString("id");
                    String name = c.getString("name");
                    String type = c.getString("type");

                    JSONObject location = c.getJSONObject("location");
                    String adress = location.getString("adress");
                    String postalCode = location.getString("postalCode");
                    String city = location.getString("city");

                    JSONObject position = location.getJSONObject("position");
                    String lat = position.getString("lat");
                    String lon = position.getString("lon");

                    Position pos = new Position(Double.parseDouble(lat), Double.parseDouble(lon));

                    Location loc = new Location(adress, postalCode, city, pos);

                    Poi poi = new Poi(id, type, name, loc);

                    result.add(poi);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}