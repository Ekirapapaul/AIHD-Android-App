package org.aihdint.aihd.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.aihdint.aihd.app.AppController;
import org.aihdint.aihd.app.Config;
import org.aihdint.aihd.model.Person;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadPatients extends IntentService {
    private Gson patientsGson;

    public LoadPatients() {
        super(LoadPatients.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        DownloadPatients();
    }

    private void DownloadPatients() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-M-d");
        patientsGson = gsonBuilder.create();

        StringRequest req = new StringRequest(Request.Method.POST, Config.PATIENT_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    // Getting JSON Array node
                    JSONArray patients = jsonObj.getJSONArray("data");

                    //List<Person> persons = Arrays.asList(patientsGson.fromJson(response, Person[].class));
                    Log.d("Response", response);
                    if (patients.length() > 0) {
                        Person.deleteAll(Person.class);

                        List<Person> persons = Arrays.asList(patientsGson.fromJson(patients.toString(), Person[].class));

                        for (Person person : persons) {
                            // GOT THE OBJECT of PEOPLE
                            person.save();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                String location_id = AppController.getInstance().getSessionManager().getUserDetails().get("mfl_code");
                location_id = location_id.toLowerCase();
                location_id = location_id.replace(".", "");
                location_id = location_id.replace(" ", "_");

                Map<String, String> params = new HashMap<>();
                params.put("mfl", location_id);
                params.put("uuid", AppController.getInstance().getSessionManager().getUserDetails().get("user_id"));

                JSONObject JSONparams = new JSONObject(params);
                Log.d("Params", JSONparams.toString());

                return params;
            }

        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);

    }

}
