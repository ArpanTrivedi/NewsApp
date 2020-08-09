package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private RequestQueue requestQueue;

    private List<ListItem> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = Volley.newRequestQueue(this);

        fetchData();

    }

    private void fetchData() {
       String url = "http://newsapi.org/v2/everything?q=bitcoin&from=2020-07-09&sortBy=publishedAt&apiKey=26a63475b3214dbda694d8296dcb04d0";
       mList = new ArrayList<>();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        // Do something with the response
                        try {

                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("articles");

                            for ( int i = 0 ; i < array.length() ; i++ ) {
                                JSONObject obj = array.getJSONObject(i);
                                ListItem listItem = new ListItem(obj.getString("title"),
                                        obj.getString("description"),
                                        obj.getString("author"),
                                        obj.getString("urlToImage"));
                                mList.add(listItem);
                            }
                            recyclerView.setAdapter(new MyAdapter(mList,getApplicationContext()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(MainActivity.this,"Error occur because " + error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(stringRequest);
        recyclerView.setAdapter(new MyAdapter(mList,MainActivity.this));

    }
}