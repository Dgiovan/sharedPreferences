package com.example.servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.servicios.apters.item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcv;
    item adapter;

  public  static ArrayList<item.Itemejemplo> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcv = findViewById(R.id.rcv);
        rcv.setHasFixedSize(true);
        LinearLayoutManager manager =
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcv.setLayoutManager(manager);

        RequestQueue cola_de_peticiones =
                Volley.newRequestQueue(this);

        String base = "https://gr.kiwilimon.com/v6";
        String Direccion_final="";

        Direccion_final =
                base + "/recipeclassification"+"?"+"key=1000"+"&full=yes"+"&language=es"+"&device=android"+"&human=1";
        JsonObjectRequest respuesta =
                new JsonObjectRequest(Request.Method.POST, Direccion_final, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("respuesta",response.toString());
                                try {
                                    JSONArray  array = response.getJSONArray("classifications");
                                    for (int i = 0; i<array.length();i++)
                                    {
                                            items.add(new item.Itemejemplo(array.getJSONObject(i)));
                                    }
                                    Log.e("hgflk",items.toString());
                                    adapter = new item(items,getApplicationContext());
                                    rcv.setAdapter(adapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("respuesta",error.toString());
                    }
                });

        cola_de_peticiones.add(respuesta);






    }
}
