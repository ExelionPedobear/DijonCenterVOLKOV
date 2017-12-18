package com.example.ruslanmanca.dijoncentervolkov.adapters;

import com.example.ruslanmanca.dijoncentervolkov.dictionaries.HealthDictionary;
import com.example.ruslanmanca.dijoncentervolkov.models.Location;
import com.example.ruslanmanca.dijoncentervolkov.models.Poi;
import com.example.ruslanmanca.dijoncentervolkov.models.Position;
import com.example.ruslanmanca.dijoncentervolkov.services.PoisGetTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class PoiAdapter {

    public interface PoiAdapterListener{
        public boolean onPoiGetById(Poi poi);
        public boolean onPoiGetCinemasOuRestaurants(ArrayList<Poi> pois);
        public boolean onPoiGetAll(ArrayList<Poi> pois);
    }
    private String apiUrl;

    public PoiAdapter(String apiUrl){
        this.apiUrl = apiUrl;
    }

    public void GetById(String id, final PoiAdapterListener listener){
        //Instantiate new instance of our class
        PoisGetTask getRequest = new PoisGetTask(){
            @Override
            protected void onPostExecute(String jsonStr) {
                super.onPostExecute(jsonStr);
                //Perform the doInBackground method, passing in our url
                Poi result = new Poi();
                try {
                    if (jsonStr != null) {
                        JSONObject poi = new JSONObject(jsonStr);
                        result.setId(poi.getString("id"));
                        result.setName(poi.getString("name"));
                        result.setType(poi.getString("type"));

                        JSONObject loc = poi.getJSONObject("location");

                        JSONObject pos = loc.getJSONObject("position");
                        Position position = new Position(Double.parseDouble(pos.getString("lat")), Double.parseDouble(pos.getString("lon")));
                        Location location = new Location(loc.getString("adress"), loc.getString("postalCode"), loc.getString("city"), position);

                        result.setLocation(location);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listener.onPoiGetById(result);
            }
        };

        getRequest.execute(apiUrl + id);
    }

    public /*ArrayList<Poi>*/ void GetAll(final Integer corpulency, final PoiAdapterListener listener){

        PoisGetTask getRequest = new PoisGetTask(){
            @Override
            protected void onPostExecute(String jsonStr) {
                super.onPostExecute(jsonStr);
                //Perform the doInBackground method, passing in our url
                ArrayList<Poi> result = new ArrayList<>();
                try {
                    if (jsonStr != null) {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("pois");

                        result = BuildPoi(contacts, "", corpulency);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listener.onPoiGetAll(result);
            }
        };

        getRequest.execute(apiUrl);

        /*
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

                result = BuildPoi(contacts, "", corpulency);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;*/
    }

    public /*ArrayList<Poi>*/ void GetCinemasOuRestaurants(final String typePoi, final PoiAdapterListener listener){

        PoisGetTask getRequest = new PoisGetTask(){
            @Override
            protected void onPostExecute(String jsonStr) {
                super.onPostExecute(jsonStr);
                //Perform the doInBackground method, passing in our url
                ArrayList<Poi> result = new ArrayList<>();
                try {
                    if (jsonStr != null) {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("pois");

                        result = BuildPoi(contacts, typePoi.equals("CINE") ? "CINE" : "REST", null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listener.onPoiGetCinemasOuRestaurants(result);
            }
        };

        getRequest.execute(apiUrl);
    }

    public ArrayList<Poi> GetRestaurants(final PoiAdapterListener listener){
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

                result = BuildPoi(contacts, "REST", null);
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

    public ArrayList<Poi> BuildPoi(JSONArray contacts, String typePoi, Integer corpulency){
        try {
            ArrayList<Poi> result = new ArrayList<>();
            for (int i = 0; i < contacts.length(); i++) {
                JSONObject c = contacts.getJSONObject(i);

                if (typePoi.equals("CINE") && !c.getString("type").equals("CINE")) {
                    continue;
                }

                if (typePoi.equals("REST") && !c.getString("type").equals("REST")) {
                    continue;
                }

                String id = c.getString("id");
                String name = c.getString("name");
                String type = c.getString("type");

                if (corpulency != null){
                    if (corpulency == HealthDictionary.ANOREXIA) {
                        if (type.equals("REST") && (!name.contains("Kebab") || !name.contains("Flunch") || !name.contains("Scala") || !name.contains("Subway") || !name.contains("Quick") || !name.contains("Wok"))){
                            continue;
                        }
                    }
                    else if (corpulency == HealthDictionary.SLIM) {

                    }
                    else if (corpulency == HealthDictionary.FIT) {
                        if (type.equals("REST") && (name.contains("Kebab") || name.contains("Subway") || name.contains("Quick") || name.contains("Wok"))){
                            continue;
                        }
                    }
                    else if (corpulency == HealthDictionary.FAT) {
                        if (type.equals("REST")){
                            continue;
                        }
                    }
                    else if (corpulency == HealthDictionary.OBESITY) {
                        continue;
                    }
                    else {
                        continue;
                    }
                }

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

            return result;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}