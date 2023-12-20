package com.example.proyecto2;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.proyecto2.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private dbContext myDbContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        //Toast.makeText(this, "onResume MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("info", "onResume MainActivity interactive mode in the foreground");
        super.onResume();
    }
    @Override
    protected void onStart() {

        //Toast.makeText(this, "onStart MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("info", "onStart MainActivity init resources");

        super.onStart();
    }
    @Override
    protected void onPause() {

        //Toast.makeText(this, "onPause MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("info", "onPause MainActivity lost focus running in the background reinit any resources released and do not do intensive ops");

        super.onPause();
    }
    @Override
    protected void onStop() {

        //Toast.makeText(this, "onStop MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("info", "onStop MainActivity release heavy resources and save to a db or persistent media");

        super.onStop();
    }
    @Override
    protected void onRestart() {

        //Toast.makeText(this, "onRestart MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("info", "onRestart MainActivity going to main screen and resuming interaction with the user");

        super.onRestart();
    }
    @Override
    protected void onDestroy() {
        //Toast.makeText(this, "onDestroy MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("info", "onDestroy MainActivity");
        super.onDestroy();
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void getData() {
        RequestQueue mRequestQueue;
        StringRequest mStringRequest;
        String url = "https://localhost:7036/api/ProductosApi/1";

        mRequestQueue = Volley.newRequestQueue(this);
        //txtCambio = findViewById(R.id.txtCambioT);

        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respObj = new JSONObject(response);
                    JSONObject ratesObj = respObj.getJSONObject("rates");
                    double crcRate = ratesObj.getDouble("nombre");
                    String crcRateString = String.valueOf(crcRate);
                    Toast.makeText(MainActivity.this, "Se extrajo " + crcRateString, Toast.LENGTH_SHORT).show();
                    // txtCambio.setText("USD= "+crcRateString+" CRC");
                    // myDbContext.agregarMoneda(crcRateString);
                    // Toast.makeText(MainActivity.this, "Se agrego a base de datos", Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e) {
                    Log.i("error", "error:" + e.getMessage());
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("info", "Error :" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
    }

}